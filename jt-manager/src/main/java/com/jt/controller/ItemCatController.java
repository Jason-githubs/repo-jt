package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController		//该Controller的返回值都是JSON
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 1.url:"/item/cat/queryItemName"
	 * 2.请求参数:data:{itemCatId:val}
	 * 3.返回值结果:返回商品分类的名称
	 */
	
	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		
		ItemCat itemCat = itemCatService.findItemCatById(itemCatId);
		return itemCat.getName();
	}
	
	
	/**
	 * 业务需求:	查询一级商品分类信息
	 * Sql语句:	SELECT * FROM tb_item_cat WHERE parent_id=0;
	 * url地址:	/item/cat/list
	 * 返回值:	List<EasyUITree>
	 *
	 * 实现异步树加载:
	 * 说明:当展开一个封闭的节点,才会发起id的参数请求.前提条件是树必须先初始化
	 * 		应该先展现一级商品分类信息.
	 * 判断依据:id是否为null,如果为null则表示第一次查询,需要初始化id,查询一级商品分类目录
	 */
	@RequestMapping("/list")
	public List<EasyUITree> findItemCatList(Long id){
		
		Long parentId= (id==null?0L:id);	//根据parentId=0 查询一级商品分类信息
		return itemCatService.findItemCatListByParentId(parentId);
		
	}
	
	
	
	
	
	
	
	
	
	
}
