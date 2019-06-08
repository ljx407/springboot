package com.ljx.chapter13.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljx.chapter13.model.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

@Slf4j
public class WebSocketMessageDecode implements Decoder.Text<WebSocketMessage> {

    @Override
    public WebSocketMessage decode(String s) throws DecodeException {
        try {
            WebSocketMessage webSocketMessage = WebSocketMessage.builder()
                    .message(s)
                    .build();
            return webSocketMessage;
        } catch (Exception e) {
            log.error("WebSocketMessageDecode erroe :" ,e );
        }
        return null ;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
