package com.springboot.springdatajpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/12/2
 */
@Entity
@Data
@Accessors(chain = true)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String studentName;

    @ManyToMany(targetEntity = Project.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "studentProject",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "projectId"))
    private List<Project> projectList;

}
