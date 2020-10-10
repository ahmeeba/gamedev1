import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//Manager class for player and other npcs when added.

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;  //using arraylist for easy insertion and deletion of entities
   //comparing entities, returning either a -1 or 1
   //-1('a' should be rendered before 'b'), 1('a' should be rendered after 'b')
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            //'a' has a lesser y component, than 'b', so render first
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()){
                return -1;
            }
            return 1;
        }
    };

    //entity manager method
    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player); //adding player to entityList, will get tick and rendered through e.tick, e.render
    }

    //tick method
    public void tick(){
        for(int i = 0 ; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.tick();
        }
        entities.sort(renderSorter); //sorting in new entities when player comes in contact with them
    }


    public void render(Graphics g){
        for(Entity e : entities){ //grabbing the entities in the arraylist and rendering them.
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);  //adding entity to arraylist
    }

    //Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

}
