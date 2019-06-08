package com.ljx.chapter13.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WebSocketMessage {
    private String from;
    private String to ;
    private String message ;
}
