package ewha_hackathon.repository;

import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // signup, login에서 사용
    User findByEmail(String email);

    //좋아요에 사용
    Optional<User> findById(Long user_id);

}
