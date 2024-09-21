package toy_project.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String imageUrl;
    private String description;
}
