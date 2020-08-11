package com.jt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemDescMapper itemDescMapper;
	
	//方式二:
	//利用MP方式进行分页查询
	//Page参数问题: 参数1:第几页  参数2:size 每页多少条
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		QueryWrapper<Item> queryWrapper = new QueryWrapper<Item>();
		queryWrapper.orderByDesc("updated");
		IPage<Item> iPage = new Page<>(page, rows);
		//根据分页模型执行分页操作,并将结果返回给分页对象
		iPage = itemMapper.selectPage(iPage, queryWrapper);
		
		Long total = iPage.getTotal();	//由分页工具动态获取
		List<Item> itemList = iPage.getRecords();	//获取当前分页的信息
		return new EasyUITable(total,itemList);
	}

	/**
	 * 执行步骤:1.手动编辑sql    2.利用MP机制 动态生成
	 * SELECT * FROM tb_item LIMIT 起始位置,查询记录数
	/*第一页 java中数组运算 一般都是含头不含尾
	  SELECT * FROM tb_item LIMIT 0,20;
	/*第二页
	  SELECT * FROM tb_item LIMIT 20,20;
	/*第三页
	  SELECT * FROM tb_item LIMIT 40,20;
	*第N页
	  SELECT * FROM tb_item LIMIT (n-1)ROWS,ROWS;
	 */
	
	/** 方式一:
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//1.参数1: 记录总数		参数2: rows 当前页的记录数
		long total = itemMapper.selectCount(null);
		int startIndex = (page - 1) * rows;
		List<Item> itemList = itemMapper.findItemByPage(startIndex,rows);
		
		return new EasyUITable(total,itemList);
	}
	*/

	/**
	 * 实现商品信息的入库操作
	 * 入库之前需要提前将数据补全.	刚新增的商品应该处于上架状态1
	 * @param item
	 * 注意事项:完成数据库更新操作时,需要注意数据库事务问题
	 *
	 * 完成商品表同时入库
	 * 难点:如何保证商品ID和详情的ID一致?
	 * <insert id="" useGeneratedKeys="true" keyProperty="id" keyColumn="id"></insert>
	 */
	@Override
	@Transactional
	public void saveItem(Item item, ItemDesc itemDesc) {
		//保证入库的时间一致
		item.setStatus(1);
		//item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		//分析问题:item表的主键是自增	数据库入库之后才会有主键生成.
		//解决方案:让数据库完成入库之后自动的实现主键的回显.该操作由MP方式动态完成
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	@Transactional
	public void updateItem(Item item,ItemDesc itemDesc) {

		//item.setUpdated(new Date());
		//根据对象中不为null的元素充当set条件.主键充当where条件
		itemMapper.updateById(item);
		//实现商品详情更新
		itemDesc.setItemId(item.getId());//设定主键
		itemDescMapper.updateById(itemDesc);
	}

	/**
	 * 1.利用MP方式完成
	 * 2.利用Sql方式动态拼接完成
	 * 利用批量执行的方式进行操作
	 * sql:delete from tb_item where id in(id1,id2...)
	 */
	@Override
	@Transactional
	public void deleteItems(Long[] ids) {

		//方式1:将数组转化为list集合
		//List<Long> idList = Arrays.asList(ids);
		//itemMapper.deleteBatchIds(idList);

		//方式2:利用手写sql完成
		itemMapper.deleteItems(ids);
		//同时删除商品详情信息
		itemDescMapper.deleteBatchIds(Arrays.asList(ids));
	}

	/**
	 * 上架/下架
	 * @param ids
	 * @param status
	 */
	@Override
	public void updateStatus(Long[] ids, Integer status) {
		/* 方式1:
		//1.利用MP方式执行数据库操作
		Item item = new Item();
		item.setStatus(status);
		//2.定义修改操作的条件构造器	where id in();
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		List<Long> idList = Arrays.asList(ids);//数组转化为集合
		updateWrapper.in("id",idList);
		//3.根据MP机制实现批量的数据更新操作
		itemMapper.update(item,updateWrapper);
		*/

		/** 方式2:利用sql方式进行操作
		 * 	sql:update tb_item set status = #{status},updated=now() where id in(id...);
		 */
		itemMapper.updateStatus(ids,status);

	}

	/**
	 * 根据指定的ID查询商品详情信息
	 * @param itemId
	 * @return
	 */
	@Override
	public ItemDesc findItemDescById(Long itemId) {

		return itemDescMapper.selectById(itemId);
	}
}
