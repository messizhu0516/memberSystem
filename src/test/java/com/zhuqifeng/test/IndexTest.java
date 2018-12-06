package com.zhuqifeng.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.zhuqifeng.commons.utils.net.HttpClientUtil;
import com.zhuqifeng.test.base.BaseTest;

/**
 * ClassName: IndexTest <br/>
 * Function: TODO <br/>
 * Reason: TODO <br/>
 * date: 2018年5月31日 下午2:35:04 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
public class IndexTest  {

//	@Test
	// @Ignore
	/*public void index() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML)).andDo(MockMvcResultHandlers.print()).andReturn();
	}*/
	
	@Test
	public void t() {
		Map<String,String> m = new HashMap<String, String>();
		m.put("checked","29");m.put("csrfmiddlewaretoken","6sFBpMtxwZK7TQ2K6uw5s63hcLwq49tB");
		String sendHttpPost = HttpClientUtil.sendHttpPost("https://zjbdtech.zhangyingjinfu.com/cateVote1/", m);
		System.out.println(sendHttpPost);
	}
}
