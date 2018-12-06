/**  
 * Project Name:memberSystem  
 * File Name:a.java  
 * Package Name:com.zhuqifeng.test  
 * Date:2018年7月23日下午3:16:12  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/  
  
package com.zhuqifeng.test;  
/**  
 * ClassName:a <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2018年7月23日 下午3:16:12 <br/>  
 * @author   QiFeng.Zhu  
 * @version    1.0
 * @since    JDK 1.8  
 * @see        
 */
public interface JDK8Interface1 {

    //1.接口中可以定义静态方法了
    public static void staticMethod(){
        System.out.println("接口中的静态方法");
    }
    
    //2.使用default之后就可以定义普通方法的方法体了
    public default void defaultMethod(){
        System.out.println("接口中的默认方法");
    }
}
  
