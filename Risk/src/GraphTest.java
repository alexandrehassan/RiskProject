
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
            Object alaska = graph.insertVertex(parent, Map.ALASKA, Map.ALASKA, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object alberta = graph.insertVertex(parent, Map.ALBERTA, Map.ALBERTA, 45, 105, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object centralAmerica = graph.insertVertex(parent, Map.CENTRAL_AMERICA, Map.CENTRAL_AMERICA, 125, 260, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object easternUnitedStates = graph.insertVertex(parent, Map.EASTERN_UNITED_STATES, Map.EASTERN_UNITED_STATES, 175, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object greenland = graph.insertVertex(parent, Map.GREENLAND, Map.GREENLAND, 250, 10, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object northwestTerritory = graph.insertVertex(parent, Map.NORTHWEST_TERRITORY, Map.NORTHWEST_TERRITORY, 120, 25, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ontario = graph.insertVertex(parent, Map.ONTARIO, Map.ONTARIO, 125, 110, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object quebec = graph.insertVertex(parent, Map.QUEBEC, Map.QUEBEC, 210, 100, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernUnitedStates = graph.insertVertex(parent, Map.WESTERN_UNITED_STATES, Map.WESTERN_UNITED_STATES, 75, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");

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
            Object argentina = graph.insertVertex(parent, Map.ARGENTINA, Map.ARGENTINA, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object brazil = graph.insertVertex(parent, Map.BRAZIL, Map.BRAZIL, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object peru = graph.insertVertex(parent, Map.PERU, Map.PERU, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object venezuela = graph.insertVertex(parent, Map.VENEZUELA, Map.VENEZUELA, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");

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
