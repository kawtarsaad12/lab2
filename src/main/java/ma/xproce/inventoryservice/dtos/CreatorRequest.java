package ma.xproce.inventoryservice.dtos;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatorRequest {
    private String name;
    private String email;
}
