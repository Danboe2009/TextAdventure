import java.util.*;
/**
 * Write a description of class Battle here.
 * 
 * @author Dan Boehmke 
 * @version (a version number or a date)
 */
public class Battle
{
    private Monster enemy; 
    private Player currentPlayer;
    private boolean life;
    private ArrayList battle;
    
    public Battle(Player currentPlayer, Monster enemy)
    {
        this.currentPlayer = currentPlayer;
        this.enemy = enemy;
        battle = new ArrayList();
        life = true;
	enemy.setFull();
    }
    
    public String fight()
    {
        while (currentPlayer.getHealth()>0 && enemy.getHealth()>0)
        {
            if (currentPlayer.getHealth()>0)
            {
                attack();
            }
                        
            if (enemy.getHealth()>0)
            {
                getAttacked(currentPlayer);
                if(currentPlayer.getHealth()<=0) 
                {
                battle.add(enemy.getName()+ " has defeated " + currentPlayer.getName()+"\n");
                die();
                }
            }
            else
            {
                battle.add(currentPlayer.getName()+ " has defeated " + enemy.getName()+"\n");
                kill();
            }
        }
        return getBattleString();
        
    }
    public void attack()
    {
        if (!currentPlayer.getMiss())
        {
            enemy.subtractHealth(currentPlayer.getAttack());
            battle.add(currentPlayer.getName() + " attacks for " + currentPlayer.getAttack() + " damage leaving " + enemy.getHealth() + " hp left \n");
        }
        else
            battle.add(currentPlayer.getName() + " has missed. \n");
    }
    
    public void getAttacked(Player player)
    {
        
        currentPlayer.subtractHealth(enemy.getAttack(player));
        battle.add(enemy.getName() + " attacks for " + enemy.getAttack(player) + " damage leaving " + currentPlayer.getHealth() + " hp left \n");
        
    }
    public void die()
    {
        battle.add("you died");
        life = false;
    }
    
    public boolean getLife()
    {
        return life;
    }
    
    private String getBattleString()
    {
          
        String returnString = "";
        for(Iterator iter = battle.iterator(); iter.hasNext(); )
            returnString += "" + iter.next();
        return returnString;
    }
    
    public void kill()
    {
        double money = enemy.getMoney(); 
        battle.add("you won " + money + " Titanium");
        currentPlayer.addMoney(money);
	
    }

}
