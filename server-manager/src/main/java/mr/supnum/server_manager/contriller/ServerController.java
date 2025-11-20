package mr.supnum.server_manager.contriller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mr.supnum.server_manager.dto.RenameServerRequest;
import mr.supnum.server_manager.entity.Server;
import mr.supnum.server_manager.exception.ServerDeletionException;
import mr.supnum.server_manager.exception.ServerNotFoundException;
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

    @PutMapping("/{id}/rename")
    public Server renameServer(@PathVariable Long id,
                               @RequestBody RenameServerRequest request) {
        return serverService.renameServer(id, request.getNewName());
    }

    @GetMapping("/{id}/status")
    public Map<String, Object> getStatus(@PathVariable Long id) {
        boolean running = serverService.getServerStatus(id);
        return Map.of(
                "id", id,
                "running", running
        );
    }

    @PostMapping("/{id}/start")
    public Server startServer(@PathVariable Long id) {
        return serverService.startServer(id);
    }

    @PostMapping("/{id}/stop")
    public Server stopServer(@PathVariable Long id) {
        return serverService.stopServer(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        serverService.deleteServer(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ServerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ServerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(ServerDeletionException.class)
    public ResponseEntity<Map<String, String>> handleDeletion(ServerDeletionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }
}
