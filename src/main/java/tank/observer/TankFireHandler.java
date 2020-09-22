package tank.observer;

import tank.gameObject.Tank;

public class TankFireHandler implements TankFireObserver{
    @Override
    public void actionOnFire(TankFireEvent tankFireEvent) {
        Tank tank = tankFireEvent.getSource();
        System.out.println("fire");
        tank.fire();
    }
}
