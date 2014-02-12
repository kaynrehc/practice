package com.enthusys.play.cron;

/**
 * User: mchernyak
 * Date: 2/4/14
 * Time: 11:57 AM
 */

/*
 * * * * * *
 | | | | | |
 | | | | | +-- Year              (range: 1900-3000)
 | | | | +---- Day of the Week   (range: 1-7, 1 standing for Monday)
 | | | +------ Month of the Year (range: 1-12)
 | | +-------- Day of the Month  (range: 1-31)
 | +---------- Hour              (range: 0-23)
 +------------ Minute            (range: 0-59)
 */

public class CronEntry {
	int hour;
	int minute;
	String localTimeZone;
	String processTimeZone;

	public void addHour(int hour)
	{
		this.hour += hour;
	}

	public CronEntry() {
		hour = 0;
		minute = 0;
	}

	public CronEntry(int hour, int minute) throws IllegalArgumentException {

		if (hour < 0 || hour > 23) {
			throw new IllegalArgumentException("hour must be between 0 and 23");
		} else if (minute < 0 || minute > 59) {
			throw new IllegalArgumentException("minute must be between 0 and 59");
		}

		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public String toString() {
		return "CronEntry{" +
				"hour=" + hour +
				", minute=" + minute +
				'}';
	}

	public static void main(String[] args) {
		CronEntry cronEntry = new CronEntry(9, 3099);
	}
}
