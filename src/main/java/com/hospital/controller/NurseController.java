package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Nurse;
import com.hospital.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @GetMapping
    public Result<Map<String, Object>> findByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> result = nurseService.findByPage(name, departmentId, page, pageSize);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Nurse> findById(@PathVariable Long id) {
        Nurse nurse = nurseService.findById(id);
        if (nurse == null) {
            return Result.error("护士不存在");
        }
        return Result.success(nurse);
    }

    @PostMapping
    public Result<Void> insert(@RequestBody Nurse nurse) {
        nurseService.insert(nurse);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Nurse nurse) {
        nurseService.update(nurse);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        nurseService.deleteById(id);
        return Result.success();
    }
}
