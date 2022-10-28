package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("integration-test")
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;
    private Course course;

    @BeforeEach
    public void setUp() {
        courseRepository.deleteAll();
        course = new Course(null, "courseName", List.of("program1", "program2"));
    }

    @Test
    @DisplayName("JUnit test for save Course operation")
    public void givenCourse_whenSave_thenReturnSavedCourse() {
        //Given - precondition or set up

        //When - action or the behaviour that we are going to test
        Course savedCourse = courseRepository.save(course);

        //Then - verify the output
        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getId()).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for find all Courses operation")
    public void givenCourses_whenFindAll_thenReturnAllCourses(){
        Course course2 = new Course(null, "courseName", List.of("program1", "program2"));
        List<Course> list = new ArrayList<>();
        list.add(this.course);
        list.add(course2);
        courseRepository.saveAll(list);

        List<Course> courses = courseRepository.findAll();

        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for find a Course operation")
    public void givenCourse_whenFindById_thenReturnCourse(){
        courseRepository.save(course);

        Course courseDB = courseRepository.findById(course.getId()).get();

        assertThat(courseDB).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for edit a Course operation")
    public void givenCourse_whenEdit_thenReturnUpdatedCourse() {
        courseRepository.save(course);

        Course savedCourse = courseRepository.findById(course.getId()).get();
        savedCourse.setPrograms(new ArrayList(Arrays.asList("diff program1", "diff program2", "diff program3")));
        Course editedCourse = courseRepository.save(savedCourse);

        assertThat(editedCourse.getCourseName()).isEqualTo("courseName");
        assertThat(editedCourse.getPrograms().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("JUnit test for delete a Course operation")
    public void givenCourse_whenDelete_thenRemoveCourse(){
        courseRepository.save(course);

        courseRepository.delete(course);
        Optional<Course> optionalCourse = courseRepository.findById(course.getId());

        assertThat(optionalCourse).isEmpty();
    }

}
