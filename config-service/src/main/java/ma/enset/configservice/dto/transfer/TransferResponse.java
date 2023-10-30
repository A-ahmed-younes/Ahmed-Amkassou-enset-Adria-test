package ma.enset.core.dto.transfer;

import ma.enset.core.dto.wallet.WalletResponse;
import ma.enset.core.enums.TransferStatus;
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
