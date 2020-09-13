package tank.fireStrategy.imple;

import tank.fireStrategy.FireStrategy;
import tank.gameEnum.Dir;
import tank.gameEnum.Group;
import tank.gameObject.Bullet;
import tank.gameObject.Tank;
import tank.utils.Audio;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH /2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
            tank.getGameModel().getGameObjectList().add(new Bullet(bx, by, dir, tank.getGameModel(), tank.getGroup()));
        }
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
