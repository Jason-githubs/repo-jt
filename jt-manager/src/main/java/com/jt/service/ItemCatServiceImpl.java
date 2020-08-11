package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
//快捷键 alt + shift + p     alt + 回车
//快捷键 动态生成get/set等方法   alt + insert
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 根据id查询显示商品类目
	 */
	@Override
	public ItemCat findItemCatById(Long itemCatId) {
		
		//利用MP机制查询数据库数据.
		return itemCatMapper.selectById(itemCatId);
	}

	/**
	 * 根据parentId查询显示树形商品目录信息
	 * 业务思路:
	 * 1.用户传递的数据parentId
	 * 2.可以查询List<ItemCat>数据库对象信息
	 * 3.动态将ItemCat对象转化为EasyUITree对象
	 * 4.返回值要求 返回List<EasyUITree> 对象
	 */
	@Override
	public List<EasyUITree> findItemCatListByParentId(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id",parentId);
		List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
		List<EasyUITree> treeList = new ArrayList<>();//先准备一个空集合
		//需要将数据一个一个的格式转化
		for (ItemCat itemCat:itemCatList) {
			Long id = itemCat.getId();	//获取ID
			String text = itemCat.getName();	//获取文本
			//如果是父级,则默认应该处于关闭状态closed,如果不是父级,则应该处于打开状态open
			String state = itemCat.getIsParent()?"closed":"open";
			//利用构造方法,为VO对象赋值	至此,已经实现了数据的转化
			EasyUITree tree = new EasyUITree(id,text,state);
			treeList.add(tree);
		}
		//用户需要返回List<EasyUITree>
		return treeList;
	}



}
