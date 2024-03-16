package ewha_hackathon.heart.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartRequestDTO {

    private Long user_id;
    private Long event_id;

    public HeartRequestDTO(Long user_id, Long event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
    }
}
