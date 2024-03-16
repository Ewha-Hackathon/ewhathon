package ewha_hackathon.home.controller;

import ewha_hackathon.domain.User;
import ewha_hackathon.home.DTO.HomeResponseDto;
import ewha_hackathon.home.service.HomeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    @GetMapping()
    public HomeResponseDto getHomeInfo(HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        return homeService.getHomeInfo(user);
    }

}
