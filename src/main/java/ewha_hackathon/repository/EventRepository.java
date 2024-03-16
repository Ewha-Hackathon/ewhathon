package ewha_hackathon.repository;

import ewha_hackathon.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
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

    @Query(value = "SELECT * FROM event ORDER BY heart_count DESC LIMIT 2", nativeQuery = true)
    List<Event> findByOrderByHeartCount();

    @Query("SELECT e FROM Event e WHERE e.start_date <= :today AND e.end_date >= :today ORDER BY e.start_date LIMIT 3")
    List<Event> findByOrderByStartDate(@Param("today") LocalDate today);

}
