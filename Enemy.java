

/**
 * A Enemy in Zuul.
 *
 * Tyler Kay
 *
 */

import java.util.*;

public class Enemy
{
    private String name;
    private int health;
    private int attackDamage;
    private boolean isEnderDragon;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy()
    {
        // initialise instance variables
        name = "";
        health = 20;
        attackDamage = 0;
        isEnderDragon = false;
    }
    
    public Enemy(String n, int h, int dmg)
    {
        // initialise instance variables
        name = n;
        health = h;
        attackDamage = dmg;
        
    }

    public void setName(String n)
    {
        name = n;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setHealth(int h)
    {
        health = h;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public void setAttackDamage(int dmg){
        attackDamage = dmg;
    }
    
    public int getAttackDamage(){
        return attackDamage;
    }
    
    public void setIsEnderDragon(boolean b){
        isEnderDragon = b;
    }
    
    public boolean getIsEnderDragon(){
        return isEnderDragon;
    }
}
