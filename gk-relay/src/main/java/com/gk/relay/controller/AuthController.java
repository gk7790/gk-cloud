package com.gk.relay.controller;

import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.tools.Result;
import com.gk.relay.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* relay客户表
*
* @author lowen lowen@gmail.com
* @since 3.0 2026-04-28
*/
@RestController
@RequestMapping("auth/relay")
@Tag(name = "Relay客户表")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;

    @PostMapping("login")
    @Operation(summary = "登入")
    public R<?> login(@RequestBody DataMap body){
        String username = body.getStr("username");
        String password =  body.getStr("password");
        Result<String> result = clientService.login(username, password);
        if (result.isFail()){
            return R.error(result.getMsg());
        }
        return R.ok(result.getData());
    }





}