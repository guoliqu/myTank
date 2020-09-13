package tank.gameModel;

import tank.collider.BulletTankCollider;
import tank.collider.Collider;
import tank.collider.ColliderChain;
import tank.collider.TankAndTankCollider;
import tank.gameEnum.Dir;
import tank.gameEnum.Group;
import tank.gameObject.GameObject;
import tank.gameObject.Tank;
import tank.utils.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameModel {
    ColliderChain colliderChain = new ColliderChain();
    /**
     * 游戏对象集合
     */
    private List<GameObject> gameObjectList = new ArrayList<>();
    /**
     * 主坦克/我的坦克
     */
    private Tank myTank = new Tank(200, 400, false, Dir.UP, this, Group.GOOD);

    public GameModel() {
        int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            gameObjectList.add(new Tank(50 + i * 80, 200, true, Dir.DOWN, this, Group.BAD));
        }
    }

    /**
     * 画出场景内容
     * @param graphics
     */
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("游戏对象集合:" + gameObjectList.size(), 10, 60);
        graphics.setColor(c);

        myTank.paint(graphics);

        for (int i = 0; i < gameObjectList.size(); i++) {
            gameObjectList.get(i).paint(graphics);
        }

        //互相碰撞
        for(int i=0; i< gameObjectList.size(); i++) {
            for(int j=i+1; j< gameObjectList.size(); j++) {
                GameObject o1 = gameObjectList.get(i);
                GameObject o2 = gameObjectList.get(j);
                //for
                colliderChain.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }
}
