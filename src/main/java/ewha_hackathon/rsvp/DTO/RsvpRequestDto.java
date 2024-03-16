package ewha_hackathon.rsvp.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RsvpRequestDto {
    private Long user_id;
    private Long event_id;

    public RsvpRequestDto(Long user_id, Long event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
    }
}
