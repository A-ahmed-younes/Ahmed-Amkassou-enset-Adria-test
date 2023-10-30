package ma.enset.transferservice.model;

import jakarta.persistence.*;
import ma.enset.configservice.enums.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransferStatus status;
    private LocalDate createdDate;
    private String source;
    private String destination;
}
