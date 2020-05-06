package org.codejudge.sb.dao;

import org.codejudge.sb.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {

    @Query(value = "select * from student_subject where student_id in (:ids)", nativeQuery = true)
    public List<StudentSubject> findByStudentIds(@Param("ids") List<Integer> studentIds);
}
