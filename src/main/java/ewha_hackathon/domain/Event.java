package ewha_hackathon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    private String filename;//파일이름
    private String filepath;//파일경로

    @Column(name = "selected_keywords", length = 500)
    private String selectedKeywords;

    @ColumnDefault("0")
    private Integer heart_count;
    @ColumnDefault("0")
    private Integer rsvp_count;

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
                filename,
                filepath,
                null,
                0,
                0
        );
    }

    public void setSelectedKeywords(String selectedKeywords) {
        this.selectedKeywords = selectedKeywords;
    }

    public String getSelectedKeywords() {
        return selectedKeywords;
    }
}
