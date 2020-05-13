package club.byteyun.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @ClassName QueryRequest
 * @Description //TODO
 * @Date 11:31 2020/5/13
 * @Author lql
 **/
@Data
public class QueryRequest implements Serializable
{
    private static final long serialVersionUID = -4869594085374385813L;
    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
