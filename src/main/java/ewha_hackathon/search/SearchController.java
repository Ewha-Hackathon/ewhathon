package ewha_hackathon.search;

import ewha_hackathon.domain.Category;
import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Hashtag;
import ewha_hackathon.domain.Heart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String showSearchForm() {
        return "search";
    }

    @GetMapping("/searchResult")
    public String showSearchResults(
            @RequestParam(required = false, name="title") String title,
            @RequestParam(required = false, name="category") Category category,
            @RequestParam(required = false, name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false, name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false, name="free") Boolean free,
            @RequestParam(required = false, name="keywords") List<Hashtag> keywords,
            Model model) {

        List<Event> events = searchService.searchEvents(title, category, startDate, endDate, free, keywords);

        model.addAttribute("events", events);

        return "searchResult";
    }
}
