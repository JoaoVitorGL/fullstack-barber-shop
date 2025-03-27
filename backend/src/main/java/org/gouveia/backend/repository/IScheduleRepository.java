package org.gouveia.backend.repository;

import org.gouveia.backend.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThenEqualOrderByStartAtEndAt(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    boolean existsStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
