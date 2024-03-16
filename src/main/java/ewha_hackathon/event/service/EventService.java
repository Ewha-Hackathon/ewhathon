package ewha_hackathon.event.service;

import ewha_hackathon.event.DTO.EventRequestDto;
import ewha_hackathon.domain.Event;
import ewha_hackathon.domain.User;
import ewha_hackathon.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void createEvent(User user, EventRequestDto dto) throws Exception{
        File file = write(dto.getFile());
        Event event = Event.createEvent(dto, user, file.getName(), "/files/"+file.getName());
        eventRepository.save(event);
    }

    //사진 저장
    public File write(MultipartFile file) throws Exception {
        //사진 담을 경로
        String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        //랜덤으로 이름 만들어줌
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "-" + file.getOriginalFilename();
        //파일 저장
        File saveFile = new File(filepath, filename);
        file.transferTo(saveFile);

        return saveFile;
    }
}
