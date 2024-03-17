package ewha_hackathon.mypage.DTO;

import ewha_hackathon.domain.Event;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MypageResponseDto {
    private String user_name;
    private String user_email;
    private List<Event> myHeartDtoList;
    private List<Event> myRegisterDtoList;
    private List<Event> myRsvpDtoList;
}
