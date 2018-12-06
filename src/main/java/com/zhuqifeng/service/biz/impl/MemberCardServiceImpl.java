package com.zhuqifeng.service.biz.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.mapper.biz.MemberCardMapper;
import com.zhuqifeng.model.pojo.MemberCard;
import com.zhuqifeng.service.biz.MemberCardService;

/**
 * <p>
 * 会员卡 服务实现类
 * </p>
 *
 * @author QiFeng.Zhu
 * @since 2018-06-20
 */
@Service
public class MemberCardServiceImpl extends ServiceImpl<MemberCardMapper, MemberCard> implements MemberCardService {

}
