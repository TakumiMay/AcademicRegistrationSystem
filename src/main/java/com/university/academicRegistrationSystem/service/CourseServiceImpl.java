package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;
import com.university.academicRegistrationSystem.model.mapper.CourseMapper;
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
        course.getSubjects().forEach(subject -> subject.setCourse(course));
        return CourseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(CourseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        System.out.println("HOOLIII   "+optionalCourse.toString());
        if(optionalCourse.isPresent())
            return optionalCourse.map(CourseMapper::toDto);
        return Optional.ofNullable(null);
    }

}
