package example;

import java.util.ArrayList;

import Geom.Point3D;
import Packman_Game.Game;
import Packman_Game.Map;
import Robot.Play;

/*
 * This is the basic example of how to work with the Ex4 "server" like system:
 * 1. Create a "play" with one of the 9 attached files 
 * 2. Set your ID's - of all the group members (numbers only);
 * 3. Get the GPS coordinates of the "arena" - as in Ex3.
 * 4. Get the game-board data
 * 5. Set the "player" init location - should be a valid location
 * 6. Start the Server
 * 7. while (the game is running):
 * 7.1 "Play" as long as there are "fruits" and time
 * 7.2 get the current score of the game
 * 7.3 get the game-board current state
 * 7.4 goto 7
 * 8. stop the server (this is not needed - in your implementation);
 * @author ben-moshe
 *
 */
public class Ex4_Main_Example {
	public static void main(String[] args) {
		// 1) Create a "play" from a file (attached to Ex4)
		String file_name = "data/Ex4_OOP_example6.csv";
		Play play1 = new Play(file_name);
		Game g=new Game();

		// 2) Set your ID's - of all the group members
		play1.setIDs(9876,543,21);

		// 3)Get the GPS coordinates of the "arena"
		String map_data = play1.getBoundingBox();
		System.out.println("Bounding Box info: "+map_data);
		String []str=map_data.split(",");
		Map map=new Map();
		map.setRightDown(new Point3D(str[2]+","+str[3]+","+str[4]));
		map.setLeftUp(new Point3D(str[5]+","+str[6]+","+str[7]));

		// 4) get the game-board data
		ArrayList<String> board_data = play1.getBoard();
		for(int i=0;i<board_data.size();i++) {
			System.out.println(board_data.get(i));
		}
		System.out.println();
		g=g.loadstring(board_data);
		System.out.println("Init Player Location should be set using the bounding box info");

		// 5) Set the "player" init location - should be a valid location
		play1.setInitLocation(32.1040,35.2061);
		g.getPlayer().setOrinet(new Point3D(32.1040,35.2061));

		//		// 6) Start the "server"
		//		play1.start(); // default max time is 100 seconds (1000*100 ms).
		//
		//		// 7) "Play" as long as there are "fruits" and time
		//		for(int i=0;i<10;i++) {
		//
		//			// 7.1) this is the main command to the player (on the server side)
		//			play1.rotate(36*i); 
		//			System.out.println("***** "+i+"******");
		//
		//			// 7.2) get the current score of the game
		//			String info = play1.getStatistics();
		//			System.out.println(info);
		//			// 7.3) get the game-board current state
		//			board_data = play1.getBoard();
		//			for(int a=0;a<board_data.size();a++) {
		//				System.out.println(board_data.get(a));
		//			}
		//			System.out.println();
		//		}
		//		// 8) stop the server - not needed in the real implementation.
		//		play1.stop();
		//		System.out.println("**** Done Game (user stop) ****");
		//
		//		// 9) print the data & save to the course DB
		//		String info = play1.getStatistics();
		//		System.out.println(info);
		System.out.println(g.toString());
	}
}
