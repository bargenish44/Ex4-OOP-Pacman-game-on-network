package Packman_Game;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;
import Robot.Play;
import Coords.MyCoords;
import Geom.Point3D;

public class MyFrame implements ActionListener{
	/**
	 * This class is the graphic class and use all the project methods. 
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private Game game;
	private ArrayList<Packman>Packmanarrtemp=new ArrayList<>();
	private ArrayList<Fruit>Fruitarrtemp=new ArrayList<>();
	private Player player;
	private boolean ans=false;
	private double angle=0;
	private ImageIcon packmanimage,cherryimage,ghostimage,playerimage;
	private int counter=0,count=0,boxcounter=0,ghostcounter=0,azimuthcount=-1;
	private String choose="";
	private JMenuItem load,save,run,how_to_run,about_the_game,reload,Save_as_kml,addpackman,addfruit,addghost,addbox,addplayer,Play_alone,StartGame,azimuth;
	private JMenuBar menubar;
	private JMenu menu2,menu,menu3;
	private Image img;
	private int width,hight;
	private JFrame frame;
	private Map map;
	private ImagePanel panel;
	private Play play1;
	private String map_data,info,file_name;
	private String[]str;
	private ArrayList<String> board_data;
	private MyCoords mycords;
//	private boolean gameruns=false;

	public static void main(String[] args) {
		new MyFrame();
	}
	public MyFrame(){//constractor
		try {
			mycords=new MyCoords();
			file_name = "data/Ex4_OOP_example9.csv";
			play1 = new Play(file_name);
			play1.setIDs(3131,745,83);
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
			StartGame=new JMenuItem("Start Game");
			StartGame.addActionListener(this);
			Play_alone=new JMenuItem("Play alone");
			Play_alone.addActionListener(this);
			menu2.add(run);
			menu2.add(Play_alone);
			menu2.add(StartGame);
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
			for(int i=0;i<game.getPackmanArr().size();i++) {
				Point3D p=map.CoordsToPixel(game.getPackmanArr().get(i).getOrinet(),width,hight);
				g.drawImage(packmanimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}
			for(int i=0;i<game.getFruitArr().size();i++) {
				Point3D p=map.CoordsToPixel(game.getFruitArr().get(i).getOrient(),width,hight);
				g.drawImage(cherryimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}


			for(int i=0;i<game.getGhostarr().size();i++) {
				Point3D p=map.CoordsToPixel(game.getGhostarr().get(i).getPos(),width,hight);
				g.drawImage(ghostimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}
			try {
				Point3D p=map.CoordsToPixel(game.getPlayer().getOrinet(),width,hight);
				g.drawImage(playerimage.getImage(), p.ix()-15, p.iy()-15,30,30,null);
			}
			catch(NullPointerException e) {}
			Graphics2D g2=(Graphics2D)g;

			for(int i=0;i<game.getBoxarr().size();i++) {
				Point3D p=map.CoordsToPixel(game.getBoxarr().get(i).getLeftDown(),width,hight);
				Point3D p2=map.CoordsToPixel(game.getBoxarr().get(i).getRightUp(),width,hight);
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
						int count=getmathpath(game.getPackmanArr());
						MyCoords mc = new MyCoords();
						for (int j = 0; j < count; j++)
						{
							for (int i = 0; i < game.getPackmanArr().size(); i++) 
							{
								try
								{
									game.getPackmanArr().get(i).setOrinet(game.getPackmanArr().get(i).getPath().getArr().get(j));
									for ( int k = 0; k < game.getPackmanArr().size(); k++) {
										if(mc.distance3d(game.getPackmanArr().get(i).getOrinet(),game.getFruitArr().get(k).getOrient())<3) {
											game.getFruitArr().remove(k);
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
					game.getPackmanArr().add(new Packman(count,p.x(),p.y(),0, speed, radius));
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
					game.getFruitArr().add(new Fruit(counter,p.x(),p.y(),0,weight));
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
					game.getGhostarr().add(new Ghost(ghostcounter,new Point3D(p.x(),p.y(),0),speed,1));//רדיוס 1?
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
					game.getBoxarr().add(new Box(boxcounter,new Point3D(p.x(),p2.y(),0),new Point3D(p2.x(),p.y(),0)));
					x1=-1;
					y1=-1;
					counterboxes=0;
					repaint();
				}

			}
			else if(choose.equals("Play_alone")) {
				if(!play1.isRuning())
					System.out.println("You should start game first");
				else {
					if(azimuthcount<10) {
						int x=e.getX();
						int y=e.getY();
						Point3D p=new Point3D(x,y,0);
						p=map.PixelToCoords(x, y, 0, width, hight);
						angle=mycords.azimuth_elevation_dist(game.getPlayer().getOrinet(),p)[0];
						System.out.println(angle);
						game.getPlayer().setAzimuth(angle);
						play1.rotate(game.getPlayer().getAzimuth());
						System.out.println("***** "+game.getPlayer().getAzimuth()+"******");

						// 7.2) get the current score of the game
						info = play1.getStatistics();
						System.out.println(info);
						// 7.3) get the game-board current state
						board_data = play1.getBoard();
						for(int a=0;a<board_data.size();a++) {
							System.out.println(board_data.get(a));
						}
						System.out.println();
						game=game.loadstring(board_data);
						azimuthcount++;
						System.out.println(play1.getStatistics());
						frame.repaint();
					}
					while(azimuthcount>=10&&play1.isRuning()) 
						azimuthcount-=10;
					if(!play1.isRuning()&&azimuthcount!=-1) {
						play1.stop();
						System.out.println("**** Done Game (user stop) ****");

						// 9) print the data & save to the course DB
						String info = play1.getStatistics();
						System.out.println(info);
					}
				}
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
				game.setPackmanArr(g.getPackmanArr());
				game.setFruitArr(g.getFruitArr());
				game.setGhostarr(g.getGhostarr());
				game.setBoxarr(g.getBoxarr());
			}
		}
		if(e.getSource()==save) {
			Game g=new Game();
			g.save(new Game(game.getPackmanArr(),game.getFruitArr(),game.getGhostarr(),game.getBoxarr()));
		}
		if(e.getSource()==run) {
			Packmanarrtemp.clear();
			for(int i=0;i<game.getPackmanArr().size();i++) 
				Packmanarrtemp.add(new Packman(game.getPackmanArr().get(i)));
			Game g=new Game(game.getPackmanArr(), game.getFruitArr(),game.getGhostarr(),game.getBoxarr());
			ShortestPathAlg s=new ShortestPathAlg();
			System.out.println(s.Shortalgo(g));
			game.setPackmanArr(g.getPackmanArr());
			game.setFruitArr(g.getFruitArr());
			Fruitarrtemp.clear();
			for(int i=0;i<game.getFruitArr().size();i++) {
				Fruitarrtemp.add(new Fruit(game.getFruitArr().get(i)));
			}
			double dist=0;
			double score=0;
			for(int i=0;i<game.getPackmanArr().size();i++) {
				dist=game.getPackmanArr().get(i).getPath().GetDist();	
				score=game.getPackmanArr().get(i).getScore();
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
			game.getFruitArr().clear();
			for(int i=0;i<Fruitarrtemp.size();i++) {
				game.getFruitArr().add(new Fruit(Fruitarrtemp.get(i)));
			}
			game.getPackmanArr().clear();
			for(int i=0;i<Packmanarrtemp.size();i++) {
				game.getPackmanArr().add(new Packman(Packmanarrtemp.get(i)));
			}
			ans=false;
		}
		if(e.getSource()==Save_as_kml) {
			game.getFruitArr().clear();
			for(int i=0;i<game.getPackmanArr().size();i++) {
				game.getPackmanArr().get(i).setOrinet(Packmanarrtemp.get(i).getOrinet());
			}
			Game g=new Game(game.getPackmanArr(),Fruitarrtemp,game.getGhostarr(),game.getBoxarr());
			Path2KML p2k=new Path2KML();
			ans=p2k.path2kml(g);
			if(ans)System.out.println("saved int data folder under name:mygamekml.kml");
			else System.out.println("Ops something went weong");
		}
		if(e.getSource()==StartGame) {
			map_data = play1.getBoundingBox();
			System.out.println("Bounding Box info: "+map_data);
			str=map_data.split(",");
			board_data = play1.getBoard();
			for(int i=0;i<board_data.size();i++) {
				System.out.println(board_data.get(i));
			}
			System.out.println();
			game=game.loadstring(board_data);
			System.out.println("Init Player Location should be set using the bounding box info");
			play1.setInitLocation(32.1040,35.2061);
			game.getPlayer().setOrinet(new Point3D(35.2061,32.1040));
			azimuthcount=0;
			play1.start();
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
		if(e.getSource()==Play_alone)
			choose="Play_alone";
//		if(e.getSource()==azimuth)
//			choose="azimuth";
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