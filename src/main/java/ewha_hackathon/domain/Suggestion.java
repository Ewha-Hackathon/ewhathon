package ewha_hackathon.domain;

import jakarta.persistence.*;

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

    public void setSuggestedKeywords(String join) {
    }
}
