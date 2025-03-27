package org.gouveia.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.gouveia.backend.controller.request.SaveClientRequest;
import org.gouveia.backend.controller.request.UpdateClientRequest;
import org.gouveia.backend.controller.response.ClientDetailResponse;
import org.gouveia.backend.controller.response.ListClientResponse;
import org.gouveia.backend.controller.response.SaveClientResponse;
import org.gouveia.backend.controller.response.UpdateClientResponse;
import org.gouveia.backend.mapper.IClientMapper;
import org.gouveia.backend.service.IClientService;
import org.gouveia.backend.service.query.IClientQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }
}
