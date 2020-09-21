package tank.decorator;

import tank.gameObject.GameObject;

import java.awt.Color;
import java.awt.Graphics;

public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics graphics) {
        this.x = gameObject.x;
        this.y = gameObject.y;

        gameObject.paint(graphics);

        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawLine(gameObject.x, gameObject.y, gameObject.x + getWidth(), gameObject.y + getHeight());
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
