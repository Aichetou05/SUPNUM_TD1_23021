package mr.supnum.server_manager.exception;

public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException(Long id) {
        super("Server with id " + id + " not found");
    }
}
