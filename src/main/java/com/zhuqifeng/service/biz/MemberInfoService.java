package com.zhuqifeng.service.biz;

import com.baomidou.mybatisplus.service.IService;
import com.zhuqifeng.model.pojo.MemberInfo;
import com.zhuqifeng.model.vo.MemberVO;

/**
 * <p>
 * 会员信息 服务类
 * </p>
 *
 * @author QiFeng.Zhu
 * @since 2018-06-20
 */
public interface MemberInfoService extends IService<MemberInfo> {

	void registerMember(MemberVO memberVO);

}
