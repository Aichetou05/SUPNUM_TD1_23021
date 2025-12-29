package com.supnum.server_manager_middle.controller;

import com.supnum.server_manager_middle.clients.ConsumateurClient;
import com.supnum.server_manager_middle.dto.GetServerStatusResponse;
import com.supnum.server_manager_middle.dto.Server;
import com.supnum.server_manager_middle.dto.StartServerResponse;
import com.supnum.server_manager_middle.dto.StopServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client/servers")
public class MiddleController {

    private final ConsumateurClient consumateurClient;

    public MiddleController(ConsumateurClient consumateurClient) {
        this.consumateurClient = consumateurClient;
    }

    @GetMapping
    public ResponseEntity<List<Server>> getAllServers() {
        List<Server> servers = consumateurClient.getAllServers();
        return ResponseEntity.ok(servers);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<GetServerStatusResponse> getServerStatus(@PathVariable Long id) {
        GetServerStatusResponse response = consumateurClient.getServerStatus(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<StartServerResponse> startServer(@PathVariable Long id) {
        StartServerResponse response = consumateurClient.startServer(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<StopServerResponse> stopServer(@PathVariable Long id) {
        StopServerResponse response = consumateurClient.stopServer(id);
        return ResponseEntity.ok(response);
    }
}
