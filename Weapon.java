
/**
 * An Weapon in Zuul.
 *
 * Tyler Kay
 *
 */
import java.lang.*;

public class Weapon extends Item
{
    // instance variables
    private int damageIncrease;
    
    /**
     * Constructor for objects of class Weapon.
     */
    public Weapon(String description, double weight, int dmgInc)
    {
        // initialise instance variables
        super(description, weight);
        damageIncrease = dmgInc;
    }
    
    public int getDamageIncrease(){
        return damageIncrease;
    }

    
}
