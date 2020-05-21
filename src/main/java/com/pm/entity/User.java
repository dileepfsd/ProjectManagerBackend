package com.pm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @SequenceGenerator(name = "userSeqGen", sequenceName = "userSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "userSeqGen")
    private long userId;
    private String firstName;
    private String lastName;
    private long employeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TASK_ID")
    private Task task;
}
