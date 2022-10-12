package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;
import com.university.academicRegistrationSystem.model.mapper.SubjectMapper;
import com.university.academicRegistrationSystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectDto addSubject(SubjectDto subjectDto){
        Subject subject = SubjectMapper.toBO(subjectDto);
        subject.getCourse().getSubjects().add(subject);
        subject.getStudents().forEach(student -> student.getSubjects().add(subject));
        return SubjectMapper.toDto(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectDto> getAllByCourse(Long id) {
        return subjectRepository.findByCourseId(id).stream().map(SubjectMapper::toDto).collect(Collectors.toList());
    }

}
