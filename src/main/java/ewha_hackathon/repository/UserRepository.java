package ewha_hackathon.repository;

import ewha_hackathon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // signup, login에서 사용
    User findByEmail(String email);

}
