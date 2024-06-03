package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.BonusService;
import com.rhmaster.rhmaster.models.Bonus;
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
}
