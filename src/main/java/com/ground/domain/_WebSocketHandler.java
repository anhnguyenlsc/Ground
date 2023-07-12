package com.ground.domain;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class _WebSocketHandler implements WebSocketHandler {
  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session.send(
      session.receive()
        .map(msg -> session.textMessage("ECHO -> " + msg.getPayloadAsText()))
    );
  }
}
