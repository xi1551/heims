package com.hospital.mapper;

import com.hospital.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DoctorMapper {

    List<Doctor> findByPage(@Param("name") String name,
                            @Param("departmentId") Long departmentId,
                            @Param("offset") int offset,
                            @Param("limit") int limit);

    int count(@Param("name") String name,
              @Param("departmentId") Long departmentId);

    Doctor findById(Long id);

    int insert(Doctor doctor);

    int update(Doctor doctor);

    int deleteById(Long id);
}
