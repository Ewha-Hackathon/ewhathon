package ewha_hackathon.search;

import ewha_hackathon.domain.Category;
import ewha_hackathon.domain.Hashtag;
import org.springframework.data.jpa.domain.Specification;
import ewha_hackathon.domain.Event;

import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecifications {

    public static Specification<Event> titleContains(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Event> inCategory(Category category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Event> betweenDates(LocalDate start, LocalDate end) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("start_date"), start, end);
    }

    public static Specification<Event> isFree(Boolean free) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("free"), free);
    }

    public static Specification<Event>hasKeywords(List<Hashtag> hashtags) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Hashtag hashtag : hashtags) {
                predicates.add(cb.isMember(hashtag, root.get("suggestedKeywords"))); // Use Enum directly
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
