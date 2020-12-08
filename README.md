# SYSC 3110 FALL 2020: RiskProject Milestone 3
### Team: Group
###### Author: Baillie Noell
**THE JAR FILE IS COMPILED AND MUST BE RUN IN JAVA 15**

The deliverables for Milestone 3 include an executable JAR file with source files included, JUnit tests for game scenarios, a UML class diagram, 
7 UML sequence diagrams,and a document that describes the data structures that were used in this implementation, as well as the changes made from the last Milestone, 
and this READMe. The UML class diagram shows the relationships between the classes, while the sequence diagrams demonstrate
important events such as; troop movement, placing reinforcements, attacking, and scenarios for the AI player such as; an AI turn, AI troop movement, AI reinforcements, and an AI attack.

#### User Manual
- To start game click game menu bar item and click start game 
- Add all the player names and click okay
- An AI Player can be set by clicking the computer player check box under the name field 
- A list of countries owned by each player can be seen in the player boxes at the side along with the number of troops in each
- At the start of each player's turn, highlighted at the top of the screen, the player can add reinforcements to the countries they own, by clicking on the country and then typing how many they choose to place until they have no troops left to place
- Once all reinforcements have been placed the player is in attack mode
- To attack, the player chooses a country that they own, and a neighboring one to attack 
- The player can choose to blitz attack which will keep attacking until the attacking country wins, or can no longer attack
- If blitz attack is not chosen, then the player can choose how many troops to attack with, and the result of the automated dice roll is shown on screen
- If the attacker wins, they can then choose how many troops to move into their newly acquired country
- A player can continue to attack, choose to move troops and then end their turn, or pass their turn to the next player
- To move troops click the move button and then select the country to move troops from, the country to move troops to, and the number of troops they would like to move
- To end turn at any time click end to pass the turn to the next player
- When it is an AI Players turn, their turn will be completed automatically, and the turn will pass to the next human player
- To view past turns, including moves made by an AI player, select the view history menu option

#### Improvements to be implemented  
- The graph creation will become more streamlined, in preparation for the automation of custom board creation for Milestone 4
- Trying to attack with a country that does not have enough troops will alert the player when clicked instead of when the attack happens forcing the player to restart the attack

### Known Bugs
- At the beginning of a game, for a currently unknown reason, a null player will begin the game. The current solution is to place the reinforcements on any country, and then end turn to move to the next real player.

#### Roadmap Ahead:
For Milestone 4 we will be implementing the save/load feature as well as the option to import a custom map. 



