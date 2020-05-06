package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student", nativeQuery = true)
    List<Student> getPaginatedStudents();

    @Query(value = "select * from student where name like CONCAT(:prefix, '%')", nativeQuery = true)
    List<Student> getFilteredPaginatedStudents(@Param("prefix") String prefix);
}
