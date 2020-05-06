package org.codejudge.sb.service;

import org.codejudge.sb.dao.StudentSubjectRepository;
import org.codejudge.sb.entity.StudentSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSubjectService {

    @Autowired
    private StudentSubjectRepository studentSubjectRepo;

    public List<StudentSubject> getAllMappingsByStudentId(List<Integer> studentIds) {
        List<StudentSubject> studentSubjects = studentSubjectRepo.findByStudentIds(studentIds);
        return studentSubjects;
    }
}
