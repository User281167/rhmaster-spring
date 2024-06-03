package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.BonusRequestDto;
import com.rhmaster.rhmaster.dtos.BonusResponseDto;
import com.rhmaster.rhmaster.models.Bonus;
import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.EmployeeBonus;
import com.rhmaster.rhmaster.repository.BonusRepository;
import com.rhmaster.rhmaster.repository.EmployeeBonusRepository;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeBonusService {
    @Autowired
    private EmployeeBonusRepository employeeBonusRepository;

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(BonusRequestDto bonusDto) {
        Bonus bonus = bonusRepository.findById(bonusDto.getBonusId()).get();
        Employee employee = employeeRepository.findById(bonusDto.getEmployeeId()).get();
        EmployeeBonus employeeBonus;

        if (bonusDto.getId() == null) {
            employeeBonus = EmployeeBonus.builder()
                    .bonus(bonus)
                    .employee(employee)
                    .build();
        } else {
            employeeBonus = employeeBonusRepository.findById(bonusDto.getId()).get();
            employeeBonus.setBonus(bonus);
            employeeBonus.setEmployee(employee);
            employeeBonusRepository.save(employeeBonus);
        }

        employeeBonusRepository.save(employeeBonus);
    }

    public void deleteById(UUID id) {
        employeeBonusRepository.deleteById(id);
    }

    public List<BonusResponseDto> getByEmployeeId(UUID employeeId) {
        List<EmployeeBonus> bonus = employeeBonusRepository.findAllByEmployeeId(employeeId);

        return bonus.stream().map(b -> {
            BonusResponseDto bonusResponseDto = new BonusResponseDto();
            bonusResponseDto.setId(b.getId());
            bonusResponseDto.setBonus(b.getBonus());
            return bonusResponseDto;
        }).toList();
    }
}
