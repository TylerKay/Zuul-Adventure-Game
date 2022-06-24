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
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 * 
 * 

 * Tyler Kay
 */
import java.util.*;

class Game 
{
    private Parser parser;
    private Room currentRoom;
    private String direction;
    private Room previousRoom;
    private Player player;
    private HealthPotion healthPotion;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        player = new Player("outside");
        parser = new Parser();
        direction = null;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, cellar, cave, lockedEntrance, endPortal, end, secretLabEntrance, netherPortal, basaltBiome, bastion, netherWaste, netherFortress, insideNetherFortress;
        Room mineshaft, mineshaftTreasureRoom, dungeon1, dungeon1Entrance, dungeon1ChestRoom, dungeon2, dungeon2Entrance, dungeonBossRoom, dungeonBossRoomEntrance, bossHintRoom, exitRoom, mysteriousLadder, backStage, soulSandBiome1, soulSandBiome2, soulSandBiome3, hiddenUndergroundFortress, crimsonForest1, crimsonForest2, crimsonForest3;
        Room outside2, jungleBiome, temple1, temple2, templeChestRoom, desert, desertTemple, village, teleportRoom, office2;
        Item duck, toilet, magicCookie, key;
        
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab. There appears to be a zombie in the lab! Battle it by using the 'battle' command!");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar");
        backStage = new Room("on backstage of theatre. Nothing here. ");
        //
        cave = new Room("in a dark and spooky cave");
        lockedEntrance = new Room("in a secret entrance in the cave");
        endPortal = new Room("in a room with an end portal after entering the secret entrance");
        end = new Room("in the end dimension. There is a end dragon. Battle it! ");
        
        secretLabEntrance = new Room("in a secret room outside the lab");
        netherPortal = new Room("in a room with a nether portal");
        basaltBiome = new Room("in the basalt biome");
        bastion = new Room("in a Bastion after walking for awhile");        
        netherWaste = new Room("in the Nether Waste Biome");
        netherFortress = new Room("in the Nether Fortress. There appears to be blazes along the way");
        insideNetherFortress = new Room("inside the nether fortress. You found a chest along the way");
        soulSandBiome1 = new Room("entered in the Soul Sand Biome");
        hiddenUndergroundFortress = new Room("in a hidden underground Fortress");
        soulSandBiome2 = new Room("entered in Soul Sand Biome 2. There are hoglins charging at you! ");
        soulSandBiome3 = new Room("entered in Soul Sand Biome 3. ");
        crimsonForest1 = new Room("in the Crimson Forest Biome 1. There are enemies! ");
        crimsonForest2 = new Room("in the Crimson Forest Biome 2. Defeat the enemies! ");
        crimsonForest3 = new Room("in the Crimson Forest Biome 3");
        mineshaft = new Room("in a mineshaft. Look! There's something ahead! ");
        mineshaftTreasureRoom = new Room("in a treasure room. Look at all the things on the ground! ");
        dungeon1Entrance = new Room("at the entrance of dungeon 1. ");
        dungeon1 = new Room("in dungeon 1. There are zombies in this dungeon! ");
        dungeon1ChestRoom = new Room("Wow! Look at all these loot! ");
        dungeon2Entrance = new Room("at the entrance of dungeon 2. ");
        dungeon2 = new Room("in dungeon 2. Defeat the enemies! ");
        dungeonBossRoomEntrance = new Room("at the boss room entrance. Continue south to enter the boss room. ");
        dungeonBossRoom = new Room("at the boss room. Fight the boss!");
        bossHintRoom = new Room("in a mysterious room. Look there's a paper on the ground. It reads: 'Search for a ender pearl and blaze rod to complete the journey'");
        exitRoom = new Room("in the exit room. ");
        mysteriousLadder = new Room("climbing the ladder. Where does this go to? ");
        
        outside2 = new Room("outside again. However, not where you intially started");
        jungleBiome = new Room("in a Jungle Biome");
        temple1 = new Room("in Temple 1 inside the Jungle. Fight the enemies! ");
        temple2 = new Room("in Temple 2 inside the Jungle. Fight the enemies! ");
        templeChestRoom = new Room("in a chest room in the temple");
        desert = new Room("in a desert");
        desertTemple = new Room("in a desert temple");
        village = new Room("in a village");
        teleportRoom = new Room("in a teleport room");
        office2 = new Room("in a office room. However, not at the university...");
    
        
        // initialise room exits
        outside.setExit("north", "east", "south", "west", cave, theatre, lab, pub);
        theatre.setExit("west", "east", outside, backStage);
        backStage.setExit("west", theatre);
        pub.setExit("east", outside);
        lab.setExit("north", "east", "south", outside, office, secretLabEntrance);
        secretLabEntrance.setExit("north", lab);
        office.setExit("west", lab);
        office.setExit("down", cellar);
        cellar.setExit("up", office);
        cellar.setExit("down", netherPortal);
        netherPortal.setExit("east", basaltBiome);
        netherPortal.setExit("south", netherWaste);
        netherPortal.setExit("north", cellar);
        basaltBiome.setExit("east", bastion);
        basaltBiome.setExit("west", netherPortal);
        bastion.setExit("west", basaltBiome);
        
        netherWaste.setExit("north", netherPortal);
        netherWaste.setExit("east", netherFortress);
        netherWaste.setExit("south", soulSandBiome1);
        netherFortress.setExit("south", insideNetherFortress);
        netherFortress.setExit("west", netherWaste);
        insideNetherFortress.setExit("north", netherFortress);
        soulSandBiome1.setExit("north", "east", "south", netherWaste, hiddenUndergroundFortress, soulSandBiome2);
        hiddenUndergroundFortress.setExit("west", soulSandBiome1);
        soulSandBiome2.setExit("north", "east", "south", soulSandBiome1, crimsonForest1, soulSandBiome3);
        soulSandBiome3.setExit("north", soulSandBiome2);
        crimsonForest1.setExit("west", "south", soulSandBiome2, crimsonForest2);
        crimsonForest2.setExit("north", "south", crimsonForest1, crimsonForest3);
        crimsonForest3.setExit("north", crimsonForest2);
        
        
        cave.setExit("north", "south", "west", lockedEntrance, outside, mineshaft);
        lockedEntrance.setExit("east", "south", endPortal, outside);
        lockedEntrance.setIsLocked(true);
        endPortal.setExit("south", "west", end, lockedEntrance);
        
        mineshaft.setExit("west", "east", "north", dungeon1Entrance, cave, mineshaftTreasureRoom);
        mineshaftTreasureRoom.setExit("south", mineshaft);
        dungeon1Entrance.setExit("west", "east", dungeon1, mineshaft);
        dungeon1.setExit("north", "south", "east", dungeon1ChestRoom, dungeon2Entrance, dungeon1Entrance);
        dungeon2Entrance.setExit("north", "south", dungeon1, dungeon2);
        dungeon2.setExit("north", "south", dungeon2Entrance, dungeonBossRoomEntrance);
        dungeonBossRoomEntrance.setExit("north", "south", dungeon2, dungeonBossRoom);
        dungeonBossRoom.setExit("north", "south", "east", dungeon2, exitRoom, bossHintRoom);
        bossHintRoom.setExit("west", dungeonBossRoom);
        exitRoom.setExit("east", "south", mysteriousLadder, outside2);
        mysteriousLadder.setExit("north", secretLabEntrance);
        
        outside2.setExit("north", "west", "east", "south", exitRoom, desert, jungleBiome, village);
        jungleBiome.setExit("north", "west", "south", mysteriousLadder, outside2, temple1);
        temple1.setExit("north", "south", jungleBiome, temple2);
        temple2.setExit("north", "south", temple1, templeChestRoom);
        templeChestRoom.setExit("north", temple2);
        village.setExit("north", "south", outside2, teleportRoom);
        teleportRoom.setExit("north", "south", "west", village, netherPortal, office2);
        office2.setExit("south", office);
        desert.setExit("east", "north", outside2, desertTemple);
        desertTemple.setExit("south", desert);
        
        //create the items
        duck = new Item("beijing-duck", 0.69);
        toilet = new Item("yellow-tinted-toilet", 42);
        magicCookie = new Item("magic-cookie", 2);
        key = new Item("key", 1);
        Item key2 = new Item("key", 1);
        Item key3 = new Item("key", 1);
        Item key4 = new Item("key", 1);
        Item key5 = new Item("key", 1);
        
        
        //initialise the items
        pub.addItem(duck);
        lab.addItem(toilet);
        office.addItem(magicCookie);
        
        // Keys are located in: bastion, jungle temple chest room, desert temple, crimson forest 3, and lab
        bastion.addItem(key);
        templeChestRoom.addItem(key2);
        desertTemple.addItem(key3);
        crimsonForest3.addItem(key4);
        lab.addItem(key5);
        
        // Locations of Health Potions
        HealthPotion healthPot1 = new HealthPotion("health-potion-1", 3, 10);
        HealthPotion healthPot2 = new HealthPotion("health-potion-2", 3, 10);
        HealthPotion healthPot3 = new HealthPotion("health-potion-3", 3, 10);
        
        
        HealthPotion healthPot4 = new HealthPotion("health-potion-4", 3, 20);
        HealthPotion healthPot5 = new HealthPotion("health-potion-5", 3, 20);
        HealthPotion healthPot6 = new HealthPotion("health-potion-6", 3, 20);
        
        mineshaftTreasureRoom.addItem(healthPot1);
        exitRoom.addItem(healthPot2);
        exitRoom.addItem(healthPot3);
        
        pub.addItem(healthPot4);
        soulSandBiome3.addItem(healthPot4);
        insideNetherFortress.addItem(healthPot5);
        crimsonForest3.addItem(healthPot6);
        
        // Initialize enemies: Format: Name, health, attack damage
        Enemy blaze, enderDragon, zombie, zombie2, skeleton, spider, megaCreeper;
        Enemy hoglin1, hoglin2, hoglin3, ghast;
        Enemy templeZombie1, templeZombie2, templeSkeleton; 
        
        zombie = new Enemy("Zombie 1", 5, 2);
        zombie2 = new Enemy("Zombie 2", 5, 2); 
        skeleton = new Enemy("Skeleton", 10, 5);
        spider = new Enemy("Spider", 10, 5);
        blaze = new Enemy("Blaze", 20, 10); 
        megaCreeper= new Enemy("MegaCreeper", 20, 15);
        enderDragon = new Enemy("EnderDragon", 30, 20);
        enderDragon.setIsEnderDragon(true);
        hoglin1 = new Enemy("Hoglin 1", 15, 10);
        hoglin2 = new Enemy("Hoglin 2", 15, 10);
        hoglin3 = new Enemy("Hoglin 3", 15, 10);
        templeZombie1 = new Enemy("Temple Zombie 1", 4, 5);
        templeZombie2 = new Enemy("Temple Zombie 2", 4, 5);
        templeSkeleton = new Enemy("Temple Skeleton", 10, 5);
        
        lab.addEnemy(zombie);
        dungeon1.addEnemy(zombie);
        dungeon2.addEnemy(zombie2);
        dungeon2.addEnemy(skeleton);
        dungeonBossRoom.addEnemy(megaCreeper);
        
        netherFortress.addEnemy(blaze);
        crimsonForest1.addEnemy(hoglin1);
        crimsonForest2.addEnemy(hoglin2);
        crimsonForest2.addEnemy(hoglin3);
        
        temple1.addEnemy(templeZombie1);
        temple2.addEnemy(templeZombie2);
        temple2.addEnemy(templeSkeleton);
        
        end.addEnemy(enderDragon);
        
        // Initialize weapon stats
        Weapon woodenSword, stoneSword, goldSword, ironSword, diamondSword, diamondSword2;
        woodenSword = new Weapon("wooden-sword", 5, 3);
        goldSword = new Weapon("gold-sword", 10, 6);
        stoneSword = new Weapon("stone-sword", 15, 7);
        ironSword = new Weapon("iron-sword", 30, 10);
        diamondSword = new Weapon("diamond-sword", 20, 15);
        diamondSword2 = new Weapon("diamond-sword", 20, 15);
        
        mineshaftTreasureRoom.addItem(woodenSword); // wooden sword location: mineshaft treasure room
        dungeon1ChestRoom.addItem(goldSword); // gold sword location: dungeon1 chest room
        secretLabEntrance.addItem(stoneSword); // stone sword location: secret lab entrance
        bastion.addItem(ironSword); // iron sword location: bastion
        insideNetherFortress.addItem(diamondSword); // diamond sword location: inside of nether fortress, exit room (after defeating boss)
        exitRoom.addItem(diamondSword2);
        
        
        
        currentRoom = outside;  // start game outside
        
        
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
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
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
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("eat") || (commandWord.equals("drink")))
            eat(command);
        else if (commandWord.equals("back"))
            goBack(command);
        else if (commandWord.equals("take")){
            take();
        }
        else if (commandWord.equals("drop")){
            drop(command);
        } 
        else if (commandWord.equals("items")){
            items();
        }
        else if (commandWord.equals("battle")){
            if (battle() == false){
                System.out.println("Game over. Your HP is now 0");
                System.exit(1);
            }
            battle();
        }
        else if (commandWord.equals("equip")){
            equip();
        }


        return wantToQuit;
    }

    // implementations of user commands:

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

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
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
        
            
        if (nextRoom == null){
            System.out.println("There is no door!");
        } else if (nextRoom.getIsLocked()){
            if (player.isInInventory("key")){
                nextRoom.setIsLocked(false);
                enterRoom(nextRoom);
            } else {
                 System.out.println("Door is locked. Perhaps there is a key somewhere... Try looking in the lab to see if the scientists have hidden something");   
            }
        }
        else {
            enterRoom(nextRoom);
        }
    }
    
    

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
    
    /**
     * Tells the location and description of surroundings to the user.
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }
    
    /**
     * Prints the description of the room and the exits
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Tells the user that they have eaten
     */
    
    
    private boolean eat(Command command)
    {
        
        
        if (command.hasSecondWord()) {
            System.out.println("Eat what?");
            return false;
        } 
        
        
        else {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter in the item you wish to eat/drink: ");
            String userInput = myObj.nextLine();
            
            if (!player.isInInventory(userInput)){
                System.out.println(userInput + " does not exist in player's inventory");
                return false;
            }
            
            int curr_weightCapacity = player.getWeightCapacityOfCarriedItems();
            if (userInput.toLowerCase().equals("magic-cookie")){
                player.dropItem(userInput);
                player.setWeightCapacityOfCarriedItems(curr_weightCapacity + 20); // Increment "inventory" weightCapacity by 20
                System.out.println("You have eaten a magic cookie! Player's carrying item capacity has increased by 20!");   
            } else if ( (userInput.toLowerCase().equals("health-potion-1")) || (userInput.toLowerCase().equals("health-potion-2")) || (userInput.toLowerCase().equals("health-potion-3")) || (userInput.toLowerCase().equals("health-potion-4")) || (userInput.toLowerCase().equals("health-potion-5")) || (userInput.toLowerCase().equals("health-potion-6")) ){
                int hpGain = 10;
                player.setHealth(player.getHealth() + 10);
                if (player.getHealth() > 100){
                    player.setHealth(100);
                }
                player.dropItem(userInput);
                System.out.println("You have drank the health potion! Player's health is now " + player.getHealth() );
            }
            System.out.println("Total weight of items player can hold is now " + player.getWeightCapacityOfCarriedItems()); 
            System.out.println(player.getInventory());
            return true;
                    
            
        }
        
    }
    
    /**
     * Enters the specified room and prints the description.
     */
     private void enterRoom(Room nextRoom)
    {
         previousRoom = currentRoom;
         currentRoom = nextRoom;
         System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Go back to the previous room.
     */
    private void goBack(Command command)
    {
        if (command.hasSecondWord()) {
            System.out.println("Back where?");
            return;
        }
        if (previousRoom == null) {
            System.out.println("You can't go back");
        }
        else {
            enterRoom(previousRoom);
        }
    }
    
    private boolean take(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter in the item you wish to take: ");
        String userInput = myObj.nextLine();
        
        Item item = currentRoom.getItem(userInput);
        if (item == null){
            System.out.println("Item does not exist in the room");
            return false;
        }
        
        if (item.getDescription().equals(userInput)){ // Check if there exist an item in the room which the user entered
            String itemDescription = currentRoom.getItemDescription();
            double itemWeight = currentRoom.getItemWeight(userInput);
        
        
            if (player.getWeightOfCarriedItems() + itemWeight > player.getWeightCapacityOfCarriedItems()){
                System.out.println("Bag capacity weight has reached its limit. Cannot pickup any more items");
                return false;
            } else {
                // Add take item from room
                player.setWeightOfCarriedItems(itemWeight);
                player.addItem(currentRoom.getItem(userInput));
                currentRoom.removeItem(item);
                
                System.out.println("Added item into player's inventory");
                
                // Display items player has
                System.out.println(player.getInventory());
                return true;
            }
        } else {
            System.out.println("There doesn't exist a item " + userInput + " in this room"); 
            return false;
        }
        
    }
    
    private boolean drop(Command command)
    {
        if (!command.hasSecondWord()) {
            
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter in the item you wish to drop: ");
            String userInput = myObj.nextLine();
        
            if (player.isInInventory(userInput)){
                Item item = player.getFromInventory(userInput);
                player.dropItem(userInput);
                currentRoom.addItem(item);
                System.out.println("Successfully dropped " + userInput); 
                System.out.println(player.getInventory());
                return true;
            } else {
                System.out.println("Item does not exist in inventory. Failed to drop the item");
                return false;
            }
            
        } else {
            System.out.println("Drop what?");
        }
        
        return false;
    }
    
    private void items()
    {
        System.out.println(player.getInventory());
    }
    
    private boolean battle(){
        Scanner myObj = new Scanner(System.in);
        int playerAttackDamage = player.getAttackDamage();
        boolean isFinalBattle = false;
        
        if (currentRoom.getEnemies().get(0).getIsEnderDragon()){
            isFinalBattle = true;
        }
        
        while (currentRoom.roomHasEnemies()){
                Enemy currentEnemy = currentRoom.getEnemies().get(0);
                int enemyAttackDamage = currentEnemy.getAttackDamage();
                
                System.out.println("Your health: " + player.getHealth());
                System.out.println(currentEnemy.getName() + "'s health: " + currentEnemy.getHealth());
                
                System.out.println("Type 'attack' to attack the enemy: ");
                String userInput = myObj.nextLine();
                
                currentEnemy.setHealth(currentEnemy.getHealth() - playerAttackDamage); // User attacks enemy
                if (currentEnemy.getHealth() <= 0) {
                    currentRoom.removeEnemy(currentEnemy);
                }
                
                player.setHealth(player.getHealth() - enemyAttackDamage);
                if (player.getHealth() <= 0){
                    return false;
                }
        }
        
        if (isFinalBattle == true){
            System.out.println("Congrats on beating the game! ");
            System.out.println("Thanks for playing! ");
            System.exit(1);
            return true;
        }
        System.out.println("You defeated the enemies/enemy in this room!");
        return true;
    }
    
    private boolean equip(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the weapon name you wish to equip: ");
        String userInput = myObj.nextLine();
        
        String output = player.equip(userInput);
        if (output.equals("Equipped " + userInput)){
            System.out.println("Your attack damage is now " + player.getAttackDamage());
        }
        
        
        return true;
    }
    
}



