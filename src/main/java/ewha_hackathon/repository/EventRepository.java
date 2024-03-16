package ewha_hackathon.repository;

import ewha_hackathon.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Optional<Event> findById(Long eventId);

    //좋아요 개수 count
    @Modifying
    @Query("UPDATE Event e SET e.heart_count = CASE WHEN :increase = true THEN e.heart_count + 1 ELSE e.heart_count - 1 END WHERE e = :event")
    void updateHeartCount(@Param("event") Event event, @Param("increase") boolean increase);

    //rsvp 개수 count
    @Modifying
    @Query("UPDATE Event e SET e.rsvp_count = CASE WHEN :increase = true THEN e.rsvp_count + 1 ELSE e.rsvp_count - 1 END WHERE e = :event")
    void updateRsvpCount(@Param("event") Event event, @Param("increase") boolean increase);
}
