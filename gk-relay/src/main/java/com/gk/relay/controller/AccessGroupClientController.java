package com.gk.relay.controller;

import com.gk.common.annotation.LogOperation;
import com.gk.common.annotation.RequestMap;
import com.gk.common.constant.Constant;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.validator.AssertUtils;
import com.gk.relay.dto.GroupClientDTO;
import com.gk.relay.service.GroupClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
* Relay授权组客户关系表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@RestController
@RequestMapping("relay/accessgroupclient")
@Tag(name = "Relay授权组客户关系表")
@RequiredArgsConstructor
public class AccessGroupClientController {
    private final GroupClientService accessGroupClientService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @PreAuthorize("hasAuthority('relay:accessgroupclient:page')")
    public R<?> page(@Parameter(hidden = true) @RequestMap DataMap params){
        PageData<GroupClientDTO> page = accessGroupClientService.page(params);
        return R.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('relay:accessgroupclient:save')")
    public R<?> save(@RequestBody GroupClientDTO dto){
        accessGroupClientService.save(dto);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @PreAuthorize("hasAuthority('relay:accessgroupclient:update')")
    public R<?> update(@RequestBody GroupClientDTO dto){
        accessGroupClientService.update(dto);
        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('relay:accessgroupclient:delete')")
    public R<?> delete(@RequestParam Long id){
        //效验数据
        AssertUtils.isNull(id, "id");
        accessGroupClientService.deleteById(id);
        return R.ok();
    }
}