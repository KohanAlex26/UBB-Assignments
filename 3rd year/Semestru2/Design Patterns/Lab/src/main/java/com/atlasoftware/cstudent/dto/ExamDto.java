package com.atlasoftware.cstudent.dto;


import com.atlasoftware.cstudent.domain.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamDto {
    private String id;
    private String date;
    private String courseCode;
    private String examType;
    private String formula;
    private String courseName;
}
