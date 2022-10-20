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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Optional<SubjectDto> addSubject(Long id, SubjectDto subjectDto){
        Subject subject = SubjectMapper.toBO(subjectDto);
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            subject.setCourse(course.get());
            course.get().getSubjects().add(subject);
            subjectRepository.save(subject);
            courseRepository.save(course.get());
            return Optional.of(subjectDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<SubjectDto>> getAllByCourse(Long id) {
        return Optional.of(subjectRepository.findByCourseId(id).stream().map(SubjectMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<SubjectDto> getSubjectById(Long id, Long subId) {
        if(checkSubjectInCourse(id, subId)) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subId);
            if (optionalSubject.isPresent())
                return optionalSubject.map(SubjectMapper::toDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<SubjectDto> editSubject(Long id, Long subId, SubjectDto subjectDto) {
        if(checkSubjectInCourse(id, subId)) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subId);
            if(optionalSubject.isPresent()){
                Subject subject = optionalSubject.get();
                subject.setName(subjectDto.getName());
                subject.setSchedule(subjectDto.getSchedule());
                subject.setProfessor(subjectDto.getProfessor());
                subject.setCredits(subjectDto.getCredits());
                subjectRepository.save(subject);
                return Optional.of(subjectDto);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteSubject(Long id, Long subId) {
        if(checkSubjectInCourse(id, subId)) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subId);
            subjectRepository.delete(optionalSubject.get());
            return true;
        }
        return false;
    }

    public boolean checkSubjectInCourse(Long id, Long subId) {//Usar filter o reduce
        List<SubjectDto> subjects = subjectRepository.findByCourseId(id).stream().map(SubjectMapper::toDto).collect(Collectors.toList());
        for (SubjectDto subject:subjects) {
            if(Objects.equals(subject.getId(), subId))
                return true;
        }
        return false;
    }

}
