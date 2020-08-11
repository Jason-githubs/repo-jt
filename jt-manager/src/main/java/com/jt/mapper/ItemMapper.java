package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;

public interface ItemMapper extends BaseMapper<Item>{
	
	//利用注解执行sql.
	@Select("select * from tb_item order by updated desc limit #{startIndex},#{rows}")
	List<Item> findItemByPage(Integer startIndex, Integer rows);

	/**
	 * 问题:为什么Mybatis需要将参数封装为Map?
	 * 答案:Mybatis规定 一般的参数只能进行单值传参,不能多值传参
	 * 		但是有时业务需要必须进行多值传递,那么这时需要将多值,封装为单值
	 * 		为了解决多值传参的问题,则Mybatis提供了@Param注解,其作用将参数封装为Map集合.
	 *
	 * @Param("ids")Long[] ids 	将参数封装为Map集合,其中@Params中的ids当做key,参数当做value
	 *
	 * @param ids
	 */
   //void deleteItems(@Param("ids") Long[] ids);
	void deleteItems(Long[] ids);

	//mybatis需要将多值转化为单值.	自动完成封装 封装为单值 @Param() (可写可不写)
	void updateStatus(@Param("ids")Long[] ids,Integer status);
}
