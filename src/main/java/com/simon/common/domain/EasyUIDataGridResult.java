package com.simon.common.domain;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI DataGrid数据格式
 *
 * @author simon
 * @date 2018-10-27
 **/
@ApiModel(description = "EasyUI DataGrid数据格式")
@Data
@EqualsAndHashCode(callSuper = false)
public class EasyUIDataGridResult<T> implements Serializable {
    private static final long serialVersionUID = -1778687339119859732L;

    @ApiModelProperty(value = "记录总数")
    private Long total;

    @ApiModelProperty(value = "记录列表")
    private List<T> rows;

    public EasyUIDataGridResult(){

    }

    public EasyUIDataGridResult(Long total, List<T> rows){
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDataGridResult(PageInfo<T> pageInfo){
        this.total = pageInfo.getTotal();
        this.rows = pageInfo.getList();
    }
}
