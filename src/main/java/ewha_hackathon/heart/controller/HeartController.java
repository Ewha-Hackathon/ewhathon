package ewha_hackathon.heart.controller;

import ewha_hackathon.domain.User;
import ewha_hackathon.heart.DTO.HeartRequestDto;
import ewha_hackathon.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/{event_id}")
    public ResponseEntity<?> insert(@PathVariable Long event_id, HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        heartService.insert(event_id, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{event_id}")
    public ResponseEntity<?> delete(@PathVariable Long event_id, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        heartService.delete(event_id, user);
        return ResponseEntity.ok().build();
    }

}
