package com.ground.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.Map;

public class WebSocketConfig {

  @Bean
  public EchoWebSocketHandler echoHandler() {
    return new EchoWebSocketHandler();
  }

  //@Bean
  //@Profile({"sink"})
  Sinks.Many<String> chatMessageStream() {
    return Sinks.many().multicast().<String>onBackpressureBuffer();
  }

  @Bean
  public HandlerMapping handlerMapping(EchoWebSocketHandler echoHandler) {
    Map<String, WebSocketHandler> map = new HashMap<>();
    map.put("/echo", echoHandler);

    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    mapping.setUrlMap(map);
    mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return mapping;
  }

  @Bean
  public WebSocketHandlerAdapter handlerAdapter(WebSocketService webSocketService) {
    return new WebSocketHandlerAdapter(webSocketService);
  }

  @Bean
  public WebSocketService webSocketService() {
    return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
  }


  public static class EchoWebSocketHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
      return session.send(
          session.receive()
              .map(msg -> session.textMessage("ECHO -> " + msg.getPayloadAsText()))
      );
    }
  }

}
