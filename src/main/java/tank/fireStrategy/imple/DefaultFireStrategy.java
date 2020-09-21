package tank.fireStrategy.imple;

import tank.decorator.RectDecorator;
import tank.decorator.TailDecorator;
import tank.gameModel.GameModel;
import tank.gameObject.GameObject;
import tank.utils.Audio;
import tank.gameObject.Bullet;
import tank.gameEnum.Group;
import tank.gameObject.Tank;
import tank.fireStrategy.FireStrategy;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH /2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
//        GameObject gameObject = new TailDecorator(new RectDecorator(new Bullet(bx, by, tank.getDir(), tank.getGroup())));
//        GameModel.getInstance().getGameObjectList().add(gameObject);

        GameModel.getInstance().getGameObjectList().add(new Bullet(bx, by, tank.getDir(), tank.getGroup()));
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
