/**  
 * Project Name:memberSystem  
 * File Name:A.java  
 * Package Name:com.zhuqifeng.test  
 * Date:2018年7月27日下午1:59:24  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/  
  
package com.zhuqifeng.test;  
/**  
 * ClassName:A <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2018年7月27日 下午1:59:24 <br/>  
 * @author   QiFeng.Zhu  
 * @version    1.0
 * @since    JDK 1.8  
 * @see        
 */
public class A implements Cloneable{
	private String a;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

	@Override
	public String toString() {
		return a;
	}
	
	
}
  
