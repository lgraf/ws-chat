package net.wschat.engine;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;

/**
 * 
 * @author eliyo, 25.02.2012
 */
public class ChatConnectionManagerTest {

	@Test
	public void shouldRecognizeJoinCommand() throws JSONException {
		String jSonDummyMessage = "{\"type\":\"enter\",\"data\":\"dummy1\"}";
		ChatConnectionManager manager = new ChatConnectionManager();
		assertTrue(manager.isJoinCommand(jSonDummyMessage));
	}
}
