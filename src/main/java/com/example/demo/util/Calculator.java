package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Calculator {

    private int num1;
    private int num2;

    public int multiply(int num1, int num2){
        return num1 + num2;
    }
}
