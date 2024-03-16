package ewha_hackathon.home.controller;

import ewha_hackathon.domain.User;
import ewha_hackathon.home.DTO.HomeDto;
import ewha_hackathon.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    @GetMapping()
    public HomeDto getHomeInfo(@AuthenticationPrincipal User user){
        return homeService.getHomeInfo(user);
    }

}
