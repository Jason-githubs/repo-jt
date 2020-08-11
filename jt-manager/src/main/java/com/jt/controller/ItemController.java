package com.jt.controller;


import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;

import javax.servlet.http.HttpServletRequest;

@RestController		//返回值数据都是JSON串.
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 业务: 根据分页要求展现商品列表.要求将最新最热门的商品首先给用户展现.
	 * url: url:'/item/query
	 * 参数: page=1&rows=20   page当前页    rows 记录数
	 * 返回值: EasyUITable
	 * 编码习惯: mapper-service-controller-页面js   手敲  自下而上的开发
	 * 课堂代码格式:
	 * 			url-controller-service-mapper  结构代码自动生成  自上而下开发
	 * 			
	 */

	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page,Integer rows) {
		//1.调用业务层,获取商品分页信息
		return itemService.findItemByPage(page, rows);
	}

	/**
	 * 1.url地址:http://localhost:8091/item/save
	 * 2.请求参数:整个form表单
	 * 3.返回值结果:SysResult对象
	 */
	@RequestMapping("/save")
	public SysResult saveItem(Item item, ItemDesc itemDesc){
		itemService.saveItem(item,itemDesc);
		return SysResult.success();
	}
		/*try {
			itemService.saveItem(item);
			return SysResult.success();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}*/

	/**
	 * 完成商品信息修改
	 * url:http://localhost:8091/item/update
	 * 参数:整个商品表单
	 * 返回值:SysResult对象
	 */
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc){

		itemService.updateItem(item,itemDesc);
		return  SysResult.success();
	}

	/**
	 * 完成商品信息删除
	 * url:http://localhost:8091/item/delete
	 * 参数:ids=id1,id2...
	 *	返回值结果:SysResult对象
	 * SpringMvc知识点:可以根据指定的类型动态的实现参数类型的转化.
	 * 	如果字符串使用","号分隔,则可以使用数组的方式接参
	 */
	@RequestMapping("/delete")
	public SysResult deleteItems(Long[] ids){

		itemService.deleteItems(ids);
		return SysResult.success();

	}

	/**
	 * 利用restFul方式实现状态修改
	 * 1./item/reshelf	上架	status=1
	 * 2./item/instock	下架	status=2
	 * 修改js  /item/1	/item/2
	 */
	@RequestMapping("/{status}")
	public SysResult updateStatus(@PathVariable Integer status,Long[] ids){

		itemService.updateStatus(ids,status);
		return SysResult.success();
	}

	/**
	 * 业务:
	 * url地址:http://localhost:8091/item/query/item/desc/1474391973
	 * 参数:包含在url中,利用restFul方式动态获取
	 * 返回值:SysResult对象
	 */
	@RequestMapping("/query/item/desc/{itemId}")
	public  SysResult findItemDescById(@PathVariable Long itemId){

		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		//将服务器数据返回页面
		return	SysResult.success(itemDesc);
	}
}
