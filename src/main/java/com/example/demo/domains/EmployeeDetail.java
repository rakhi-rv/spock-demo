package com.example.demo.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value = "employee_detail")
public class EmployeeDetail {

    @PrimaryKey(value = "employee_id")
    private Integer employeeId;

    @Column("name")
    private String name;

    @Column("designation")
    private String designation;

}
