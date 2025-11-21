package mr.supnum.server_manager.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mr.supnum.server_manager.entities.Server;
import mr.supnum.server_manager.service.ServerService;
import mr.supnum.server_manager.wsdl.CreateServerRequest;
import mr.supnum.server_manager.wsdl.CreateServerResponse;
import mr.supnum.server_manager.wsdl.DeleteServerRequest;
import mr.supnum.server_manager.wsdl.DeleteServerResponse;
import mr.supnum.server_manager.wsdl.GetAllServersResponse;
import mr.supnum.server_manager.wsdl.GetServerStatusRequest;
import mr.supnum.server_manager.wsdl.GetServerStatusResponse;
import mr.supnum.server_manager.wsdl.RenameServerRequest;
import mr.supnum.server_manager.wsdl.RenameServerResponse;
import mr.supnum.server_manager.wsdl.StartServerRequest;
import mr.supnum.server_manager.wsdl.StartServerResponse;
import mr.supnum.server_manager.wsdl.StopServerRequest;
import mr.supnum.server_manager.wsdl.StopServerResponse;

@Endpoint
public class ServerEndpoint {

    private static final String NAMESPACE_URI = "http://supnum.mr/servers";

    private final ServerService serverService;

    public ServerEndpoint(ServerService serverService) {
        this.serverService = serverService;
    }

    // Entity -> Server (SOAP)
    private mr.supnum.server_manager.wsdl.Server mapToWs(Server s) {
        if (s == null) return null;
        mr.supnum.server_manager.wsdl.Server ws = new mr.supnum.server_manager.wsdl.Server();
        ws.setId(s.getId());
        ws.setName(s.getName());
        ws.setIpAddress(s.getIpAddress());
        ws.setRunning(s.isRunning());
        return ws;
    }

    //createServer 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createServerRequest")
    @ResponsePayload
    public CreateServerResponse createServer(@RequestPayload CreateServerRequest request) {
        Server server = new Server();
        server.setName(request.getName());
        server.setIpAddress(request.getIpAddress());

        Server created = serverService.createServer(server);

        CreateServerResponse response = new CreateServerResponse();
        response.setServer(mapToWs(created));
        return response;
    }

    //getAllServers 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllServersRequest")
    @ResponsePayload
    public GetAllServersResponse getAllServers() {
        GetAllServersResponse response = new GetAllServersResponse();
        for (Server s : serverService.getAllServers()) {
            response.getServers().add(mapToWs(s));
        }
        return response;
    }

    //renameServer
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "renameServerRequest")
    @ResponsePayload
    public RenameServerResponse renameServer(@RequestPayload RenameServerRequest request) {
        Server updated = serverService.renameServer(request.getId(), request.getNewName());

        RenameServerResponse response = new RenameServerResponse();
        response.setServer(mapToWs(updated));
        return response;
    }

    // getServerStatus 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getServerStatusRequest")
    @ResponsePayload
    public GetServerStatusResponse getServerStatus(@RequestPayload GetServerStatusRequest request) {
        boolean running = serverService.getServerStatus(request.getId());

        GetServerStatusResponse response = new GetServerStatusResponse();
        response.setId(request.getId());
        response.setRunning(running);
        return response;
    }

    // startServer
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "startServerRequest")
    @ResponsePayload
    public StartServerResponse startServer(@RequestPayload StartServerRequest request) {
        Server started = serverService.startServer(request.getId());

        StartServerResponse response = new StartServerResponse();
        response.setServer(mapToWs(started));
        return response;
    }

    // stopServer
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "stopServerRequest")
    @ResponsePayload
    public StopServerResponse stopServer(@RequestPayload StopServerRequest request) {
        Server stopped = serverService.stopServer(request.getId());

        StopServerResponse response = new StopServerResponse();
        response.setServer(mapToWs(stopped));
        return response;
    }

    // deleteServer 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteServerRequest")
    @ResponsePayload
    public DeleteServerResponse deleteServer(@RequestPayload DeleteServerRequest request) {
        
        serverService.deleteServer(request.getId());

        DeleteServerResponse response = new DeleteServerResponse();
        response.setSuccess(true);
        return response;
    }
}
