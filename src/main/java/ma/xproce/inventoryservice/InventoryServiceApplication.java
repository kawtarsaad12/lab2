package ma.xproce.inventoryservice;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            List<Creator> creators = List.of(Creator.builder().name("x")
                    .email("x@gmail.com").build(), Creator.builder().name("y")
                    .email("y@gmail.com").build(), Creator.builder().name("z")
                    .email("z@gmail.com").build());
            for (Creator creator : creators) {
                creatorRepository.save(creator);
            }

            List<Video> videos = List.of(Video.builder().name("vEducation")
                    .url("vEducation.com").datePublication(new Date())
                    .description("this is an educational video").creator(creators.get(1))
                    .build(), Video.builder().name("vGaming").url("vEGaming.com")
                    .datePublication(new Date()).description("this is a Gaming video")
                    .creator(creators.get(0)).build(), Video.builder().name("vEntertainement")
                    .url("vEntertainement.com").datePublication(new Date())
                    .description("this is an entertainement video")
                    .creator(creators.get(2)).build());
            for (Video video : videos) {
                videoRepository.save(video);
            }
        };
    }
}
