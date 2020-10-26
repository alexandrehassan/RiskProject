# SYSC 3110 FALL 2020: RiskProject
###Team: Group
######Authors: Baillie Noell, Sarah Abdallah 
**THE JAR FILE IS COMPILED AND MUST BE RUN IN JAVA 15**

The deliverables for Milestone 1 include an executable JAR file with source files included, a UML class diagram, 
five UML sequence diagrams,and a document that describes the data structures that were used in this implementation 
of this Milestone and this READMe. The UML class diagram shows the relationships between the classes, while the sequence diagrams demonstrate
important events such as; how the program processes commands, an attack turn, how troops are moved, 
how a new turn begins, and how the state of the game is printed. 

####User Manual
To play this text-based implementation of RISK, the user must run the JAR file from the command line. When prompted, the 
user must enter the name of each player and enter the word "done" when all players have been added. 
Otherwise, the game will auto-begin when 6 players have been added. 

The game begins by printing the state of the map by listing each player's collection of randomly-assigned countries and troops.
The game randomly places reinforcements at the beginning of each player's turn (the user should see the reinforcements listed
on screen). 
Finally, one player will be assigned the first turn, and will see their name depicted on screen, prompting them to input what they would like to do.
The possible commands that the user may enter are: "attack","end","state", or "help". 

- If the user types **"attack"** and hits enter, they will be prompted to enter the country that they wish to attack. Once they have 
entered a country (with the first letter being capitalized, for example, "China"), the user will be prompted to input the country that they wish to attack from. 
If the user enters an invalid country, they will be prompted to enter another country. 
Given that the user attacks a country that neighbors the country that they are attacking from, the user will also enter the number of troops they want to attack with (unless the player only has 2 troops in the country they are attacking from,
in which case the game will automatically send only 1 troop). 
The game automatically rolls the correct number of dice and prints the outcome of the attack. 
If the attacker wins, they will be prompted to choose the number of troops that they wish to move into their newly-acquired country. If the attacker loses, they lose the troops that they attacked with. 
The player must type **"end"** to end (or skip) their turn. 
Note: If the user enters in the wrong country at any point of their attack, they can retype "attack" to restart the turn. 
- If the user **"state"**, the state of the map will be printed. 
- If the user types **"help"**, they will receive assistance and information regarding the different command words. 

####Known Issues 

Within the current version of the game, the reinforcements are assigned randomly at the beginning of each player's turn based 
on the number of countries that each player holds. This means that the game *can* be won, but this will take a long time because players
are not yet able to strategically choose where to place their reinforcements at the beginning of their turns. 

#### Improvements to be implemented  
In reference to the Map class, the planned improvements include changing the countries from the form of a LinkedList of Country Objects to a Hashmap, using the name of the 
country as the key and the Country Object as the value. This alteration will significantly reduce game creation and country access time, and in result, will increase efficiency. 
The number of method calls for adding neighbors will also be reduced due to the fact that the method will now assign both countries as 
eachother's neighbors simultaneously (instead of having separate calls for each country). 
For example, CHINA.add(MONGOLIA) will add MONGOLIA as one of CHINA's neighbors and CHINA as one of MONGOLIA's neighbors in a single line. 
The countries will no longer be added to continents individually using for loops, but will instead be stored in arrays via their 
variable names. All countries will be simultaneously added to their respective continents by passing an array of strings to each continent. 
Finally, all final fields will also be made static, if not already defined in this way.
In reference to the Player class, an improvement to include is hiding the internal ArrayList from the other classes (and thus reducing the chaining of the method calls). 
  
####Roadmap Ahead:
For Milestone 2, the noted improvements will be implemented, as well as the GUI-based version of the current game. 
The display will be in a JFrame, and user input will be in the form of mouse clicks. 
Our current plan is to use a graph and make each country a node, and when the node is clicked, the country information is displayed. 
Furthermore, unit tests will be added to the model. 



