package org.gouveia.backend.service;

import org.gouveia.backend.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);
}
