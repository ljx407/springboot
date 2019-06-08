package com.ljx.chapter13.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljx.chapter13.model.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

@Slf4j
public class WebSocketMessageEncocde implements Encoder.Text<WebSocketMessage> {


    @Override
    public String encode(WebSocketMessage object) throws EncodeException {
        try {
            return String.format("%s:%s",object.getFrom(),object.getMessage());
        } catch (Exception e) {
            log.error("WebSocketMessageEncocde encode error :" , e);
        }
        return null ;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
