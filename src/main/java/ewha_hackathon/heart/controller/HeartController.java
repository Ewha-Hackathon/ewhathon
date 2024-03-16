package ewha_hackathon.heart.controller;

import ewha_hackathon.heart.DTO.HeartRequestDTO;
import ewha_hackathon.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody HeartRequestDTO heartRequestDTO) throws Exception {
        heartService.insert(heartRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody HeartRequestDTO heartRequestDTO) {
        heartService.delete(heartRequestDTO);
        return ResponseEntity.ok().build();
    }


    //rsvp 발급하기
    @PostMapping("/rsvp/")
    public void RsvpEvent(@PathVariable Long event_id) {

    }
}
