package com.pm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParentTask {
    @Id
    @SequenceGenerator(name = "parentSeqGen", sequenceName = "parentSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "parentSeqGen")
    private long id;
    private String parentTaskName;
}
