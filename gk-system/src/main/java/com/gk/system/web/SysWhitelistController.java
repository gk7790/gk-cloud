package com.gk.system.web;


import com.gk.common.annotation.RequestMap;
import com.gk.common.annotation.RequiresPermission;
import com.gk.common.constant.Constant;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.validator.AssertUtils;
import com.gk.system.dto.SysWhitelistDTO;
import com.gk.system.service.SysWhitelistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 白名单
 *
 * @author stark system@gmail.com
 * @since 1.0.0 2019-05-08
 */
@RestController
@RequestMapping("sys/whitelist")
@Tag(name = "白名单(系统管理)")
@RequiredArgsConstructor
public class SysWhitelistController {
    private final SysWhitelistService sysWhitelistService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始",  in = ParameterIn.QUERY, required = true) ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true) ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY) ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY),
        @Parameter(name = "tenantId", description = "站点ID", in = ParameterIn.QUERY),
        @Parameter(name = "input", description = "输入值", in = ParameterIn.QUERY)
    })
    @RequiresPermission("sys:whitelist:page")
    public R<?> page(@Parameter(hidden = true) @RequestMap DataMap params){
        PageData<SysWhitelistDTO> page = sysWhitelistService.page(params);
        return R.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    @RequiresPermission("sys:whitelist:save")
    public R<?> save(@RequestBody SysWhitelistDTO dto){
        sysWhitelistService.save(dto);
        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @RequiresPermission("sys:whitelist:delete")
    public R<?> delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        sysWhitelistService.delete(ids);
        return R.ok();
    }

}