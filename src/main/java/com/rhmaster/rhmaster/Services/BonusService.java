package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.models.Bonus;
import com.rhmaster.rhmaster.repository.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BonusService {
    @Autowired
    private BonusRepository bonusRepository;

    public void saveBonus(Bonus bonus) {
        bonusRepository.save(bonus);
    }

    public List<Bonus> getBonusList() {
        return bonusRepository.findAll();
    }

    public void deleteBonus(UUID id) {
        bonusRepository.deleteById(id);
    }
}
