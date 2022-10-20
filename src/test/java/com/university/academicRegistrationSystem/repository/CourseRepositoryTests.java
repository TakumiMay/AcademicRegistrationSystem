package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;
    private Course course;

    @BeforeEach
    public void setUp(){
        course = new Course(
                1L, "courseName", new String[]{"p1", "p2"});
    }

    @Test
    @DisplayName("JUnit test for save Course operation")
    public void givenCourse_whenSave_thenReturnSavedCourse() {
        //Given - precondition or set up

        //When - action or the behaviour that we are going to test
        Course savedCourse = courseRepository.save(course);

        //Then - verify the output
        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("JUnit test for find all Courses operation")
    public void givenCourses_whenFindAll_thenReturnAllCourses(){
        Course course2 = new Course(1L, "courseName", new String[]{"p1", "p2"});
        courseRepository.save(course);
        courseRepository.save(course2);

        List<Course> courses = courseRepository.findAll();

        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for find a Course operation")
    public void givenCourse_whenFindById_thenReturnCourse(){
        courseRepository.save(course);

        Course courseDB = courseRepository.findById(1L).get();

        assertThat(courseDB).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for edit a Course operation")
    public void givenCourse_whenEdit_thenReturnUpdatedCourse() {
        courseRepository.save(course);

        Course savedCourse = courseRepository.findById(course.getId()).get();
        savedCourse.setCourseName("The courseName");
        Course editedCourse = courseRepository.save(savedCourse);

        assertThat(editedCourse.getCourseName()).isEqualTo("The courseName");
        assertThat(editedCourse.getId()).isEqualTo(1L);
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
