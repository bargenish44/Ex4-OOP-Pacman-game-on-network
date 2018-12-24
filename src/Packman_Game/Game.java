package Packman_Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;


import Geom.Point3D;

public class Game {
	/**
	 * This class represents game- list of packmans and fruits.
	 * Game can be saved on scv file and can be read from csv file.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private ArrayList<Fruit> array=new ArrayList<>();
	private ArrayList<Packman>arr=new ArrayList<>();
	public String GameName;//for the test.
	public Game(ArrayList<Packman>arr,ArrayList<Fruit> array) {//constracors
		this.arr=arr;	
		this.array=array;
	}
	public Game() {
		arr=new ArrayList<>();
		array=new ArrayList<>();
	}
	public Game(Game g) {
		arr=g.arr;
		array=g.array;
	}
	public ArrayList<Packman> getArr() {//getters and setters
		return arr;
	}
	public void setArr(ArrayList<Packman> arr) {
		this.arr = arr;
	}
	public ArrayList<Fruit> getArray() {
		return array;
	}
	public void setArray(ArrayList<Fruit> array) {
		this.array = array;
	}
	/**
	 * write the Game as string(helps to save the game in csv file).
	 * @return string of the Game.
	 */
	public String toString() {
		String s="Type,id,Lat,Lon,Alt,Speed/Weight,Radius,"+arr.size()+","+array.size()+"\n";
		for(int i=0;i<arr.size();i++) {
			s+="P,"+arr.get(i).toString()+"\n";
		}
		for(int i=0;i<array.size();i++) {
			try {
				s+="F,"+array.get(i).toString()+"\n";
			}catch(IndexOutOfBoundsException e) {}
		}
		return s;
	}
	/**
	 * This function make a new game from the csv that we got. 
	 * @param CsvFile the path of csv file that we want to read from him.
	 * @return Game g with all data from the csv.
	 */
	public Game load(String CsvFile) 
	{
		String line = "";
		String cvsSplitBy = ",";
		Game g=new Game();
		try (BufferedReader br = new BufferedReader(new FileReader(CsvFile))) 
		{
			line=br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				if(userInfo[0].equals("P")) {
					g.arr.add(new Packman(Integer.parseInt(userInfo[1]), new Point3D(userInfo[2]+","+userInfo[3]+","+userInfo[4]),Double.parseDouble(userInfo[5]),Double.parseDouble(userInfo[6])));
				}
				else if(userInfo[0].equals("F"))
					g.array.add(new Fruit(Integer.parseInt(userInfo[1]), new Point3D(userInfo[2]+","+userInfo[3]+","+userInfo[4]),Double.parseDouble(userInfo[5])));
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return g;
	}
	/**
	 * This function make a new csv file with all the game details. 
	 * @param g the Game that we want to saved his details on csv.
	 */
	public void save(Game g) {
		LocalTime s=LocalTime.now();
		String time=s.toString().replaceAll(":", ".");
		String fileName="game"+time+".csv";
		GameName=fileName;
		String newfilepath="data\\"+fileName;
		PrintWriter pw=null;
		try 
		{
			pw = new PrintWriter(new File(newfilepath));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		pw.write(g.toString());
		pw.close();
		System.out.println("saved: "+newfilepath);
	}
}
