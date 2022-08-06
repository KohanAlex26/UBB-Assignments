package com.atlasoftware.cstudent.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id",nullable = false)
    private LocationDao locationDao;
    private String name;
    @OneToMany(mappedBy = "roomDao", cascade = CascadeType.ALL)
    private List<CourseActivityDao> courseActivities;
    @OneToMany(mappedBy = "roomDao", cascade = CascadeType.ALL)
    private List<StudentDao> students;
}
