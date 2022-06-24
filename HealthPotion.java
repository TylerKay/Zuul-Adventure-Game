
/**
 * An Health Potion in Zuul.
 *
 * Tyler Kay
 *
 */
import java.lang.*;

public class HealthPotion extends Item
{
    // instance variables
    private int hpAmount;
    private String description;
    private double weight;
    private boolean isPickedUp;
    
    /**
     * Constructor for objects of class Item.
     */
    public HealthPotion(String description, double weight, int hp)
    {
        // initialise instance variables
        super(description, weight);
        hpAmount = hp;
    }
    
    public int getHPAmount(){
        return hpAmount;
    }

    
}
