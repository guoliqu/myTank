package tank.collider;

import tank.gameObject.GameObject;
import tank.gameObject.Tank;
import tank.gameObject.Wall;

public class TankWallCollider implements Collider {

    /**
     * 游戏对象碰撞检测
     *
     * @param gameObject1 游戏对象1
     * @param gameObject2 游戏对象2
     * @return
     */
    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Tank && gameObject2 instanceof Wall) {
            Tank t = (Tank)gameObject1;
            Wall w = (Wall)gameObject2;


            if(t.getRect().intersects(w.getRect())) {
                t.back();
            }

        } else if (gameObject1 instanceof Wall && gameObject2 instanceof Tank) {
            return collide(gameObject2, gameObject1);
        }
        return true;
    }
}
