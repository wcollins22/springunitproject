package com.example.Spring_Unit_Project.team;

import com.example.Spring_Unit_Project.student.Student;
import com.example.Spring_Unit_Project.student.StudentRepository;
import com.example.Spring_Unit_Project.manager.Manager;
import com.example.Spring_Unit_Project.manager.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ManagerRepository managerRepository;

    @GetMapping
    List<Team> getMembers() {
        return teamRepository.findAll();
    }

    @PostMapping
    Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/{teamId}/students/{studentId}")
    Team addStudentToTeam(
            @PathVariable Long teamId,
            @PathVariable Long studentId
    ) {
        Team team = teamRepository.findById(teamId).get();
        Student student = studentRepository.findById(studentId).get();
        team.members.add(student);
        return teamRepository.save(team);
    }

    @PutMapping("/{teamId}/manager/{managerId}")
    Team assignManagerToTeam(
            @PathVariable Long teamId,
            @PathVariable Long managerId
    ) {
        Team team = teamRepository.findById(teamId).get();
        Manager manager = managerRepository.findById(managerId).get();
        team.setManager(manager);
        return teamRepository.save(team);
    }

    @DeleteMapping("/{teamId}")
    public void deleteTeam(
            @PathVariable Long teamId
    ) {

        boolean exists = teamRepository.existsById(teamId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + teamId + " does not exists"
            );

        }
        teamRepository.deleteById(teamId);

    }
}
