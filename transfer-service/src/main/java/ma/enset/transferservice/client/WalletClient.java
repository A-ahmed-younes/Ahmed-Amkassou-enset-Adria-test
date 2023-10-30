package ma.enset.transferservice.client;


import ma.enset.configservice.dto.wallet.WalletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange(url = "/api/wallets")
public interface WalletClient {
    @GetExchange(url = "/wallet/{id}")
    ResponseEntity<WalletResponse> findById(
            @PathVariable("id") String id
    );

    @GetExchange(url = "/wallet/{id}/exists")
    ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    );
}
