package ewha_hackathon.home.service;

import ewha_hackathon.domain.User;
import ewha_hackathon.home.DTO.HomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {

    public HomeDto getHomeInfo(User user) {
        HomeDto homeDto = HomeDto.builder()
                .user_name(user.getUsername())
                .build();

        return homeDto;
    }
}
