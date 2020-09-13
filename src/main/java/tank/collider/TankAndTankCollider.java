package tank.collider;

import tank.gameEnum.Dir;
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
            backToLastPosition(tank1);
            // tank2 change dir
            backToLastPosition(tank2);
        }
        return true;
    }

    /**
     * 回到上个位置
     * @param tank
     */
    private void backToLastPosition(Tank tank){
        tank.setX(tank.getPreX());
        tank.setY(tank.getPreY());
    }
}
