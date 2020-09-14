package tank.gameObject;

import tank.utils.ResourceMgr;
import tank.TankFrame;
import tank.gameEnum.Dir;
import tank.gameEnum.Group;
import tank.gameModel.GameModel;

import java.awt.*;

/**
 * 子弹对象
 */
public class Bullet extends GameObject{
    /**
     * 子弹速度
     */
    private static final int SPEED = 8;
    /**
     * 子弹宽度
     */
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    /**
     * 子弹长度
     */
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    /**
     * 方向
     */
    private Dir dir;
    /**
     * 是否存活（是否有效）
     */
    private boolean living = true;
    /**
     * 敌我分组
     */
    private Group group;
    /**
     * 子弹所在的矩形
     */
    private Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

    }

    /**
     * 画出子弹位置
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if(!living) {
            GameModel.getInstance().getGameObjectList().remove(this);
            return;
        }
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        // 移动
        move();
    }

    /**
     * 子弹移动
     */
    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        // 子弹超出屏幕失效
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    /**
     * 子弹死亡
     */
    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
