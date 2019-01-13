package TestingAll;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Packman_Game.MyFrame;
import Packman_Game.Packman;



class MyFrameTest {
	private static final String M = null;
	/**
	 * MyFrame tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	ArrayList<Packman> arr_test = new ArrayList<>();
	Class<MyFrame> test_1 ;
	//MyFrame test_4 ;
	Class<MyCoords> test_3 ;
	MyFrame test_7 ;
	int x=7,y=3;
	String test_2;
	String test_8= M;
	ArrayList<Packman> arr_test_2 = new ArrayList<>();


	@Test 
	void testMyFrame() {

		test_1 = MyFrame.class;

		test_2 = "class Packman_Game.MyFrame";

		if(test_1.equals(test_2))

			fail("Not yet implemented");
		
		test_3 = MyCoords.class;
		
		if(test_3.equals(test_8))
			
			fail("Not yet implemented");
	}
		

	@Test
	void testActionPerformed() {

		ActionEvent test_5 = new ActionEvent(7, 3, null);

		ActionEvent test_6 = new ActionEvent(x, y, null);

		if(test_5 == test_6)

			fail("Not yet implemented");
		
		MyFrame	test_0 = new MyFrame();
		
		if(test_0.equals(test_7))
			
			fail("Not yet implemented");
	}

}
