# SYSC 3110 FALL 2020: RiskProject Milestone 2
### Team: Group
###### Authors: Baillie Noell, Sarah Abdallah 
**THE JAR FILE IS COMPILED AND MUST BE RUN IN JAVA 15**

The deliverables for Milestone 2 include an executable JAR file with source files included, JUnit tests for game senerios, a UML class diagram, 
five UML sequence diagrams,and a document that describes the data structures that were used in this implementation 
of this Milestone and this READMe. The UML class diagram shows the relationships between the classes, while the sequence diagrams demonstrate
important events such as; how the program processes commands, an attack turn, how troops are moved, 
how a new turn begins, and how the state of the game is printed. 

#### User Manual
- to start game click game menu bar item and click start game 
- add all player names and click okay
- a list of countries owned bu each player can be seen in the player boxes at the side along with teh number of trrops in each
- at the start of each player turn, highlighted at the top of the screen, the player can add reinforcemnets to the countries they own, by clicking on the country and then typing how many they choose to place until they have no more to place
- Once all reinforcements have been placed the player is in attack mode
- to attack the player chooses a country that they own, and a neighboring one to attack 
- the player can choose to blitz attack which will keep attacking until the attacking country wins, or can no longer attack
 if blitz attack is not chosen then the player can then choose how many troops to attack with, and the result of teh automated dice roll is shown on screen
 - a player can continue to attack, choose to move troops and then end their turn, or pass their turn to the nect player
 - to move troops click the move button and 
 - to end turn at any time click end 
#### Known Issues 

#### Improvements to be implemented  
-The graph creation will become more streamlined, in preperation for the automation of custom board creation for milestone 4
-trying to attack with a country that does not have enough troops will alert the player when clicked instead of when the attack happens forcing the player to restart the attack
  
#### Roadmap Ahead:
 In Milestone 3 an AI player will be created, as well as troop movement and reinforecment placement will be refined



