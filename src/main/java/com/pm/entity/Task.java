package com.pm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @SequenceGenerator(name = "taskSeqGen", sequenceName = "taskSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "taskSeqGen")
    private long taskId;

    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private String status;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PARENT_TASK_ID")
    private ParentTask parentTask;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Transient
    private long userId;

    @Transient
    private long projectId;

    @Transient
    private long parentTaskId;

    @Transient
    private String userName;
}
