package net.wschat;

/**
 * Listens for events on a chat-connection.
 * 
 * @author achim, 25.02.2012
 */
interface MessageListener {
	public void onMessage(String msg);

	public void onOpen(ChatConnection conn);

	public void onClose(ChatConnection conn);
}