package ewha_hackathon.rsvp.controller;

import ewha_hackathon.rsvp.DTO.RsvpRequestDto;
import ewha_hackathon.rsvp.service.RsvpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rsvp")
public class RsvpController {
    private final RsvpService rsvpService;

    //rsvp 발급하기
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody RsvpRequestDto rsvpRequestDto) throws Exception {
        rsvpService.insert(rsvpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //rsvp 취소하기
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody RsvpRequestDto rsvpRequestDto) {
        rsvpService.delete(rsvpRequestDto);
        return ResponseEntity.ok().build();
    }
}
