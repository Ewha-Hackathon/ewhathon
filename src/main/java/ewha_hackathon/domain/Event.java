package ewha_hackathon.domain;

import ewha_hackathon.event.DTO.EventRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Enumerated(EnumType.STRING)
    private Category category;    //종류(공연,작품,연구..)
    private String title;
    private String location;
    private String host;    //주최자(단체명)
    private LocalDate post_date;    //올린 날짜
    private LocalDate start_date;
    private LocalDate end_date;
    private boolean free;
    private String content;
    @ElementCollection
    private List<String> keywords = new ArrayList<>();  //해시태그
    private String filename;//파일이름
    private String filepath;//파일경로
    private int count;  //좋아요 개수

    public String getContent() {
        return content;
    }

    public static Event createEvent(User user, Category category, String title, String location, String host, LocalDate startDate, LocalDate endDate, int free, String content, String filename, String filepath) {
        boolean freeBoolean;
        if(free == 0)
            freeBoolean = true;
        else
            freeBoolean = false;

        return new Event(
                null,
                user,
                category,
                title,
                location,
                host,
                LocalDate.now(),
                startDate,
                endDate,
                freeBoolean,
                content,
                null,
                filename,
                filepath,
                0   //초기값 0 지정
        );
    }
}
