package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;
import com.university.academicRegistrationSystem.model.mapper.CourseMapper;
import com.university.academicRegistrationSystem.model.mapper.SubjectMapper;
import com.university.academicRegistrationSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        Course course = CourseMapper.toBO(courseDto);
        if(!courseDto.getSubjects().isEmpty()){
            course.setSubjects(courseDto.getSubjects().stream().map(SubjectMapper::toBO).collect(Collectors.toList()));
            course.getSubjects().forEach(subject -> subject.setCourse(course));
        }
        return CourseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(CourseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent())
            return optionalCourse.map(CourseMapper::toDto);
        return Optional.empty();
    }

    @Override
    public Optional<CourseDto> editCourse(Long id, CourseDto courseDto){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent())
            return Optional.of(CourseMapper.toDto( courseRepository.save(CourseMapper.toBO(courseDto)) ));
        return Optional.empty();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.findById(id)
                .ifPresentOrElse(
                        course -> courseRepository.delete(course),
                        () -> {throw new RuntimeException("The course to delete by id does not exist");}
                );
    }

}
