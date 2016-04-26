
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car 
{
    private int horsePower;
    private String name;
    private String description;
    
    public Car(String name, String description, int horsePower)
    {
        this.name = name;
        this.description = description;
        this.horsePower = horsePower;
    }

    
    public String getLongDescription()
    {
        return "The Car " + name + " has "+ horsePower+ "horse power and is described as " + description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getHorsePower()
    {
        return horsePower;
    }
    
}
