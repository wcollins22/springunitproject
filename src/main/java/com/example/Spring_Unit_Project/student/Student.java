package com.example.Spring_Unit_Project.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.Spring_Unit_Project.team.Team;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "members")
    private Set<Team> teams = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Team> getTeams() {
        return teams;
    }

}
