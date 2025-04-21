@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal currentBudget;

    @Column(precision = 10, scale = 2)
    private BigDecimal yearlyAllocation;
}
