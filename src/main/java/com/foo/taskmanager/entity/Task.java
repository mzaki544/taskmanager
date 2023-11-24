package com.foo.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
   @Id
    private Long id;
    private String title;
    private boolean status;

}
