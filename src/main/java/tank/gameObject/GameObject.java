package tank.gameObject;

import java.awt.*;

public abstract class GameObject {

    public int x;

    public int y;

    public abstract void paint(Graphics graphics);

    public abstract int getWidth();

    public abstract int getHeight();
}
