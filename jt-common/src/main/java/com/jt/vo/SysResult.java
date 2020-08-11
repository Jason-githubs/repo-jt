package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 该VO对象是系统返回值VO对象,主要包含3个属性
 * 1.设定状态码  200表示执行成功   201执行失败  人为定义的(和浏览器没关系)业务定义.
 * 2.定义返回值信息    服务器可能会给用户一些提示信息.例如:执行成功,用户名或密码错误等
 * 3.定义返回值对象    服务器在后端处理完成业务之后,将对象返回给前端
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

    private Integer status;     //200成功     201失败
    private String  msg;        //服务器提示信息
    private Object  data;       //服务器返回前端的业务数据

    /**
     * 为了简化用户的调用过程,准备了一些工具API
     * 用户的关注点:  1.执行成功  2.执行失败
     */

    public static SysResult fail(){

        return new SysResult(201,"业务执行失败",null);
    }

    public static SysResult success(){//只标识成功!不携带数据

        return new SysResult(200,"业务执行成功",null);
    }

    //注意事项:写工具API方法时切记方法重载千万不要耦合
    public static SysResult success(Object data){//成功之后返回业务数据

        return new SysResult(200,"业务执行成功",data);
    }

    public static SysResult success(String msg,Object data){

        return new SysResult(200,msg,data);
    }

}
