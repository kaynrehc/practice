package com.enthusys.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestUserPlay {
	private static final Logger logger = LoggerFactory.getLogger(TestUserPlay.class);
	private static final int TOTAL_USERS = 100000;

	// @Test
	public void testLoadUsers() {
		UserPlay userPlay = new UserPlay();
		logger.info("*** total users: {}", userPlay.getNumberOfUsers());
		assertEquals("expect exact number of users", TOTAL_USERS, userPlay.getNumberOfUsers());

		User testUser = userPlay.getUserByNumber(50000 + 49945);
		logger.info("*** {}", testUser);
		assertEquals("name must match", "Lang", testUser.getSurname());
		assertEquals("username must match", "Theirequal", testUser.getUsername());

		testUser = userPlay.getUserByNumber(2006);
		logger.info("*** {}", testUser);
		assertEquals("name must match", "Shcherbakov", testUser.getSurname());
		assertEquals("username must match", "Tatelly1963", testUser.getUsername());
	}

	@Test
	public void testRandomNames() {
		Random random = new Random();
 		UserPlay userPlay = new UserPlay();
		User user1 = userPlay.getUserByNumber(random.nextInt() % TOTAL_USERS);
		User user2 = userPlay.getUserByNumber(random.nextInt() % TOTAL_USERS);
		User user3 = userPlay.getUserByNumber(random.nextInt() % TOTAL_USERS);
		logger.info("***** {}", user1);
		logger.info("***** {}", user2);
		logger.info("***** {}", user3);
	}

	// @Test
	public void testAccessUsers() {
		long lStartTime = System.currentTimeMillis();
		UserPlay userPlay = new UserPlay();
		logger.info("*** total users:{}", userPlay.getNumberOfUsers());
		logger.info("*** time to load:{}", System.currentTimeMillis() - lStartTime);

		// iterate over Map
		lStartTime = System.currentTimeMillis();
		Map<Integer, User> userMap = userPlay.getUserMap();
		for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
			entry.getKey();
			entry.getValue();
		}
		logger.info("*** time to iterate map:{}", System.currentTimeMillis() - lStartTime);

		// iterate over TreeSet
		lStartTime = System.currentTimeMillis();
		TreeSet<User> userSet = userPlay.getUserTreeSet();
		assertEquals("expect exact number of users", 100000, userSet.size());

		for (User u : userSet) {
//			logger.debug("*** {}", u);
		}
		logger.info("*** time to iterate TreeSet:{}", System.currentTimeMillis() - lStartTime);
	}
}
