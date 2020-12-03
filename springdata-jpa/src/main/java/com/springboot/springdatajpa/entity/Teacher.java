package com.springboot.springdatajpa.entity;

import lombok.Data;
import lombok.ToString;
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
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String teacherName;

    @OneToMany(targetEntity = Project.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId")
    private List<Project> projectList;

}
