package ma.enset.transferservice.mapper;

import ma.enset.configservice.dto.shared.PagingResponse;
import ma.enset.configservice.dto.transfer.TransferCreateRequest;
import ma.enset.configservice.dto.transfer.TransferResponse;
import ma.enset.configservice.dto.transfer.TransferUpdateRequest;
import ma.enset.transferservice.model.Transfer;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TransferMapper {
    Transfer toModel(TransferCreateRequest request);

    List<Transfer> toModelList(List<TransferCreateRequest> requestList);

    @Mapping(target = "source", ignore = true)
    @Mapping(target = "destination", ignore = true)
    TransferResponse toResponse(Transfer transfer);

    List<TransferResponse> toResponseList(List<Transfer> transferList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(TransferUpdateRequest request, @MappingTarget Transfer transfer);

    @Mapping(target = "page", expression = "java(model.getNumber())")
    @Mapping(target = "size", expression = "java(model.getSize())")
    @Mapping(target = "totalPages", expression = "java(model.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(model.getNumberOfElements())")
    @Mapping(target = "records", expression = "java(toResponseList(model.getContent()))")
    PagingResponse<TransferResponse> toPagingResponse(Page<Transfer> model);
}
