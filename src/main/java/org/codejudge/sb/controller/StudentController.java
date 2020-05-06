package org.codejudge.sb.controller;

import org.codejudge.sb.error.exception.CustomException;
import org.codejudge.sb.model.StudentDto;
import org.codejudge.sb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/subjects/all/")
    @ResponseBody
    public ResponseEntity getPaginatedStudents(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) throws CustomException {
        return new ResponseEntity<>(studentService.getPaginatedStudents(limit, offset), HttpStatus.OK);
    }

    @GetMapping("/subjects/filter/")
    @ResponseBody
    public ResponseEntity getFilteredPaginatedStudents(@RequestParam(required = false) Integer limit,
                                                       @RequestParam(required = false) Integer offset,
                                                       @RequestParam(value = "student_name_starts_with", required = false) String startsWith) throws CustomException {
        if (StringUtils.isEmpty(startsWith)) {
            throw new CustomException("empty prefix!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentService.getFilteredPaginatedStudents(limit, offset, startsWith), HttpStatus.OK);
    }
}
