package Packman_Game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;
import Algorithm.ShortestPathAlg;
import Algorithm.Shortestfruitalg;
import Algorithm.findbestpoint;
import Robot.Play;
import Coords.MyCoords;
import Geom.Point3D;

public class MyFrame implements ActionListener {
	/**
	 * This class is the graphic class and use all the project methods.
	 * 
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	private Game game;
	private ArrayList<Packman> Packmanarrtemp = new ArrayList<>();
	private ArrayList<Fruit> Fruitarrtemp = new ArrayList<>();
	private boolean ans = false, isBoxPress = false, playerinsert = false;
	private double angle = 0;
	private int counter = 0, count = 0, boxcounter = 0, ghostcounter = 0, azimuthcount = -1;
	private String choose = "";
	private JMenuItem clear, load, save, run, how_to_run, about_the_game, reload, Save_as_kml, addpackman, addfruit,
			addghost, addbox, addplayer, Play_alone, StartGame, Play_automatic;
	private JMenuBar menubar;
	private JMenu menu2, menu, menu3;
	private Image img;
	private int width, hight;
	private JFrame frame;
	private Map map;
	private ImagePanel panel;
	private Play play1;
	private String map_data, info, file_name;
	private ArrayList<String> board_data;
	private boolean gameruns = false;
	private GameTimer timer;
	private JMenuItem cheak, betweencheck, betweencheck2;

	public static void main(String[] args) {
		new MyFrame();
	}

	public MyFrame() {// constractor
		try {
			timer = new GameTimer();
			file_name = "data/Ex4_OOP_example9.csv";
			play1 = new Play(file_name);
			play1.setIDs(3131, 745, 83);
			map = new Map();
			img = ImageIO.read(new File(map.getMap()));
			frame = new JFrame("OOP-EX4");
			menubar = new JMenuBar();
			menu = new JMenu("Help");
			menubar.add(menu);
			about_the_game = new JMenuItem("About the game");
			about_the_game.addActionListener(this);
			menu.add(about_the_game);
			how_to_run = new JMenuItem("How to run");
			how_to_run.addActionListener(this);
			menu.add(how_to_run);
			menu2 = new JMenu("Option");
			reload = new JMenuItem("Reload");
			reload.addActionListener(this);
			clear = new JMenuItem("Clear");
			clear.addActionListener(this);
			menu2.add(reload);
			menu2.add(clear);
			Save_as_kml = new JMenuItem("Save as kml");
			Save_as_kml.addActionListener(this);
			menu2.add(Save_as_kml);
			load = new JMenuItem("Load");
			load.addActionListener(this);
			menu2.add(load);
			save = new JMenuItem("Save");
			save.addActionListener(this);
			menu2.add(save);
			run = new JMenuItem("Run(eats the fruits)");
			run.addActionListener(this);
			StartGame = new JMenuItem("Start Game");
			StartGame.addActionListener(this);
			Play_alone = new JMenuItem("Play alone");
			Play_alone.addActionListener(this);
			Play_automatic = new JMenuItem("Play automatic");
			Play_automatic.addActionListener(this);
			cheak = new JMenuItem("cheak");
			cheak.addActionListener(this);
			betweencheck = new JMenuItem("betweencheck");
			betweencheck.addActionListener(this);
			betweencheck2 = new JMenuItem("betweencheck2");
			betweencheck2.addActionListener(this);
			menu2.add(betweencheck);
			menu2.add(betweencheck2);
			menu2.add(cheak);
			menu2.add(run);
			menu2.add(Play_alone);
			menu2.add(Play_automatic);
			menu2.add(StartGame);
			menubar.add(menu2);
			menu3 = new JMenu("Add");
			addpackman = new JMenuItem("Packman");
			addpackman.addActionListener(this);
			addfruit = new JMenuItem("Fruit");
			addfruit.addActionListener(this);
			addghost = new JMenuItem("Ghost");
			addghost.addActionListener(this);
			addbox = new JMenuItem("Box");
			addbox.addActionListener(this);
			addplayer = new JMenuItem("Player");
			addplayer.addActionListener(this);
			menu3.add(addbox);
			menu3.add(addfruit);
			menu3.add(addpackman);
			menu3.add(addghost);
			menu3.add(addplayer);
			menubar.add(menu3);
			frame.setJMenuBar(menubar);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			panel = new ImagePanel(img);
			frame.add(panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			game = new Game();

		} catch (IOException | HeadlessException exp) {
			exp.printStackTrace();
		}

	}

	public void setGame(Game g) {
		game = g;
	}

	class ImagePanel extends JPanel implements MouseListener {

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
		 * 
		 * @param g - Graphics that we want to paint.
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			for (int i = 0; i < game.getPackmanArr().size(); i++) {
				Point3D p = map.CoordsToPixel(game.getPackmanArr().get(i).getOrinet(), width, hight);
				g.drawImage(game.getPackmanArr().get(i).getPackmanimage().getImage(), p.ix() - 15, p.iy() - 15, 30, 30,
						null);
			}
			for (int i = 0; i < game.getFruitArr().size(); i++) {
				Point3D p = map.CoordsToPixel(game.getFruitArr().get(i).getOrient(), width, hight);
				g.drawImage(game.getFruitArr().get(i).getFruitimage().getImage(), p.ix() - 15, p.iy() - 15, 30, 30,
						null);
			}
			for (int i = 0; i < game.getGhostarr().size(); i++) {
				Point3D p = map.CoordsToPixel(game.getGhostarr().get(i).getPos(), width, hight);
				g.drawImage(game.getGhostarr().get(i).getGhostimage().getImage(), p.ix() - 15, p.iy() - 15, 30, 30,
						null);
			}
			try {
				Point3D p = map.CoordsToPixel(game.getPlayer().getOrinet(), width, hight);
				g.drawImage(game.getPlayer().getPlayerimage().getImage(), p.ix() - 15, p.iy() - 15, 30, 30, null);
			} catch (NullPointerException e) {
			}
			Graphics2D g2 = (Graphics2D) g;

			for (int i = 0; i < game.getBoxarr().size(); i++) {
				Point3D p = map.CoordsToPixel(game.getBoxarr().get(i).getLeftDown(), width, hight);
				Point3D p2 = map.CoordsToPixel(game.getBoxarr().get(i).getRightUp(), width, hight);
				g2.setColor(Color.black);
				g2.drawRect(p2.ix(), p.iy(), Math.abs(p2.ix() - p.ix()), Math.abs(p2.iy() - p.iy()));
				g2.fillRect(p2.ix(), p.iy(), Math.abs(p2.ix() - p.ix()), Math.abs(p2.iy() - p.iy()));

			}
			if (gameruns) {
				g.setColor(Color.CYAN);
				int fontSize = 20;
				g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
				g.drawString(play1.getStatistics(), 15, 15);
			}
			if (ans) {
				ans = false;
				Thread t = new Thread() {
					public void run() {
						int count = getmathpath(game.getPackmanArr());
						MyCoords mc = new MyCoords();
						for (int j = 0; j < count; j++) {
							for (int i = 0; i < game.getPackmanArr().size(); i++) {
								try {
									game.getPackmanArr().get(i)
											.setOrinet(game.getPackmanArr().get(i).getPath().getArr().get(j));
									for (int k = 0; k < game.getPackmanArr().size(); k++) {
										if (mc.distance3d(game.getPackmanArr().get(i).getOrinet(),
												game.getFruitArr().get(k).getOrient()) < 3) {
											game.getFruitArr().remove(k);
										}
									}
								} catch (Exception e) {
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
		 * This is the mouseClicked func. You can add new Packmans if you use mouse left
		 * click on screen. You can add new Fruit if you use mouse right click on
		 * screen.
		 * 
		 * @param e - MouseEvent by the button that you use it create new Packman/fruit.
		 */

		int counterboxes = 0;
		int x1, x2, y1, y2 = -1;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (choose.equals("packman")) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("You create new packman X: " + x + " Y: " + y);
				String test1 = JOptionPane.showInputDialog("Please input packman speed : ");
				double speed = -1, radius = -1;
				boolean ans = true;
				try {
					speed = Double.parseDouble(test1);
				} catch (NullPointerException n) {
					ans = false;
				} catch (Exception a) {
					speed = -1;
				}
				while (speed <= 0 && ans) {
					test1 = JOptionPane.showInputDialog("Please input packman speed(larger than 0) : ");
					try {
						speed = Double.parseDouble(test1);
					} catch (NullPointerException n) {
						ans = false;
					} catch (Exception a) {
						speed = -1;
					}
				}
				if (ans) {
					String test2 = JOptionPane.showInputDialog("Please input packman radius : ");
					try {
						radius = Double.parseDouble(test2);
					} catch (NullPointerException n) {
						ans = false;
					} catch (Exception a) {
						radius = -1;
					}
					while (radius <= 0 && ans) {
						test2 = JOptionPane.showInputDialog("Please input packman radius(larger than 0) : ");
						try {
							radius = Double.parseDouble(test2);
						} catch (NullPointerException n) {
							ans = false;
						} catch (Exception a) {
							radius = -1;
						}
					}
				}
				if (ans) {
					Point3D p = map.PixelToCoords(x, y, 0, width, hight);
					game.getPackmanArr().add(new Packman(count, p.x(), p.y(), 0, speed, radius));
					count++;
					repaint();
				} else
					System.out.println("you quit before crete new packman");
			} else if (choose.equals("fruit")) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("You create new fruit X: " + x + " Y: " + y);
				String test1 = JOptionPane.showInputDialog("Please input fruit weight : ");
				double weight = -1;
				boolean ans = true;
				try {
					weight = Double.parseDouble(test1);
				} catch (NullPointerException n) {
					ans = false;
				} catch (Exception a) {
					weight = -1;
				}
				while (weight <= 0 && ans) {
					test1 = JOptionPane.showInputDialog("Please input fruit weight(larger than 0) : ");
					try {
						weight = Double.parseDouble(test1);
					} catch (NullPointerException n) {
						ans = false;
					} catch (Exception a) {
						weight = -1;
					}
				}
				if (ans) {
					Point3D p = map.PixelToCoords(x, y, 0, width, hight);
					game.getFruitArr().add(new Fruit(counter, p.x(), p.y(), 0, weight));
					counter++;
					repaint();
				} else
					System.out.println("you quit before crete new fruit");
			} else if (choose.equals("ghost")) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("You create new ghost X: " + x + " Y: " + y);
				String test1 = JOptionPane.showInputDialog("Please input ghost speed : ");// לברר על מהירות ורדיוס
				double speed = -1;
				boolean ans = true;
				try {
					speed = Double.parseDouble(test1);
				} catch (NullPointerException n) {
					ans = false;
				} catch (Exception a) {
					speed = -1;
				}
				while (speed <= 0 && ans) {
					test1 = JOptionPane.showInputDialog("Please input ghost speed(larger than 0) : ");
					try {
						speed = Double.parseDouble(test1);
					} catch (NullPointerException n) {
						ans = false;
					} catch (Exception a) {
						speed = -1;
					}
				}
				if (ans) {
					Point3D p = map.PixelToCoords(x, y, 0, width, hight);
					game.getGhostarr().add(new Ghost(ghostcounter, new Point3D(p.x(), p.y(), 0), speed, 1));// רדיוס 1?
					ghostcounter++;
					repaint();
				} else
					System.out.println("you quit before crete new ghost");
			} else if (choose.equals("player")) {
				if (gameruns) {
					int x = e.getX();
					int y = e.getY();
					Point3D p = map.PixelToCoords(x, y, 0, width, hight);
					play1.setInitLocation(p.y(), p.x());
					game.setPlayer(new Player(0, p.x(), p.y(), 0, 20, 1));
					board_data = play1.getBoard();
					for (int a = 0; a < board_data.size(); a++) {
						System.out.println(board_data.get(a));
					}
					System.out.println();
					playerinsert = true;
					play1.start();
					repaint();
				} else {
					int x = e.getX();
					int y = e.getY();
					System.out.println("You create new player X: " + x + " Y: " + y);
					String test1 = JOptionPane.showInputDialog("Please input player speed : ");
					double speed = -1, radius = -1;
					boolean ans = true;
					try {
						speed = Double.parseDouble(test1);
					} catch (NullPointerException n) {
						ans = false;
					} catch (Exception a) {
						speed = -1;
					}
					while (speed <= 0 && ans) {
						test1 = JOptionPane.showInputDialog("Please input player speed(larger than 0) : ");
						try {
							speed = Double.parseDouble(test1);
						} catch (NullPointerException n) {
							ans = false;
						} catch (Exception a) {
							speed = -1;
						}
					}
					if (ans) {
						String test2 = JOptionPane.showInputDialog("Please input player radius : ");
						try {
							radius = Double.parseDouble(test2);
						} catch (NullPointerException n) {
							ans = false;
						} catch (Exception a) {
							radius = -1;
						}
						while (radius <= 0 && ans) {
							test2 = JOptionPane.showInputDialog("Please input player radius(larger than 0) : ");
							try {
								radius = Double.parseDouble(test2);
							} catch (NullPointerException n) {
								ans = false;
							} catch (Exception a) {
								radius = -1;
							}
						}
					}
					if (ans) {
						Point3D p = map.PixelToCoords(x, y, 0, width, hight);
						game.setPlayer(new Player(0, p.x(), p.y(), 0, speed, radius));
						repaint();
					} else
						System.out.println("you quit before crete new player");
				}
			} else if (choose.equals("Play_alone")) {
				if (!gameruns)
					System.out.println("You should start game first");
				else {
					if (!playerinsert) {
						System.out.println("You should insert player first");
					} else if (azimuthcount < 10) {
						try {
							timer.endTimer();
						} catch (Exception ex) {
						}
						int x = e.getX();
						int y = e.getY();
						Shortestfruitalg alg = new Shortestfruitalg(game);
						Point3D p = new Point3D(x, y, 0);
						p = map.PixelToCoords(x, y, 0, width, hight);
						angle = map.azimuth_elevation_dist(game.getPlayer().getOrinet(), p)[0];
						System.out.println(angle);
						double tmp = alg.Go2Fruit();
						if (tmp != -1)
							angle = tmp;
						game.getPlayer().setAzimuth(angle);
						play1.rotate(game.getPlayer().getAzimuth());
						System.out.println("***** " + game.getPlayer().getAzimuth() + "******");

						// 7.2) get the current score of the game
						info = play1.getStatistics();
						System.out.println(info);
						// 7.3) get the game-board current state
						board_data = play1.getBoard();
						for (int a = 0; a < board_data.size(); a++) {
							System.out.println(board_data.get(a));
						}
						System.out.println();
						game = game.loadstring(board_data);
						azimuthcount++;
						System.out.println(play1.getStatistics());
						frame.repaint();
						timer.startTimer(task);
					}
					while (azimuthcount >= 10 && play1.isRuning())
						azimuthcount -= 10;
					if (!play1.isRuning() && azimuthcount != -1) {
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

		Point3D p = null;

		@Override
		public void mousePressed(MouseEvent e) {
			if (isBoxPress) {
				x1 = e.getX();
				y1 = e.getY();
				p = map.PixelToCoords(x1, y1, 0, width, hight);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			Point3D p2 = map.PixelToCoords(x2, y2, 0, width, hight);
			game.getBoxarr().add(new Box(boxcounter, new Point3D(p.x(), p2.y(), 0), new Point3D(p2.x(), p.y(), 0)));
			repaint();
			isBoxPress = false;
		}

	}

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			if (!gameruns)
				System.out.println("You should start game first");
			else {
				if (!playerinsert) {
					System.out.println("You should insert player first");
				} else if (azimuthcount < 10) {
					Shortestfruitalg alg = new Shortestfruitalg(game);
					angle = game.getPlayer().getAzimuth();
					double tmp = alg.Go2Fruit();
					if (tmp != -1)
						angle = tmp;
					game.getPlayer().setAzimuth(angle);
					play1.rotate(game.getPlayer().getAzimuth());
					System.out.println("***** " + game.getPlayer().getAzimuth() + "******");

					// 7.2) get the current score of the game
					info = play1.getStatistics();
					System.out.println(info);
					// 7.3) get the game-board current state
					board_data = play1.getBoard();
					for (int a = 0; a < board_data.size(); a++) {
						System.out.println(board_data.get(a));
					}
					System.out.println();
					game = game.loadstring(board_data);
					azimuthcount++;
					System.out.println(play1.getStatistics());
					frame.repaint();
				}
				while (azimuthcount >= 10 && play1.isRuning())
					azimuthcount -= 10;
				if (!play1.isRuning() && azimuthcount != -1) {
					play1.stop();
					System.out.println("**** Done Game (user stop) ****");

					// 9) print the data & save to the course DB
					String info = play1.getStatistics();
					System.out.println(info);
				}
			}
		}
	};

	/**
	 * This is the actionPerformed func. this func can save game,load game,run
	 * game,reload the game,save as kml the game and have 2 helps actions how to
	 * play and about the game.
	 * 
	 * @param e - ActionEvent Open you what you want depending on the button you
	 *          clicked on.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == load) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Game g = new Game();
				g = new Game(g.load(selectedFile.toString()));
				game.setPackmanArr(g.getPackmanArr());
				game.setFruitArr(g.getFruitArr());
				game.setGhostarr(g.getGhostarr());
				game.setBoxarr(g.getBoxarr());
			}
		}
		if (e.getSource() == save) {
			Game g = new Game();
			g.save(new Game(game.getPackmanArr(), game.getFruitArr(), game.getGhostarr(), game.getBoxarr()));
		}
		if (e.getSource() == run) {
			Packmanarrtemp.clear();
			for (int i = 0; i < game.getPackmanArr().size(); i++)
				Packmanarrtemp.add(new Packman(game.getPackmanArr().get(i)));
			Game g = new Game(game.getPackmanArr(), game.getFruitArr(), game.getGhostarr(), game.getBoxarr());
			ShortestPathAlg s = new ShortestPathAlg();
			System.out.println(s.Shortalgo(g));
			game.setPackmanArr(g.getPackmanArr());
			game.setFruitArr(g.getFruitArr());
			Fruitarrtemp.clear();
			for (int i = 0; i < game.getFruitArr().size(); i++) {
				Fruitarrtemp.add(new Fruit(game.getFruitArr().get(i)));
			}
			double dist = 0;
			double score = 0;
			for (int i = 0; i < game.getPackmanArr().size(); i++) {
				dist = game.getPackmanArr().get(i).getPath().GetDist();
				score = game.getPackmanArr().get(i).getScore();
				System.out.println("Packman: " + i + " ,distance is: " + dist + " , score is: " + score);
			}

			ans = true;
		}
		if (e.getSource() == how_to_run)
			JOptionPane.showMessageDialog(null,
					"For new Packman pressed left click on mouse on the place in the map that you want"
							+ ",\nFor new Fruit pressed right click on mouse on the place in the map that you want,"
							+ "\nFor run the game pressed on run button on menu under option,"
							+ "\nIf you want to go back before you run the game click reload button on menu under option,"
							+ "\nIf you want to go create a kml and see the path of the packmans click Save as kml button on menu under option.",
					"How to play", JOptionPane.PLAIN_MESSAGE);
		if (e.getSource() == about_the_game) {
			JOptionPane.showMessageDialog(null, "This is a packman game:\n" + "The purpose is to eat all the fruit \n"
					+ "The borad game is map, while the game start you can see on kml the path of the packmans and it"
					+ " prints the min time that we make our packmans eat all the fruit on board."
					+ " \nCreated & Designed by :\nBar Genish and Elyashiv Deri.", "About the game",
					JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource() == reload) {
			game.getFruitArr().clear();
			for (int i = 0; i < Fruitarrtemp.size(); i++) {
				game.getFruitArr().add(new Fruit(Fruitarrtemp.get(i)));
			}
			game.getPackmanArr().clear();
			for (int i = 0; i < Packmanarrtemp.size(); i++) {
				game.getPackmanArr().add(new Packman(Packmanarrtemp.get(i)));
			}
			ans = false;
		}
		if (e.getSource() == Save_as_kml) {
			game.getFruitArr().clear();
			for (int i = 0; i < game.getPackmanArr().size(); i++) {
				game.getPackmanArr().get(i).setOrinet(Packmanarrtemp.get(i).getOrinet());
			}
			Game g = new Game(game.getPackmanArr(), Fruitarrtemp, game.getGhostarr(), game.getBoxarr());
			Path2KML p2k = new Path2KML();
			ans = p2k.path2kml(g);
			if (ans)
				System.out.println("saved int data folder under name:mygamekml.kml");
			else
				System.out.println("Ops something went weong");
		}
		if (e.getSource() == StartGame) {
			map_data = play1.getBoundingBox();
			System.out.println("Bounding Box info: " + map_data);
			board_data = play1.getBoard();
			for (int i = 0; i < board_data.size(); i++) {
				System.out.println(board_data.get(i));
			}
			System.out.println();
			game = game.loadstring(board_data);
			System.out.println("Init Player Location should be set using the bounding box info");
			azimuthcount = 0;
			gameruns = true;
		}
		if (e.getSource() == addbox) {
			choose = "box";
			isBoxPress = true;
		}
		if (e.getSource() == addplayer)
			choose = "player";
		if (e.getSource() == addfruit)
			choose = "fruit";
		if (e.getSource() == addghost)
			choose = "ghost";
		if (e.getSource() == addpackman)
			choose = "packman";
		if (e.getSource() == Play_alone) {
			choose = "Play_alone";
		}
		if (e.getSource() == clear) {
			game = new Game();
			gameruns = false;
		}
		if (e.getSource() == cheak) {
			findbestpoint find = new findbestpoint(game);
			Point3D p = find.findspot(width, hight);
			play1.setInitLocation(p.y(), p.x());
			game.setPlayer(new Player(0, p.x(), p.y(), 0, 20, 1));
			board_data = play1.getBoard();
			for (int a = 0; a < board_data.size(); a++) {
				System.out.println(board_data.get(a));
			}
			System.out.println();
			playerinsert = true;
			play1.start();
			frame.repaint();
		}
		if (e.getSource() == Play_automatic) {
			findbestpoint find = new findbestpoint(game);
			Point3D p = find.findspot(width, hight);
			play1.setInitLocation(p.y(), p.x());
			game.setPlayer(new Player(0, p.x(), p.y(), 0, 20, 1));
			board_data = play1.getBoard();
			for (int a = 0; a < board_data.size(); a++) {
				System.out.println(board_data.get(a));
			}
			System.out.println();
			playerinsert = true;
			play1.start();
			frame.repaint();
			Shortestfruitalg algo = new Shortestfruitalg(game);
			Thread t = new Thread() {
				public void run() {
					while (play1.isRuning()) {
						Fruit f = algo.algowithoutboxes();
						angle = map.azimuth_elevation_dist(game.getPlayer().getOrinet(), f.getOrient())[0];
						Shortestfruitalg alg = new Shortestfruitalg(game);
						double tmp = alg.escapefroomguest(game.getPlayer().getOrinet(), f);
						if (tmp != -1)
							angle = tmp;
						game.getPlayer().setAzimuth(angle);
						play1.rotate(game.getPlayer().getAzimuth());
						game.getPlayer().setAzimuth(angle);
						play1.rotate(game.getPlayer().getAzimuth());
						// 7.2) get the current score of the game
						info = play1.getStatistics();
						System.out.println(info);
						// 7.3) get the game-board current state
						board_data = play1.getBoard();
						for (int a = 0; a < board_data.size(); a++) {
							System.out.println(board_data.get(a));
						}
						System.out.println();
						game = game.loadstring(board_data);
						algo.setGame(game);
						System.out.println(play1.getStatistics());
						frame.repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// 9) print the data & save to the course DB
					String info = play1.getStatistics();
					System.out.println(info);
					System.out.println("end");
				}
			};
			t.start();
		}
		if (e.getSource() == betweencheck) {
			Shortestfruitalg boxs = new Shortestfruitalg(game);
			System.out.println(boxs.LineofSight(game.getFruitArr().get(0), width, hight));
		}
		if (e.getSource() == betweencheck2)
			game = game.load("C:\\Users\\barge\\eclipse-workspace\\Ex4-OOP\\data\\cheacks.csv");
		panel.repaint();
	}

	public int getmathpath(ArrayList<Packman> arr) {
		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).getPath().getArr().size() > count)
				count = arr.get(i).getPath().getArr().size();
		}
		return count;
	}
}