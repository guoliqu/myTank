package tank.collider;

import tank.gameObject.GameObject;

public interface Collider {
    /**
     * 游戏对象碰撞检测
     * @param gameObject1 游戏对象1
     * @param gameObject2 游戏对象2
     * @return
     */
    boolean collide(GameObject gameObject1, GameObject gameObject2);
}
