package ma.enset.core.dto.transfer;

import ma.enset.core.enums.TransferStatus;

public record TransferUpdateRequest(
        TransferStatus status
) {
}
