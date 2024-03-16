package ewha_hackathon.domain;

import ewha_hackathon.event.DTO.EventRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;   //게시물 작성자
    
    @Column
    private String category;    //종류(공연,작품,연구..)
    private String title;
    private String location;
    private String host;    //주최자(단체명)
    private LocalDate post_date;    //올린 날짜
    private LocalDate start_date;
    private LocalDate end_date;
    private boolean free;
    private String content;
    @ElementCollection
    private List<String> keywords;  //해시태그
    private String filename;//파일이름
    private String filepath;//파일경로


    public static Event createEvent(EventRequestDto dto, User user){
        boolean free;
        if(dto.getFree() > 0)
            free = true;
        else
            free=false;

        return new Event(
                dto.getId(),
                user,
                dto.getCategory(),
                dto.getTitle(),
                dto.getLocation(),
                dto.getHost(),
                LocalDate.now(),
                dto.getStart_date(),
                dto.getEnd_date(),
                free,
                dto.getContent(),
                dto.getKeywords(),
                null,
                null
        );
    }
}
