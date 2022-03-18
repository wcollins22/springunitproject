package com.example.Spring_Unit_Project.manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.Spring_Unit_Project.team.Team;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Manager {
    @Id
    @SequenceGenerator(
            name = "manager_sequence",
            sequenceName = "manager_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "manager_sequence"
    )
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private Set<Team> teams;

    private String name;

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
        return this.teams;
    }
}
