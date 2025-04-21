@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetRequest {
    @Id @GeneratedValue
    private Long id;

    private BigDecimal requestedAmount;
    private String purpose;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String requestedBy;
    private String approvedBy;

    @ManyToOne
    private Department department;

    private LocalDateTime dateCreated = LocalDateTime.now();
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public enum Status {
        PENDING, APPROVED, REJECTED
    }
}
