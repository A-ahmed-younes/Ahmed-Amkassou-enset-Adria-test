package ma.enset.configservice.dto.transfer;

import ma.enset.configservice.enums.TransferStatus;

public record TransferUpdateRequest(
        TransferStatus status
) {
}
