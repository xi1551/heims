package com.hospital.service;

import com.hospital.entity.Nurse;

import java.util.Map;

public interface NurseService {

    Map<String, Object> findByPage(String name, Long departmentId, int page, int pageSize);

    Nurse findById(Long id);

    int insert(Nurse nurse);

    int update(Nurse nurse);

    int deleteById(Long id);
}
