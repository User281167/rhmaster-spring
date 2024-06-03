package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.ExtraHourDto;
import com.rhmaster.rhmaster.dtos.ScheduleMissingDto;
import com.rhmaster.rhmaster.models.ExtraHour;
import com.rhmaster.rhmaster.models.Schedule;
import com.rhmaster.rhmaster.models.ScheduleMissing;
import com.rhmaster.rhmaster.repository.ExtraHourRepository;
import com.rhmaster.rhmaster.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExtraHourService {
    @Autowired
    private ExtraHourRepository extraHourRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public void save(ExtraHour extraHour) {
        extraHourRepository.save(extraHour);
    }

    public void delete(UUID id) {
        extraHourRepository.deleteById(id);
    }

    public List<ExtraHourDto> getExtrahours(UUID scheduleId) {
        List<ExtraHour> missings = extraHourRepository.findAllByScheduleId(scheduleId);
        return missings.stream().map(ExtraHourDto::new).toList();
    }

    public void addExtraHour(ExtraHourDto extraHourDto) {
        Schedule schedule = scheduleRepository.findById(extraHourDto.getScheduleId()).get();

        ExtraHour missing = ExtraHour.builder()
                .id(extraHourDto.getId())
                .hours(extraHourDto.getHours())
                .date(extraHourDto.getDate())
                .schedule(schedule)
                .build();

        extraHourRepository.save(missing);
    }
}
