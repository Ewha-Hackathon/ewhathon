package ewha_hackathon.controller;

import ewha_hackathon.service.UserService;
import ewha_hackathon.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "main";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // 이메일 중복 확인
    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmail(@RequestParam("email") String email) {
        boolean isAvailable = userService.isEmailAvailable(email);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        boolean isEmailAvailable = userService.isEmailAvailable(email);

        if (!isEmailAvailable) {
            return "redirect:/signup?error";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        userService.saveUser(newUser);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpSession session) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            return "redirect:/main";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}


