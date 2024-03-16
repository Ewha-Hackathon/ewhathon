package ewha_hackathon.repository;

import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Heart;
import ewha_hackathon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByUserAndEvent(User user, Event event);
}
