package com.hospital.service.impl;

import com.hospital.entity.Doctor;
import com.hospital.mapper.DoctorMapper;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Map<String, Object> findByPage(String name, Long departmentId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Doctor> list = doctorMapper.findByPage(name, departmentId, offset, pageSize);
        int total = doctorMapper.count(name, departmentId);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    @Override
    public Doctor findById(Long id) {
        return doctorMapper.findById(id);
    }

    @Override
    public int insert(Doctor doctor) {
        return doctorMapper.insert(doctor);
    }

    @Override
    public int update(Doctor doctor) {
        return doctorMapper.update(doctor);
    }

    @Override
    public int deleteById(Long id) {
        return doctorMapper.deleteById(id);
    }
}
