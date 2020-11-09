# SYSC 3110 FALL 2020: RiskProject Milestone 2
### Team: Group
###### Author: Baillie Noell
**THE JAR FILE IS COMPILED AND MUST BE RUN IN JAVA 15**

The deliverables for Milestone 2 include an executable JAR file with source files included, JUnit tests for game senerios, a UML class diagram, 
five UML sequence diagrams,and a document that describes the data structures that were used in this implementation, as well as the changes made from the last Milestone, 
and this READMe. The UML class diagram shows the relationships between the classes, while the sequence diagrams demonstrate
important events such as; passing a turn, troop movement, updating the state of the game, and attacking.

#### User Manual
- To start game click game menu bar item and click start game 
- Add all the player names and click okay
- A list of countries owned by each player can be seen in the player boxes at the side along with the number of troops in each
- At the start of each players turn, highlighted at the top of the screen, the player can add reinforcements to the countries they own, by clicking on the country and then typing how many they choose to place until they have no troops left to place
- Once all reinforcements have been placed the player is in attack mode
- To attack, the player chooses a country that they own, and a neighboring one to attack 
- The player can choose to blitz attack which will keep attacking until the attacking country wins, or can no longer attack
- If blitz attack is not chosen, then the player can choose how many troops to attack with, and the result of the automated dice roll is shown on screen
- If the attacker wins, they can then choose how many troops to move into their newly acquired country
- A player can continue to attack, choose to move troops and then end their turn, or pass their turn to the next player
- To move troops click the move button and then select the country to move troops from, the country to move troops to, and the number of troops they would like to move
- To end turn at any time click end to pass the turn to the next player

#### Improvements to be implemented  
- The graph creation will become more streamlined, in preparation for the automation of custom board creation for Milestone 4
- Trying to attack with a country that does not have enough troops will alert the player when clicked instead of when the attack happens forcing the player to restart the attack

#### Roadmap Ahead:
 In Milestone 3 an AI player will be created, as well as troop movement and reinforcement placement will be refined



