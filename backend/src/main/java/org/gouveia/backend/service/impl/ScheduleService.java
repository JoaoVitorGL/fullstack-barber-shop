package org.gouveia.backend.service.impl;

import lombok.AllArgsConstructor;
import org.gouveia.backend.entity.ScheduleEntity;
import org.gouveia.backend.repository.IScheduleRepository;
import org.gouveia.backend.service.IScheduleService;
import org.gouveia.backend.service.query.IScheduleQueryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);
    }

    @Override
    public void delete(final long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
