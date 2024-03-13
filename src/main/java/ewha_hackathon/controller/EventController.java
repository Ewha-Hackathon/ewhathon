package ewha_hackathon.controller;

import ewha_hackathon.DTO.EventRequestDto;
import ewha_hackathon.domain.User;
import ewha_hackathon.service.EventService;
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
    public ResponseEntity<String> createEvent(@RequestBody EventRequestDto dto, HttpSession session){
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        eventService.createEvent(user, dto);
        return ResponseEntity.status(HttpStatus.OK).body("공연 정보 추가 완료");
    }
}
