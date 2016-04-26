
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    public int weight;
    public String name;
    public String description;
    public int value;
    public int price;
	public int advantage;
	public String stat;

    public Item(String name, String description, int weight, int value)
    {
        this.name = name;
        this.value = value;
        this.description = description;
        this.weight = weight;
        stat = "item";
    }
    
    public String getLongDescription()
    {
        return "The item " + name + " has a weight " + weight + " and is described as " + description + " with a value of " + value+" titanium";
    }
      
    public String getStat()
    {
        return stat;
    }
       
    public int getValue()
    {
        return value;
    }
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
}
