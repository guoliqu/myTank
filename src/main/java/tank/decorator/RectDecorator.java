package tank.decorator;

import tank.gameObject.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics graphics) {
        this.x = gameObject.x;
        this.y = gameObject.y;

        gameObject.paint(graphics);

        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawRect(super.gameObject.x, super.gameObject.y, super.gameObject.getWidth(), super.gameObject.getHeight());
        graphics.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObject.getHeight();
    }
}
