package com.haeny.todoserver.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<TaskGroup, Long> {
}
