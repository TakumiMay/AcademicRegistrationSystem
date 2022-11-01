package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;
import com.university.academicRegistrationSystem.model.mapper.SubjectMapper;
import com.university.academicRegistrationSystem.repository.CourseRepository;
import com.university.academicRegistrationSystem.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTests {

    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    private Course course;
    private Subject subject;

    @BeforeEach
    public void setUp() {
        this.course = new Course(1L, "courseName", List.of("program1", "program2"));
        this.subject = new Subject(1L, "subject1", "LUN - MIE 9:00AM", "professor1", 4);
        this.subject.setCourse(course);
    }

    @Test
    public void shouldAddSubject() {
        when(subjectRepository.save(subject)).thenReturn(subject);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Optional<SubjectDto> subjectDto = subjectService.addSubject(1L, SubjectMapper.toDto(subject));

        assertThat(subjectDto).isPresent();
        assertThat(subjectDto.get().getId()).isEqualTo(1L);
        assertThat(subjectDto.get().getName()).isEqualTo("subject1");
    }

    @Test
    public void shouldNotAddSubject() {
        SubjectDto subjectDto = new SubjectDto(1L, null, null, null, 0);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        assertThatThrownBy(() -> {
            subjectService.addSubject(1L, subjectDto);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The subject fields can not be null");
    }

        @Test
    public void shouldGetAllByCourse() {
        Subject subject2 = new Subject(2L, "subject2", "MAR - JUE 2:00PM", "professor2", 3);
        Subject subject3 = new Subject(3L, "subject3", "MIE - VIE 11:00AM", "professor3", 3);
        List<Subject> subjects = List.of(subject, subject2, subject3);

        when(subjectRepository.findByCourseId(1L)).thenReturn(subjects);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        List<SubjectDto> subjectDtoList = subjectService.getAllByCourse(1L).get();

        assertThat(subjectDtoList).isNotNull();
        assertThat(subjectDtoList.size()).isEqualTo(3);
        assertThat(subjectDtoList.get(0).getName()).isEqualTo("subject1");
        assertThat(subjectDtoList.get(1).getName()).isEqualTo("subject2");
        assertThat(subjectDtoList.get(2).getName()).isEqualTo("subject3");
    }

    @Test
    public void shouldGetAllEmptyByCourse() {
        List<Subject> subjects = List.of();

        when(subjectRepository.findByCourseId(1L)).thenReturn(subjects);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        List<SubjectDto> subjectDtoList = subjectService.getAllByCourse(1L).get();

        assertThat(subjectDtoList).isNotNull();
        assertThat(subjectDtoList.size()).isEqualTo(0);
    }

    @Test
    public void shouldGetSubjectById() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of(subject));

        Optional<SubjectDto> optionalSubjectDto = subjectService.getSubjectById(1L, 1L);

        assertThat(optionalSubjectDto).isPresent();
        assertThat(optionalSubjectDto.get().getId()).isEqualTo(1L);
        assertThat(optionalSubjectDto.get().getName()).isEqualTo("subject1");
    }

    @Test
    public void shouldNotGetSubjectByIdForWrongCourseId() {
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of());

        assertThatThrownBy(() -> {
            subjectService.getSubjectById(1L, 1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The subject to does not belong to course");
    }

    @Test
    public void shouldNotGetSubjectByIdForWrongId() {
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of(subject));
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<SubjectDto> optionalSubjectDto = subjectService.getSubjectById(1L, 1L);

        assertThat(optionalSubjectDto).isEmpty();
    }

    @Test
    public void shouldEditSubject() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of(subject));

        Optional<SubjectDto> optionalSubjectDto = subjectService.editSubject(1L, 1L, SubjectMapper.toDto(subject));

        assertThat(optionalSubjectDto).isPresent();
        assertThat(optionalSubjectDto.get().getName()).isEqualTo("subject1");
    }

    @Test
    public void shouldNotEditSubjectForWrongCourseId() {
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of());

        assertThatThrownBy(() -> {
            subjectService.editSubject(1L, 1L, SubjectMapper.toDto(subject));
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The subject to does not belong to course");
    }

    @Test
    public void shouldDeleteSubject() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of(subject));

        subjectService.deleteSubject(1L, 1L);

        verify(subjectRepository, times(1)).delete(subject);
    }

    @Test
    public void shouldNotDeleteSubjectForWrongCourseId() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of());

        assertThatThrownBy(() -> {
            subjectService.deleteSubject(1L, 1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The subject to does not belong to course");
    }

    @Test
    public void shouldNotDeleteSubjectForWrongId() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            subjectService.deleteSubject(1L, 1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The subject to delete does not exist");
    }

}
