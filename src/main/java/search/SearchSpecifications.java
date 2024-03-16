package search;

import ewha_hackathon.domain.Category;
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

    public static Specification<Event> isFree(boolean free) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("free"), free);
    }

    public static Specification<Event> hasKeywords(List<String> keywords) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String keyword : keywords) {
                predicates.add(cb.isMember(keyword, root.get("keywords")));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
