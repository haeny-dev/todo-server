package com.haeny.todoserver.domain.step;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStepRepository extends JpaRepository<TaskStep, Long> {
}
