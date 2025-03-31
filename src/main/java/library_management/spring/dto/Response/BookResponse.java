package library_management.spring.dto.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private boolean isAvailable;
}
