package org.codejudge.sb.service;

import org.codejudge.sb.dao.SubjectRepository;
import org.codejudge.sb.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepo;

    public List<Subject> getSubjectsById(List<Integer> ids) {
        List<Subject> subjects = subjectRepo.getByIds(ids);
        return subjects;
    }
}
