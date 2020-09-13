package tank.gameModel;

import tank.gameEnum.Dir;
import tank.gameEnum.Group;
import tank.gameObject.Bullet;
import tank.gameObject.Explode;
import tank.gameObject.Tank;
import tank.utils.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    /**
     * 子弹集合
     */
    private List<Bullet> bullets = new ArrayList<>();
    /**
     * 敌方坦克集合
     */
    private List<Tank> tanks = new ArrayList<>();
    /**
     * 爆炸集合
     */
    private List<Explode> explodes = new ArrayList<>();
    /**
     * 主坦克/我的坦克
     */
    private Tank myTank = new Tank(200, 400, false, Dir.UP, this, Group.GOOD);

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + i * 80, 200, true, Dir.DOWN, this, Group.BAD));
        }
    }


    /**
     * 画出场景内容
     * @param graphics
     */
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量:" + bullets.size(), 10, 60);
        graphics.drawString("敌人的数量:" + tanks.size(), 10, 80);
        graphics.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        graphics.setColor(c);

        myTank.paint(graphics);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphics);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(graphics);
        }

        for(int i=0; i<bullets.size(); i++) {
            for(int j = 0; j<tanks.size(); j++)
                bullets.get(i).collideWith(tanks.get(j));
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(graphics);
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }
}
