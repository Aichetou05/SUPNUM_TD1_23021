package com.supnum.server_manager_middle.clients;


import com.supnum.server_manager_middle.dto.GetServerStatusResponse;
import com.supnum.server_manager_middle.dto.Server;
import com.supnum.server_manager_middle.dto.StartServerResponse;
import com.supnum.server_manager_middle.dto.StopServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@FeignClient(name = "consumateur", url = "${consumateur.service.url}")
public interface ConsumateurClient {

    @GetMapping("/api/servers")
    List<Server> getAllServers();

    @GetMapping("/api/servers/{id}/status")
    GetServerStatusResponse getServerStatus(@PathVariable("id") Long id);

    @PostMapping("/api/servers/{id}/start")
    StartServerResponse startServer(@PathVariable("id") Long id);

    @PostMapping("/api/servers/{id}/stop")
    StopServerResponse stopServer(@PathVariable("id") Long id);
}
