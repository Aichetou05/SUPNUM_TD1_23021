package mr.supnum.server_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mr.supnum.server_manager.entity.Server;
import mr.supnum.server_manager.exception.ServerDeletionException;
import mr.supnum.server_manager.exception.ServerNotFoundException;
import mr.supnum.server_manager.repository.ServerRepository;

@Service
@Transactional
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server createServer(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @Override
    public Server renameServer(Long id, String newName) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new ServerNotFoundException(id));
        server.setName(newName);
        return serverRepository.save(server);
    }

    @Override
    public boolean getServerStatus(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new ServerNotFoundException(id));
        return server.isRunning();
    }

    @Override
    public Server startServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new ServerNotFoundException(id));
        server.setRunning(true);
        return serverRepository.save(server);
    }

    @Override
    public Server stopServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new ServerNotFoundException(id));
        server.setRunning(false);
        return serverRepository.save(server);
    }

    @Override
    public void deleteServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new ServerNotFoundException(id));

        if (server.isRunning()) {
            throw new ServerDeletionException(
                    "Can not delete a running server! Please stop it first"
            );
        }
        serverRepository.delete(server);
    }
}
