import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import com.zhuqifeng.commons.utils.excel.ExcelReadHelper;

/**  
 * Project Name:memberSystem  
 * File Name:BindcardTest.java  
 * Package Name:  
 * Date:2019年1月8日  上午11:22:54  
 * Copyright (c) 2019, zz621@126.com All Rights Reserved.   
 */

/**
 * ClassName:BindcardTest </br>
 * Function: TODO ADD FUNCTION. </br>
 * Reason: TODO ADD REASON. </br>
 * Date: 2019年1月8日 上午11:22:54
 *
 * @author James Zhu
 * @version 3.8
 * @since JDK 1.7
 * @see
 */
public class BindcardTest {

	private List<XY> xyList;
	private List<ALL> allList;

	@Before
	public void before() {
		xyList = new LinkedList<XY>();
		allList = new LinkedList<ALL>();
		String filePath = "G:\\协议绑卡2.xls";
		String[] properties = { "id", "acct_name", "account", "mobile" };
		try {
			// 处理协议绑卡数据
			List<Object> list = ExcelReadHelper.excelRead(filePath, properties, XY.class);
			this.add(xyList, list);

			// 所有数据
			filePath = "G:\\所有.xls";
			String[] properties2 = { "id", "user_id", "acct_name", "account", "mobile" };
			list = ExcelReadHelper.excelRead(filePath, properties2, ALL.class);
			this.add(allList, list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private <T> void add(List<T> addList, List<Object> list) {
		if (list != null && list.size() > 0) {
			for (Object object : list) {
				T xy = (T) object;
				addList.add(xy);
			}
		}
	}

	@Test
	public void test() {
		if (xyList.size() > 0 && allList.size() > 0) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (XY xy : xyList) {
				String account = xy.getAccount();
				String acct_name = xy.getAcct_name();
				String mobile = xy.getMobile();
				String id = xy.getId();
				for (ALL xyall : allList) {
					String accountall = xyall.getAccount();
					String acct_nameall = xyall.getAcct_name();
					String mobileall = xyall.getMobile();
					String user_id = xyall.getUser_id();
					if (account.equals(accountall) && acct_name.equals(acct_nameall) && mobile.equals(mobileall) && user_id != null) {
						StringBuffer sb = new StringBuffer();
						sb.append("update account_bank set user_id =");
						sb.append(user_id);
						sb.append(" where id=").append(id).append(" and user_id is null;");
						String string = sb.toString();
						if (!map.containsKey(id)) {
							map.put(id, string);
							System.out.println(string);
						}
					}
				}
			}
			System.out.println("所有数据条数：" + allList.size());
			System.out.println("协议绑卡user_id为空条数：" + xyList.size());
			System.out.println("总共比对成功条数：" + map.size());
		}
	}

	public static class XY {
		private String id;
		private String acct_name;
		private String account;
		private String mobile;

		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return "XY [id=" + id + ", acct_name=" + acct_name + ", account=" + account + ", mobile=" + mobile + "]";
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAcct_name() {
			return acct_name;
		}

		public void setAcct_name(String acct_name) {
			this.acct_name = acct_name;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

	}

	public static class ALL {
		private String id;
		private String acct_name;
		private String account;
		private String mobile;
		private String user_id;

		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return "XY [id=" + id + ", acct_name=" + acct_name + ", account=" + account + ", mobile=" + mobile + "]";
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAcct_name() {
			return acct_name;
		}

		public void setAcct_name(String acct_name) {
			this.acct_name = acct_name;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

	}
	
}
