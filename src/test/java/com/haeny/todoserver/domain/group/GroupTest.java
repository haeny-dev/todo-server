package com.haeny.todoserver.domain.group;

import com.haeny.todoserver.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GroupTest {

    @Test
    void 그룹_생성시_유저에도_추가() {
        // Given
        User user = User.builder()
                .email("haeny@gmail.com")
                .name("haeny")
                .password("1234")
                .build();

        // When
        TaskGroup taskGroup = TaskGroup.builder()
                .user(user)
                .name("group")
                .build();

        // Then
        assertThat(user.getGroups()).containsExactly(taskGroup);
        assertThat(user.getGroups().get(0).getName()).isEqualTo(taskGroup.getName());
    }

}