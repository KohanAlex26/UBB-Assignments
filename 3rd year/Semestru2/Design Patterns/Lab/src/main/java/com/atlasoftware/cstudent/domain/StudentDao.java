package com.atlasoftware.cstudent.domain;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDao extends AbstractUserDao {
    @Column(name = "\"group\"")
    private Integer group;
    private Integer registrationNumber;
    private Boolean semigroup;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private LocationDao locationDao;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomDao roomDao;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "student_enrolled_courses",
        joinColumns = @JoinColumn(name = "studentId"),
        inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private List<CourseDao> courses;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_activity_id")
    private List<CourseActivityDao> timeTable;
    private String section;

    public StudentDao(Integer group, Integer registrationNumber, Boolean semigroup, LocationDao locationDao, RoomDao roomDao, String section) {
    }

    @Override
    public String toString() {
        return "StudentDao{" +
                "group=" + group +
                ", registrationNumber=" + registrationNumber +
                ", semigroup=" + semigroup +
                ", locationDao=" + locationDao +
                ", roomDao=" + roomDao +
                ", timeTable=" + timeTable +
                ", section='" + section + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDao)) return false;
        if (!super.equals(o)) return false;
        StudentDao that = (StudentDao) o;
        return Objects.equals(getGroup(), that.getGroup()) && Objects.equals(getRegistrationNumber(), that.getRegistrationNumber()) && Objects.equals(getSemigroup(), that.getSemigroup()) && Objects.equals(getLocationDao(), that.getLocationDao()) && Objects.equals(getRoomDao(), that.getRoomDao()) && Objects.equals(getTimeTable(), that.getTimeTable()) && Objects.equals(getSection(), that.getSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGroup(), getRegistrationNumber(), getSemigroup(), getLocationDao(), getRoomDao(), getTimeTable(), getSection());
    }

    public void addToTimeTable(List<CourseActivityDao> courseActivities) {
        timeTable.addAll(courseActivities);
    }



    public void update()
    {
        System.out.println("News uploaded!");
    }

//    Boolean subscribed;
//
//    public void subscribe()
//    {
//        subscribed=Boolean.TRUE;
//    }

}
