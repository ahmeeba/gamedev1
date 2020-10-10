
//Creature class is for entities that move, static-entity is for entities that can't move.
//Static so it can't change, StaticEntity does not change/move.. place trees, rocks, etc in here.
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }


}
