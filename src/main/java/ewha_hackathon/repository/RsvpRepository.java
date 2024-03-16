package ewha_hackathon.repository;

import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Rsvp;
import ewha_hackathon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RsvpRepository extends JpaRepository<Rsvp, Long> {
    Optional<Rsvp> findByUserAndEvent(User user, Event event);
}
