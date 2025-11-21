package mr.supnum.server_manager.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mr.supnum.server_manager.entities.Server;
import mr.supnum.server_manager.service.ServerService;
import mr.supnum.server_manager.wsdl.*;

@Endpoint
public class ServerEndpoint {

    private static final String NAMESPACE_URI = "http://supnum.mr/servers";

    private final ServerService serverService;

    public ServerEndpoint(ServerService serverService) {
        this.serverService = serverService;
    }

    private mr.supnum.server_manager.wsdl.Server mapToWs(Server s) {
        if (s == null) return null;
        mr.supnum.server_manager.wsdl.Server ws = new mr.supnum.server_manager.wsdl.Server();
        ws.setId(s.getId());
        ws.setName(s.getName());
        ws.setIpAddress(s.getIpAddress());
        ws.setRunning(s.isRunning());
        return ws;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getServerRequest")
    @ResponsePayload
    public GetServerResponse getServer(@RequestPayload GetServerRequest request) {
        Server s = serverService.getById(request.getId());
        GetServerResponse response = new GetServerResponse();
        response.setServer(mapToWs(s));
        return response;
    }

    // ===== getAllServers =====
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllServersRequest")
    @ResponsePayload
    public GetAllServersResponse getAllServers(@RequestPayload GetAllServersRequest request) {
        GetAllServersResponse response = new GetAllServersResponse();
        for (Server s : serverService.getAll()) {
            response.getServers().add(mapToWs(s));
        }
        return response;
    }

    // ===== createServer =====
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createServerRequest")
    @ResponsePayload
    public CreateServerResponse createServer(@RequestPayload CreateServerRequest request) {
        Server s = serverService.create(request.getName(), request.getIpAddress());
        CreateServerResponse response = new CreateServerResponse();
        response.setServer(mapToWs(s));
        return response;
    }

    // ===== deleteServer =====
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteServerRequest")
    @ResponsePayload
    public DeleteServerResponse deleteServer(@RequestPayload DeleteServerRequest request) {
        boolean ok = serverService.delete(request.getId());
        DeleteServerResponse response = new DeleteServerResponse();
        response.setSuccess(ok);
        return response;
    }
}
