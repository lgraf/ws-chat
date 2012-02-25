package net.wschat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

/**
 * 
 * @author achim, 25.02.2012
 */
public class ChatWebSocketHandler extends WebSocketHandler implements MessageListener {
	private final List<ChatWebSocket> sockets = new CopyOnWriteArrayList<>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		final ChatWebSocket socket = new ChatWebSocket();
		socket.addMessageListener(this);
		return socket;
	}

	@Override
	public void onOpen(ChatWebSocket socket) {
		sockets.add(socket);
	}

	@Override
	public void onMessage(String msg) {
		publishMessage(msg);
	}

	@Override
	public void onClose(ChatWebSocket socket) {
		sockets.remove(socket);
	}

	private void publishMessage(String msg) {
		for (ChatWebSocket socket : sockets)
			socket.sendMessage(msg);
	}
}
