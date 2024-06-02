package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.ScheduleMissingDto;
import com.rhmaster.rhmaster.models.Schedule;
import com.rhmaster.rhmaster.models.ScheduleMissing;
import com.rhmaster.rhmaster.repository.ScheduleMissingRepository;
import com.rhmaster.rhmaster.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScheduleMissingService {
    @Autowired
    ScheduleMissingRepository scheduleMissingRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public void save(ScheduleMissing schedule) {
        scheduleMissingRepository.save(schedule);
    }

    public void delete(UUID id) {
        scheduleMissingRepository.deleteById(id);
    }

    public List<ScheduleMissingDto> getMissing(UUID scheduleId) {
        List<ScheduleMissing> missings = scheduleMissingRepository.findAllByScheduleId(scheduleId);
        return missings.stream().map(ScheduleMissingDto::new).toList();
    }

    public void addMissing(ScheduleMissingDto missingDto) {
        Schedule schedule = scheduleRepository.findById(missingDto.getScheduleId()).get();

        ScheduleMissing missing = ScheduleMissing.builder()
                .id(missingDto.getId())
                .reason(missingDto.getReason())
                .date(missingDto.getDate())
                .schedule(schedule)
                .build();

        scheduleMissingRepository.save(missing);
    }
}
