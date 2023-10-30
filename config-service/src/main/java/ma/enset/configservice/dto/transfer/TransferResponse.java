package ma.enset.configservice.dto.transfer;

import ma.enset.configservice.dto.wallet.WalletResponse;
import ma.enset.configservice.enums.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferResponse {
    private String id;

    private String amount;
    private TransferStatus status;

    private WalletResponse source;
    private WalletResponse destination;

    private LocalDate createdDate;

}
