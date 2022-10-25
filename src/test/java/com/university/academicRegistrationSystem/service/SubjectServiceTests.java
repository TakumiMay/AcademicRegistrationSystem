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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTests {

    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    private Subject subject;

    @BeforeEach
    public void setUp() {
        this.subject = new Subject(1L, "subject1", "LUN - MIE 9:00AM", "professor1", 4);
    }

    @Test
    public void shouldAddSubject() {
        Course course = new Course(1L, "courseName", new String[]{"program1", "program2"});

        when(subjectRepository.save(subject)).thenReturn(subject);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Optional<SubjectDto> subjectDto = subjectService.addSubject(1L, SubjectMapper.toDto(subject));

        assertThat(subjectDto).isPresent();
        assertThat(subjectDto.get().getId()).isEqualTo(1L);
        assertThat(subjectDto.get().getName()).isEqualTo("subject1");
    }

    @Test
    public void shouldNotAddSubject() {
        Subject subject2 = new Subject();

        Optional<SubjectDto> subjectDto = subjectService.addSubject(1L, SubjectMapper.toDto(subject2));

        assertThat(subjectDto).isEmpty();
    }

        @Test
    public void shouldGetAllByCourse() {
        Subject subject2 = new Subject(2L, "subject2", "MAR - JUE 2:00PM", "professor2", 3);
        Subject subject3 = new Subject(3L, "subject3", "MIE - VIE 11:00AM", "professor3", 3);
        List<Subject> subjects = List.of(subject, subject2, subject3);

        when(subjectRepository.findByCourseId(1L)).thenReturn(subjects);
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
    public void shouldNotGetSubjectById() {
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of());

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
    public void shouldDeleteSubject() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.findByCourseId(1L)).thenReturn(List.of(subject));

        boolean deleted = subjectService.deleteSubject(1L, 1L);

        assertThat(deleted).isTrue();
    }

}
