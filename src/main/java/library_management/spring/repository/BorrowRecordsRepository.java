package library_management.spring.repository;

import library_management.spring.entity.BorrowRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRecordsRepository extends JpaRepository<BorrowRecordEntity, Long> {
    Optional<BorrowRecordEntity> findByUserIdAndBookId(Long userId, Long bookId);
}
