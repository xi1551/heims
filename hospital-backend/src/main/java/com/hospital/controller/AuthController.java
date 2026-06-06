package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import com.hospital.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return Result.error("用户名和密码不能为空");
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!password.equals(user.getPassword())) {
            return Result.error("密码错误");
        }

        String token = jwtUtil.generateToken(username);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("username", username);
        data.put("role", user.getRole());

        return Result.success(data);
    }
}
