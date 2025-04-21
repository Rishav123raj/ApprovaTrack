@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id @GeneratedValue
    private Long id;

    private String action; // APPROVED, REJECTED, CREATED
    private Long entityId;
    private String entityType;
    private String oldValue;
    private String newValue;
    private String changedBy;
    private LocalDateTime timestamp = LocalDateTime.now();
}
