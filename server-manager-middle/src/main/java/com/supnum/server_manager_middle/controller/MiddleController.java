package com.supnum.server_manager_middle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supnum.server_manager_middle.soap.ServerSoapClient;

@RestController
@RequestMapping("/api/servers")
public class MiddleController {

    private final ServerSoapClient soapClient;

    public MiddleController(ServerSoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @GetMapping
    public String getServers() {
        return "REST → SOAP communication works";
    }
}
