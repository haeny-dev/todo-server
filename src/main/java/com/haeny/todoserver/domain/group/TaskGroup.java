package com.haeny.todoserver.domain.group;

import com.haeny.todoserver.domain.BaseTimeEntity;
import com.haeny.todoserver.domain.tasklist.TaskList;
import com.haeny.todoserver.domain.user.User;
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
public class TaskGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_group_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "taskGroup")
    private List<TaskList> taskLists = new ArrayList<>();

    /* 연관관계 메서드 */
    private void addTaskGroupToUser(User user) {
        this.user = user;
        user.getTaskGroups().add(this);
    }

    /* 생성 메서드 */
    @Builder
    public TaskGroup(String name, User user) {
        this.name = name;
        addTaskGroupToUser(user);
    }
}
