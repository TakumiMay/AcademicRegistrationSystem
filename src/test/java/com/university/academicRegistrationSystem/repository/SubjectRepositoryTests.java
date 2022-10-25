package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.domain.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SubjectRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;
    private Course savedCourse;
    private Subject subject;

    @BeforeEach
    public void setUp(){
        Course course = new Course(null, "courseName", new String[]{"program1", "program2"});
        savedCourse = courseRepository.save(course);
        subject = new Subject(null, "subject1", "LUN - MIE 9:00AM", "professor1", 4);
        subject.setCourse(savedCourse);
    }

    @Test
    @DisplayName("JUnit test for save Subject in Course operation")
    public void givenSubjectAndCourse_whenSave_thenReturnSavedSubject() {
        //Given - precondition or set up

        //When - action or the behaviour that we are going to test
        Subject savedSubject = subjectRepository.save(subject);

        //Then - verify the output
        assertThat(savedSubject).isNotNull();
        assertThat(savedSubject.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("JUnit test for find all Subjects for a course operation")
    public void givenSubjects_whenFindAllByCourseId_thenReturnSubjects() {
        Subject subject2 = new Subject(null, "subject2", "MAR - JUE 9:00AM", "professor2", 4);
        subject2.setCourse(this.savedCourse);

        List<Subject> subjectsToSave = new ArrayList<>();
        subjectsToSave.add(this.subject);
        subjectsToSave.add(subject2);
        subjectRepository.saveAll(subjectsToSave);

        List<Subject> subjects = subjectRepository.findByCourseId(this.savedCourse.getId());

        assertThat(subjects).isNotNull();
        assertThat(subjects.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for find all by Course id and Subject id operation")
    public void givenSubject_whenFindByCourseIdAndSubjectId_thenReturnSubject() {
        subjectRepository.save(this.subject);

        List<Subject> subjects = subjectRepository.findByCourseId(this.savedCourse.getId());
        Optional<Subject> subjectSaved = subjectRepository.findById(1L);

        assertThat(subjects).isNotNull();
        assertThat(subjects).contains(this.subject);
    }

    @Test
    @DisplayName("JUnit test for edit a Subject operation")
    public void givenSubject_whenEdit_thenReturnUpdatedSubject() {
        subjectRepository.save(this.subject);

        Subject savedSubject = subjectRepository.findById(subject.getId()).get();
        savedSubject.setName("anotherName");
        Subject editedSubject = subjectRepository.save(savedSubject);

        assertThat(editedSubject.getName()).isEqualTo("anotherName");
        assertThat(editedSubject.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("JUnit test for delete a Subject operation")
    public void givenSubject_whenDelete_thenRemoveSubject() {
        subjectRepository.save(subject);

        subjectRepository.delete(subject);
        Optional<Subject> optionalSubject = subjectRepository.findById(subject.getId());

        assertThat(optionalSubject).isEmpty();
    }

}
