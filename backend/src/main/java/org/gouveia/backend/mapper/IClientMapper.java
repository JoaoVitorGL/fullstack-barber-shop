package org.gouveia.backend.mapper;

import org.gouveia.backend.controller.request.SaveClientRequest;
import org.gouveia.backend.controller.request.UpdateClientRequest;
import org.gouveia.backend.controller.response.ClientDetailResponse;
import org.gouveia.backend.controller.response.ListClientResponse;
import org.gouveia.backend.controller.response.SaveClientResponse;
import org.gouveia.backend.controller.response.UpdateClientResponse;
import org.gouveia.backend.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}
