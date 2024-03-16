package suggestion;

import ewha_hackathon.domain.Suggestion;
import ewha_hackathon.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionService(SuggestionRepository suggestRepository) {
        this.suggestionRepository = suggestRepository;
    }

    public Suggestion saveSuggestion(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }

    public List<Suggestion> findAll() {
        return suggestionRepository.findAll();
    }
}
