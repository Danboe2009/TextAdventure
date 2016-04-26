import java.util.*;
/**
 * Write a description of class Monsters here.
 * 
 * @author Dan Boehmke
 * @version (a version number or a date)
 */
public class Monster extends Character

{
    public Monster(String name, int level)
    {
        money = 100;
        hp = strength * 5;
        items = new HashMap();
        this.name = name;
        this.level = level;
        strength = level * 2;
        hp = strength * 3;
        rand = new Random();
    }
     public Monster()
    {
        
    }
    
    public String getName()
    {
        return name;
    }
    
    public void subtractHealth(int value)
    {
        hp = hp - value;
    }
    
    public void refreshHealth()
    {
        hp = strength * 3;
    }
    
    public int getExp()
    {
        return level * 30;
    }
    
    public int getRand(int x)
    {
        int ran = rand.nextInt(x);
        return ran;
    }
    
    public int getHealth()
    {
        if (hp <= 0)
        {
            return 0;
        }
        return hp;
    }
    
    public void setFull()
    {
	hp = strength * 3;
    }

    public double getMoney()
    {
        return money + getRand(32);
    }
    
    public int getAttack(Player currentPlayer)
    {
        int bonus = currentPlayer.currentArmour.getAdvantage();
        return strength * 2 - bonus + getRand(3)-getRand(3);
    }
    
    public int getStrength()
    {
        return strength;
    }
}
