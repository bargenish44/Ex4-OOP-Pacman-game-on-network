package DB;

public class main {

	public static void main(String[] args) {
		DataBase dataBase = new DataBase("jdbc:mysql://ariel-oop.xyz:3306/oop","student","student");
		GETdata get = new GETdata(dataBase);
		System.out.println(get.avScore(9, 3131));
	}
}
