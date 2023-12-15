package ma.xproce.inventoryservice.service;

import lombok.AllArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import ma.xproce.inventoryservice.mappers.CreatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatorManager {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CreatorRepository creatorRepository ;
    @Autowired
    private  CreatorMapper creatorMapper;

    public List<Video> videoList(){
        return videoRepository.findAll();
    }

    public Creator creatorById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }

    public Creator saveCreator( CreatorRequest CreatorRequest){
        Creator creator  = creatorMapper.fromCreatorRequestToCreator(CreatorRequest);
        return creatorRepository.save(creator);
    }

    public CreatorRequest getCreator(Long id){
        Creator creator =  creatorRepository.findById(id).get();
        return this.creatorMapper.fromCreatorToCreatorRequest(creator);
    }
}
