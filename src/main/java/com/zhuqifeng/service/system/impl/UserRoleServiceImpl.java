package com.zhuqifeng.service.system.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.mapper.system.UserRoleMapper;
import com.zhuqifeng.model.pojo.UserRole;
import com.zhuqifeng.service.system.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}