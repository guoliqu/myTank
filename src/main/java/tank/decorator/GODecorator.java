package tank.decorator;

import tank.gameObject.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    GameObject gameObject;

    public GODecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public abstract void paint(Graphics graphics);
}
