package com.example.departmentbudgetapproval.controller;

import com.example.departmentbudgetapproval.model.AuditLog;
import com.example.departmentbudgetapproval.model.BudgetRequest;
import com.example.departmentbudgetapproval.model.BudgetRequest.Status;
import com.example.departmentbudgetapproval.repository.AuditLogRepository;
import com.example.departmentbudgetapproval.repository.BudgetRequestRepository;
import com.example.departmentbudgetapproval.service.BudgetRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetRequestController {

    @Autowired
    private BudgetRequestService service;

    @Autowired
    private BudgetRequestRepository budgetRepo;

    @Autowired
    private AuditLogRepository auditRepo;

    /**
     * Submit a new budget request
     */
    @PostMapping("/budget-request")
    public ResponseEntity<?> submitRequest(@Valid @RequestBody BudgetRequest request, Principal principal) {
        request.setRequestedBy(principal.getName());
        return ResponseEntity.ok(service.submitRequest(request));
    }

    /**
     * Approve a budget request (only for MANAGER)
     */
    @PutMapping("/budget-request/{id}/approve")
    public ResponseEntity<?> approveRequest(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(service.approve(id, principal.getName()));
    }

    /**
     * Reject a budget request (only for MANAGER)
     */
    @PutMapping("/budget-request/{id}/reject")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(service.reject(id, principal.getName()));
    }

    /**
     * Get all pending requests
     */
    @GetMapping("/budget-request/pending")
    public ResponseEntity<List<BudgetRequest>> getPendingRequests() {
        return ResponseEntity.ok(budgetRepo.findByStatus(Status.PENDING));
    }

    /**
     * Get audit logs filtered by entity type
     */
    @GetMapping("/audit-logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs(@RequestParam String entityType) {
        return ResponseEntity.ok(auditRepo.findByEntityType(entityType));
    }
}
