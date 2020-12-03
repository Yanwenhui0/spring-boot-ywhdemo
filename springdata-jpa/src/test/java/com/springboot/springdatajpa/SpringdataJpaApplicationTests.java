package com.springboot.springdatajpa;

import com.springboot.springdatajpa.entity.Project;
import com.springboot.springdatajpa.entity.Student;
import com.springboot.springdatajpa.entity.Teacher;
import com.springboot.springdatajpa.repository.ProjectRepository;
import com.springboot.springdatajpa.repository.StudentRepository;
import com.springboot.springdatajpa.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringdataJpaApplicationTests {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    void oneToOne() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student().setStudentName("s0001"));
        studentList.add(new Student().setStudentName("s0002"));
        studentList.add(new Student().setStudentName("s0003"));
        studentList.add(new Student().setStudentName("s0004"));


        projectRepository.save(new Project()
                .setProjectName("p0001")
                .setTeacher(new Teacher()
                        .setTeacherName("t0001"))
                .setStudentList(studentList));


//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.enableFilter("nameFilter").setParameter("projectName", "p0003");

//        List<Project> list = projectRepository.findAll();
//        System.out.println(list.toString());
    }

    @Test
    @Transactional
    void oneToMany() {
//        List<Project> projectList = new ArrayList<>();
//
//        projectList.add(new Project().setProjectName("p0002"));
//        projectList.add(new Project().setProjectName("p0003"));
//        projectList.add(new Project().setProjectName("p0004"));
//
//        teacherRepository.save(new Teacher().setTeacherName("t0002").setProjectList(projectList));


        Example<Teacher> example = Example.of(new Teacher().setTeacherName("t0001"));
        Teacher teacher = teacherRepository.findOne(example).orElse(new Teacher());
        List<Project> projectList = teacher.getProjectList();
        System.out.println("==========================================");
        System.out.println(teacher.toString());
        projectList.forEach(project -> System.out.println(project.getStudentList().toString()));
        System.out.println("==========================================");
    }


}
