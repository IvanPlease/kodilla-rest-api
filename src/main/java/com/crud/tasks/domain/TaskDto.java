package com.crud.tasks.domain;

import com.crud.tasks.view.TaskView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDto {
    @JsonView(TaskView.Public.class)
    private Long id;
    @JsonView(TaskView.Public.class)
    private String title;
    @JsonView(TaskView.Internal.class)
    private String content;
}
