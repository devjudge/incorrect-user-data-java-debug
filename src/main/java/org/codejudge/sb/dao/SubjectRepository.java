package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "select * from subject where id in (:ids)", nativeQuery = true)
    List<Subject> getByIds(@Param("ids") List<Integer> ids);
}
