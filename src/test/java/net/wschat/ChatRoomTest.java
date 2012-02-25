package net.wschat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ChatRoomTest {

	private ChatRoom chatRoom;

	@Before
	public void setUp() {
		chatRoom = new ChatRoom();

	}

	@Test
	public void userShouldBeAbleToEnter() {
		User user = new User("Dummy");
		chatRoom.enter(user);

		assertTrue(chatRoom.isUserInRoom(user));
	}

	@Test
	public void userShouldNotBeAbleToEnterIfSameUserName() {
		User user1 = new User("Hans");
		User user2 = new User("Hans");
		chatRoom.enter(user1);
		chatRoom.enter(user2);

		assertFalse(chatRoom.isUserInRoom(user2));
	}

	@Test
	public void allUsersShouldBeInformedIfNewUserEntersRoom() {
		User user1 = mock(User.class);
		User user2 = mock(User.class);
		when(user1.getName()).thenReturn("dummy1");
		when(user2.getName()).thenReturn("dummy2");

		chatRoom.enter(user1);
		chatRoom.enter(user2);

		User user3 = new User("Dummy3");
		chatRoom.enter(user3);

		verify(user1).receiveMessage("user Dummy3 entered the chatroom!");
	}
}
