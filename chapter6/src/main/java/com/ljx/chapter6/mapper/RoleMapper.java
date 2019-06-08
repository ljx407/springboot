package com.ljx.chapter6.mapper;

import com.ljx.chapter6.model.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {
    @Select("select * from t_role where id = #{id}")
    Role findById(@Param("id") Long id);

    @Insert("insert into t_role(roleName) values (#{roleName})")
    int insertRole(Role role);

    @Insert("insert into t_role(id,roleName) values (#{id},#{roleName})")
    int insertRoleWithIdAndName(@Param("id") Long id, @Param("roleName") String roleName);

    @Delete("delete from t_role where id = #{id}")
    int deleteRoleById(@Param("id") Long id);
}
