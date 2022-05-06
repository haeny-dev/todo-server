package com.haeny.todoserver.domain.step;

import com.haeny.todoserver.domain.BaseTimeEntity;
import com.haeny.todoserver.domain.task.Task;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TaskStep extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_step_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Enumerated(EnumType.STRING)
    private TaskStepStatus status;

    /* 연관관계 메서드 */
    private void addTaskStepToTask(Task task) {
        this.task = task;
        task.getSteps().add(this);
    }

    /* 생성 메서드 */
    @Builder
    public TaskStep(String content, Task task, TaskStepStatus status) {
        this.content = content;
        this.status = status;
        addTaskStepToTask(task);
    }
}
