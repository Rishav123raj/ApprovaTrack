package com.example.departmentbudgetapproval.repository;

import com.example.departmentbudgetapproval.model.BudgetRequest;
import com.example.departmentbudgetapproval.model.BudgetRequest.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BudgetRequestRepository extends JpaRepository<BudgetRequest, Long> {

    List<BudgetRequest> findByStatus(Status status);

    @Query("SELECT b FROM BudgetRequest b WHERE b.department.id = :deptId AND b.purpose = :purpose AND b.dateCreated >= :since")
    List<BudgetRequest> findRecentByPurpose(Long deptId, String purpose, LocalDateTime since);
}
