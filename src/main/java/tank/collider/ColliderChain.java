package tank.collider;

import tank.gameObject.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        colliders.add(new BulletTankCollider());
        colliders.add(new TankAndTankCollider());
    }

    /**
     * 游戏对象碰撞检测
     *
     * @param gameObject1 游戏对象1
     * @param gameObject2 游戏对象2
     * @return
     */
    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        for (Collider collider: colliders) {
            boolean doNext = collider.collide(gameObject1, gameObject2);
            if (!doNext){
                return false;
            }
        }
        return false;
    }
}
