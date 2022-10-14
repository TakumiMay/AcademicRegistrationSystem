package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;
import com.university.academicRegistrationSystem.model.mapper.SubjectMapper;
import com.university.academicRegistrationSystem.repository.CourseRepository;
import com.university.academicRegistrationSystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override //TO DO
    public Optional<SubjectDto> addSubject(Long id, SubjectDto subjectDto){
        Subject subject = SubjectMapper.toBO(subjectDto);
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            subject.setCourse(course.get());
            course.get().getSubjects().add(subject);
            return Optional.of(subjectDto);
        }
        return Optional.empty();
    }

    @Override
    public List<SubjectDto> getAllByCourse(Long id) {
        return subjectRepository.findByCourseId(id).stream().map(SubjectMapper::toDto).collect(Collectors.toList());
    }

    @Override// TO DO CHECK IF SUBJECT IS FROM COURSE
    public Optional<SubjectDto> getSubjectById(Long id, Long subId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subId);
        if (optionalSubject.isPresent())
            return optionalSubject.map(SubjectMapper::toDto);
        return Optional.empty();
    }

    @Override
    public Optional<SubjectDto> editSubject(Long id, SubjectDto subjectDto) {
        //TO DO
        return null;
    }

    @Override //TO DO CHECK IF SUBJECT IS FROM COURSE
    public boolean deleteSubject(Long id, Long subId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subId);
        if(optionalSubject.isPresent()) {
            subjectRepository.delete(optionalSubject.get());
            return true;
        }
        return false;
    }

}
