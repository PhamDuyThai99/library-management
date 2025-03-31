package library_management.spring.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseStatus {
    @Builder.Default
    int code = 1000;

    @Builder.Default
    String message = "success";
}
