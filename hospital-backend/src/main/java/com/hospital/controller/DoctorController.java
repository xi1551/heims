package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public Result<Map<String, Object>> findByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> result = doctorService.findByPage(name, departmentId, page, pageSize);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Doctor> findById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        if (doctor == null) {
            return Result.error("医生不存在");
        }
        return Result.success(doctor);
    }

    @PostMapping
    public Result<Void> insert(@RequestBody Doctor doctor) {
        doctorService.insert(doctor);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Doctor doctor) {
        doctorService.update(doctor);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        doctorService.deleteById(id);
        return Result.success();
    }
}
