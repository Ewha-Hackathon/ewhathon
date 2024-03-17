package ewha_hackathon.rsvp.service;

import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Rsvp;
import ewha_hackathon.domain.User;
import ewha_hackathon.repository.EventRepository;
import ewha_hackathon.repository.RsvpRepository;
import ewha_hackathon.repository.UserRepository;
import ewha_hackathon.rsvp.DTO.RsvpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RsvpService {
    private final RsvpRepository rsvpRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Transactional
    public void insert(RsvpRequestDto rsvpRequestDto) throws Exception {
        User user = userRepository.findById(rsvpRequestDto.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found member id"));

        Event event = eventRepository.findById(rsvpRequestDto.getEvent_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found event id"));

        // 이미 좋아요되어있으면 에러 반환
        if (rsvpRepository.findByUserAndEvent(user, event).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        Rsvp rsvp = Rsvp.builder()
                .event(event)
                .user(user)
                .build();

        rsvpRepository.save(rsvp);
        eventRepository.updateRsvpCount(event,true);
    }
    @Transactional
    public void delete(RsvpRequestDto rsvpRequestDto) {
        User user = userRepository.findById(rsvpRequestDto.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found member id"));

        Event event = eventRepository.findById(rsvpRequestDto.getEvent_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found event id"));

        Rsvp rsvp = rsvpRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found heart id"));

        rsvpRepository.delete(rsvp);
        eventRepository.updateRsvpCount(event,false);
    }
}
