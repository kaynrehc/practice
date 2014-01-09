package com.enthusys.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class UserPlay {
	private static final Logger logger = LoggerFactory.getLogger(UserPlay.class);
	private Map<Integer, User> userMap;
	private TreeSet<User> userTreeSet;

	public static void main(String[] args) {
	}

	public Map<Integer,User> getUserMap() {
		return userMap;
	}

	public TreeSet<User> getUserTreeSet() {
		return userTreeSet;
	}

	public UserPlay() {
		userMap = new HashMap<Integer, User>();
		userTreeSet = new TreeSet<User>();

		loadUsersFromFile("/home/mchernyak/data/People1.csv");
		loadUsersFromFile("/home/mchernyak/data/People2.csv");
	}

	public int getNumberOfUsers() {
		return userMap.size();
	}

	public User getUserByNumber(Integer number) {
		User u = userMap.get(number);
		return u;
	}

	public void loadUsersFromFile(String csvFile) {
		BufferedReader br = null;
		String line = "";
		int baseNumber = -1;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			String[] t = line.split(",");
			User.setBaseNumber(Integer.parseInt(t[1]));

			while ((line = br.readLine()) != null) {
				User u = new User(line);
				userMap.put(u.getNumber(), u);
				userTreeSet.add(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
