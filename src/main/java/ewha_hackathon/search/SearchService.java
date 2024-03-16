package ewha_hackathon.search;

import ewha_hackathon.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ewha_hackathon.domain.Event;
import ewha_hackathon.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> searchEvents(String title, Category category, LocalDate startDate, LocalDate endDate, Boolean free, List<String> keywords) {
        Specification<Event> spec = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            spec = spec.and(SearchSpecifications.titleContains(title));
        }
        if (category != null) {
            spec = spec.and(SearchSpecifications.inCategory(category));
        }
        if (startDate != null && endDate != null) {
            spec = spec.and(SearchSpecifications.betweenDates(startDate, endDate));
        }
        if (free != null) {
            spec = spec.and(SearchSpecifications.isFree(free));
        }
        if (keywords != null && !keywords.isEmpty()) {
            spec = spec.and(SearchSpecifications.hasKeywords(keywords));
        }

        return eventRepository.findAll(spec);
    }
}

