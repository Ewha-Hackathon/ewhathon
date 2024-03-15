package ewha_hackathon.domain;

import ewha_hackathon.event.DTO.EventRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;   //게시물 작성자
    
    @Column(nullable = false)
    private String type;    //종류(공연,작품,연구..)
    private String genre;   //장르(국악,댄스,밴드..)
    private String title;
    private String location;
    private String host;    //주최자(단체명)
    private LocalDate post_date;    //올린 날짜
    private LocalDate start_date;
    private LocalDate end_date;
    private boolean free;
    private String content;

    public static Event createEvent(EventRequestDto dto, User user){
        return new Event(
                dto.getId(),
                user,
                dto.getType(),
                dto.getGenre(),
                dto.getTitle(),
                dto.getLocation(),
                dto.getHost(),
                LocalDate.now(),
                dto.getStart_date(),
                dto.getEnd_date(),
                dto.getFree(),
                dto.getContent()
        );
    }
}
