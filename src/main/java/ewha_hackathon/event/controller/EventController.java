package ewha_hackathon.event.controller;

import ewha_hackathon.event.DTO.EventRequestDto;
import ewha_hackathon.domain.User;
import ewha_hackathon.event.DTO.EventResponseDto;
import ewha_hackathon.event.service.EventService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody EventRequestDto dto, HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        eventService.createEvent(user, dto);
        return ResponseEntity.status(HttpStatus.OK).body("공연 정보 추가 완료");
    }

    @GetMapping("/detail/{event_id}")
    public EventResponseDto showEvent(@PathVariable Long event_id) {
        return eventService.showEventDetail(event_id);
    }
}
