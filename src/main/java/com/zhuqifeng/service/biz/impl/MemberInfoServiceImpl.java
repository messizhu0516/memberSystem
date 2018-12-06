package com.zhuqifeng.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuqifeng.mapper.biz.MemberInfoMapper;
import com.zhuqifeng.mapper.system.UserMapper;
import com.zhuqifeng.model.pojo.MemberInfo;
import com.zhuqifeng.model.pojo.User;
import com.zhuqifeng.model.vo.MemberVO;
import com.zhuqifeng.service.biz.MemberInfoService;

/**
 * <p>
 * 会员信息 服务实现类
 * </p>
 *
 * @author QiFeng.Zhu
 * @since 2018-06-20
 */
@Service
@Transactional
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoMapper, MemberInfo> implements MemberInfoService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MemberInfoMapper memberInfoMapper;

	@Override
	public void registerMember(MemberVO memberVO) {
		MemberInfo memberInfo = memberVO.getMemberInfo();
		User user = memberVO.getUser();
		this.userMapper.insert(user);
		this.memberInfoMapper.insert(memberInfo);
	}

}
