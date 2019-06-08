package com.ljx.chapter6.service.impl;

import com.ljx.chapter6.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RoleBatchServiceImpl  {

    @Autowired
    private RoleServiceImpl roleService;

    @Transactional
    public void batchInsert(List<Role> roleList) {
        for (Role role : roleList) {
            roleService.insertRoleWithPropagatoinNewThrowExceptionForSpeicifyCount(role,2);
        }
    }

}
