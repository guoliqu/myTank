package tank;

import tank.gameModel.GameModel;

import java.awt.*;

/**
 * 坦克爆炸
 */
public class Explode {
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int x, y;
	private GameModel gameModel;
	private int step = 0;
	
	public Explode(int x, int y, GameModel gameModel) {
		this.x = x;
		this.y = y;
		this.gameModel = gameModel;

		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}

	/**
	 * 画出爆炸过程
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length) {
			gameModel.getExplodes().remove(this);
		}
	}
	
	

}
