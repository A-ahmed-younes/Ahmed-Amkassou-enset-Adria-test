package ma.enset.core.dto.wallet;

public record WalletUpdateRequest(
        String currency,
        double balance
) {
}
