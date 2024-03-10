package ewha_hackathon.service;

import ewha_hackathon.DTO.EventRequestDto;
import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.User;
import ewha_hackathon.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void createEvent(User user, EventRequestDto dto) {
        Event event = Event.createEvent(dto, user);
        eventRepository.save(event);
    }
}
