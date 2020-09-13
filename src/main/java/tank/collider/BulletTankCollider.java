package tank.collider;

import tank.gameObject.Bullet;
import tank.gameObject.Explode;
import tank.gameObject.GameObject;
import tank.gameObject.Tank;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Tank && gameObject2 instanceof Bullet){
            Tank tank = (Tank) gameObject1;
            Bullet bullet = (Bullet) gameObject2;
            return doCollide(tank, bullet);
        }else if (gameObject1 instanceof Bullet && gameObject2 instanceof Tank){
            collide(gameObject2, gameObject1);
        }else {
            return true;
        }
        return false;
    }

    /**
     * 子弹和坦克碰撞判断
     * @param tank 坦克对象
     */
    public boolean doCollide(Tank tank, Bullet bullet) {
        if (bullet.getGroup() == tank.getGroup()){
            return true;
        }

        if(bullet.getRect().intersects(tank.getRect())) {
            tank.die();
            bullet.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tank.getGameModel().getGameObjectList().add(new Explode(eX, eY, tank.getGameModel()));
            return false;
        }

        return true;
    }
}
