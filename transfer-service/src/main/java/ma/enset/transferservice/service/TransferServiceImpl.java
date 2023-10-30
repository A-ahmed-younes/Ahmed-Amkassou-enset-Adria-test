package ma.enset.transferservice.service;

import jakarta.transaction.Transactional;
import ma.enset.configservice.constant.configservice.onstants;
import ma.enset.configservice.dto.shared.PagingResponse;
import ma.enset.configservice.dto.transfer.TransferCreateRequest;
import ma.enset.configservice.dto.transfer.TransferResponse;
import ma.enset.configservice.dto.transfer.TransferUpdateRequest;
import ma.enset.configservice.dto.wallet.WalletResponse;
import ma.enset.configservice.exception.AlreadyExistsException;
import ma.enset.configservice.exception.InsufficientBalanceException;
import ma.enset.configservice.exception.NotFoundException;
import ma.enset.transferservice.client.WalletClient;
import ma.enset.transferservice.mapper.TransferMapper;
import ma.enset.transferservice.model.Transfer;
import ma.enset.transferservice.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final String ELEMENT_NAME = "Transfer";
    private final String ELEMENT_ID = "id";

    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final WalletClient walletClient;

    @Transactional
    @Override
    public TransferResponse create(TransferCreateRequest request) throws AlreadyExistsException {

        // check if source exits
        ResponseEntity<WalletResponse> source = walletClient.findById(request.source());

        if (source.getStatusCode().isError())
            throw new NotFoundException(
                   .configservice.onstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{"Wallet", "id", request.source()},
                    null
            );

        WalletResponse sourceWallet = source.getBody();

        // check if source has enough balance
        if (sourceWallet == null || sourceWallet.getBalance() < request.amount())
            throw new InsufficientBalanceException();

        // check if destination exists
        if (Boolean.FALSE.equals(walletClient.existsById(request.destination()).getBody()))
            throw new NotFoundException(
                   .configservice.onstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{"Wallet", "id", request.destination()},
                    null
            );


        // TODO: update source balance
        // TODO: destination balance

        TransferResponse response = transferMapper.toResponse(
                transferRepository.save(
                        transferMapper.toModel(request)
                )
        );

        setWallets(response, request.source(), request.destination());

        return response;
    }

    @Override
    public TransferResponse update(String id, TransferUpdateRequest request) throws NotFoundException, AlreadyExistsException {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                       .configservice.onstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                        null
                ));

        transferMapper.updateModel(request, transfer);

        TransferResponse response = transferMapper.toResponse(
                transferRepository.save(transfer)
        );

        setWallets(response, transfer.getSource(), transfer.getDestination());

        return response;
    }

    @Override
    public TransferResponse findById(String id) throws NotFoundException {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                       .configservice.onstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                        null
                ));

        TransferResponse response = transferMapper.toResponse(
                transfer
        );

        setWallets(response, transfer.getSource(), transfer.getDestination());

        return response;
    }

    @Override
    public PagingResponse<TransferResponse> findAll(int page, int size) {

        Page<Transfer> transfers = transferRepository.findAll(
                PageRequest.of(page, size)
        );

        PagingResponse<TransferResponse> response = transferMapper.toPagingResponse(
                transfers
        );

        for (int i = 0; i < transfers.getSize(); i++) {
            setWallets(
                    response.records().get(i),
                    transfers.getContent().get(i).getSource(),
                    transfers.getContent().get(i).getDestination()
            );
        }
        return response;
    }

    @Override
    public boolean existsById(String id) {
        return transferRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        if (!transferRepository.existsById(id))
            throw new NotFoundException(
                   .configservice.onstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                    null
            );
        transferRepository.deleteById(id);
    }


    private void setWallets(TransferResponse response, String source, String destination) {
        response.setSource(
                walletClient.findById(source).getBody()
        );
        response.setDestination(
                walletClient.findById(destination).getBody()
        );
    }

}
