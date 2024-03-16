package ewha_hackathon.home.service;

import ewha_hackathon.domain.User;
import ewha_hackathon.home.DTO.HomeResponseDto;
import ewha_hackathon.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {

    @Autowired
    private final EventRepository eventRepository;

    public HomeResponseDto getHomeInfo(User user) {

        return HomeResponseDto.builder()
                .user_name(user.getUsername())
                .popularEvents(eventRepository.findByOrderByHeartCount())
                .latestEvents(eventRepository.findByOrderByStartDate(LocalDate.now()))
                .build();
    }
}
