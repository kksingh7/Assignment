package com.assignment.taskManager.modals;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_update_log")
public class TaskUpdateLog {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @ManyToOne(targetEntity = Task.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
    private String updated_timestamp;
    private String updated_fields;
}
