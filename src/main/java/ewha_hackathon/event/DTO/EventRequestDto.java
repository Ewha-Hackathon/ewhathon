package ewha_hackathon.event.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EventRequestDto {
    private Long id;
    private String type;    //종류(공연,작품,연구..)
    private String genre;   //장르(국악,댄스,밴드..)
    private String title;
    private String location;
    private String host;    //주최자(단체명)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    private Boolean free;
}
