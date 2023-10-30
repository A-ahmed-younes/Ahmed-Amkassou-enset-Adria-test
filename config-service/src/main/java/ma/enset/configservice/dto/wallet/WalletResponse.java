package ma.enset.configservice.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {
    private String id;
    private double balance;
    private String currency;
    private LocalDate createdDate;
    private Integer customerId;

//    Customer Response from customer-service which it doesn't exist yet
//    private CustomerResponse customer;
}
