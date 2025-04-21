@Service
public class AuditService {
    @Autowired private AuditLogRepository repo;

    public void log(String action, BudgetRequest req) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntityId(req.getId());
        log.setEntityType("BudgetRequest");
        log.setOldValue("N/A"); // Add logic to diff if needed
        log.setNewValue(req.getStatus().name());
        log.setChangedBy(req.getApprovedBy());
        repo.save(log);
    }
}
