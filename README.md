# RiskProject
Team Group 
Authors: Baillie Noell, Sarah
Other Deliverables: 
- Executable jar file of game with sources included
- UML diagram of all classes
- Sequence diagrams for important use cases (Sarah list use cases)
- Description of used data structures 

User Manual:
- Run jar file from command line
- Enter name of each player when prompted, enter done when all players have been added, game will auto start when 6 players have been added
- Game starts
- Reinforcements are randomly placed before player turn, and will be listed on screen
- countries and troops assigned to players will be displayed
- Player whos turn it is will appear on screen

- To attack type attack and hit enter
- enter country to attack 
- enter country to attack from 
- enter number of troops to attack with, if only one possibility for number of troops, game will choose that option without prompting
- Game will automatically roll dice and print outcome of attack 
- if attacker wins, they will be prompted to choose number of troops to move into newly acquried country 

- to end or skip turn, type "end"

- to print state of map type "state"

- to get help with commands type "help"

How the game works:
SARAH ADD HOW GAME WORKS

Known issues:
Currently reinforcements are assigned randomly at the beginning of each players turn based on the number of countries held, so technically the game can be won, but will take a very long time as players can not stratigically choose where to place their reinforcemnets at the beginning of their turn 

Planned improvements:

Map:
- changing countries from LinkedList to a hashmap using the name of the country as the key, and the country object as the value. This cuts game creation and country access significantly (about half the time)
- The number of method calls for adding neighbors will be cut in half, as the method will now add both countries as each others neighbors simultaniously instead of having one call for each (eg. a.add(b) will add b to a's neighbors and a to b's neighbors at the same time)
- countries will no longer be added to continents individually using for loops, instead their variable names will be stored in arrays and the 
- make as much as possible static 

Player:
- abstrction of player, hiding internal ArrayList from other classes and reducing the chaining of method calls


Plans for future:
- implimenting planed improvements 
- GUI plans (Sarah add here)



