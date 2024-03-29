import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A library of functions used to translate certain objects to XML and the other way.
 *
 * @author Alexandre Hassan, Sarah Abdallah
 * @version 2020-12-08
 */
public class XML {

    private static final String MAP_TAG = "map";
    private static final String CONTINENT_TAG = "continent";
    private static final String CONTINENT_NAME_TAG = "continentName";
    private static final String CONTINENT_REINFORCEMENTS_TAG = "continentReinforcements";
    private static final String COUNTRY_TAG = "country";
    private static final String COUNTRY_NAME_TAG = "countryName";
    private static final String TROOPS_TAG = "troops";
    private static final String X_TAG = "X";
    private static final String Y_TAG = "Y";
    private static final String NEIGHBORS_TAG = "neighbors";
    private static final String NEIGHBOR_TAG = "neighbor";
    private static final String GAME_TAG = "game";
    private static final String PLAYERS_TAG = "players";
    private static final String AIPLAYER_TAG = "AIplayer";
    private static final String PLAYER_TAG = "player";
    private static final String PLAYER_NAME_TAG = "playerName";
    private static final String PLAYER_OWNED_TAG = "playerOwned";
    private static final String CURRENT_PLAYER_TAG = "currentPlayer";
    private static final String HISTORY_TAG = "history";
    private static final String CONTINENT_COLOR_TAG = "continentColor";
    private static final String DEFAULT_MAP_FILENAME = "Maps/defaultMap.xml";

    /**
     * Returns the default map.
     * @return default map
     */
    public static Map getDefaultMap(){
        File inputFile = new File(DEFAULT_MAP_FILENAME);
        return mapFromXML(inputFile);
    }

    /**
     * Creates a map from an XML file.
     * @param inputFile the file
     * @return a map object
     */
    public static Map mapFromXML(File inputFile){
        Map map = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList continentNodes = doc.getElementsByTagName(CONTINENT_TAG);
            map = mapMaker(continentNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Makes a XML file representing the Gamemodel.
     * @param filename the name of the save file.
     * @param model the model to be translated into XML
     */
    public static void saveGame(String filename, GameModel model) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element root = doc.createElement(GAME_TAG);
            doc.appendChild(root);

            Element playersElem = doc.createElement(PLAYERS_TAG);

            for(Player player: model.getPlayers()){
                Element playerElem;
                playerElem = player instanceof AIPlayer ? doc.createElement(AIPLAYER_TAG) :
                        doc.createElement(PLAYER_TAG);

                Element playerNameElem = doc.createElement(PLAYER_NAME_TAG);
                playerNameElem.appendChild(doc.createTextNode(player.getName()));
                playerElem.appendChild(playerNameElem);

                for(Country c: player.getCountries()){
                    Element playerOwned = doc.createElement(PLAYER_OWNED_TAG);
                    playerOwned.appendChild(doc.createTextNode(c.getName()));
                    playerElem.appendChild(playerOwned);
                }

                playersElem.appendChild(playerElem);
            }
            root.appendChild(playersElem);

            Element currentPlayerElem = doc.createElement(CURRENT_PLAYER_TAG);
            currentPlayerElem.appendChild(doc.createTextNode(model.getCurrentPlayer().getName()));
            root.appendChild(currentPlayerElem);

            root.appendChild(makeMapElement(model.getMap(), doc));

            Element historyElem = doc.createElement(HISTORY_TAG);
            historyElem.appendChild(doc.createTextNode(model.getHistory()));
            root.appendChild(historyElem);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Saves/"+filename+ ".xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to load a started game.
     * @param inputFile the file containing the XML file representing the state of the game.
     * @return GameModel allowing to continue a game.
     */
    public static GameModel loadGame(File inputFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //get the map
            Map map = mapMaker(doc.getElementsByTagName(CONTINENT_TAG));

            ArrayList<Player> players = new ArrayList<>();
            HashMap<String, ArrayList<Country>> AiPlayers = new HashMap<>();

            //get the non-Ai players
            NodeList playerNodes = doc.getElementsByTagName(PLAYER_TAG);
            for (int playerIndex = 0; playerIndex < playerNodes.getLength(); playerIndex++) {
                Node playerNode = playerNodes.item(playerIndex);

                if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element playerElem = (Element) playerNode;
                    Player player = new Player(playerElem.getElementsByTagName(PLAYER_NAME_TAG).item(0).getTextContent());

                    NodeList playerOwnedCountries = playerElem.getElementsByTagName(PLAYER_OWNED_TAG);
                    for (int countryIndex = 0; countryIndex < playerOwnedCountries.getLength(); countryIndex++) {
                        player.addCountry(map.getCountry(playerOwnedCountries.item(countryIndex).getTextContent()));
                    }
                    players.add(player);
                }
            }

            //get the Ai players information
            NodeList AINodes = doc.getElementsByTagName(AIPLAYER_TAG);
            ArrayList<Country> AiCountries;
            for (int AIIndex = 0; AIIndex < AINodes.getLength(); AIIndex++) {
                Node playerNode = AINodes.item(AIIndex);

                if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element playerElem = (Element) playerNode;
                    String playerName = playerElem.getElementsByTagName(PLAYER_NAME_TAG).item(0).getTextContent();

                    NodeList playerOwnedCountries = playerElem.getElementsByTagName(PLAYER_OWNED_TAG);
                    AiCountries = new ArrayList<>();
                    for (int countryIndex = 0; countryIndex < playerOwnedCountries.getLength(); countryIndex++) {
                        AiCountries.add(map.getCountry(playerOwnedCountries.item(countryIndex).getTextContent()));
                    }
                    AiPlayers.put(playerName,AiCountries);
                }
            }

            String history = doc.getElementsByTagName(HISTORY_TAG).item(0).getTextContent();
            String currentPlayerName = doc.getElementsByTagName(CURRENT_PLAYER_TAG).item(0).getTextContent();
            Player currentPlayer = null;

            GameModel model = new GameModel();

            //make the aiPlayers
            AIPlayer aiPlayer;
            for (String playerName : AiPlayers.keySet()) {
                aiPlayer = new AIPlayer(playerName, model);
                for(Country c : AiPlayers.get(playerName)){
                    aiPlayer.addCountry(c);
                }
                players.add(aiPlayer);
            }

            //set the current player
            for (Player player : players) {
                if (player.getName().equals(currentPlayerName)) {
                    currentPlayer = player;
                    break;
                }
            }

            //set the models attributes
            model.setPlayers(players);
            model.setMap(map);
            model.setCurrentPlayer(currentPlayer);
            model.setHistory(new StringBuilder(history));

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Helper method for saveGame that reads the map part of the document.
     * @param map the map to be transformed into and Elem
     * @param doc the document that map is going to written to
     * @return the map Elem
     */
    private static Element makeMapElement(Map map, Document doc) {
        Element mapElem = doc.createElement(MAP_TAG);

        for(Continent continent: map.getContinents()){
            Element continentElem = doc.createElement(CONTINENT_TAG);
            mapElem.appendChild(continentElem);
            Element continentName = doc.createElement(CONTINENT_NAME_TAG);
            continentName.appendChild(doc.createTextNode(continent.getName()));
            continentElem.appendChild(continentName);

            Element continentTroops = doc.createElement(CONTINENT_REINFORCEMENTS_TAG);
            continentTroops.appendChild(doc.createTextNode(String.valueOf(continent.getReinforcements())));
            continentElem.appendChild(continentTroops);

            Element continentColor = doc.createElement(CONTINENT_COLOR_TAG);
            continentColor.appendChild(doc.createTextNode(String.valueOf(continent.getColor())));
            continentElem.appendChild(continentColor);

            for(Country c: continent.getCountries()){
                Element countryElem = doc.createElement(COUNTRY_TAG);

                Element countryName = doc.createElement(COUNTRY_NAME_TAG);
                countryName.appendChild(doc.createTextNode(c.getName()));
                countryElem.appendChild(countryName);

                Element troops = doc.createElement(TROOPS_TAG);
                troops.appendChild(doc.createTextNode(String.valueOf(c.getTroops())));
                countryElem.appendChild(troops);

                Element xPos = doc.createElement(X_TAG);
                xPos.appendChild(doc.createTextNode(String.valueOf(map.getPosition(c).x)));
                countryElem.appendChild(xPos);

                Element yPos = doc.createElement(Y_TAG);
                yPos.appendChild(doc.createTextNode(String.valueOf(map.getPosition(c).y)));
                countryElem.appendChild(yPos);

                Element neighbors = doc.createElement(NEIGHBORS_TAG);
                ArrayList<Element> neighborElems = new ArrayList<>();
                for(Country neighbor : c.getNeighbors()){
                    Element elem = doc.createElement(NEIGHBOR_TAG);
                    elem.appendChild(doc.createTextNode(neighbor.getName()));
                    neighborElems.add(elem);
                }
                for (Element neighborElem : neighborElems) {
                    neighbors.appendChild(neighborElem);
                }
                countryElem.appendChild(neighbors);
                continentElem.appendChild(countryElem);
            }
        }
        return mapElem;
    }

    /**
     * helper method that makes map objects from NodeLists.
     * @param continentNodes the node list representing the map
     * @return map object
     */
    private static Map mapMaker(NodeList continentNodes){
        HashMap<String, Country> countries=  new HashMap<>();
        HashMap<String, Continent> continents = new HashMap<>();
        HashMap<Country, ArrayList<String>> countryNeighborMap = new HashMap<>();
        HashMap<Country, Point> positionsCountries = new HashMap<>();

        for (int temp = 0; temp < continentNodes.getLength(); temp++) {
            Node continentNode = continentNodes.item(temp);

            if (continentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element continentElem = (Element) continentNode;
                String continentName =continentElem.getElementsByTagName(CONTINENT_NAME_TAG).item(0).getTextContent();

                Continent newContinent = new Continent(
                        continentName,
                        Integer.parseInt(continentElem.getElementsByTagName(CONTINENT_REINFORCEMENTS_TAG).item(0).getTextContent()),
                        continentElem.getElementsByTagName(CONTINENT_COLOR_TAG).item(0).getTextContent());

                continents.put(newContinent.getName(), newContinent);

                NodeList countryNodes = continentElem.getElementsByTagName(COUNTRY_TAG);

                for(int countryIndex = 0; countryIndex<countryNodes.getLength();countryIndex++){

                    Node countryNode = countryNodes.item(countryIndex);

                    if(countryNode.getNodeType() == Node.ELEMENT_NODE){
                        Element countryElem = (Element) countryNode;

                        Country newCountry = new Country(countryElem.getElementsByTagName(COUNTRY_NAME_TAG).item(0).getTextContent(),
                                Integer.parseInt(countryElem.getElementsByTagName(TROOPS_TAG).item(0).getTextContent()));
                        newContinent.addCountry(newCountry);
                        countries.put(newCountry.getName(), newCountry);
                        positionsCountries.put(newCountry, new Point(Integer.parseInt(countryElem.getElementsByTagName(X_TAG).item(0).getTextContent()),
                                Integer.parseInt(countryElem.getElementsByTagName(Y_TAG).item(0).getTextContent())));
                        NodeList neighborsNodes = countryElem.getElementsByTagName(NEIGHBORS_TAG);
                        ArrayList<String> neighborList = new ArrayList<>();
                        for(int neighborIndex = 0; neighborIndex<neighborsNodes.getLength(); neighborIndex++){
                            Node neighborNode = neighborsNodes.item(neighborIndex);
                            if(neighborNode.getNodeType() == Node.ELEMENT_NODE){
                                Element neighborElem = (Element) neighborNode;
                                NodeList neighbors = neighborElem.getElementsByTagName(NEIGHBOR_TAG);
                                for(int i = 0; i< neighbors.getLength(); i++){
                                    neighborList.add(neighbors.item(i).getTextContent());
                                }
                            }
                        }
                        countryNeighborMap.put(newCountry,neighborList);
                    }

                }

            }

        }
        for (Country country : countryNeighborMap.keySet()) {
            for (String neighbor : countryNeighborMap.get(country)) {
                country.addNeighbor(countries.get(neighbor));
            }
        }
        Map map = new Map(countries, continents, positionsCountries);
        if(!map.checkMapValidity()){
            map  =null;
        }
        return  map;
    }

}