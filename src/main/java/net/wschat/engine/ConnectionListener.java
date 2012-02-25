package net.wschat.engine;

/**
 * Listens for events on a chat-connection.
 * 
 * @author achim, 25.02.2012
 */
interface ConnectionListener {
	public void onMessage(ChatConnection chatConnection, String msg);

	public void onOpen(ChatConnection conn);

	public void onClose(ChatConnection conn);
}