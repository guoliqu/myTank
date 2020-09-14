package tank.collider;

import tank.gameObject.GameObject;
import tank.gameObject.Tank;

public class TankAndTankCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Tank && gameObject2 instanceof Tank){
            Tank tank1 = (Tank) gameObject1;
            Tank tank2 = (Tank) gameObject2;
            doCollide(tank1, tank2);
        }
        return true;
    }

    /**
     * 坦克和坦克碰撞判断
     * @param tank1 坦克对象
     * @param tank2 坦克对象
     */
    public boolean doCollide(Tank tank1, Tank tank2) {
        if(tank1.getRect().intersects(tank2.getRect())) {
            // 直接掉头
            // tank1 change dir
            tank1.back();
            // tank2 change dir
            tank2.back();
        }
        return true;
    }
}
