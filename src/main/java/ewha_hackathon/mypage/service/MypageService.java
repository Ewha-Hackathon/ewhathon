package ewha_hackathon.mypage.service;

import ewha_hackathon.domain.User;
import ewha_hackathon.home.DTO.HomeResponseDto;
import ewha_hackathon.mypage.DTO.MypageResponseDto;
import ewha_hackathon.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {

    @Autowired
    private final EventRepository eventRepository;

    public MypageResponseDto getMypageInfo(User user) {

        return MypageResponseDto.builder()
                .user_name(user.getUsername())
                .user_email(user.getEmail())
                .myRsvpDtoList(eventRepository.findByUserRsvp(user))
                .myHeartDtoList(eventRepository.findByUserHeart(user))
                .myRegisterDtoList(eventRepository.findByUserRegister(user))
                .build();
    }

}
