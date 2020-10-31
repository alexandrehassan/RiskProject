
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
            Object alaska = graph.insertVertex(parent, "Alaska", "Alaska", 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object alberta = graph.insertVertex(parent, "Alberta", "Alberta", 45, 105, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object centralAmerica = graph.insertVertex(parent, "Central America", "Central America", 125, 260, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object easternUnitedStates = graph.insertVertex(parent, "Eastern United States", "Eastern United States", 175, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object greenland = graph.insertVertex(parent, "Greenland", "Greenland", 250, 10, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object northwestTerritory = graph.insertVertex(parent, "Northwest Territory", "Northwest Territory", 120, 25, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ontario = graph.insertVertex(parent, "Ontario", "Ontario", 125, 110, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object quebec = graph.insertVertex(parent, "Quebec", "Quebec", 210, 100, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernUnitedStates = graph.insertVertex(parent, "Western United States", "Western United States", 75, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");

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
