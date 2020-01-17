package com.guazi.web.service.impl;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@ServerEndpoint("/webSocket")
@Component
@Slf4j
public class MyWebSocket {

	// 给客户端发送消息
	private javax.websocket.Session wsSession;
	// 存放客户端对应的MyWebSocket对象
	private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

	/**
	 * localhost:8080/sell/webSocket
	 * 建立连接
	 * @param msg
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.wsSession = session;
		webSocketSet.add(this);
		log.info("【WebSocket消息】建立连接,连接数:{}", webSocketSet.size());
	}

	/**
	 * 关闭连接
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		log.info("【WebSocket消息】关闭连接");
	}

	/**
	 * 客户端接收服务端数据
	 */
	@OnMessage
	public void onMessage(String msg) {
		log.info("【WebSocket消息】收到客户端发送消息 msg={}", msg);
	}

	/**
	 * 建立连接发送数据
	 */
	public void sendMsg(String msg) {
		for (MyWebSocket webSocket : webSocketSet) {
			log.info("【WebSocket消息】发送数据 msg={}", msg);
			try {
				webSocket.wsSession.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通信发生错误
	 */
	@OnError
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
		log.info("【WebSocket消息】通信发生错误");
	}

}
