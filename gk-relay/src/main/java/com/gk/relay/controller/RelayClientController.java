package com.gk.relay.controller;

import com.gk.common.annotation.RequestMap;
import com.gk.common.constant.Constant;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.validator.AssertUtils;
import com.gk.relay.dto.RelayClientDTO;
import com.gk.relay.service.RelayClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;


/**
* Relay客户表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-28
*/
@RestController
@RequestMapping("relay/client")
@Tag(name = "Relay客户表")
@RequiredArgsConstructor
public class RelayClientController {
    private final RelayClientService relayClientService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @PreAuthorize("hasAuthority('relay:client:page')")
    public R<?> page(@Parameter(hidden = true) @RequestMap DataMap params){
        PageData<RelayClientDTO> page = relayClientService.page(params);
        return R.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('relay:client:save')")
    public R<?> save(@RequestBody RelayClientDTO dto){
        relayClientService.save(dto);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
//    @LogOperation("修改")
    @PreAuthorize("hasAuthority('relay:client:update')")
    public R<?> update(@RequestBody RelayClientDTO dto){
        relayClientService.update(dto);
        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('relay:client:delete')")
    public R<?> delete(@RequestParam Long id){
        //效验数据
        AssertUtils.isNull(id, "id");
        relayClientService.deleteById(id);
        return R.ok();
    }


}