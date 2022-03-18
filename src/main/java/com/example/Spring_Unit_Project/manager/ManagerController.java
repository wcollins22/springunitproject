package com.example.Spring_Unit_Project.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerRepository managerRepository;

    @GetMapping
    List<Manager> getManagers() {
        return managerRepository.findAll();
    }

    @PostMapping
    Manager createManager(@RequestBody Manager manager) {
        return managerRepository.save(manager);
    }

    @DeleteMapping("/{managerId}")
    public void deleteManager(
            @PathVariable Long managerId
    ) {

        boolean exists = managerRepository.existsById(managerId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + managerId + " does not exists"
            );

        }
        managerRepository.deleteById(managerId);

    }
}
