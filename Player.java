

/**
 * A Player in Zuul.
 *
 * Tyler Kay
 *
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Player
{
    private String currentRoom;
    private ArrayList<String> previousRooms;
    private HashMap<String, Item> itemsCarried;
    private double weightOfCarriedItems;
    private int weightCapacityOfCarriedItems;
    private int health;
    private int attackDamage;
    private Weapon weapon;
    
    
    /**
     * Constructor for objects of class Player
     */
    
    public Player(String curr_room)
    {
        String currentRoom = curr_room;
        itemsCarried = new HashMap<>();
        weightOfCarriedItems = 0;
        weightCapacityOfCarriedItems = 50; // Initialize "bag" capacity of player to 50.
        health = 100;
        attackDamage = 4;
        weapon = null;
    }
    public void setCurrentRoom(String curr_room){
        previousRooms.add(currentRoom);
        currentRoom = curr_room;
    }
    
    public String getCurrentRoom(){
        return currentRoom;
    }
    
    public void setWeightOfCarriedItems(double n_itemWeight){
        weightOfCarriedItems = n_itemWeight;
    }
    
    public double getWeightOfCarriedItems(){
        return weightOfCarriedItems;
    }
    
    public void addItem(Item item){
        itemsCarried.put(item.getDescription(), item);
    }
    
    public void dropItem(String itemName){
        double inventoryWeight = weightOfCarriedItems - itemsCarried.get(itemName).getWeight();
        setWeightOfCarriedItems(inventoryWeight);
        System.out.println("Inventory weight is now " + getWeightOfCarriedItems());
        Item item = getFromInventory(itemName);
        
        if (item.equals(weapon)){
            setAttackDamage(getAttackDamage() - weapon.getDamageIncrease()); // Decrease attack damage
            weapon = null;
        }
        itemsCarried.remove(itemName);
    }
    
    public String getInventory(){
        String inventory = "You're carrying:";
        for (String item : itemsCarried.keySet()){
            inventory += " " + item + " : " + itemsCarried.get(item).getWeight() + "\n";
        }
        return inventory;
    }
    
    public boolean isInInventory(String itemName){
        return (itemsCarried.get(itemName) != null);
    }
    
    public Item getFromInventory(String itemName){
        return itemsCarried.get(itemName);
    }
    
    public void setWeightCapacityOfCarriedItems(int capacity){
        weightCapacityOfCarriedItems = capacity;
    }
    
    public int getWeightCapacityOfCarriedItems(){
        return weightCapacityOfCarriedItems;
    }
    
    public void setHealth(int h){
        health = h;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setAttackDamage(int dmg){
        attackDamage = dmg;
    }
    
    public int getAttackDamage(){
        return attackDamage;
    }
    
    public String equip(String itemName){
        if (isInInventory(itemName)){
            if (itemsCarried.get(itemName) instanceof Weapon) {
                weapon = (Weapon)itemsCarried.get(itemName);
            } else {
                return itemName + " is not a weapon";
            }
            setAttackDamage(getAttackDamage() + getWeaponDamageIncrease());
            return "Equipped " + itemName;
        } else {
            return "You do not have "+ itemName +" in your inventory";
        }
    }
    
    public int getWeaponDamageIncrease(){
        if (weapon == null){
            return 0;
        } else {
            return weapon.getDamageIncrease();
        }
    }

}
