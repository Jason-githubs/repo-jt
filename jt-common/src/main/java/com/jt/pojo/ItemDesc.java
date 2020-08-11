package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 构建商品详情的POJO对象
 */
@TableName("tb_item_desc")
@Data
@Accessors(chain = true)
public class ItemDesc extends BasePojo{

    @TableId                //只标识主键即可
    private Long itemId;    //要求与商品表Id保持一致.
    private String itemDesc;//商品详情信息

}
