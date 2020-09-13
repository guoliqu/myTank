package tank.fireStrategy.imple;

import tank.Audio;
import tank.Bullet;
import tank.Group;
import tank.Tank;
import tank.fireStrategy.FireStrategy;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH /2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tank.getGameModel().getBullets().add(new Bullet(bx, by, tank.getDir(), tank.getGameModel(), tank.getGroup()));
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
