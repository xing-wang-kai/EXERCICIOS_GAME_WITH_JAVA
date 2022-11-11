import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private boolean isRunning;
	private Thread thread;
	
	private JFrame frame;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	
	private BufferedImage image;
	
	private SpriteSheet sheet;
	private ArrayList<BufferedImage> players;
	
	private int frames = 0;
	private int maxFrames = 20;
	
	private int currencyAnimator = 0;
	private int MaxCurrencyAnimator = 3;
	
	public Game() {
		
		this.players = new ArrayList<BufferedImage>();
		this.sheet = new SpriteSheet("/arteHeroinaSprit.png");
		this.players.add(this.sheet.GetSprite(0, 0, 16, 16));
		this.players.add(this.sheet.GetSprite(16, 0, 16, 16));
		this.players.add(this.sheet.GetSprite(32, 0, 16, 16));
		this.players.add(this.sheet.GetSprite(48, 0, 16, 16));
		
		this.setPreferredSize(new Dimension(this.WIDTH*this.SCALE, this.HEIGHT*this.SCALE));
		this.SetFrame();
		this.image = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.Start();
	}
	
	public synchronized void Start() 
	{
		this.isRunning = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public synchronized void Stop()
	{
		this.isRunning = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Update() 
	{
		this.frames ++;
		if(frames >= this.maxFrames)
		{
			this.frames = 0;
			this.currencyAnimator ++;
			if(this.currencyAnimator > this.MaxCurrencyAnimator)
			{
				this.currencyAnimator = 0;
			}
		}
		
	}
	
	public void Render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graph = image.getGraphics();
		graph.setColor(new Color(0, 0, 0));
		graph.fillRect(0, 0, 160, 120);
		
		graph.setColor(new Color(255, 0, 0));
		graph.fillOval(10, 20, 50, 50);
		
		graph.setColor(new Color(255, 200, 0));
		graph.fillOval(80, 40, 50, 30);
		
		graph.setFont(new Font("Calibri", Font.BOLD, 12));
		graph.setColor(Color.white);
		graph.drawString(String.format("My Game\n with Java"), 5, 20);
		
		/**Render the player of the**/
		
		Graphics2D graph2D = (Graphics2D) graph;
		
		/** Adiciona uma layer escura no projeto **/
		graph2D.setColor(new Color(0,0,0,100));
		graph2D.fillRect(0, 0, WIDTH, HEIGHT);
		
		//graph2D.rotate(Math.toRadians(90),90+7,90);//look rotation for Player
		graph2D.drawImage(this.players.get(this.currencyAnimator), 10, 30, null);
		
		/***/
		
		graph.dispose();
		graph = bs.getDrawGraphics();
		graph.drawImage(image, 0, 0, this.WIDTH*this.SCALE, this.HEIGHT*this.SCALE, null);
		bs.show();
	}
	
	public void run() 
	{
		long lastTime = System.nanoTime();
		double amountUpdate = 60.0;
		double ns = 1000000000/ amountUpdate;
		double delta = 0;
		
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) 
		{
			long nowTime = System.nanoTime();
			delta += (nowTime - lastTime) / ns;
			lastTime = nowTime;
			
			if(delta>=1) 
			{
				this.Update();
				this.Render();
				delta --;
				
				frames ++;
			}
			if(System.currentTimeMillis() - timer >= 1000)
			{
				System.out.println("FPS : " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		this.Stop();
	}
	
	public void SetFrame() 
	{
		this.frame = new JFrame("GAME #001");
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}	
}
