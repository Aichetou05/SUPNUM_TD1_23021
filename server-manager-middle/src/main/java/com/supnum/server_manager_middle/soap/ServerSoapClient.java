package com.supnum.server_manager_middle.soap;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.supnum.server_manager_middle.dto.ServerDto;

// ⬇️ هذه الكلاسات مولّدة من WSDL (نفس package عندك)
import mr.supnum.server_manager.wsdl.GetAllServersRequest;
import mr.supnum.server_manager.wsdl.GetAllServersResponse;

@Service
public class ServerSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public ServerSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public List<ServerDto> getAllServers() {

        // 1️⃣ إنشاء SOAP Request
        GetAllServersRequest request = new GetAllServersRequest();

        // 2️⃣ إرسال الطلب إلى Service SOAP
        GetAllServersResponse response =
            (GetAllServersResponse) webServiceTemplate
                .marshalSendAndReceive("http://localhost:8080/ws", request);

        // 3️⃣ تحويل SOAP → REST (XML → JSON)
        return response.getServers().stream()
                .map(s -> new ServerDto(
                        s.getId(),
                        s.getName(),
                        s.getIpAddress(),
                        s.isRunning()
                ))
                .toList();
    }
}
