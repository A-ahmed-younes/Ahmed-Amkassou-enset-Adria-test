package ma.enset.transferservice.service;

import ma.enset.configservice.dto.shared.PagingResponse;
import ma.enset.configservice.dto.transfer.TransferCreateRequest;
import ma.enset.configservice.dto.transfer.TransferResponse;
import ma.enset.configservice.dto.transfer.TransferUpdateRequest;
import ma.enset.configservice.exception.AlreadyExistsException;
import ma.enset.configservice.exception.NotFoundException;

public interface TransferService {
    TransferResponse create(TransferCreateRequest request) throws AlreadyExistsException;

    TransferResponse update(String id, TransferUpdateRequest request) throws NotFoundException, AlreadyExistsException;

    TransferResponse findById(String id) throws NotFoundException;

    PagingResponse<TransferResponse> findAll(int page, int size);


    boolean existsById(String id);

    void deleteById(String id) throws NotFoundException;
}
