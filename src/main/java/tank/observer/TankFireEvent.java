package tank.observer;

import tank.gameObject.Tank;

public class TankFireEvent {
    private Tank tank;

    public Tank getSource() {
        return tank;
    }

    public void setSource(Tank tank) {
        this.tank = tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
