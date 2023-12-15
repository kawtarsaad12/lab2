package ma.xproce.inventoryservice.service;


import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoManager {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CreatorRepository creatorRepository ;


    public Video saveVideo( Video video){

        creatorRepository.save(video.getCreator()) ;
        return videoRepository.save(video);
    }

    public Video findById(long l) {
        return videoRepository.findById(l).get();
    }

    public void updateVideo(Video video) {
     videoRepository.save(video);
    }
}
