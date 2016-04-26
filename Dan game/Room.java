import java.util.*;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Room 
{
    private String description;
    private String type;
    private HashMap exits;        // stores exits of this room.
    private HashMap items;
    private HashMap cars;
    private HashMap monsters;
    private HashMap weapons;
    
    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description)
    {
	this.description = description;
	type = "Room";
	exits = new HashMap();
	items = new HashMap();
	cars = new HashMap();
	monsters = new HashMap();
	weapons = new HashMap();
    }

    public Room(String description,String type) 
    {
        this.description = description;
	this.type = type;
        exits = new HashMap();
        items = new HashMap();
        cars = new HashMap();
        monsters = new HashMap();
        weapons = new HashMap();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    
    public void addItem(String name, Item item) 
    {
        items.put(name, item);
    }
    
    public void removeItem(String name)
    {
        items.remove(name);
    }
    
    public void addCar(String name, Car car) 
    {
        cars.put(name, car);
    }
    
    public void removeCar(String name)
    {
        cars.remove(name);
    }
    
    public void addMonster(String name, Monster monster) 
    {
        monsters.put(name, monster);
    }
    
    public void addWeapon(String name, Weapon weapon) 
    {
        items.put(name, weapon);
    }
    
    public Item getItem(String name)
    {
        return (Item) items.get(name);
    }
    
    public Weapon getWeapon(String name)
    {
        return (Weapon) items.get(name);
    }
    
    public Armour getArmour(String name)
    {
        return (Armour) items.get(name);
    }
    
    public Monster getMonster(String name)
    {
        return (Monster) monsters.get(name);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    public String getType()
    {
	return type;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + description + "" + getExitString() + "" + getItemString() + "" + getCarString() + "" + getMonsterString() + "" + getWeaponString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        if (!exits.isEmpty())
        {
            String returnString = "\nExits:";
            Set keys = exits.keySet();
            for(Iterator iter = keys.iterator(); iter.hasNext(); )
                returnString += " " + iter.next();
            return returnString;
        }
        else
            return "";
    }
    
    private String getItemString()
    {
        if (!items.isEmpty())
        {
            String returnString = "\n\nItems:";
            Set keys = items.keySet();
            for(Iterator iter = keys.iterator(); iter.hasNext(); )
                returnString += "\n" + iter.next();
            return returnString;
            }
        else
            return "";
    }
    
    private String getWeaponString()
    {
        if (!weapons.isEmpty())
        {
            String returnString = "\n\nWeapons:";
            Set keys = weapons.keySet();
            for(Iterator iter = keys.iterator(); iter.hasNext(); )
                returnString += "\n" + iter.next();
            return returnString;
            }
        else
            return "";
    }
    
    private String getMonsterString()
    {
        if (!monsters.isEmpty())
        {
            String returnString = "\nMonsters:";
            Set keys = monsters.keySet();
            for(Iterator iter = keys.iterator(); iter.hasNext(); )
                returnString += " " + iter.next();
            return returnString;
        }
        else
            return "";
    }
    
    public void refreshMonster()
    {
        if (!monsters.isEmpty())
        {
            Monster monster = new Monster();
            Set keys = monsters.keySet();
            for(Iterator iter = keys.iterator(); iter.hasNext(); )
               {
               monster = (Monster) iter.next();
               monster.refreshHealth();
               }
        }
        
            
    }
    
    private String getCarString()
    {
        if (!cars.isEmpty())
        {
            String returnString = "\nCars:";
            Set keys = cars.keySet();
            for(Iterator iter = keys.iterator(); iter.hasNext(); )
                returnString += " " + iter.next();
            return returnString;
        }
        else
            return "";
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return (Room)exits.get(direction);
    }
}

