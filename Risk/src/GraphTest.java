
import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphTest extends JFrame{
    public GraphTest() {

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try
        {
            //North America
            Object alaska = graph.insertVertex(parent, Map.ALASKA, Map.ALASKA, 20, 20, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object alberta = graph.insertVertex(parent, Map.ALBERTA, Map.ALBERTA, 45, 105, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object centralAmerica = graph.insertVertex(parent, Map.CENTRAL_AMERICA, Map.CENTRAL_AMERICA, 125, 260, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object easternUnitedStates = graph.insertVertex(parent, Map.EASTERN_UNITED_STATES, Map.EASTERN_UNITED_STATES, 175, 185, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object greenland = graph.insertVertex(parent, Map.GREENLAND, Map.GREENLAND, 250, 10, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object northwestTerritory = graph.insertVertex(parent, Map.NORTHWEST_TERRITORY, Map.NORTHWEST_TERRITORY, 120, 25, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object ontario = graph.insertVertex(parent, Map.ONTARIO, Map.ONTARIO, 125, 110, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object quebec = graph.insertVertex(parent, Map.QUEBEC, Map.QUEBEC, 210, 100, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object westernUnitedStates = graph.insertVertex(parent, Map.WESTERN_UNITED_STATES, Map.WESTERN_UNITED_STATES, 75, 185, 72, 72, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "e0", "", alaska, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "e1", "", alaska, alberta, "endArrow=false");
            graph.insertEdge(parent, "e2", "", alberta, ontario, "endArrow=false");
            graph.insertEdge(parent, "e3", "", alberta, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "e4", "", ontario, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "e5", "", greenland, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "e6", "", northwestTerritory, alberta, "endArrow=false");
            graph.insertEdge(parent, "e7", "", greenland, ontario, "endArrow=false");
            graph.insertEdge(parent, "e8", "", greenland, quebec, "endArrow=false");
            graph.insertEdge(parent, "e9", "", ontario, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "e10", "", ontario, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "e11", "", ontario, quebec, "endArrow=false");
            graph.insertEdge(parent, "e12", "", quebec, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "e13", "", centralAmerica, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "e14", "", centralAmerica, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "e15", "", easternUnitedStates, westernUnitedStates, "endArrow=false");

            //South America
            Object argentina = graph.insertVertex(parent, Map.ARGENTINA, Map.ARGENTINA, 145, 540, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object brazil = graph.insertVertex(parent, Map.BRAZIL, Map.BRAZIL, 200, 460, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object peru = graph.insertVertex(parent, Map.PERU, Map.PERU, 90, 460, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object venezuela = graph.insertVertex(parent, Map.VENEZUELA, Map.VENEZUELA, 145, 380, 72, 72, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",venezuela, centralAmerica, "endArrow=false");
            graph.insertEdge(parent, "","",venezuela, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",venezuela, peru, "endArrow=false");
            graph.insertEdge(parent, "","",argentina, peru, "endArrow=false");
            graph.insertEdge(parent, "","",argentina, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",brazil, peru, "endArrow=false");

            //Europe
            Object greatBritain = graph.insertVertex(parent, Map.GREAT_BRITAIN, Map.GREAT_BRITAIN, 340, 120, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object iceland = graph.insertVertex(parent, Map.ICELAND, Map.ICELAND, 360, 35, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object northernEurope = graph.insertVertex(parent, Map.NORTHERN_EUROPE, Map.NORTHERN_EUROPE, 430, 135, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object scandinavia = graph.insertVertex(parent, Map.SCANDINAVIA, Map.SCANDINAVIA, 450, 55, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object southernEurope = graph.insertVertex(parent, Map.SOUTHERN_EUROPE, Map.SOUTHERN_EUROPE, 430, 225, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object ukraine = graph.insertVertex(parent, Map.UKRAINE, Map.UKRAINE, 520, 130, 72, 72, "shape=ellipse;whiteSpace=wrap");
            Object westernEurope = graph.insertVertex(parent, Map.WESTERN_EUROPE, Map.WESTERN_EUROPE, 345, 205, 72, 72, "shape=ellipse;whiteSpace=wrap");

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
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

}
