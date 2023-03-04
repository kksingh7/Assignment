package com.assignment.taskManager.modals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    // task title
    private String title;

    // Estimated date of completion
    private String eta;

    // Status of task
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

}

