package com.gk.system.web;

import com.gk.common.annotation.RequiresPermission;
import com.gk.common.tools.R;
import com.gk.common.validator.AssertUtils;
import com.gk.system.dto.SysDeptDTO;
import com.gk.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 *
 * @author Lowen
 */
@RestController
@RequestMapping("/sys/dept")
@Tag(name = "部门管理")
@AllArgsConstructor
public class SysDeptController {
    private final SysDeptService sysDeptService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermission("sys:dept:list")
    public R<?> list() {
        List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));
        return R.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermission("sys:dept:info")
    public R<?> get(@PathVariable("id") Long id) {
        SysDeptDTO data = sysDeptService.get(id);
        return R.ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @RequiresPermission("sys:dept:save")
    public R<?> save(@RequestBody SysDeptDTO dto) {
        //效验数据
        sysDeptService.save(dto);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @RequiresPermission("sys:dept:update")
    public R<?> update(@RequestBody SysDeptDTO dto) {
        //效验数据
        sysDeptService.update(dto);
        return R.ok();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @RequiresPermission("sys:dept:delete")
    public R<?> delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");
        sysDeptService.delete(id);
        return R.ok();
    }

}
