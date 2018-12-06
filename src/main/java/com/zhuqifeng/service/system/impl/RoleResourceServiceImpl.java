package com.zhuqifeng.service.system.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.mapper.system.RoleResourceMapper;
import com.zhuqifeng.model.pojo.RoleResource;
import com.zhuqifeng.service.system.RoleResourceService;

@Service
@Transactional
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

}