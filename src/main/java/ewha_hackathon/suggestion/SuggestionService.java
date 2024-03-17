package ewha_hackathon.suggestion;


import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.Hashtag;
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
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<String> suggestKeywords(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        return fetchSuggestedKeywords(eventId, event.getContent());
    }

    public List<String> fetchSuggestedKeywords(Long eventId, String description) {
        // Flask 서버의 URL
        String url = "http://localhost:5000/extractKeywords";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("description", description);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestMap, headers);

        List<String> keywords = restTemplate.exchange(url, HttpMethod.POST, entity, List.class).getBody();
        System.out.println(keywords);

        saveKeywords(eventId, keywords);
        return keywords;
    }

    @Transactional
    public void saveKeywords(Long eventId, List<String> keywords) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 event_id 없음: " + eventId));
        List<Hashtag> suggestedKeywords = Hashtag.parseKeywords(keywords);
        Suggestion suggestion = new Suggestion(suggestedKeywords);
        suggestion.setEvent(event);
        suggestionRepository.save(suggestion);
        String joinedKeywords = suggestedKeywords.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
        System.out.println("Event ID: " + eventId + ", Suggested Keywords: " + joinedKeywords);
    }

    @Transactional
    public void saveSelectedKeywords(Long eventId, List<Hashtag> selectedKeywords) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다: " + eventId));
        String joinedKeywords = selectedKeywords.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
        event.setSelectedKeywords(joinedKeywords);

        System.out.println(joinedKeywords);
        eventRepository.save(event);
    }

}
