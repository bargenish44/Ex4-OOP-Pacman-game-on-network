package Packman_Game;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;

import Coords.MyCoords;
import Geom.Point3D;

public class MyFrame implements ActionListener{
	/**
	 * This class is the graphic class and use all the project methods. 
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private Game game;
	private ArrayList<Packman>Packmanarr=new ArrayList<>();
	private ArrayList<Fruit>Fruitarr=new ArrayList<>();
	private ArrayList<Packman>Packmanarrtemp=new ArrayList<>();
	private ArrayList<Fruit>Fruitarrtemp=new ArrayList<>();
	private ArrayList<Ghost>Ghostarr=new ArrayList<>();
	private ArrayList<Box>Boxarr=new ArrayList<>();
	private Player player;
	private boolean ans=false;
	private double angle=0;
	private ImageIcon packmanimage;
	private ImageIcon cherryimage;
	private ImageIcon ghostimage;
	private ImageIcon playerimage;
	private int counter=0;
	private int ghostcounter=0;
	private int boxcounter=0;
	private String choose="";
	private int count=0;
	private JMenuItem load;
	private JMenuBar menubar;
	private JMenuItem save;
	private JMenuItem run;
	private JMenuItem how_to_run;
	private JMenuItem about_the_game;
	private JMenuItem reload;
	private JMenuItem Save_as_kml;
	private JMenuItem addpackman;
	private JMenuItem addfruit;
	private JMenuItem addghost;
	private JMenuItem addbox;
	private JMenuItem addplayer;
	private JMenuItem azimuth;
	private JMenu menu2;
	private JMenu menu;
	private JMenu menu3;
	private Image img;
	private int width;
	private int hight;
	private JFrame frame;
	private Map map;
	private ImagePanel panel;
	private int anss=-1;

	public static void main(String[] args) {
		new MyFrame();
	}
	public MyFrame(){//constractor
		try {
			map=new Map();
			img = ImageIO.read(new File(map.getMap()));
			packmanimage=new ImageIcon("pacman.jpg");
			cherryimage=new ImageIcon("cherry.png");
			ghostimage=new ImageIcon("ghost.png");
			playerimage=new ImageIcon("player.png");
			frame = new JFrame("OOP-EX4");
			menubar = new JMenuBar();
			menu = new JMenu("Help");
			menubar.add(menu);
			about_the_game=new JMenuItem("About the game");
			about_the_game.addActionListener(this);
			menu.add(about_the_game);
			how_to_run =new JMenuItem("How to run");
			how_to_run.addActionListener(this);
			menu.add(how_to_run);
			menu2=new JMenu("Option");
			reload=new JMenuItem("Reload");
			reload.addActionListener(this);
			menu2.add(reload);
			Save_as_kml=new JMenuItem("Save as kml");
			Save_as_kml.addActionListener(this);
			menu2.add(Save_as_kml);
			load=new JMenuItem("Load");
			load.addActionListener(this);
			menu2.add(load);
			save=new JMenuItem("Save");
			save.addActionListener(this);
			menu2.add(save);
			run=new JMenuItem("Run");
			run.addActionListener(this);
			menu2.add(run);
			menubar.add(menu2);
			menu3=new JMenu("Add");
			azimuth=new JMenuItem("Azimuth");
			azimuth.addActionListener(this);
			addpackman=new JMenuItem("Packman");
			addpackman.addActionListener(this);
			addfruit=new JMenuItem("Fruit");
			addfruit.addActionListener(this);
			addghost=new JMenuItem("Ghost");
			addghost.addActionListener(this);
			addbox=new JMenuItem("Box");
			addbox.addActionListener(this);
			addplayer=new JMenuItem("Player");
			addplayer.addActionListener(this);
			menu3.add(addbox);
			menu3.add(addfruit);
			menu3.add(addpackman);
			menu3.add(addghost);
			menu3.add(addplayer);
			menu3.add(azimuth);
			menubar.add(menu3);
			frame.setJMenuBar(menubar);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			panel = new  ImagePanel(img);
			frame.add(panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			game=new Game();

		} catch (IOException | HeadlessException exp) {
			exp.printStackTrace();
		}

	}
	public MyFrame(Game g,Map map){//constractor
		try {
			this.map=map;
			game=g;
			img = ImageIO.read(new File(this.map.getMap()));
			Packmanarr=g.getPackmanArr();
			Fruitarr=g.getFruitArr();
			Ghostarr=g.getGhostarr();
			Boxarr=g.getBoxarr();
			player=g.getPlayer();
			packmanimage=new ImageIcon("pacman.jpg");
			cherryimage=new ImageIcon("cherry.png");
			ghostimage=new ImageIcon("ghost.png");
			playerimage=new ImageIcon("player.png");
			frame = new JFrame("OOP-EX3");
			menubar = new JMenuBar();
			menu = new JMenu("Help");
			menubar.add(menu);
			about_the_game=new JMenuItem("About the game");
			about_the_game.addActionListener(this);
			menu.add(about_the_game);
			how_to_run =new JMenuItem("How to run");
			how_to_run.addActionListener(this);
			menu.add(how_to_run);
			menu2=new JMenu("Option");
			reload=new JMenuItem("Reload");
			reload.addActionListener(this);
			menu2.add(reload);
			Save_as_kml=new JMenuItem("Save as kml");
			Save_as_kml.addActionListener(this);
			menu2.add(Save_as_kml);
			load=new JMenuItem("Load");
			load.addActionListener(this);
			menu2.add(load);
			save=new JMenuItem("Save");
			save.addActionListener(this);
			menu2.add(save);
			run=new JMenuItem("Run");
			run.addActionListener(this);
			menu2.add(run);
			menubar.add(menu2);
			menu3=new JMenu("Add");
			azimuth=new JMenuItem("Azimuth");
			azimuth.addActionListener(this);
			addpackman=new JMenuItem("Packman");
			addpackman.addActionListener(this);
			addfruit=new JMenuItem("Fruit");
			addfruit.addActionListener(this);
			addghost=new JMenuItem("Ghost");
			addghost.addActionListener(this);
			addbox=new JMenuItem("Box");
			addbox.addActionListener(this);
			addplayer=new JMenuItem("Player");
			addplayer.addActionListener(this);
			menu3.add(addbox);
			menu3.add(addfruit);
			menu3.add(addpackman);
			menu3.add(addghost);
			menu3.add(addplayer);
			menu3.add(azimuth);
			menubar.add(menu3);
			frame.setJMenuBar(menubar);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			panel = new  ImagePanel(img);
			frame.add(panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		} catch (IOException | HeadlessException exp) {
			exp.printStackTrace();
		}
	}
	public int getAnss() {
		return this.anss;
	}
	public void setGame(Game g) {
		game=g;
	}

	class ImagePanel extends JPanel implements MouseListener{

		private static final long serialVersionUID = 1L;
		private Image img;
		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
			this.addMouseListener(this); 
		}

		public ImagePanel(Image img) {
			this.img = img;
			this.addMouseListener(this); 
		}
		@Override
		public void invalidate() {
			super.invalidate();
			width = getWidth();
			hight = getHeight();
		}
		@Override
		public Dimension getPreferredSize() {
			return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
		}
		/**
		 * This is the paint func it paints the image.
		 * @param g - Graphics that we want to paint.
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(),null);
			for(int i=0;i<Packmanarr.size();i++) {
				Point3D p=map.CoordsToPixel(Packmanarr.get(i).getOrinet(),width,hight);
				g.drawImage(packmanimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}
			for(int i=0;i<Fruitarr.size();i++) {
				Point3D p=map.CoordsToPixel(Fruitarr.get(i).getOrient(),width,hight);
				g.drawImage(cherryimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}


			for(int i=0;i<Ghostarr.size();i++) {
				Point3D p=map.CoordsToPixel(Ghostarr.get(i).getPos(),width,hight);
				g.drawImage(ghostimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}
			try {
				Point3D p=map.CoordsToPixel(player.getOrinet(),width,hight);
				g.drawImage(playerimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}
			catch(NullPointerException e) {}
			Graphics2D g2=(Graphics2D)g;

			for(int i=0;i<Boxarr.size();i++) {
				Point3D p=map.CoordsToPixel(Boxarr.get(i).getLeftDown(),width,hight);
				Point3D p2=map.CoordsToPixel(Boxarr.get(i).getRightUp(),width,hight);
				g2.setColor(Color.black);
				g2.drawRect(p2.ix(), p.iy(),Math.abs(p2.ix()-p.ix()) ,Math.abs(p2.iy()-p.iy()));
				g2.fillRect(p2.ix(), p.iy(),Math.abs(p2.ix()-p.ix()) ,Math.abs(p2.iy()-p.iy()));

			}


			if (ans) {
				ans = false;
				Thread t = new Thread()
				{

					public void run() 
					{
						int count=getmathpath(Packmanarr);
						MyCoords mc = new MyCoords();
						for (int j = 0; j < count; j++)
						{
							for (int i = 0; i < Packmanarr.size(); i++) 
							{
								try
								{
									Packmanarr.get(i).setOrinet(Packmanarr.get(i).getPath().getArr().get(j));
									for ( int k = 0; k < Packmanarr.size(); k++) {
										if(mc.distance3d(Packmanarr.get(i).getOrinet(),Fruitarr.get(k).getOrient())<3) {
											Fruitarr.remove(k);
										}
									}
								}
								catch (Exception e) {
								}
							}
							frame.repaint();
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}

						ans = false;
					}
				};

				t.start();
			}
		}


		/**
		 * This is the mouseClicked func.
		 * You can add new Packmans if you use mouse left click on screen.
		 * You can add new Fruit if you use mouse right click on screen.
		 * @param e - MouseEvent by the button that you use it create new Packman/fruit.
		 */

		int counterboxes=0;
		int x1,x2,y1,y2=-1;
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(width+","+hight);

			if(choose.equals("packman")) {
				int x=e.getX();
				int y=e.getY();
				System.out.println("You create new packman X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input packman speed : ");
				double speed=-1,radius=-1;
				boolean ans=true;
				try {
					speed=Double.parseDouble(test1);
				}catch (NullPointerException n) {ans=false;}
				catch(Exception a) {speed=-1;}
				while(speed<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input packman speed(larger than 0) : ");	
					try {
						speed=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {speed=-1;}
				}
				if(ans) {
					String test2= JOptionPane.showInputDialog("Please input packman radius : ");
					try {
						radius=Double.parseDouble(test2);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {radius=-1;}
					while(radius<=0&&ans) {
						test2= JOptionPane.showInputDialog("Please input packman radius(larger than 0) : ");
						try {
							radius=Double.parseDouble(test2);
						}catch (NullPointerException n) {ans=false;}
						catch(Exception a) {radius=-1;}
					}
				}
				if(ans) {
					Point3D p=map.PixelToCoords(x, y, 0,width,hight);
					Packmanarr.add(new Packman(count,p.x(),p.y(),0, speed, radius));
					count++;
					repaint();
				}
				else 
					System.out.println("you quit before crete new packman");
			}
			else if(choose.equals("fruit")) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("You create new fruit X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input fruit weight : ");
				double weight=-1;
				boolean ans=true;
				try {
					weight=Double.parseDouble(test1);
				}catch(NullPointerException n) {ans=false;}
				catch(Exception a) {weight=-1;}
				while(weight<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input fruit weight(larger than 0) : ");
					try {
						weight=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {weight=-1;}
				}
				if(ans) {
					Point3D p=map.PixelToCoords(x, y,0,width,hight);
					Fruitarr.add(new Fruit(counter,p.x(),p.y(),0,weight));
					counter++;
					repaint();			
				}
				else
					System.out.println("you quit before crete new fruit");
			}
			else if(choose.equals("ghost")) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("You create new ghost X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input ghost speed : ");//לברר על מהירות ורדיוס
				double speed=-1;
				boolean ans=true;
				try {
					speed=Double.parseDouble(test1);
				}catch(NullPointerException n) {ans=false;}
				catch(Exception a) {speed=-1;}
				while(speed<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input ghost speed(larger than 0) : ");
					try {
						speed=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {speed=-1;}
				}
				if(ans) {
					Point3D p=map.PixelToCoords(x, y,0,width,hight);
					Ghostarr.add(new Ghost(ghostcounter,new Point3D(p.x(),p.y(),0),speed,1));//רדיוס 1?
					ghostcounter++;
					repaint();			
				}
				else
					System.out.println("you quit before crete new ghost");
			}
			else if(choose.equals("player")) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("You create new player X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input player speed : ");
				double speed=-1,radius=-1;
				boolean ans=true;
				try {
					speed=Double.parseDouble(test1);
				}catch (NullPointerException n) {ans=false;}
				catch(Exception a) {speed=-1;}
				while(speed<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input player speed(larger than 0) : ");	
					try {
						speed=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {speed=-1;}
				}
				if(ans) {
					String test2= JOptionPane.showInputDialog("Please input player radius : ");
					try {
						radius=Double.parseDouble(test2);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {radius=-1;}
					while(radius<=0&&ans) {
						test2= JOptionPane.showInputDialog("Please input player radius(larger than 0) : ");
						try {
							radius=Double.parseDouble(test2);
						}catch (NullPointerException n) {ans=false;}
						catch(Exception a) {radius=-1;}
					}
				}
				if(ans) {
					Point3D p=map.PixelToCoords(x, y, 0,width,hight);
					player=new Player(0,p.x(),p.y(),0, speed, radius);
					repaint();
				}
				else 
					System.out.println("you quit before crete new player");
			}
			else if(choose.equals("box")) {//לסדר אם שמים קודם ס גדול ואז קטן כנל לגבי y.
				//לסדר גם את זה שאם ממשיכים ללחוץ רק לחיצה אחת אז הוא שם חדש הוא לא מחייב 2.
				System.out.println("For new Box click first the uperleft point, than the rightdown");
				if(counterboxes==0) {
					x1=e.getX();
					y1=e.getY();
					counterboxes++;
				}
				else if(counterboxes==1) {
					x2=e.getX();
					y2=e.getY();
					counterboxes--;
				}
				if(counterboxes>=3)counterboxes=0;
				if(counterboxes<0)counterboxes=1;
				if(x1!=-1&&x2!=-1&&y1!=-1&&y2!=-1) {
					Point3D p=map.PixelToCoords(x1, y1, 0,width,hight);
					Point3D p2=map.PixelToCoords(x2, y2, 0,width,hight);
					Boxarr.add(new Box(boxcounter,new Point3D(p.x(),p2.y(),0),new Point3D(p2.x(),p.y(),0)));
					x1=-1;
					y1=-1;
					counterboxes=0;
					repaint();
				}

			}
			else if(choose.equals("azimuth")) {
				MyCoords my=new MyCoords();
				int x=e.getX();
				int y=e.getY();
				Point3D p=new Point3D(x,y,0);
				p=map.PixelToCoords(x, y, 0, width, hight);
				angle=my.azimuth_elevation_dist(player.getOrinet(),p)[0];
				System.out.println(angle);
				player.setAzimuth(angle);
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	/**
	 * This is the actionPerformed func.
	 * this func can save game,load game,run game,reload the game,save as kml the game and have 2 helps actions how to play and about the game.
	 * @param e - ActionEvent Open you what you want depending on the button you clicked on.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==load) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Game g=new Game();
				g=new Game(g.load(selectedFile.toString()));
				Packmanarr=g.getPackmanArr();
				Fruitarr=g.getFruitArr();
				Ghostarr=g.getGhostarr();
				Boxarr=g.getBoxarr();
			}
		}
		if(e.getSource()==save) {
			Game g=new Game();
			g.save(new Game(Packmanarr,Fruitarr,Ghostarr,Boxarr));
		}
		if(e.getSource()==run) {
			Packmanarrtemp.clear();
			for(int i=0;i<Packmanarr.size();i++) 
				Packmanarrtemp.add(new Packman(Packmanarr.get(i)));
			Game g=new Game(Packmanarr, Fruitarr,Ghostarr,Boxarr);
			ShortestPathAlg s=new ShortestPathAlg();
			System.out.println(s.Shortalgo(g));
			Packmanarr=g.getPackmanArr();
			Fruitarr=g.getFruitArr();
			Fruitarrtemp.clear();
			for(int i=0;i<Fruitarr.size();i++) {
				Fruitarrtemp.add(new Fruit(Fruitarr.get(i)));
			}
			double dist=0;
			double score=0;
			for(int i=0;i<Packmanarr.size();i++) {
				dist=Packmanarr.get(i).getPath().GetDist();	
				score=Packmanarr.get(i).getScore();
				System.out.println("Packman: "+i+" ,distance is: "+dist+" , score is: "+score);
			}

			ans=true;
		}
		if(e.getSource()==how_to_run)
			JOptionPane.showMessageDialog(null, "For new Packman pressed left click on mouse on the place in the map that you want"
					+ ",\nFor new Fruit pressed right click on mouse on the place in the map that you want,"
					+ "\nFor run the game pressed on run button on menu under option,"
					+ "\nIf you want to go back before you run the game click reload button on menu under option,"
					+"\nIf you want to go create a kml and see the path of the packmans click Save as kml button on menu under option.",
					"How to play", JOptionPane.PLAIN_MESSAGE);
		if(e.getSource()==about_the_game) {
			JOptionPane.showMessageDialog(null, "This is a packman game:\n"
					+ "The purpose is to eat all the fruit \n"
					+ "The borad game is map, while the game start you can see on kml the path of the packmans and it"
					+ " prints the min time that we make our packmans eat all the fruit on board."
					+ " \nCreated & Designed by :\nBar Genish and Elyashiv Deri." ,
					"About the game", JOptionPane.PLAIN_MESSAGE);
		}
		if(e.getSource()==reload) {
			Fruitarr.clear();
			for(int i=0;i<Fruitarrtemp.size();i++) {
				Fruitarr.add(new Fruit(Fruitarrtemp.get(i)));
			}
			Packmanarr.clear();
			for(int i=0;i<Packmanarrtemp.size();i++) {
				Packmanarr.add(new Packman(Packmanarrtemp.get(i)));
			}
			ans=false;
		}
		if(e.getSource()==Save_as_kml) {
			Fruitarr.clear();
			for(int i=0;i<Packmanarr.size();i++) {
				Packmanarr.get(i).setOrinet(Packmanarrtemp.get(i).getOrinet());
			}
			Game g=new Game(Packmanarr,Fruitarrtemp,Ghostarr,Boxarr);
			Path2KML p2k=new Path2KML();
			ans=p2k.path2kml(g);
			if(ans)System.out.println("saved int data folder under name:mygamekml.kml");
			else System.out.println("Ops something went weong");
		}
		if(e.getSource()==addbox) 
			choose="box";
		if(e.getSource()==addplayer) 
			choose="player";
		if(e.getSource()==addfruit) 
			choose="fruit";
		if(e.getSource()==addghost)
			choose="ghost";
		if(e.getSource()==addpackman)
			choose="packman";
		if(e.getSource()==azimuth) {
			choose="azimuth";
			anss++;
		}
		panel.repaint();
	}
	public int getmathpath(ArrayList<Packman>arr) {
		int count=0;
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getPath().getArr().size()>count)
				count=arr.get(i).getPath().getArr().size();
		}
		return count;
	}
}
