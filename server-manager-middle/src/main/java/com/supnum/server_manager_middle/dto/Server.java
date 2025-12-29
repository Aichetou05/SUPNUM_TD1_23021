package com.supnum.server_manager_middle.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    private Long id;
    private String name;
    private String ipAddress;
    private Boolean running;
    private Instant createdAt;
    private Instant updatedAt;
}