package com.hospital.mapper;

import com.hospital.entity.Nurse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NurseMapper {

    List<Nurse> findByPage(@Param("name") String name,
                           @Param("departmentId") Long departmentId,
                           @Param("offset") int offset,
                           @Param("limit") int limit);

    int count(@Param("name") String name,
              @Param("departmentId") Long departmentId);

    Nurse findById(Long id);

    int insert(Nurse nurse);

    int update(Nurse nurse);

    int deleteById(Long id);
}
