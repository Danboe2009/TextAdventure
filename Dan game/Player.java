import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author Dan Boehmke 
 * @version (a version number or a date)
 */
public class Player extends Character
{
    private HashMap cars;
    private HashMap weapons;
    private Weapon currentWeapon;
    public Armour currentArmour;
       
    public Player(String name, int level)
    {
        money = 2000;
        alignment = 0;
        items = new HashMap();
        cars = new HashMap();
	weapons = new HashMap();
        this.name = name;
        this.level = level;
        strength = level * 3;
        hp = strength * 5;
        rand = new Random();
    }
    
    public void addItem(String name, Item item) 
    {
        items.put(name, item);
    }
    
    public void addToExp(int exp)
    {
        experience = experience + exp;
        if (exp >= (100*level))
        level++;
    }

    public void addWeapon(String name, Weapon weapon)
    {
	weapons.put(name, weapon);
    }
    
    public int getExp()
    {
        return experience;
    }
    
    public void subtractMoney(double value)
    {
        if(money >= value)
        money = money - value;
        
    }
    
    public void changeAlignment(int change)
    {
        alignment = alignment + change;
    }
    
    public String getAlignment()
    {
        if(alignment < 0)
        return "evil";
        else if (alignment == 0)
        return "neutral";
        else
        return "good";
    }
    
    public double getMoney()
    {
        return money;
    }
    
    public int getHealth()
    {
        if (hp <= 0)
        {
            return 0;
        }
        return hp;
    }
    
    public int getAttack()
    {
        int bonus = currentWeapon.getAdvantage();
        return strength * 2 + bonus + getRand(3)-getRand(3);
    }
    
    public Weapon getWeapon(String name)
    {
        return (Weapon) weapons.get(name);
    }
    
    public Armour getArmour(String name)
    {
        return (Armour) weapons.get(name);
    }
    
    public void addMoney(double value)
    {
        money = money + value;
    }
    
    public void removeItem(String name)
    {
        items.remove(name);
    }
    
    public void subtractHealth(int value)
    {
        hp = hp - value;
    }
    
    public void addCar(String name, Car car) 
    {
        cars.put(name, car);
    }
    
    public void removeCar(String name)
    {
        cars.remove(name);
    }
    
    public boolean getMiss()
    {
        int miss = getRand(9);
        if (miss == 0)
        return true;
        else
        return false;
    }
    
    public void setWeapon(Weapon weapon)
    {
        currentWeapon = weapon;
    }
    
    public void setArmour(Armour armour)
    {
        currentArmour = armour;
    }
    
     public int getRand(int x)
    {
        int ran = rand.nextInt(x);
        return ran;
    }
    
    public String itemDescription(String item)
    {
        currentItem=getItem(item);
        return currentItem.getLongDescription();
    }
    
    public String getLongDescription()
    {
        return "Your name is " + name + ".\n" + "You can lift " + strength + "lb." +"\n" + "You are "+ getAlignment()+"(" + alignment + ")" 
            + "\nYour weapon is " + currentWeapon.getDescription() +"(" + "+" + currentWeapon.getAdvantage() + ")" + "\nYour armour is " 
            + currentArmour.getDescription() + "(" + "+" + currentArmour.getAdvantage() + ")";
    }
    
    public String getInventory()
    {
        return getItemString() + "\n" + getWeapons() + "\n" + "you have " + getMoney() + " titanium";
    }
    
    private String getItemString()
    {
        String returnString = "Items:";
        Set keys = items.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }
    
    public String getItems()
    {
        String returnString = "";
        Set keys = items.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += "\n" + iter.next();
        return returnString;
    }
    
    public String getCars()
    {
        String returnString = "";
        Set keys = cars.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }
    
    public String getWeapons()
    {
	String returnString = "Weapons";
	Set keys = weapons.keySet();
	for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += "" + iter.next();
	return returnString;
    }
    
    public Item getItem(String name)
    {
        return (Item) items.get(name);
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getStrength()
    {
        return strength;
    }
}
