package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.pojo.Item;

@Controller
public class IndexController {
	
	/**
	 * 面试问题:  你说说你是怎么用restFul的.
	 * 用法1:  可以用来动态的接收url中的参数.之后完成业务调用
	 * 方法2:  可以通过不同的请求类型来标识不同的业务需求.
	 * 
	 * 用法1: 动态获取url中的参数,简化了Controller中方法的个数.
	 * 需求:利用一个请求方法.实现页面通用跳转
	 * 页面url地址:
	 * 	/page/item-add
	 *  /page/item-list
	 *  /page/item-param-list'
	 * 思路: 只要能够获取url中的第二个参数就可以实现通用的页面跳转功能.
	 * 如何获取url中的参数?????
	 * 解决方案:  使用restFul风格
	 * 语法:
	 * 		1.参数与参数之间使用/分隔
	 * 		2.参数使用{}形式包裹
	 * 		3.定义参数使用特定的注解接收. @PathVariable
	 * 
	 * 
	 * 用法2
	 * 	说明:
	 * 		利用restFul风格,可以简化用户url的写法.
	 * 	例子:
	 * 		利用同一个请求http://localhost:8091/item  实现CRUD操作
	 * 	知识回顾:
	 * 		http://localhost:8091/item/save?xxxx
	 * 		http://localhost:8091/item/delete?xxxx
	 * 		http://localhost:8091/item/update?xxxx
	 *      http://localhost:8091/findItemxxxx
	 *      多个页面请求  
	 *      优化:
	 *      http://localhost:8091/item?xxxx  
	 *      type="GET"   查询操作
	 *      type="POST"  新增操作
	 *      type="PUT"   更新操作
	 *      type="DELETE"删除操作
	 */
	
	//需求:利用一个请求方法,实现页面通用跳转
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName) {
		
		return moduleName;
	}
	
	//  /page/item-add 跳转指定的页面路径
//	@RequestMapping("/page/item-add")
//	public String itemAdd() {
//		
//		return "item-add";
//	}
	
	/*
	//@RequestMapping(value="/item",method=RequestMethod.GET)
	@GetMapping("/item")
	public void getItem(Integer id) {
		//执行查询业务
	}
	
	//@RequestMapping(value="/item",method=RequestMethod.POST)
	@PostMapping("/item")
	public void saveItem(Item Item) {
		//执行新增业务
	}
	*/
}
