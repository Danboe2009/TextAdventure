import java.io.*;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Dan Boehmke and Matt Musser
 * @version 0.2
 */

public class Game 
{
    public static void main(String [] args)
	{
	Game start = new Game();
	start.play();
	}
	
    public void init()
	{
	Game start = new Game();
	start.play();
	}
	
    private Parser parser;
    private Room currentRoom;
    private Player currentPlayer;
    private Battle currentBattle;
    private String item;
    private String car;
    private Monster monster;
    private Weapon weapon;
    private Armour armour;
    private Room outside, theatre, pub, lab, office, 
            home, garage, 
            acenter, 
            carlot, newLot, usedLot, 
            dungeon, 
            town, bar, hotel, weaponShop, armourShop;
            
    private Item bench;
    private Car skyline;
    private Monster dragon;
    private Weapon hand, pen, steakKnife;
    private Armour naked;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        
        
        currentPlayer = new Player("Dan",1);
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        home = new Room("at home");
        garage = new Room("in your private garage");
        
        acenter = new Room("in the town center");
        
        carlot = new Room("at a car lot");
        newLot = new Room("the new car lot");
        usedLot = new Room("the used car lot");
        
        dungeon = new Room("a dungeon");
        
        town = new Room("in DanTown");
        bar = new Room("the local bar");
        hotel = new Room("a hotel");
        weaponShop = new Room("a local weapon shop", "shop");
        armourShop = new Room("a local armour shop", "shop");
        
        //create the items
        bench = new Item("bench", "a bench", 500, 1000);
        
        //create the weapons
        hand = new Weapon("hand", "your hand for punching", 0 ,0);
        pen = new Weapon("pen", "pen for stabing", 150, 1);
        steakKnife = new Weapon("steak", "a Steak Knife" , 250, 3);

        //create the armour
        naked = new Armour("naked", "your bare skin", 0, 0);
                
        //create the cars
        skyline = new Car("Car", "A Nissan Skyline", 250);
        
        //create the monsters
        dragon = new Monster("Dragon", 1);
        
        // initialise room exits
        acenter.setExit("home", home);
        acenter.setExit("university", outside);
        acenter.setExit("carlot", carlot);
        acenter.setExit("dungeon", dungeon);
        acenter.setExit("town",town);
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", garage);
        outside.setExit("center", acenter);        
        theatre.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        
        home.setExit("center", acenter);
        home.setExit("garage", garage);
        garage.setExit("home", home);
        
        carlot.setExit("center",acenter);
        carlot.setExit("newlot",newLot);
        carlot.setExit("usedlot",usedLot);
        newLot.setExit("offices",carlot);
        newLot.setExit("usedlot",usedLot);
        usedLot.setExit("newlot",newLot);
        usedLot.setExit("offices",carlot);
        
        dungeon.setExit("center",acenter);
        
        town.setExit("center",acenter);
        town.setExit("bar",bar);
        town.setExit("hotel",hotel);
        town.setExit("weaponshop",weaponShop);
        town.setExit("armourshop",armourShop);
        bar.setExit("town",town);
        bar.setExit("hotel",hotel);
        bar.setExit("weaponshop",weaponShop);
        bar.setExit("armourshop",armourShop);
        hotel.setExit("town",town);
        hotel.setExit("bar",bar);
        hotel.setExit("weaponshop",weaponShop);
        hotel.setExit("armourshop",armourShop);
        weaponShop.setExit("town",town);
        weaponShop.setExit("hotel",hotel);
        weaponShop.setExit("bar",bar);
        weaponShop.setExit("armourshop",armourShop);
        armourShop.setExit("town",town);
        armourShop.setExit("hotel",hotel);
        armourShop.setExit("weaponshop",weaponShop);
        armourShop.setExit("bar",bar);
               
        //initialise room items
        garage.addCar("skyline", skyline);
        acenter.addItem("bench", bench);
        
        //initialise room weapons
        weaponShop.addWeapon("pen", pen);
        weaponShop.addWeapon("knife",steakKnife);  
       
        //initialise room monsters
        dungeon.addMonster("dragon", dragon);

        currentRoom = acenter;
        currentPlayer.setWeapon(hand);
        currentPlayer.setArmour(naked);
        
        
        // start game outside
        currentBattle = new Battle(currentPlayer, dragon);
    }
    
    private void addMonsters()
    {
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished && currentBattle.getLife()) {
            
           Command command = parser.getCommand();
           finished = processCommand(command);
            
        }
        System.out.println("Thank you for playing.  Good bye " + currentPlayer.getName() + ".");
        try {
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentPlayer.getName() + ".txt"), "UTF8"));
        out.write("Players name is "+ currentPlayer.getName()+"\n");
        out.write(currentPlayer.getName() + " has a strength of " + currentPlayer.getStrength()+"\n");
        out.write(currentPlayer.getName() + " has these items: " + currentPlayer.getItems()+"\n");
        out.write(currentPlayer.getName() + " has these cars : " + currentPlayer.getCars()+"\n");
        out.close();}
        catch (UnsupportedEncodingException e) {    }
        catch (IOException e) {}
        
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {        
        System.out.println();
        System.out.println("Welcome to Dan's Game of Awesomosity!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("moveto"))
            goRoom(command);
        else if (commandWord.equals("look"))
            printLook();
        else if (commandWord.equals("sell"))
            sell(command);
        else if (commandWord.equals("quit")) 
            wantToQuit = quit(command);
        else if (commandWord.equals("battle")) 
            battle(command);
        else if (commandWord.equals("equip")) 
            equip(command);
        else if (commandWord.equals("steal")) 
            steal(command);
        else if (commandWord.equals("stat")) 
            stat();
        else if (commandWord.equals("examine")) 
            examine(command);
        else if (commandWord.equals("inventory")) 
            inventory();   
        else if (commandWord.equals("drop")) 
            drop(command);
        else if (commandWord.equals("buy"))
            buy(command);
        
        return wantToQuit;
    }

    // implementations of user commands:
    private void printLook() 
    {
        System.out.println(currentRoom.getLongDescription());
    }
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    private void battle(Command command)
    {   
        String enemy = command.getSecondWord();
        if (currentRoom.getMonster(enemy)!= null)
        {
            monster = currentRoom.getMonster(enemy);
            currentBattle = new Battle(currentPlayer,monster);
            System.out.println(currentBattle.fight());
	}
	else
	    System.out.println("no such monster to fight");
    }
        
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        
    
    }
    
    private void inventory() 
    {
        System.out.println(currentPlayer.getInventory());
    }
    
    private void stat() 
    {
        System.out.println(currentPlayer.getLongDescription());
    }
    
    private void equip(Command command) 
    {
        item = command.getSecondWord();
	if(currentPlayer.getWeapon(item) != null)
        {
        	if(currentPlayer.getWeapon(item).getStat() == "weapon")
        	{
            	     currentPlayer.setWeapon(currentPlayer.getWeapon(item));
            	     System.out.println("you equiped " + item);
        	}
        	else if(currentPlayer.getItem(item).getStat() == "armour")
        	{
            	     currentPlayer.setArmour(currentPlayer.getArmour(item));
            	     System.out.println("you equiped " + item);
        	}
        	else
        	     System.out.println("can not equip that item");
        }
	else
		System.out.println("you dont have that item");
    }
    
    private void examine(Command command) 
    {
        item = command.getSecondWord();
        System.out.println (currentPlayer.itemDescription(item));
    }
    
    private void steal(Command command) 
    {
        item = command.getSecondWord();
        currentPlayer.addItem(item, currentRoom.getItem(item));
        currentRoom.removeItem(item);
        currentPlayer.changeAlignment(-5);
        System.out.println("you have picked up " + item);
        
    }
    
    private void drop(Command command) 
    {
        item = command.getSecondWord();
        currentRoom.addItem(item, currentPlayer.getItem(item));
        currentPlayer.removeItem(item);
        System.out.println("you have dropped " + item);
    }
    
    private void buy(Command command)
    {
        item = command.getSecondWord();
        System.out.println(item);
        if(currentRoom.getItem(item).getStat() == "item")
        {
            if(currentRoom.getItem(item) != null)
            {
                if(currentRoom.getItem(item).getValue() <= currentPlayer.getMoney())
                {
                    currentPlayer.addItem(item, currentRoom.getItem(item));
                    currentPlayer.subtractMoney(currentRoom.getItem(item).getValue());
                    System.out.println("you bought " + item);
                }
                else
                    System.out.println("you do not have enough titanium.");
            }
                else
                System.out.println("no such item");
        }
        else if(currentRoom.getItem(item).getStat() == "weapon")
        {
            if(currentRoom.getWeapon(item) != null)
            {
                if(currentRoom.getWeapon(item).getValue() <= currentPlayer.getMoney())
                {
                    currentPlayer.addWeapon(item, currentRoom.getWeapon(item));
                    currentPlayer.subtractMoney(currentRoom.getWeapon(item).getValue());
                    System.out.println("you bought " + item);
                }
                else
                    System.out.println("you do not have enough titanium.");
            }
                else
                System.out.println("no such item");
        }
    }
    
    private void sell(Command command)
    {
        if (currentRoom.getType() == "shop")
        {
		item = command.getSecondWord();
        	if(currentPlayer.getItem(item) != null)
        	{
            		currentPlayer.addMoney(currentPlayer.getItem(item).getValue() * .75);
            		currentPlayer.removeItem(item);
            		System.out.println("you just sold " + item);
        	}
        	else
            		System.out.println("you do not have that item to sell.");
	}
	else
		System.out.println("You cant sell something if your not in the shop");
    }	

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
