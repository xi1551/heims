package com.hospital.service.impl;

import com.hospital.entity.Department;
import com.hospital.mapper.DepartmentMapper;
import com.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentMapper.findById(id);
    }

    @Override
    public int insert(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int update(Department department) {
        return departmentMapper.update(department);
    }

    @Override
    public int deleteById(Long id) {
        return departmentMapper.deleteById(id);
    }
}
