package ma.enset.configservice.dto.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletCreateRequest(
        @NotNull Integer customerId,
        @NotBlank String currency,
        Double balance // initial balance
) {
}
