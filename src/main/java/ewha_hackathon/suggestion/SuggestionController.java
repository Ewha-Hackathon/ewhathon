package ewha_hackathon.suggestion;

import ewha_hackathon.domain.Event;
import ewha_hackathon.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
//    public ResponseEntity<List<String>> suggestKeywords(@PathVariable Long eventId) {
//        Event event = eventService.findEventById(eventId);
//        List<String> keywords = suggestionService.fetchSuggestedKeywords(event.getContent());
//        return ResponseEntity.ok(keywords);
//    }

    @PostMapping("/suggestKeywords/{eventId}")
    public String submitKeywords(@RequestParam("eventId") Long eventId,
                                 @RequestParam("selectedKeywords") List<String> selectedKeywords,
                                 RedirectAttributes redirectAttributes) {
        suggestionService.updateEventKeywords(eventId, selectedKeywords);
        return "redirect:/main";
    }
}