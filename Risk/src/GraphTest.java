import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class GraphTest extends JFrame{

    public static final String VERTEX_STYLE = "shape=ellipse;whiteSpace=wrap;strokeWidth=2";
    public static final String VERTEX_STYLE_ONE_WORD = "shape=ellipse;strokeWidth=2";
    public static final String NA_COLOR = "strokeColor=#ff9000";
    public static final String SA_COLOR = "strokeColor=#ffd000";
    public static final String EU_COLOR = "strokeColor=#ff6500";
    public static final String AF_COLOR = "strokeColor=#ffa500";
    public static final String AS_COLOR = "strokeColor=#ffba00";
    public static final String AU_COLOR = "strokeColor=#ff7b00";
    public static final String BLACK = "strokeColor=#000000";
    public static final int WIDTH = 65;
    public static final int HEIGHT = 65;
    public static final String EDGE_STYLE = "endArrow=false;";

    private final Object parent;
    private final mxGraph graph;

    public GraphTest() {

        graph = new mxGraph();
        parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try
        {
            //North America
            Object alaska = insertVertex(Map.ALASKA,20,20, VERTEX_STYLE, NA_COLOR);
            Object alberta = insertVertex(Map.ALBERTA,45,105, VERTEX_STYLE, NA_COLOR);
            Object centralAmerica = insertVertex(Map.CENTRAL_AMERICA,125,260, VERTEX_STYLE, NA_COLOR);
            Object easternUnitedStates = insertVertex(Map.EASTERN_UNITED_STATES,175,185, VERTEX_STYLE, NA_COLOR);
            Object greenland = insertVertex(Map.GREENLAND,250,10, VERTEX_STYLE, NA_COLOR);
            Object northwestTerritory = insertVertex(Map.NORTHWEST_TERRITORY,120,25, VERTEX_STYLE, NA_COLOR);
            Object ontario = insertVertex(Map.ONTARIO,125,110, VERTEX_STYLE, NA_COLOR);
            Object quebec = insertVertex(Map.QUEBEC,210,100, VERTEX_STYLE, NA_COLOR);
            Object westernUnitedStates = insertVertex(Map.WESTERN_UNITED_STATES,75,185, VERTEX_STYLE, NA_COLOR);

            //South America
            Object argentina = insertVertex(Map.ARGENTINA,145,540, VERTEX_STYLE, SA_COLOR);
            Object brazil = insertVertex(Map.BRAZIL,200,460, VERTEX_STYLE, SA_COLOR);
            Object peru = insertVertex(Map.PERU,90,460, VERTEX_STYLE, SA_COLOR);
            Object venezuela = insertVertex(Map.VENEZUELA,145,380, VERTEX_STYLE, SA_COLOR);

            //Europe
            Object greatBritain = insertVertex(Map.GREAT_BRITAIN,340,120, VERTEX_STYLE, EU_COLOR);
            Object iceland = insertVertex(Map.ICELAND,360,35, VERTEX_STYLE, EU_COLOR);
            Object northernEurope = insertVertex(Map.NORTHERN_EUROPE,430,135, VERTEX_STYLE, EU_COLOR);
            Object scandinavia = insertVertex(Map.SCANDINAVIA,450,55, VERTEX_STYLE_ONE_WORD, EU_COLOR);
            Object southernEurope = insertVertex(Map.SOUTHERN_EUROPE,430,225, VERTEX_STYLE, EU_COLOR);
            Object ukraine = insertVertex(Map.UKRAINE,520,130, VERTEX_STYLE, EU_COLOR);
            Object westernEurope = insertVertex(Map.WESTERN_EUROPE,345,205, VERTEX_STYLE, EU_COLOR);

            //Africa
            Object congo = insertVertex(Map.CONGO,380,405, VERTEX_STYLE, AF_COLOR);
            Object eastAfrica = insertVertex(Map.EAST_AFRICA,460,415, VERTEX_STYLE, AF_COLOR);
            Object egypt = insertVertex(Map.EGYPT,430,340, VERTEX_STYLE, AF_COLOR);
            Object madagascar = insertVertex(Map.MADAGASCAR,490,490, VERTEX_STYLE_ONE_WORD, AF_COLOR);
            Object northAfrica = insertVertex(Map.NORTH_AFRICA,350,330, VERTEX_STYLE, AF_COLOR);
            Object southAfrica = insertVertex(Map.SOUTH_AFRICA,410,490, VERTEX_STYLE, AF_COLOR);

            //Asia
            Object afghanistan = insertVertex(Map.AFGHANISTAN,630,110, VERTEX_STYLE_ONE_WORD, AS_COLOR);
            Object china = insertVertex(Map.CHINA,715,120, VERTEX_STYLE, AS_COLOR);
            Object india = insertVertex(Map.INDIA,680,200, VERTEX_STYLE, AS_COLOR);
            Object irkutsk = insertVertex(Map.IRKUTSK,820,100, VERTEX_STYLE, AS_COLOR);
            Object japan = insertVertex(Map.JAPAN,910,120, VERTEX_STYLE, AS_COLOR);
            Object kamchatka = insertVertex(Map.KAMCHATKA,890,20, VERTEX_STYLE, AS_COLOR);
            Object middleEast = insertVertex(Map.MIDDLE_EAST,600,180, VERTEX_STYLE, AS_COLOR);
            Object mongolia = insertVertex(Map.MONGOLIA,860,200, VERTEX_STYLE, AS_COLOR);
            Object siam = insertVertex(Map.SIAM,770,220, VERTEX_STYLE, AS_COLOR);
            Object siberia = insertVertex(Map.SIBERIA,715,40, VERTEX_STYLE, AS_COLOR);
            Object ural = insertVertex(Map.URAL,630,30, VERTEX_STYLE, AS_COLOR);
            Object yakutsk = insertVertex(Map.YAKUTSK,800,30, VERTEX_STYLE, AS_COLOR);

            //Australia
            Object easternAustralia = insertVertex(Map.EASTERN_AUSTRALIA,850,480, VERTEX_STYLE, AU_COLOR);
            Object indonesia = insertVertex(Map.INDONESIA,780,360, VERTEX_STYLE, AU_COLOR);
            Object newGuinea = insertVertex(Map.NEW_GUINEA,870,390, VERTEX_STYLE, AU_COLOR);
            Object westernAustralia = insertVertex(Map.WESTERN_AUSTRALIA,760,450, VERTEX_STYLE, AU_COLOR);

            insertEdge(alaska, northwestTerritory,NA_COLOR);
            insertEdge(alaska, alberta, NA_COLOR);
            insertEdge(alberta, ontario, NA_COLOR);
            insertEdge(alberta, westernUnitedStates,NA_COLOR);
            insertEdge(ontario, northwestTerritory,NA_COLOR);
            insertEdge(greenland, northwestTerritory, NA_COLOR);
            insertEdge(northwestTerritory, alberta,NA_COLOR);
            insertEdge(greenland, ontario,NA_COLOR);
            insertEdge(greenland, quebec,NA_COLOR);
            insertEdge(ontario, easternUnitedStates,NA_COLOR);
            insertEdge(ontario, westernUnitedStates,NA_COLOR);
            insertEdge(ontario, quebec,NA_COLOR);
            insertEdge(quebec, easternUnitedStates,NA_COLOR);
            insertEdge(centralAmerica, easternUnitedStates,NA_COLOR);
            insertEdge(centralAmerica, westernUnitedStates,NA_COLOR);
            insertEdge(easternUnitedStates, westernUnitedStates, NA_COLOR);

            insertEdge(venezuela, centralAmerica,BLACK);
            insertEdge(venezuela, brazil,SA_COLOR);
            insertEdge(venezuela, peru,SA_COLOR);
            insertEdge(argentina, peru,SA_COLOR);
            insertEdge(argentina, brazil,SA_COLOR);
            insertEdge(brazil, peru,SA_COLOR);

            insertEdge(iceland, greenland,BLACK);
            insertEdge(iceland, greatBritain,EU_COLOR);
            insertEdge(iceland, scandinavia,EU_COLOR);
            insertEdge(greatBritain, scandinavia,EU_COLOR);
            insertEdge(greatBritain, northernEurope,EU_COLOR);
            insertEdge(scandinavia, northernEurope,EU_COLOR);
            insertEdge(scandinavia, ukraine,EU_COLOR);
            insertEdge(northernEurope, ukraine,EU_COLOR);
            insertEdge(northernEurope, southernEurope,EU_COLOR);
            insertEdge(northernEurope, westernEurope,EU_COLOR);
            insertEdge(westernEurope, greatBritain,EU_COLOR);
            insertEdge(westernEurope,northernEurope,EU_COLOR);
            insertEdge(westernEurope, southernEurope,EU_COLOR);
            insertEdge(ukraine, southernEurope,EU_COLOR);


            insertEdge(northAfrica, westernEurope,BLACK);
            insertEdge(northAfrica, brazil,BLACK);
            insertEdge(northAfrica, egypt,AF_COLOR);
            insertEdge(northAfrica, eastAfrica,AF_COLOR);
            insertEdge(northAfrica, congo,AF_COLOR);
            insertEdge(egypt, southernEurope,BLACK);
            insertEdge(egypt, eastAfrica,AF_COLOR);
            insertEdge(eastAfrica, congo,AF_COLOR);
            insertEdge(eastAfrica, madagascar,AF_COLOR);
            insertEdge(congo, southAfrica,AF_COLOR);
            insertEdge(southAfrica, madagascar,AF_COLOR);
            insertEdge(southAfrica, eastAfrica,AF_COLOR);


            insertEdge(afghanistan, middleEast,AS_COLOR);
            insertEdge(afghanistan, china,AS_COLOR);
            insertEdge(afghanistan, india,AS_COLOR);
            insertEdge(afghanistan, ural,AS_COLOR);
            insertEdge(afghanistan, ukraine,BLACK);
            insertEdge(china, india,AS_COLOR);
            insertEdge(china, siam,AS_COLOR);
            insertEdge(china, mongolia,AS_COLOR);
            insertEdge(china, siberia,AS_COLOR);
            insertEdge(china, ural,AS_COLOR);
            insertEdge(india, middleEast,AS_COLOR);
            insertEdge(india, siam,AS_COLOR);
            insertEdge(irkutsk, kamchatka,AS_COLOR);
            insertEdge(irkutsk, mongolia,AS_COLOR);
            insertEdge(irkutsk, siberia,AS_COLOR);
            insertEdge(irkutsk, yakutsk,AS_COLOR);
            insertEdge(japan, kamchatka,AS_COLOR);
            insertEdge(japan, mongolia,AS_COLOR);
            insertEdge(kamchatka, yakutsk,AS_COLOR);
            insertEdge(kamchatka, mongolia,AS_COLOR);
            insertEdge(middleEast, ukraine,BLACK);
            insertEdge(middleEast, southernEurope,BLACK);
            insertEdge(middleEast, egypt,BLACK);
            insertEdge(middleEast, eastAfrica,BLACK);
            insertEdge(mongolia, siberia,AS_COLOR);
            insertEdge(siberia, ural,AS_COLOR);
            insertEdge(siberia, yakutsk,AS_COLOR);
            insertEdge(ural,ukraine,BLACK);


            insertEdge(easternAustralia, westernAustralia,AU_COLOR);
            insertEdge(easternAustralia, newGuinea,AU_COLOR);
            insertEdge(indonesia, siam,BLACK);
            insertEdge(indonesia, newGuinea,AU_COLOR);
            insertEdge(indonesia, westernAustralia,AU_COLOR);
            insertEdge(westernAustralia, newGuinea,AU_COLOR);

            //placeholders
            Object placeholder1 = graph.insertVertex(parent, "placeholder1", "", -20, 50, 1, 1, VERTEX_STYLE);
            Object placeholder2 = graph.insertVertex(parent, "placeholder2", "", 970, 45, 1, 1, VERTEX_STYLE);
            insertEdge(alaska, placeholder1,BLACK);
            insertEdge(kamchatka, placeholder2,BLACK);
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);
        getContentPane().add(graphComponent);
    }

    /**
     * Helper function for the creation of the insertion of vertices.
     * @param name the name of the vertex
     * @param xPosition .
     * @param yPosition .
     * @param style  .
     * @param color .
     * @return the new vertex.
     */
    private Object insertVertex(String name,int xPosition, int yPosition, String style, String color){
        return graph.insertVertex(parent, name, name, xPosition, yPosition, WIDTH, HEIGHT, style + ";" + color);
    }

    /**
     *  Creates an edge between two vertices.
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param color the color of the edge.
     */
    private void insertEdge(Object vertex1, Object vertex2, String color){
        graph.insertEdge(parent, "","",vertex1, vertex2, EDGE_STYLE + color);
    }

    public static void main(String[] args)
    {
        GraphTest frame = new GraphTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setVisible(true);
    }

}
