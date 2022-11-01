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
        try {
            Subject subject = SubjectMapper.toBO(subjectDto);
            Optional<Course> course = courseRepository.findById(id);
            if(course.isPresent()) {
                subject.setCourse(course.get());
                course.get().getSubjects().add(subject);
                courseRepository.save(course.get());
                return Optional.of(SubjectMapper.toDto( subjectRepository.save(subject) ));
            }
            throw new RuntimeException("The course id does not exist");
        } catch (NullPointerException exception) {
            throw new RuntimeException("The subject fields can not be null");
        }
    }

    @Override
    public Optional<List<SubjectDto>> getAllByCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            return Optional.of(subjectRepository.findByCourseId(id).stream()
                    .map(SubjectMapper::toDto).collect(Collectors.toList()));
        }
        throw new RuntimeException("The course id does not exist");
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
    public void deleteSubject(Long id, Long subId) {
        subjectRepository.findById(subId)
                .ifPresentOrElse(
                        (subject) -> {
                            if(checkSubjectInCourse(id, subId))
                                subjectRepository.delete(subject);
                        },
                        () -> {
                            throw new RuntimeException("The subject to delete does not exist");
                        }
                );
    }

    public boolean checkSubjectInCourse(Long id, Long subId) {
        List<SubjectDto> subjects = subjectRepository.findByCourseId(id).stream()
                .map(SubjectMapper::toDto)
                .filter(subjectDto -> Objects.equals(subjectDto.getId(), subId))
                .collect(Collectors.toList());
        if(subjects.size()==1 && subjects.get(0).getId()==subId)
            return true;
        throw new RuntimeException("The subject to does not belong to course");
    }

}
