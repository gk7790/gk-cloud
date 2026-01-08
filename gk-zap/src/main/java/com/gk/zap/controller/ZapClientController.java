package com.gk.zap.controller;

import com.gk.common.annotation.RequestMap;
import com.gk.common.constant.Constant;
import com.gk.common.dto.LabelDTO;
import com.gk.common.exception.ErrorCode;
import com.gk.common.tools.AesCTR;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.validator.AssertUtils;
import com.gk.zap.dto.ZapClientDTO;
import com.gk.zap.service.ZapClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zap/client")
@Tag(name = "客户端")
@RequiredArgsConstructor
public class ZapClientController {
    private final ZapClientService zapClientService;

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
        return R.ok(zapClientService.page(params));
    }

    @GetMapping("dict")
    @Operation(summary = "列表")
    public R<List<LabelDTO>> list(@Parameter(hidden = true) @RequestMap DataMap params){
        List<ZapClientDTO> list = zapClientService.list(params);
        return R.ok(list.stream().map(item -> new LabelDTO(item.getId(), item.getName())).toList());
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:client:save')")
    public R<?> save(@RequestBody ZapClientDTO dto){
        zapClientService.add(dto);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:client:update')")
    public R<?> update(@RequestBody ZapClientDTO dto){
        //效验数据
        zapClientService.update(dto);
        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:client:delete')")
    public R<?> delete(@RequestParam Long id){
        //效验数据
        AssertUtils.isNull(id, "id");
        zapClientService.delete(id);
        return R.ok();
    }

    /**
     * 生成密钥
     * @param id 客户端id
     * @return 返回秘钥
     */
    @GetMapping("genSecret")
    @PreAuthorize("hasAuthority('sys:client:genSecret')")
    public R<?> genSecret(@RequestParam Long id) {
        if (id == null || id <= 100) {
            return R.error(ErrorCode.BAD_REQUEST);
        }
        AesCTR aesCTR = AesCTR.of();
        return R.ok(aesCTR.encrypt(id + ""));
    }


}
