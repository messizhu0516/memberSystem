/**  
 * Project Name:memberSystem  
 * File Name:Test.java  
 * Package Name:com.zhuqifeng.test  
 * Date:2018年7月25日下午2:29:30  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.test;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年7月25日 下午2:29:30 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
public class Test {

	public static void main(String[] args) {
		int[] arr = { 6, 3, 8, 2, 9, 1 };
		System.out.println("排序前数组为：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		for (int i = 0; i < arr.length - 1; i++) {// 外层循环控制排序趟数
			for (int j = 0; j < arr.length - 1 - i; j++) {// 内层循环控制每一趟排序多少次
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		System.out.println("排序后的数组为：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}
}
