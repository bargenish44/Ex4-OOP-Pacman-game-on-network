package Packman_Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Path2KML {
	/**
	 * This class convert Game to kml.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	/**
	 * This function write all the game details in new kml.
	 * @param g Game that we want to write his details in the kml.
	 * @return boolean if we succeed to create the kml or not.
	 */
	public boolean path2kml(Game g) {
		Time time=new Time();
		Time maxtime=new Time();
		maxtime.setHour(1);
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document><Style id=\"red\">\r\n" + 
				"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
				"</Style><Style id=\"Packman\"><IconStyle><Icon><href>https://png2.kisspng.com/sh/cbe6dbcf1fface5c8a4892f6c04c955c/L0KzQYm3V8MxN5DohJH0aYP2gLBuTf10NaFme592YX6wgLLqTf1idl58hAR1ZD21PcHog71uaZ8ySp99aHWwfrbATfFlfl51edU2bXHxPbL1hL11cJYyf9p4c4TviX7ohQZmdqV6itd8LUXlRbbpWcY1PWdnT9U5Lkm5Q4WBWcE1OWY4Sqs8NEWzQIS8VcIveJ9s/kisspng-ms-pac-man-pac-man-world-2-pac-man-2-the-new-adv-pac-man-and-the-ghostly-adventures-5b5eb96456b7c0.9634891415329345003552.png</href></Icon></IconStyle>\r\n" + 
				"</Style><Style id=\"Fruit\"><IconStyle><Icon><href>http://chittagongit.com//images/cherry-icon/cherry-icon-15.jpg</href></Icon></IconStyle></Style>";
		content.add(kmlstart);

		String kmlend = "\n</Document></kml>";
		try{
			FileWriter fw = new FileWriter("data\\mygamekml.kml");
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<g.getFruitArr().size();i++) {
				Fruit tmp=g.getFruitArr().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name>fruit"+tmp.getID()+"</name>\n" +
						"<description>Type: Fruit\nlat: "+tmp.getPos().y()+"\nlon :"+tmp.getPos().x()+"\nAlt: "+tmp.getPos().z()+"\nWeight: "+tmp.getWeight()+ "</description>\n" +
						"<styleUrl>"+"Fruit"+"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getPos().y()+","+tmp.getPos().x()+","+tmp.getPos().z()+"</coordinates>" +
						"</Point>\n" +
						"<TimeSpan>"
						+"<begin>"+time+"</begin>"
						+"<end>"+tmp.getTime()+"</end>"
						+"</TimeSpan>"+
						"</Placemark>";
				content.add(kmlelement);
			}
			for(int i=0;i<g.getPackmanArr().size();i++) {
				Packman tmp=g.getPackmanArr().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name>Packman"+tmp.getID()+"</name>\n" +
						"<description>Type: Packman\nlat: "+tmp.getPos().y()+"\nlon :"+tmp.getPos().x()+"\nAlt: "+tmp.getPos().z()+"\nSpeed: "+tmp.getSpeed()+"\nRadius: "+tmp.getRadius()+"\nScore: "+tmp.getScore()+
						"</description>\n" +
						"<styleUrl>"+"Packman"+
						"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getPos().y()+","+tmp.getPos().x()+","+tmp.getPos().z()+"</coordinates>" +
						"</Point>\n" +
						"<TimeSpan>"
						+"<begin>"+time+"</begin>"
						+"<end>"+tmp.getTime()+"</end>"
						+"</TimeSpan>"
						+"</Placemark>";
				content.add(kmlelement);
			}
			Time start=new Time(time);
			for(int i=0;i<g.getPackmanArr().size();i++) {
				Packman tmp=g.getPackmanArr().get(i);
				for(int j=0;j<g.getPackmanArr().get(i).getPath().getPoints().size();j++) {
					if(j==0)
						start=tmp.getTime();
					else
						start=tmp.getPath().getPoints().get(j-1).getTime();
				}
			}
			for(int i=0;i<g.getPackmanArr().size();i++) {
				Packman tmp=g.getPackmanArr().get(i);
				Time ends=new Time(maxtime);
				for(int j=0;j<g.getPackmanArr().get(i).getPath().getPoints().size();j++) {
					ends=tmp.getPath().getPoints().get(j).getTime();
					String kmlelement ="<Placemark>\n" +
							"<name>Packman"+tmp.getID()+"</name>\n" +
							"<description>Type: Packman\nlat: "+tmp.getPath().getPoints().get(j).y()+"\nlon :"+tmp.getPath().getPoints().get(j).x()+"\nAlt: "+tmp.getPath().getPoints().get(j).z()+"\nSpeed: "+tmp.getSpeed()+"\nRadius: "+tmp.getRadius()+"\nScore: "+tmp.getScore()+
							"</description>\n" +
							"<styleUrl>"+"Packman"+"</styleUrl>"+"<Point>\n" +
							"<coordinates>"+tmp.getPath().getPoints().get(j).y()+","+tmp.getPath().getPoints().get(j).x()+","+tmp.getPath().getPoints().get(j).z()+"</coordinates>" +
							"</Point>\n" +
							"<TimeSpan>"
							+"<begin>"+start+"</begin>"
							+"<end>"+ends+"</end>"
							+"</TimeSpan>"+
							"</Placemark>";
					content.add(kmlelement);
				}
			}
			content.add(kmlend);
			String csv = content.toString().replaceAll("</Placemark>, <Placemark>", "</Placemark><Placemark>").replaceAll("</Placemark>, ", "</Placemark>").replaceAll(", <Placemark>", "<Placemark>");
			csv = csv.substring(1, csv.length()-1);
			bw.write(csv);
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
