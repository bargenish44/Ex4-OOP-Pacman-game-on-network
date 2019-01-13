package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Packman_Game.Time;

class TimeTests {
	/**
	 * Time tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */	
	@Test
	void Time_tests() {
		Time time=new Time();
		if(!time.toString().equals("2000-01-01T01:01:01"))
			fail("Constractor/tostring problem");
		time.setYear(4);
		if(time.getYear()!=2004)
			fail("get/set year problem");
		time.setMounth(4);
		if(time.getMounth()!=5)
			fail("get/set mounth problem");
		time.setDay(4);
		if(time.getDay()!=5)
			fail("get/set day problem");
		time.setHour(4);
		if(time.getHour()!=5)
			fail("get/set hour problem");
		time.setMinute(4);
		if(time.getMinute()!=5)
			fail("get/set minute problem");
		time.setSecond(4);
		if(time.getSecond()!=5)
			fail("get/set second problem");
		time.setSecond(60);
		if(time.getSecond()!=5)
			fail("get/set second problem");
		if(!time.toString().equals("2004-05-05T05:06:05"))
			fail("getter/setter/tostring problem");
	}
}
