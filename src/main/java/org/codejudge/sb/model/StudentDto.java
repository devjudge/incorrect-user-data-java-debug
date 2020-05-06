package org.codejudge.sb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.entity.Subject;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentDto {

    private Integer id;
    private String email;
    private List<Subject> subjects;

    public StudentDto(StudentDtoBuilder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.subjects = builder.subjects;
    }

    public static class StudentDtoBuilder {

        private Integer id;
        private String email;
        private List<Subject> subjects;

        public StudentDtoBuilder(Integer id, String email, List<Subject> subjects) {
            this.id = id;
            this.email = email;
            this.subjects = subjects;
        }

        public StudentDto build() {
            return new StudentDto(this);
        }
    }
}
