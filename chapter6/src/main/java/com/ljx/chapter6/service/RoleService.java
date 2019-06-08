package com.ljx.chapter6.service;

import com.ljx.chapter6.model.Role;

import java.util.List;

public interface RoleService {

    Role findById(Long id);

    int insertRole(Role role);

    int batchInsertRole(List<Role> roleList);

    int deleteRoleById(Long id);
}
