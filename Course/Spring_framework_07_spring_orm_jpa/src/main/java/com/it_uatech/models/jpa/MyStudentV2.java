package com.it_uatech.models.jpa;

import com.it_uatech.models.jpa.common.Avatar;
import com.it_uatech.models.jpa.common.Course;
import com.it_uatech.models.jpa.common.EMail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "my_students")
@NamedEntityGraph(name = "MyStudentWithAvatarAndEmails",  attributeNodes = {@NamedAttributeNode(value = "avatar"), @NamedAttributeNode(value = "emails")})
public class MyStudentV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToMany(targetEntity = EMail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<EMail> emails;

    //@BatchSize(size = 5)
    //@Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
