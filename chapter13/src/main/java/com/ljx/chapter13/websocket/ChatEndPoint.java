package com.ljx.chapter13.websocket;

import com.ljx.chapter13.model.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@Slf4j
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ServerEndpoint(
        value = "/chat/{username}",
        encoders = WebSocketMessageEncocde.class,
        decoders = WebSocketMessageDecode.class
)
public class ChatEndPoint {

    private Session session;
    private static Set<ChatEndPoint> endPoints = new CopyOnWriteArraySet<>();
    private static Map<String, String> users = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        users.put(session.getId(),username);
        endPoints.add(this);

        WebSocketMessage message = WebSocketMessage.builder()
                .from(username)
                .message("connected!")
                .build();

        broadcast(message);
    }

    public Session getSession() {
        return this.session;
    }

    private void broadcast(WebSocketMessage message){
        endPoints.forEach(endPoint -> {
            synchronized (endPoint) {
                try {
                    endPoint.getSession().getBasicRemote().sendObject(message);
                } catch (Exception e) {
                    log.error("broadcast error " , e);
                }
            }
        });
    }

    @OnMessage
    public void onMessge(@PathParam("username") String username, WebSocketMessage message) {
        message.setFrom(username);
        broadcast(message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username) {
        endPoints.remove(this);
        WebSocketMessage message = WebSocketMessage.builder()
                .from(username)
                .message("disconneted!")
                .build();

        broadcast(message);
    }

    @OnError
    public void onError(Throwable throwable) {
        log.error("ChatEndPoint error :" , throwable);
    }
}
