package ewha_hackathon.heart.service;

import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Heart;
import ewha_hackathon.domain.User;
import ewha_hackathon.heart.DTO.HeartRequestDto;
import ewha_hackathon.repository.EventRepository;
import ewha_hackathon.repository.HeartRepository;
import ewha_hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Transactional
    public void insert(HeartRequestDto heartRequestDTO) throws Exception {
        User user = userRepository.findById(heartRequestDTO.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found member id"));

        Event event = eventRepository.findById(heartRequestDTO.getEvent_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found event id"));

        // 이미 좋아요되어있으면 에러 반환
        if (heartRepository.findByUserAndEvent(user, event).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        Heart heart = Heart.builder()
                .event(event)
                .user(user)
                .build();

        heartRepository.save(heart);
        eventRepository.updateHeartCount(event,true);
    }
    @Transactional
    public void delete(HeartRequestDto heartRequestDTO) {
        User user = userRepository.findById(heartRequestDTO.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found member id"));

        Event event = eventRepository.findById(heartRequestDTO.getEvent_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found event id"));

        Heart heart = heartRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not found heart id"));

        heartRepository.delete(heart);
        eventRepository.updateHeartCount(event,false);
    }

}
