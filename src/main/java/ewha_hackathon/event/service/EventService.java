package ewha_hackathon.event.service;

import ewha_hackathon.domain.Category;
import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.User;
import ewha_hackathon.event.DTO.EventResponseDto;
import ewha_hackathon.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.time.LocalDate;

import java.util.*;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Long createEvent(User user, Category category, String title, String location, String host, LocalDate startDate, LocalDate endDate, int free, String content, MultipartFile file) throws Exception {
        File saveFile = write(file);
        Event event = Event.createEvent(user, category, title, location, host, startDate, endDate, free, content, saveFile.getName(), "/files/" + saveFile.getName());
        eventRepository.save(event);
        return event.getId(); // 이벤트의 ID 반환
    }

    //사진 저장
    public File write(MultipartFile file) throws Exception {
        //사진 담을 경로
        String filepath = System.getProperty("user.dir") + "/src/main/resources/static.files";
        //랜덤으로 이름 만들어줌
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "-" + file.getOriginalFilename();
        System.out.println(filename);
        //파일 저장
        File saveFile = new File(filepath, filename);
        System.out.println("1");
        file.transferTo(saveFile);
        System.out.println("2");

        return saveFile;
    }


    //이벤트 상세보기
    public EventResponseDto showEventDetail(Long event_id) {

        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 id입니다"));

        return EventResponseDto.builder()
                .event_id(event.getId())
                .category(event.getCategory())
                .title(event.getTitle())
                .location(event.getLocation())
                .host(event.getHost())
                .start_date(event.getStart_date())
                .end_date(event.getEnd_date())
                .free(event.isFree())
                .content(event.getContent())
                .keywords(event.getKeywords())
                .build();

    }

    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

}