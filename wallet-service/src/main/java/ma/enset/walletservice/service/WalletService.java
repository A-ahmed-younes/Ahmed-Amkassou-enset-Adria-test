package ma.enset.walletservice.service;

import ma.enset.configservice.dto.wallet.WalletCreateRequest;
import ma.enset.configservice.dto.wallet.WalletResponse;
import ma.enset.configservice.dto.shared.PagingResponse;
import ma.enset.configservice.exception.AlreadyExistsException;
import ma.enset.configservice.exception.NotFoundException;

public interface WalletService {
    WalletResponse create(WalletCreateRequest request) throws AlreadyExistsException;

    WalletResponse update(String id, WalletCreateRequest request) throws NotFoundException, AlreadyExistsException;

    WalletResponse findById(String id) throws NotFoundException;

    PagingResponse<WalletResponse> findAll(int page, int size);


    boolean existsById(String id);

    void deleteById(String id) throws NotFoundException;
}
