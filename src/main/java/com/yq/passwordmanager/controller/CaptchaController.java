package com.yq.passwordmanager.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.util.StrUtil;
import com.yq.passwordmanager.model.Result;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @GetMapping(value = "/{identifiedCode}", produces = MediaType.IMAGE_PNG_VALUE)
    public void getCaptcha(HttpServletResponse response, HttpSession session, @PathVariable String identifiedCode) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(120, 45, 4, 4);
        log.info("identifiedCode:{}", identifiedCode);
// 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator(1));
// 重新生成code
        captcha.createCode();
        log.info("identifieCode:{}", identifiedCode);
        session.setAttribute(("captcha_" + identifiedCode), captcha.getCode());
        session.setMaxInactiveInterval(300);

        captcha.write(response.getOutputStream());
        response.getOutputStream().flush();
    }

    @PostMapping
    public Result<String> validateCaptcha(@RequestParam("code") String code, @RequestParam("identifiedCode") String identifiedCode, HttpSession session) {
        MathGenerator mathGenerator = new MathGenerator();
        String correctCode = String.valueOf(session.getAttribute(("captcha_" + identifiedCode)));
        log.info("validateCaptcha code:{}", correctCode);
        if (mathGenerator.verify(correctCode, code)) {
            return Result.success(null, "一致", null);
        } else {
            return Result.failure(null, "不一致", null);
        }
    }
}
