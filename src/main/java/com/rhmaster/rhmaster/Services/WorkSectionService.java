package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.models.WorkSection;
import com.rhmaster.rhmaster.repository.WorkSectionRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkSectionService {
    @Autowired
    WorkSectionRepositoy workSectionRepositoy;

    public List<WorkSection> getWorkSections() {
        return workSectionRepositoy.findAll();
    }

    public void save(WorkSection workSection) {
        workSectionRepositoy.save(workSection);
    }

    public void deleteById(UUID id) {
        workSectionRepositoy.deleteById(id);
    }
}
