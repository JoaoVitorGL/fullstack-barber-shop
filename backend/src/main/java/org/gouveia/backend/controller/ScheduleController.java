package org.gouveia.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.gouveia.backend.controller.request.SaveScheduleRequest;
import org.gouveia.backend.controller.response.SaveScheduleResponse;
import org.gouveia.backend.controller.response.ScheduleAppointmentMonthResponse;
import org.gouveia.backend.mapper.IScheduleMapper;
import org.gouveia.backend.service.IScheduleService;
import org.gouveia.backend.service.query.IScheduleQueryService;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

import static java.time.ZoneOffset.UTC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/schedules")
@AllArgsConstructor
public class ScheduleController {

    private final IScheduleService service;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request) {
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id) {
        service.delete(id);
    }

    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month) {
        var yearMonth = YearMonth.of(year, month);
        var startAt = yearMonth.atDay(1)
                .atTime(0, 0, 0, 0)
                .atOffset(UTC);
        var endAt = yearMonth.atEndOfMonth()
                .atTime(23, 59, 59, 999_999_999)
                .atOffset(UTC);
        var entities = queryService.findInMonth(startAt, endAt);
        return mapper.toMonthResponse(year, month, entities);
    }
}
