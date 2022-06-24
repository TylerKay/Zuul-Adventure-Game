                /*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */


import java.util.*;

class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList items;
    private Item item;
    private Enemy enemy;
    private ArrayList enemies;
    private boolean hasEnemies;
    private boolean isLocked;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList();
        enemies = new ArrayList();
        hasEnemies = false;
        isLocked = false;

    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
         exits.put(direction, neighbor);
    }
    public void setExit(String direction1, String direction2, Room neighbor1, Room neighbor2)
    {
         exits.put(direction1, neighbor1);
         exits.put(direction2, neighbor2);
    }
    public void setExit(String direction1, String direction2, String direction3, Room neighbor1, Room neighbor2, Room neighbor3)
    {
         exits.put(direction1, neighbor1);
         exits.put(direction2, neighbor2);
         exits.put(direction3, neighbor3);
    }
    public void setExit(String direction1, String direction2, String direction3, String direction4, Room neighbor1, Room neighbor2, Room neighbor3, Room neighbor4)
    {
         exits.put(direction1, neighbor1);
         exits.put(direction2, neighbor2);
         exits.put(direction3, neighbor3);
         exits.put(direction4, neighbor4);
    }

    /**
     * Return the room that is reached if we go from this
     * room in direction "direction "If there is no room in
     * that direction, return null.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return a description of the room’s exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return the description of the items.
     */
    public String getItemDescription()
    {
        // String returnItems = "Items:";
        String returnItems = "";
        for(int x = 0; x < items.size(); ++x) {
            // grab an item, and add it to the display message
            Item i1 = (Item)items.get(x);
            returnItems += " " +i1.getDescription();
        }
        return returnItems;
    }
    
    /**
     * Return a long description of this room, of the form:
     * You are in the kitchen.
     * Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString()  + " " + getItemDescription();
    }
    
    /**
     * Defines an item in this room.
     */
    public void addItem(Item item)
    {
        //add the item to the arraylist
        items.add(item);
    }
    
    
    public void removeItem(Item item){
        items.remove(item);
    }
    
    public double getItemWeight(String itemName){
        for (int i = 0; i < items.size(); ++i){
            Item item = (Item)items.get(i);
            if (item.getDescription().equals(itemName)){
                return item.getWeight();
            }
        }
        return 0;
    }
    
    public Item getItem(String itemName){
        
        
        for (int i = 0; i < items.size(); ++i){
            Item item = (Item)items.get(i);
            if (item.getDescription().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    
    public void addEnemy(Enemy enemy)
    {
        //add the item to the arraylist
        enemies.add(enemy);
        hasEnemies = true;
    }
    
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    
    public void removeEnemy(Enemy enemy)
    {
        //add the item to the arraylist
        enemies.remove(enemy);
        if (roomHasEnemies() == false){
            hasEnemies = false;
        } else {
            hasEnemies = true;
        }
    }
    
    public boolean roomHasEnemies(){
        return (enemies.size() != 0);
    }
    
    public void hasEnemies(boolean b){
        hasEnemies = b;
    }
    
    public boolean getHasEnemy(){
        return hasEnemies;
    }
    
    public void setIsLocked(boolean b){
        isLocked = b;
    }
    
    public boolean getIsLocked(){
        return isLocked;
    }
    
    
}
    

