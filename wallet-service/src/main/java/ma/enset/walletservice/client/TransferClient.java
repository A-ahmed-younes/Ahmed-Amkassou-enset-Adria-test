package ma.enset.walletservice.client;

import ma.enset.configservice.dto.transfer.TransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/api/transfers")
public interface TransferClient {
    @GetExchange(url = "/transfer/{id}")
    ResponseEntity<TransferResponse> findById(
            @PathVariable("id") String id
    );

    @GetExchange(url = "/transfer/{id}/exists")
    ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    );
}
