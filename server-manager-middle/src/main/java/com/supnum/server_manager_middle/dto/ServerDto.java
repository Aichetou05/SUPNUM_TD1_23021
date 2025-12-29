package com.supnum.server_manager_middle.dto;

public class ServerDto {

    private Long id;
    private String name;
    private String ipAddress;
    private boolean running;

    public ServerDto(Long id, String name, String ipAddress, boolean running) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.running = running;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getIpAddress() { return ipAddress; }
    public boolean isRunning() { return running; }
}
