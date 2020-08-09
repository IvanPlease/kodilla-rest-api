package com.crud.tasks.domain;

import com.crud.tasks.view.TaskView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class TaskDto {
    @JsonView(TaskView.Public.class)
    private Long id;
    @JsonView(TaskView.Public.class)
    private String title;
    @JsonView(TaskView.Internal.class)
    private String content;
}
