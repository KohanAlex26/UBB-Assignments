package com.atlasoftware.cstudent.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ProfessorDao extends AbstractUserDao {
    private ProfessorTitle title;
    @Column(name = "personal_website")
    private String personalWebsite;
    @OneToMany(mappedBy = "professorDao", cascade = CascadeType.ALL)
    @Column(name = "course_activites")
    private List<CourseActivityDao> courseActivities;
}
