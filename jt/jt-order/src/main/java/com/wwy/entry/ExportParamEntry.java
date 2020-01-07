package com.wwy.entry;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel("文件上传")
@Data
public class ExportParamEntry {
	@ApiModelProperty("id")
private List<Long> ids;

}
