package ewha_hackathon.search;

import ewha_hackathon.domain.Category;
import ewha_hackathon.domain.Event;
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

    @GetMapping("/searchResults")
    public String showSearchResults(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Boolean free,
            @RequestParam(required = false) List<String> keywords,
            Model model) {

        List<String> keywordList = new ArrayList<>();
        if (keywords != null && !keywords.isEmpty()) {
            for (String keyword : keywords) {
                keywordList.add(keyword.trim());
            }
        }

        List<Event> events = searchService.searchEvents(title, category, startDate, endDate, free, keywordList);

        model.addAttribute("events", events);

        return "searchResult";
    }
}
