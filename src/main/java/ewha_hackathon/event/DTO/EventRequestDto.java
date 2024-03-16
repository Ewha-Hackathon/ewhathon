package ewha_hackathon.event.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventRequestDto {
    private Long id;
    private String category;    //종류(공연,작품,연구..)
    private String title;
    private String location;
    private String host;    //주최자(단체명)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    private Boolean free;
    private String content;
    private List<String> keywords;
    private MultipartFile file;
}