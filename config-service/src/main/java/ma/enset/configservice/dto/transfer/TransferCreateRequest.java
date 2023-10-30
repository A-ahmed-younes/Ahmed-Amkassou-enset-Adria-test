package ma.enset.core.dto.transfer;

import jakarta.validation.constraints.NotNull;


public record TransferCreateRequest(
        @NotNull String source,
        @NotNull String destination,
        @NotNull double amount
) {
}