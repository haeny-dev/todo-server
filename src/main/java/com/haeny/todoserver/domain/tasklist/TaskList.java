package com.haeny.todoserver.domain.tasklist;

import com.haeny.todoserver.domain.BaseTimeEntity;
import com.haeny.todoserver.domain.group.TaskGroup;
import com.haeny.todoserver.domain.task.Task;
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
public class TaskList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_list_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_group_id")
    private TaskGroup taskGroup;

    @OneToMany(mappedBy = "taskList")
    private List<Task> tasks = new ArrayList<>();

    /* 연관관계 메서드 */
    private void addTaskListToTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
        taskGroup.getTaskLists().add(this);
    }

    /* 생성 메서드 */
    @Builder
    public TaskList(String name, TaskGroup taskGroup) {
        this.name = name;
        addTaskListToTaskGroup(taskGroup);
    }
}
