package com.yq.passwordmanager.controller;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "csr")
public class CsrController {
    @GetMapping
    public CsrfToken getCsrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }
}
