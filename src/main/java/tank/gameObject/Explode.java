package tank.gameObject;

import tank.utils.Audio;
import tank.utils.ResourceMgr;
import tank.gameModel.GameModel;

import java.awt.*;

/**
 * 坦克爆炸
 */
public class Explode extends GameObject{
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int step = 0;
	
	public Explode(int x, int y) {
		this.x = x;
		this.y = y;

		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}

	/**
	 * 画出爆炸过程
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length) {
			GameModel.getInstance().getGameObjectList().remove(this);
		}
	}
	
	

}
