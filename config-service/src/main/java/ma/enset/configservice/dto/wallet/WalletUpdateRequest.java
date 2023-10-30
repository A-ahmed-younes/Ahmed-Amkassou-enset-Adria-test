package ma.enset.configservice.dto.wallet;

public record WalletUpdateRequest(
        String currency,
        double balance
) {
}
