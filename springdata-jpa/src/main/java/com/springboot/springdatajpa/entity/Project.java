package com.springboot.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Filter;

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
//@ToString
public class Project {

    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

//    @Filter(name = "nameFilter", condition = "project_name = :projectName")
    private String projectName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @ManyToMany(targetEntity = Student.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "studentProject",joinColumns = @JoinColumn(name = "projectId"))
    @JoinTable(
            name = "studentProject",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "studentId"))
    private List<Student> studentList;

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
