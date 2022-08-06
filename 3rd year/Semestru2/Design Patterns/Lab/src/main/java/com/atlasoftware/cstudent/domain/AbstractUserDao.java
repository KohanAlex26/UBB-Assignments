package com.atlasoftware.cstudent.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractUserDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private MyUserType userType;
    private String password;
    private String email;
}
