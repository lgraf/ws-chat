package net.wschat.engine;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

/**
 * Accepts connections from clients and passes them on to the chat-engine.
 * 
 * @author achim, 25.02.2012
 */
public class ChatWebSocketHandler extends WebSocketHandler {
	private ChatConnectionManager conMgr = new ChatConnectionManager();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		ChatConnection conn = new ChatConnection();
		conn.addMessageListener(conMgr);
		return conn;
	}
}
