package com.example.ApprovaTrack.repository;
import com.example.ApprovaTrack.model.Department;
import com.example.ApprovaTrack.model.BudgetRequest;
import com.example.ApprovaTrack.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}

public interface BudgetRequestRepository extends JpaRepository<BudgetRequest, Long> {
    List<BudgetRequest> findByStatus(BudgetRequest.Status status);

    boolean existsByPurposeAndDepartmentAndDateCreatedAfter(
        String purpose, Department department, LocalDateTime afterDate);
}

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityType(String entityType);
}
