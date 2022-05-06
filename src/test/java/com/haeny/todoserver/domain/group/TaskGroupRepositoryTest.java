package com.haeny.todoserver.domain.group;

import com.haeny.todoserver.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class TaskGroupRepositoryTest {

    @Autowired
    TaskGroupRepository groupsRepository;

    @Test
    void 그룹생성_조회하기() {
        // Given
        User user = User.builder()
                .name("haeny")
                .email("haeny@gmail.com")
                .password("1234")
                .build();

        String name = "2022년";

        groupsRepository.save(TaskGroup.builder()
                .user(user)
                .name(name)
                .build());

        // When
        List<TaskGroup> groups = groupsRepository.findAll();

        // Then
        TaskGroup group = groups.get(0);
        assertThat(groups.size()).isGreaterThan(1);
        assertThat(group.getName()).isEqualTo(name);
        assertThat(group.getUser()).isEqualTo(user);
    }

}