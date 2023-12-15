package ma.xproce.inventoryservice.dao.repositories;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}
