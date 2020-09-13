package tank;

import tank.gameModel.GameModel;

import java.awt.*;

/**
 * 子弹对象
 */
public class Bullet {
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
     * x
     */
    private int x;
    /**
     * y
     */
    private int y;
    /**
     * 方向
     */
    private Dir dir;
    /**
     * gameModel
     */
    private GameModel gameModel;
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
    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, GameModel gameModel, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
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
    public void paint(Graphics g) {
        if(!living) {
            gameModel.getBullets().remove(this);
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
     * 子弹和坦克碰撞判断
     * @param tank 坦克对象
     */
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()){
            return;
        }

        if(rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            gameModel.getExplodes().add(new Explode(eX, eY, gameModel));
        }

    }

    /**
     * 子弹死亡
     */
    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
