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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexandre Hassan
 * @version 2020-11-21
 */
public class XML {

    public static Element makeMapElement(Map map, Document doc) {
        Element mapElem = doc.createElement("map");

        for(Continent continent: map.getContinents()){
            Element continentElem = doc.createElement("Continent");
            mapElem.appendChild(continentElem);
            Element continentName = doc.createElement("continentName");
            continentName.appendChild(doc.createTextNode(continent.getName()));
            continentElem.appendChild(continentName);

            Element continentTroops = doc.createElement("continentReinforcements");
            continentTroops.appendChild(doc.createTextNode(String.valueOf(continent.getReinforcements())));
            continentElem.appendChild(continentTroops);

            for(Country c: continent.getCountries()){
                Element countryElem = doc.createElement("country");

                Element countryName = doc.createElement("countryName");
                countryName.appendChild(doc.createTextNode(c.getName()));
                countryElem.appendChild(countryName);

                Element troops = doc.createElement("troops");
                troops.appendChild(doc.createTextNode(String.valueOf(c.getTroops())));
                countryElem.appendChild(troops);

                Element neighbors = doc.createElement("neighbors");
                ArrayList<Element> neighborElems = new ArrayList<>();
                for(Country neighbor : c.getNeighbors()){
                    Element elem = doc.createElement("neighbor");
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

    public static void GameToXML(GameModel model) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element root = doc.createElement("game");
            doc.appendChild(root);

            Element playersElem = doc.createElement("players");

            for(Player player: model.getPlayers()){
                Element playerElem;
                playerElem = player instanceof AIPlayer ? doc.createElement("AIplayer") :
                        doc.createElement("player");

                Element playerNameElem = doc.createElement("playerName");
                playerNameElem.appendChild(doc.createTextNode(player.getName()));
                playerElem.appendChild(playerNameElem);

                for(Country c: player.getCountries()){
                    Element playerOwned = doc.createElement("playerOwned");
                    playerOwned.appendChild(doc.createTextNode(c.getName()));
                    playerElem.appendChild(playerOwned);
                }

                playersElem.appendChild(playerElem);
            }
            root.appendChild(playersElem);

            Element currentPlayerElem = doc.createElement("currentPlayer");
            currentPlayerElem.appendChild(doc.createTextNode(model.getCurrentPlayer().getName()));
            root.appendChild(currentPlayerElem);

            root.appendChild(makeMapElement(model.getMap(), doc));

            Element historyElem = doc.createElement("history");
            historyElem.appendChild(doc.createTextNode(model.getHistory()));
            root.appendChild(historyElem);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("save.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map mapFromXML(String filename){
        HashMap<String, Country> countries=  new HashMap<>();
        HashMap<String, Continent> continents = new HashMap<>();
        HashMap<Country, ArrayList<String>> countryNeighborMap = new HashMap<>();

        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList continentNodes = doc.getElementsByTagName("Continent");
            for (int temp = 0; temp < continentNodes.getLength(); temp++) {
                Node continentNode = continentNodes.item(temp);
//                System.out.println("\nCurrent Element :" + continentNode.getNodeName());

                if (continentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element continentElem = (Element) continentNode;
                    String continentName =continentElem.getElementsByTagName("continentName").item(0).getTextContent();

                    Continent newContinent = new Continent(
                            continentName,
                            Integer.parseInt(continentElem.getElementsByTagName("continentReinforcements").item(0).getTextContent()),
                            continentElem.getElementsByTagName("continentColor").item(0).getTextContent());

                    continents.put(newContinent.getName(), newContinent);

//                    System.out.println("\n \n \n First Name : "
//                            + continentElem
//                            .getElementsByTagName("continentName")
//                            .item(0)
//                            .getTextContent());


                    NodeList countryNodes = continentElem.getElementsByTagName("country");

                    for(int countryIndex = 0; countryIndex<countryNodes.getLength();countryIndex++){

                        Node countryNode = countryNodes.item(countryIndex);
//                        System.out.println("countryNode NodeName " + countryNode.getNodeName());

                        if(countryNode.getNodeType() == Node.ELEMENT_NODE){
                            Element countryElem = (Element) countryNode;

                            Country newCountry = new Country(countryElem.getElementsByTagName("countryName").item(0).getTextContent(),
                                    Integer.parseInt(countryElem.getElementsByTagName("troops").item(0).getTextContent()));
                            newContinent.addCountry(newCountry);
                            countries.put(newCountry.getName(), newCountry);

//                            System.out.println(countryElem.getElementsByTagName("countryName").item(0).getTextContent());
//                            System.out.println("troops " + countryElem.getElementsByTagName("troops").item(0).getTextContent());
                            NodeList neighborsNodes = countryElem.getElementsByTagName("neighbors");
                            ArrayList<String> neighborList = new ArrayList<>();
                            for(int neighborIndex = 0; neighborIndex<neighborsNodes.getLength(); neighborIndex++){
                                Node neighborNode = neighborsNodes.item(neighborIndex);
//                                System.out.println("neighborNode NodeName"+ neighborNode.getNodeName());
                                 if(neighborNode.getNodeType() == Node.ELEMENT_NODE){
                                     Element neighborElem = (Element) neighborNode;
                                     NodeList neighbors = neighborElem.getElementsByTagName("neighbor");
                                     for(int i = 0; i< neighbors.getLength(); i++){
//                                         System.out.println("\t"+neighbors.item(i).getTextContent());
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


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Map(countries,continents);
    }


    private static Map mapMaker(NodeList continentNodes){

        HashMap<String, Country> countries=  new HashMap<>();
        HashMap<String, Continent> continents = new HashMap<>();
        HashMap<Country, ArrayList<String>> countryNeighborMap = new HashMap<>();

        for (int temp = 0; temp < continentNodes.getLength(); temp++) {
            Node continentNode = continentNodes.item(temp);
//                System.out.println("\nCurrent Element :" + continentNode.getNodeName());

            if (continentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element continentElem = (Element) continentNode;
                String continentName =continentElem.getElementsByTagName("continentName").item(0).getTextContent();

                Continent newContinent = new Continent(
                        continentName,
                        Integer.parseInt(continentElem.getElementsByTagName("continentReinforcements").item(0).getTextContent()),
                        continentElem.getElementsByTagName("continentColor").item(0).getTextContent());

                continents.put(newContinent.getName(), newContinent);

                NodeList countryNodes = continentElem.getElementsByTagName("country");

                for(int countryIndex = 0; countryIndex<countryNodes.getLength();countryIndex++){

                    Node countryNode = countryNodes.item(countryIndex);
//                        System.out.println("countryNode NodeName " + countryNode.getNodeName());

                    if(countryNode.getNodeType() == Node.ELEMENT_NODE){
                        Element countryElem = (Element) countryNode;

                        Country newCountry = new Country(countryElem.getElementsByTagName("countryName").item(0).getTextContent(),
                                Integer.parseInt(countryElem.getElementsByTagName("troops").item(0).getTextContent()));
                        newContinent.addCountry(newCountry);
                        countries.put(newCountry.getName(), newCountry);

//                            System.out.println(countryElem.getElementsByTagName("countryName").item(0).getTextContent());
//                            System.out.println("troops " + countryElem.getElementsByTagName("troops").item(0).getTextContent());
                        NodeList neighborsNodes = countryElem.getElementsByTagName("neighbors");
                        ArrayList<String> neighborList = new ArrayList<>();
                        for(int neighborIndex = 0; neighborIndex<neighborsNodes.getLength(); neighborIndex++){
                            Node neighborNode = neighborsNodes.item(neighborIndex);
//                                System.out.println("neighborNode NodeName"+ neighborNode.getNodeName());
                            if(neighborNode.getNodeType() == Node.ELEMENT_NODE){
                                Element neighborElem = (Element) neighborNode;
                                NodeList neighbors = neighborElem.getElementsByTagName("neighbor");
                                for(int i = 0; i< neighbors.getLength(); i++){
//                                         System.out.println("\t"+neighbors.item(i).getTextContent());
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
        return new Map(countries,continents);
    }


//    public static GameModel LoadGame(String filename){
//        try {
//            File inputFile = new File(filename);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(inputFile);
//            doc.getDocumentElement().normalize();
//
//            Map map = mapMaker(doc.getElementsByTagName("map"));
//
//            ArrayList<Player> players = new ArrayList<>();
//
//            NodeList playerNodes = doc.getElementsByTagName("player");
//            for (int playerIndex = 0; playerIndex < playerNodes.getLength(); playerIndex++) {
//                Node playerNode = playerNodes.item(playerIndex);
//
//                if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element playerElem = (Element) playerNode;
//                    Player player = new Player(playerElem.getElementsByTagName("playerName").item(0).getTextContent());
//
//                    NodeList playerOwnedCountries = playerElem.getElementsByTagName("playerOwned");
//                    for (int countryIndex = 0; countryIndex < playerOwnedCountries.getLength(); countryIndex++) {
//                        player.addCountry(map.getCountry(playerOwnedCountries.item(countryIndex).getTextContent()));
//                    }
//                }
//            }
//
//            NodeList AINodes = doc.getElementsByTagName("AIplayer");
//            for (int AIIndex = 0; AIIndex < AINodes.getLength(); AIIndex++) {
//                Node playerNode = AINodes.item(AIIndex);
//
//                if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element playerElem = (Element) playerNode;
//                    Player player = new AIPlayer(playerElem.getElementsByTagName("playerName").item(0).getTextContent());
//
//                    NodeList playerOwnedCountries = playerElem.getElementsByTagName("playerOwned");
//                    for (int countryIndex = 0; countryIndex < playerOwnedCountries.getLength(); countryIndex++) {
//                        player.addCountry(map.getCountry(playerOwnedCountries.item(countryIndex).getTextContent()));
//                    }
//                }
//            }
//
//
//            String history = doc.getElementsByTagName("history").item(0).getTextContent();
//            String currentPlayerName = doc.getElementsByTagName("currentPlayer").item(0).getTextContent();
//            Player currentPlayer = null;
//
//            for(Player player: players){
//                if(player.getName().equals(currentPlayerName)){
//                    currentPlayer = player;
//                    break;
//                }
//            }
//            return new GameModel(players,currentPlayer,map,history);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) {
        //mapToXML(new Map());
        mapFromXML("map.xml");

    }

}