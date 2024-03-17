package ewha_hackathon.home.DTO;

import ewha_hackathon.domain.Event;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HomeResponseDto {
    private String user_name;
    private List<Event> popularEvents;
    private List<Event> latestEvents;
}
