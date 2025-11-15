package mr.supnum.server_manager.contriller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mr.supnum.server_manager.entity.Server;
import mr.supnum.server_manager.service.ServerService;

@RestController
@RequestMapping("/api/servers")
public class ServerController {
    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }
    
    @PostMapping
    public ResponseEntity<Server> createServer(@RequestBody Server server){
        Server created = serverService.createServer(server);
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }

    @GetMapping
    public List<Server> getAllServers(){
        return serverService.getAllServers();
    }

    
}
