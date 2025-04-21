@Service
public class BudgetRequestService {
    @Autowired private BudgetRequestRepository budgetRepo;
    @Autowired private DepartmentRepository deptRepo;
    @Autowired private AuditService auditService;

    public BudgetRequest submitRequest(BudgetRequest req) {
        if (req.getRequestedAmount() <= 0)
            throw new IllegalArgumentException("Requested amount must be positive");

        List<BudgetRequest> recent = budgetRepo.findRecentByPurpose(
            req.getDepartment().getId(), req.getPurpose(), LocalDateTime.now().minusDays(7));

        if (!recent.isEmpty())
            throw new IllegalArgumentException("Duplicate request within 7 days");

        return budgetRepo.save(req);
    }

    public BudgetRequest approve(Long id, String manager) {
        BudgetRequest req = budgetRepo.findById(id).orElseThrow();
        Department dept = req.getDepartment();

        if (req.getRequestedAmount() > dept.getYearlyAllocation() * 0.1)
            throw new IllegalArgumentException("Amount exceeds 10% of yearly allocation");

        req.setStatus(BudgetRequest.Status.APPROVED);
        req.setApprovedBy(manager);
        dept.setCurrentBudget(dept.getCurrentBudget() - req.getRequestedAmount());

        deptRepo.save(dept);
        auditService.log("APPROVE", req);
        return budgetRepo.save(req);
    }

    public BudgetRequest reject(Long id, String manager) {
        BudgetRequest req = budgetRepo.findById(id).orElseThrow();
        req.setStatus(BudgetRequest.Status.REJECTED);
        req.setApprovedBy(manager);
        auditService.log("REJECT", req);
        return budgetRepo.save(req);
    }
}
