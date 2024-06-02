package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.ScheduleRequestDto;
import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.Schedule;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import com.rhmaster.rhmaster.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void saveSchedule(ScheduleRequestDto request) {
        Schedule update = scheduleRepository.findById(request.getId()).get();
        update.setScheduleDate(request.getScheduleDate());

        scheduleRepository.save(update);
    }

    public ScheduleRequestDto getSchedule(UUID userId) {
        Schedule schedule = scheduleRepository.findByEmployeeId(userId);

        if (schedule == null) {
            Employee employee = employeeRepository.findById(userId).get();

            schedule = Schedule.builder()
                    .employee(employee)
                    .build();

            scheduleRepository.save(schedule);
        }

        ScheduleRequestDto request = new ScheduleRequestDto();
        request.setId(schedule.getId());
        request.setScheduleDate(schedule.getScheduleDate());

        return request;
    }
}
