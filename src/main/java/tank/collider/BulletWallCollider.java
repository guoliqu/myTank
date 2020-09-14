package tank.collider;

import tank.gameObject.Bullet;
import tank.gameObject.GameObject;
import tank.gameObject.Wall;

public class BulletWallCollider implements Collider {

    /**
     * 游戏对象碰撞检测
     *
     * @param gameObject1 游戏对象1
     * @param gameObject2 游戏对象2
     * @return
     */
    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Bullet && gameObject2 instanceof Wall) {
            Bullet b = (Bullet)gameObject1;
            Wall w = (Wall)gameObject2;

            if(b.getRect().intersects(w.getRect())) {
                b.die();
            }

        } else if (gameObject1 instanceof Wall && gameObject2 instanceof Bullet) {
            return collide(gameObject2, gameObject1);
        }
        return true;
    }
}
