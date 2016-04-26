
/**
 * Write a description of class Armour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Armour extends Item

{
    
    public Armour(String name, String description, int weight, int value)
    {
        super(name,description,weight,value);
        name=name;
        price=weight;
        advantage=value;
        stat = "armour";
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getStat()
    {
        return stat;
    }
    
    public int getPrice()
    {
        return price;
    }
    public int getAdvantage()
    {
       return advantage; 
    }
}
