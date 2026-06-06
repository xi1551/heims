package com.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nurse {

    private Long id;

    private String jobNumber;

    private String name;

    private String gender;

    private String title;

    private Long departmentId;

    private String phone;
}
