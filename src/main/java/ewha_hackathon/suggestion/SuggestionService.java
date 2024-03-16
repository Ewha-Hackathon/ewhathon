package ewha_hackathon.suggestion;

import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Suggestion;
import ewha_hackathon.repository.EventRepository;
import ewha_hackathon.repository.SuggestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class SuggestionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<String> fetchSuggestedKeywords(String description) {
        // Flask 서버의 URL
        String url = "http://localhost:5000/extractKeywords";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("description", description);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestMap, headers);

        List<String> keywords = restTemplate.exchange(url, HttpMethod.POST, entity, List.class).getBody();
        System.out.println(keywords);

        return keywords;
    }

    @Transactional
    public void updateEventKeywords(Long eventId, List<String> keywords) {
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 event_id 없음: " + eventId));
            Suggestion suggestion = new Suggestion();
            suggestion.setEvent(event);
            suggestion.setSuggestedKeywords(String.join(",", keywords));
            suggestionRepository.save(suggestion);
        } catch (Exception e) {
        }
    }
}

