package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;
import com.university.academicRegistrationSystem.model.mapper.CourseMapper;
import com.university.academicRegistrationSystem.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void shouldAddCourse() {
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));

        when(courseRepository.save(course)).thenReturn(course);

        CourseDto courseDto = courseService.addCourse(CourseMapper.toDto(course));

        assertThat(courseDto).isNotNull();
        assertThat(courseDto.getId()).isEqualTo(1L);
        assertThat(courseDto.getCourseName()).isEqualTo("courseName");
    }

    @Test
    public void shouldNotAddCourse() {
        Course course = new Course();

        when(courseRepository.save(course)).thenReturn(course);

        CourseDto courseDto = courseService.addCourse(CourseMapper.toDto(course));

        assertThat(courseDto.getId()).isNull();
        assertThat(courseDto.getCourseName()).isNull();
    }

    @Test
    public void shouldGetAllCourses() {
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));
        Course course2 = new Course(2L, "courseName2", List.of("program2", "program3"));
        List<Course> courses = List.of(course, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<CourseDto> courseDtoList = courseService.getAll();

        assertThat(courseDtoList).isNotNull();
        assertThat(courseDtoList.size()).isEqualTo(2);
    }

    @Test
    public void shouldNotGetAllCourses() {
        List<Course> courses = new ArrayList<>();

        when(courseRepository.findAll()).thenReturn(courses);

        List<CourseDto> courseDtoList = courseService.getAll();

        assertThat(courseDtoList).isNotNull();
        assertThat(courseDtoList.size()).isEqualTo(0);
    }

    @Test
    public void shouldGetCourseById() {
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Optional<CourseDto> response = courseService.getCourseById(1L);

        assertThat(response).isPresent();
        assertThat(response.get().getCourseName()).isEqualTo("courseName");
    }

    @Test
    public void shouldNotGetCourseById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CourseDto> response = courseService.getCourseById(1L);

        assertThat(response).isEmpty();
    }

    @Test
    public void shouldEditCourse() {
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);

        Optional<CourseDto> optionalCourseDto = courseService.editCourse(1L, CourseMapper.toDto(course));

        assertThat(optionalCourseDto).isPresent();
        assertThat(optionalCourseDto.get().getCourseName()).isEqualTo("courseName");
    }

    @Test
    public void shouldNotEditCourse() {
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));

        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CourseDto> optionalCourseDto = courseService.editCourse(1L, CourseMapper.toDto(course));

        assertThat(optionalCourseDto).isEmpty();
    }

    @Test
    public void shouldDeleteCourse() {
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        courseService.deleteCourse(1L);

        verify(courseRepository, times(1)).delete(course);
    }

    @Test
    public void shouldNotDeleteCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
                courseService.deleteCourse(1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The course to delete by id does not exist");
    }

}
