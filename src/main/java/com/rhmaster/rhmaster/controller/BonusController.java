package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.BonusService;
import com.rhmaster.rhmaster.Services.EmployeeBonusService;
import com.rhmaster.rhmaster.dtos.BonusRequestDto;
import com.rhmaster.rhmaster.dtos.BonusResponseDto;
import com.rhmaster.rhmaster.models.Bonus;
import com.rhmaster.rhmaster.models.EmployeeBonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bonificaciones")
public class BonusController {
    @Autowired
    private BonusService bonusService;

    @Autowired
    private EmployeeBonusService employeeBonusService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveBonus(@RequestBody Bonus bonus) {
        bonusService.saveBonus(bonus);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Bonus> getBonusList() {
        return bonusService.getBonusList();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBonus(@PathVariable("id") UUID id) {
        bonusService.deleteBonus(id);
    }

    @PostMapping("/asignar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void assignBonus(@RequestBody BonusRequestDto employeeBonus) {
        employeeBonusService.save(employeeBonus);
    }

    @GetMapping("/empleado/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public List<BonusResponseDto> getByEmployeeId(@PathVariable("employeeId") UUID employeeId) {
        return employeeBonusService.getByEmployeeId(employeeId);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeBonus(@PathVariable("id") UUID id) {
        employeeBonusService.deleteById(id);
    }
}
