package ewha_hackathon.mypage.controller;

import ewha_hackathon.domain.User;
import ewha_hackathon.home.service.HomeService;
import ewha_hackathon.mypage.DTO.MypageResponseDto;
import ewha_hackathon.mypage.service.MypageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MypageService mypageService;

    @GetMapping()
    public MypageResponseDto getMypageInfo(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("세션 없음");

        model.addAttribute("currentUser", user);
        model.addAttribute("MypageResponseDto", mypageService.getMypageInfo(user));
        return mypageService.getMypageInfo(user);
    }
}
