package org.codejudge.sb.service;

import org.codejudge.sb.dao.StudentRepository;
import org.codejudge.sb.entity.Student;
import org.codejudge.sb.entity.StudentSubject;
import org.codejudge.sb.entity.Subject;
import org.codejudge.sb.error.exception.CustomException;
import org.codejudge.sb.model.StudentDto;
import org.codejudge.sb.model.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentSubjectService studentSubjectService;

    public StudentResponseDto getPaginatedStudents(Integer limit, Integer offset) throws CustomException {
        if (limit == null || offset == null ) {
            throw new CustomException("Limit and offset can't be empty", HttpStatus.BAD_REQUEST);
        }
        List<Student> students = studentRepo.getPaginatedStudents();
        return createResponse(students, limit, offset);
    }

    private StudentResponseDto createResponse(List<Student> students, Integer limit, Integer offset) throws CustomException {
        if (CollectionUtils.isEmpty(students)) {
            throw new CustomException("No students found!", HttpStatus.NOT_FOUND);
        }
        List<Integer> studentIds = students.stream().map(Student::getId).collect(Collectors.toList());

        Map<Integer, List<Integer>> studentSubjectIdsMap = new HashMap<>();
        Set<Integer> subjectIds = new HashSet<>();
        List<StudentSubject> studentSubjects = studentSubjectService.getAllMappingsByStudentId(studentIds);
        studentSubjects = studentSubjects.subList(offset, offset + limit);
        for (StudentSubject studentSubject : studentSubjects) {
            subjectIds.add(studentSubject.getSubjectId());
            List<Integer> studentSubjectIds = studentSubjectIdsMap.get(studentSubject.getStudentId());
            if (CollectionUtils.isEmpty(studentSubjectIds)) {
                studentSubjectIds = new ArrayList<>();
                studentSubjectIds.add(studentSubject.getSubjectId());
                studentSubjectIdsMap.put(studentSubject.getStudentId(), studentSubjectIds);
            }
            else {
                studentSubjectIds.add(studentSubject.getSubjectId());
                studentSubjectIdsMap.replace(studentSubject.getStudentId(), studentSubjectIds);
            }
        }
        List<Subject> subjects = subjectService.getSubjectsById(new ArrayList<>(subjectIds));
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            if (!CollectionUtils.isEmpty(studentSubjectIdsMap.get(student.getId()))) {
                List<Subject> subjectsForStudent = subjects.stream().filter(subject -> studentSubjectIdsMap.get(student.getId()).contains(subject.getId())).collect(Collectors.toList());
                studentDtos.add(new StudentDto.StudentDtoBuilder(student.getId(), student.getEmail(), subjectsForStudent).build());
            }
        }
        return new StudentResponseDto.StudentResponseDtoBuilder(studentDtos).build();
    }

    public StudentResponseDto getFilteredPaginatedStudents(Integer limit, Integer offset, String startsWith) throws CustomException {
        if (limit == null || offset == null ) {
            throw new CustomException("Limit and offset can't be empty", HttpStatus.BAD_REQUEST);
        }
        List<Student> students = studentRepo.getFilteredPaginatedStudents(startsWith);
        return createResponse(students, limit, offset);
    }
}
