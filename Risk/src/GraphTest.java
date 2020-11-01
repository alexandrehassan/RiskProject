
import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class GraphTest extends JFrame{
    public GraphTest() {

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try
        {
            //North America
            Object alaska = graph.insertVertex(parent, Map.ALASKA, Map.ALASKA, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object alberta = graph.insertVertex(parent, Map.ALBERTA, Map.ALBERTA, 45, 105, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object centralAmerica = graph.insertVertex(parent, Map.CENTRAL_AMERICA, Map.CENTRAL_AMERICA, 125, 260, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object easternUnitedStates = graph.insertVertex(parent, Map.EASTERN_UNITED_STATES, Map.EASTERN_UNITED_STATES, 175, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object greenland = graph.insertVertex(parent, Map.GREENLAND, Map.GREENLAND, 250, 10, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object northwestTerritory = graph.insertVertex(parent, Map.NORTHWEST_TERRITORY, Map.NORTHWEST_TERRITORY, 120, 25, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ontario = graph.insertVertex(parent, Map.ONTARIO, Map.ONTARIO, 125, 110, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object quebec = graph.insertVertex(parent, Map.QUEBEC, Map.QUEBEC, 210, 100, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernUnitedStates = graph.insertVertex(parent, Map.WESTERN_UNITED_STATES, Map.WESTERN_UNITED_STATES, 75, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "", "", alaska, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "", "", alaska, alberta, "endArrow=false");
            graph.insertEdge(parent, "", "", alberta, ontario, "endArrow=false");
            graph.insertEdge(parent, "", "", alberta, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "", "", greenland, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "", "", northwestTerritory, alberta, "endArrow=false");
            graph.insertEdge(parent, "", "", greenland, ontario, "endArrow=false");
            graph.insertEdge(parent, "", "", greenland, quebec, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, quebec, "endArrow=false");
            graph.insertEdge(parent, "", "", quebec, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", centralAmerica, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", centralAmerica, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", easternUnitedStates, westernUnitedStates, "endArrow=false");

            //South America
            Object argentina = graph.insertVertex(parent, Map.ARGENTINA, Map.ARGENTINA, 145, 540, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object brazil = graph.insertVertex(parent, Map.BRAZIL, Map.BRAZIL, 200, 460, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object peru = graph.insertVertex(parent, Map.PERU, Map.PERU, 90, 460, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object venezuela = graph.insertVertex(parent, Map.VENEZUELA, Map.VENEZUELA, 145, 380, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",venezuela, centralAmerica, "endArrow=false");
            graph.insertEdge(parent, "","",venezuela, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",venezuela, peru, "endArrow=false");
            graph.insertEdge(parent, "","",argentina, peru, "endArrow=false");
            graph.insertEdge(parent, "","",argentina, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",brazil, peru, "endArrow=false");

            //Europe
            Object greatBritain = graph.insertVertex(parent, Map.GREAT_BRITAIN, Map.GREAT_BRITAIN, 340, 120, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object iceland = graph.insertVertex(parent, Map.ICELAND, Map.ICELAND, 360, 35, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object northernEurope = graph.insertVertex(parent, Map.NORTHERN_EUROPE, Map.NORTHERN_EUROPE, 430, 135, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object scandinavia = graph.insertVertex(parent, Map.SCANDINAVIA, Map.SCANDINAVIA, 450, 55, 65, 65, "shape=ellipse");
            Object southernEurope = graph.insertVertex(parent, Map.SOUTHERN_EUROPE, Map.SOUTHERN_EUROPE, 430, 225, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ukraine = graph.insertVertex(parent, Map.UKRAINE, Map.UKRAINE, 520, 130, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernEurope = graph.insertVertex(parent, Map.WESTERN_EUROPE, Map.WESTERN_EUROPE, 345, 205, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",iceland, greenland, "endArrow=false");
            graph.insertEdge(parent, "","",iceland, greatBritain, "endArrow=false");
            graph.insertEdge(parent, "","",iceland, scandinavia, "endArrow=false");
            graph.insertEdge(parent, "","",greatBritain, scandinavia, "endArrow=false");
            graph.insertEdge(parent, "","",greatBritain, northernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",scandinavia, northernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",scandinavia, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",northernEurope, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",northernEurope, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",northernEurope, westernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",westernEurope, greatBritain, "endArrow=false");
            graph.insertEdge(parent, "","",westernEurope,northernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",westernEurope, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",ukraine, southernEurope, "endArrow=false");

            //Africa
            Object congo = graph.insertVertex(parent, Map.CONGO, Map.CONGO, 380, 405, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object eastAfrica = graph.insertVertex(parent, Map.EAST_AFRICA, Map.EAST_AFRICA, 460, 415, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object egypt = graph.insertVertex(parent, Map.EGYPT, Map.EGYPT, 430, 340, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object madagascar = graph.insertVertex(parent, Map.MADAGASCAR, Map.MADAGASCAR, 490, 490, 65, 65, "shape=ellipse");
            Object northAfrica = graph.insertVertex(parent, Map.NORTH_AFRICA, Map.NORTH_AFRICA, 350, 330, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object southAfrica = graph.insertVertex(parent, Map.SOUTH_AFRICA, Map.SOUTH_AFRICA, 410, 490, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",northAfrica, westernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, egypt, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, eastAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, congo, "endArrow=false");
            graph.insertEdge(parent, "","",egypt, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",egypt, eastAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",eastAfrica, congo, "endArrow=false");
            graph.insertEdge(parent, "","",eastAfrica, madagascar, "endArrow=false");
            graph.insertEdge(parent, "","",congo, southAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",southAfrica, madagascar, "endArrow=false");
            graph.insertEdge(parent, "","",southAfrica, eastAfrica, "endArrow=false");

            //Asia
            Object afghanistan = graph.insertVertex(parent, Map.AFGHANISTAN, Map.AFGHANISTAN, 630, 110, 65, 65, "shape=ellipse");
            Object china = graph.insertVertex(parent, Map.CHINA, Map.CHINA, 715, 120, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object india = graph.insertVertex(parent, Map.INDIA, Map.INDIA, 680, 200, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object irkutsk = graph.insertVertex(parent, Map.IRKUTSK, Map.IRKUTSK, 820, 100, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object japan = graph.insertVertex(parent, Map.JAPAN, Map.JAPAN, 910, 120, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object kamchatka = graph.insertVertex(parent, Map.KAMCHATKA, Map.KAMCHATKA, 890, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object middleEast = graph.insertVertex(parent, Map.MIDDLE_EAST, Map.MIDDLE_EAST, 600, 180, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object mongolia = graph.insertVertex(parent, Map.MONGOLIA, Map.MONGOLIA, 860, 200, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object siam = graph.insertVertex(parent, Map.SIAM, Map.SIAM, 770, 220, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object siberia = graph.insertVertex(parent, Map.SIBERIA, Map.SIBERIA, 715, 40, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ural = graph.insertVertex(parent, Map.URAL, Map.URAL, 630, 30, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object yakutsk = graph.insertVertex(parent, Map.YAKUTSK, Map.YAKUTSK, 800, 30, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",afghanistan, middleEast, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, china, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, india, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, ural, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",china, india, "endArrow=false");
            graph.insertEdge(parent, "","",china, siam, "endArrow=false");
            graph.insertEdge(parent, "","",china, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",china, siberia, "endArrow=false");
            graph.insertEdge(parent, "","",china, ural, "endArrow=false");
            graph.insertEdge(parent, "","",india, middleEast, "endArrow=false");
            graph.insertEdge(parent, "","",india, siam, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, kamchatka, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, siberia, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, yakutsk, "endArrow=false");
            graph.insertEdge(parent, "","",japan, kamchatka, "endArrow=false");
            graph.insertEdge(parent, "","",japan, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",kamchatka, yakutsk, "endArrow=false");
            graph.insertEdge(parent, "","",kamchatka, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, egypt, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, eastAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",mongolia, siberia, "endArrow=false");
            graph.insertEdge(parent, "","",siberia, ural, "endArrow=false");
            graph.insertEdge(parent, "","",siberia, yakutsk, "endArrow=false");

            //Australia
            Object easternAustralia = graph.insertVertex(parent, Map.EASTERN_AUSTRALIA, Map.EASTERN_AUSTRALIA, 850, 480, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object indonesia = graph.insertVertex(parent, Map.INDONESIA, Map.INDONESIA, 780, 360, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object newGuinea = graph.insertVertex(parent, Map.NEW_GUINEA, Map.NEW_GUINEA, 870, 390, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernAustralia = graph.insertVertex(parent, Map.WESTERN_AUSTRALIA, Map.WESTERN_AUSTRALIA, 760, 450, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",easternAustralia, westernAustralia, "endArrow=false");
            graph.insertEdge(parent, "","",easternAustralia, newGuinea, "endArrow=false");
            graph.insertEdge(parent, "","",indonesia, siam, "endArrow=false");
            graph.insertEdge(parent, "","",indonesia, newGuinea, "endArrow=false");
            graph.insertEdge(parent, "","",indonesia, westernAustralia, "endArrow=false");
            graph.insertEdge(parent, "","",westernAustralia, newGuinea, "endArrow=false");

            //placeholders
            Object placeholder1 = graph.insertVertex(parent, "", "", -20, 50, 1, 1, "shape=ellipse;whiteSpace=wrap");
            Object placeholder2 = graph.insertVertex(parent, "", "", 2000, 20, 1, 1, "shape=ellipse;whiteSpace=wrap");
            graph.insertEdge(parent, "","",alaska, placeholder1, "endArrow=false");
            graph.insertEdge(parent, "","",kamchatka, placeholder2, "endArrow=false");
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args)
    {
        GraphTest frame = new GraphTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setVisible(true);
    }

}
