package com.example.secureconfigclient.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public final class DomainController {

    private final transient DomainService service;

    public DomainController(@Autowired final DomainService service) {
        this.service = service;
    }

    @GetMapping("/domain")
    @ResponseBody
    public DomainResponse getDomain() {
        return service.serve();
    }


}
