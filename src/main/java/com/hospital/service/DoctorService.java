package com.hospital.service;

import com.hospital.entity.Doctor;

import java.util.Map;

public interface DoctorService {

    Map<String, Object> findByPage(String name, Long departmentId, int page, int pageSize);

    Doctor findById(Long id);

    int insert(Doctor doctor);

    int update(Doctor doctor);

    int deleteById(Long id);
}
