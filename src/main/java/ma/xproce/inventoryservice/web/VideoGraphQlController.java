package ma.xproce.inventoryservice.web;

import lombok.AllArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import ma.xproce.inventoryservice.service.CreatorManager;
import ma.xproce.inventoryservice.service.VideoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
public class VideoGraphQlController {
    private CreatorManager creatorManager ;
    private VideoManager videoManager  ;
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();
                    CreatorRequest creatorRequest = CreatorRequest.builder().name("x" +
                                    new Random().nextInt())
                            .email("x@gmail.com").build();
                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                }));
    }


    @QueryMapping
    public List<Video> videoList(){
        return creatorManager.videoList();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorManager.creatorById(id);
    }

    @MutationMapping
    public Video saveVideo(@Argument Video video){
        return videoManager.saveVideo(video);
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest CreatorRequest){
        return creatorManager.saveCreator(CreatorRequest);
    }

    @QueryMapping
    public CreatorRequest getCreator(@Argument Long id){
      return   creatorManager.getCreator(id);
    }

}