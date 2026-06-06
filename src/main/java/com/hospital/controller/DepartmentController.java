package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Department;
import com.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Result<List<Department>> findAll() {
        List<Department> list = departmentService.findAll();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Department> findById(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return Result.error("科室不存在");
        }
        return Result.success(department);
    }

    @PostMapping
    public Result<Void> insert(@RequestBody Department department) {
        departmentService.insert(department);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Department department) {
        departmentService.update(department);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        departmentService.deleteById(id);
        return Result.success();
    }
}
