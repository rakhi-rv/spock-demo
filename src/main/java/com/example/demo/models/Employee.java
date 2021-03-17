package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "designation is required")
    @Pattern(regexp = "Developer|Tester")
    private String designation;

}
