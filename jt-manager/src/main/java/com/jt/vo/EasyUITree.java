package com.jt.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree implements Serializable{
	//如果对象涉及到网络传输,就必须序列化或反序列化
	private static final long serialVersionUID = 1L;
	
	//{"id":"1","text":"王者荣耀","state":"closed/open"}
	private Long id;		//数据库中的类型是Long
	private String text;	//定义节点名称
	private String state;	//控制节点 打开/关闭
}
