package library_management.spring.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowRecordDetailResponse {
    Long userId;
    Long bookId;
    LocalDateTime borrowDate;
    LocalDateTime dueDate;
    LocalDateTime returnDate;
    Double fineAmount;
}
