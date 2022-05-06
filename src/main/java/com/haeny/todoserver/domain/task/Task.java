package com.haeny.todoserver.domain.task;

import com.haeny.todoserver.domain.BaseTimeEntity;
import com.haeny.todoserver.domain.step.TaskStep;
import com.haeny.todoserver.domain.tasklist.TaskList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Task extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    @OneToMany(mappedBy = "task")
    private List<TaskStep> steps = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    /* 연관관계 메서드 */
    private void addTaskToTaskList(TaskList taskList) {
        this.taskList = taskList;
        taskList.getTasks().add(this);
    }

    /* 생성 메서드 */
    @Builder
    public Task(String name, TaskList taskList, TaskStatus status) {
        this.name = name;
        this.status = status;
        addTaskToTaskList(taskList);
    }
}
