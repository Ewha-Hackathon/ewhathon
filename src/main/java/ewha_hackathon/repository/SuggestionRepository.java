package ewha_hackathon.repository;

import ewha_hackathon.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Optional<Suggestion> findByEventId(Long eventId);
}
