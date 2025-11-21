package mr.supnum.server_manager.service;
import java.util.List;

import mr.supnum.server_manager.entities.Server;

public interface ServerService {

    Server createServer(Server server);

    List<Server> getAllServers();

    Server renameServer(Long id, String newName);

    boolean getServerStatus(Long id);

    Server startServer(Long id);

    Server stopServer(Long id);

    void deleteServer(Long id);
}