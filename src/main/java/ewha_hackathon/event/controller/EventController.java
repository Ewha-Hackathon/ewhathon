package ewha_hackathon.event.controller;

import ewha_hackathon.domain.Category;
import ewha_hackathon.event.DTO.EventRequestDto;
import ewha_hackathon.domain.User;
import ewha_hackathon.event.DTO.EventResponseDto;
import ewha_hackathon.event.service.EventService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestParam("category") Category category,
                                              @RequestParam("title") String title,
                                              @RequestParam("location") String location,
                                              @RequestParam("host") String host,
                                              @RequestParam("start_date") LocalDate start_date,
                                              @RequestParam("end_date") LocalDate end_date,
                                              @RequestParam("free") int free,
                                              @RequestParam("content") String content,
                                              @RequestParam("file") MultipartFile file,
                                                 HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        eventService.createEvent(user, category, title, location,host, start_date, end_date,free,content, file);
        return ResponseEntity.status(HttpStatus.OK).body("공연 정보 추가 완료");
    }

    @GetMapping("/detail/{event_id}")
    public EventResponseDto showEvent(@PathVariable Long event_id) {
        return eventService.showEventDetail(event_id);
    }
}
