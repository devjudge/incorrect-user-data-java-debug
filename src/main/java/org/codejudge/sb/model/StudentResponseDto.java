package org.codejudge.sb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentResponseDto {

    private List<StudentDto> results;

    public StudentResponseDto(StudentResponseDtoBuilder builder) {
        this.results = builder.results;
    }

    public static class StudentResponseDtoBuilder {

        private List<StudentDto> results;

        public StudentResponseDtoBuilder(List<StudentDto> results) {
            this.results = results;
        }

        public StudentResponseDto build() {
            return new StudentResponseDto(this);
        }
    }
}
