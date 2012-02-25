package net.wschat;

interface MessageListener {
	public void onMessage(String msg);

	public void onOpen(ChatWebSocket socket);

	public void onClose(ChatWebSocket socket);
}