package com.hospital.service.impl;

import com.hospital.entity.Nurse;
import com.hospital.mapper.NurseMapper;
import com.hospital.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseMapper nurseMapper;

    @Override
    public Map<String, Object> findByPage(String name, Long departmentId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Nurse> list = nurseMapper.findByPage(name, departmentId, offset, pageSize);
        int total = nurseMapper.count(name, departmentId);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    @Override
    public Nurse findById(Long id) {
        return nurseMapper.findById(id);
    }

    @Override
    public int insert(Nurse nurse) {
        return nurseMapper.insert(nurse);
    }

    @Override
    public int update(Nurse nurse) {
        return nurseMapper.update(nurse);
    }

    @Override
    public int deleteById(Long id) {
        return nurseMapper.deleteById(id);
    }
}
