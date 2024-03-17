package ewha_hackathon.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "suggested_keywords", length = 500)
    private String suggestedKeywords;

    public void setEvent(Event event) {
        this.event = event;
    }

    public Suggestion(List<Hashtag> suggestedKeywords) {
        this.suggestedKeywords = suggestedKeywords.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }
}
