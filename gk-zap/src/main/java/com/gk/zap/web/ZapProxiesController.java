package com.gk.zap.web;

import com.gk.common.annotation.RequestMap;
import com.gk.common.constant.Constant;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.validator.AssertUtils;
import com.gk.zap.dto.ZapProxiesDTO;
import com.gk.zap.service.ZapProxiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zap/proxies")
@Tag(name = "隧道")
@AllArgsConstructor
public class ZapProxiesController {
    private final ZapProxiesService zapProxiesService;


    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true) ,
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true) ,
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY) ,
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY) ,
            @Parameter(name = "paramCode", description = "参数编码", in = ParameterIn.QUERY)
    })
    public R<?> page(@Parameter(hidden = true) @RequestMap DataMap params){
        PageData<ZapProxiesDTO> page = zapProxiesService.page(params);
        return R.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    // @RequiresPermission("sys:dict:save")
    public R<?> save(@RequestBody ZapProxiesDTO dto){
        AssertUtils.isNull(dto.getClientId(), "ClientId");
        zapProxiesService.add(dto);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    // @RequiresPermission("sys:dict:update")
    public R<?> update(@RequestBody ZapProxiesDTO dto){
        //效验数据
        zapProxiesService.update(dto);
        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public R<?> delete(@RequestParam Long id){
        //效验数据
        AssertUtils.isNull(id, "id");
        zapProxiesService.delete(id);
        return R.ok();
    }


}
