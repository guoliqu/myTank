package tank.gameObject;

import java.awt.*;

public class Wall extends GameObject{

    private int w;

    private int h;

    private Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(x, y, w, h);
        graphics.setColor(c);
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
