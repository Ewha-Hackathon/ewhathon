package ewha_hackathon.suggestion;


import ewha_hackathon.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private EventService eventService;

    @GetMapping("/keywordsRegister/{eventId}")
    public String showKeywordRegisterPage(@PathVariable("eventId") Long eventId, Model model) {
        model.addAttribute("eventId", eventId);
        return "keywordsRegister";
    }

//    @GetMapping("/suggestKeywords/{eventId}")
//    }

//    @PostMapping("/suggestKeywords/{eventId}")
//    public String submitKeywords()
//        return "redirect:/main";
//    }
}