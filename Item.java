
/**
 * An item in Zuul.
 *
 * Tyler Kay
 *
 */


public class Item
{
    // instance variables
    private String description;
    private double weight;
    private boolean isPickedUp;
    
    /**
     * Constructor for objects of class Item.
     */
    
    public Item()
    {
        // initialise instance variables
        this.description = ""; 
        this.weight = 0.0;
        isPickedUp = false;
    }
    
    public Item(String description, double weight)
    {
        // initialise instance variables
        this.description = description; 
        this.weight = weight;
        isPickedUp = false;
    }

    /**
     * Return the description of the item.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Returnt the weight of the item.
     */
    public double getWeight()
    {
        return weight;
    }
    
    public void setIsPickedUp(boolean b){
        isPickedUp = b;
    }
    
    public boolean getIsPickedUp(){
        return isPickedUp;
    }
    
}
