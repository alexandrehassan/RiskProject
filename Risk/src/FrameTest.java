import javax.swing.JFrame;


import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

import java.util.HashMap;

public class FrameTest extends JFrame {
    public static final String VERTEX_STYLE = "shape=ellipse;whiteSpace=wrap;strokeWidth=4;#000000";
    public static final String VERTEX_STYLE_ONE_WORD = "shape=ellipse;strokeWidth=4;#000000";


    public static final int WIDTH = 80;
    public static final int HEIGHT = 65;
    public static final String EDGE_STYLE = "endArrow=false;#000000";
    private Map map;

    private mxGraph graph;

    public FrameTest() {
        map = XML.mapFromXML("map.xml");
        this.graph = new mxGraph();
        graph.setMaximumGraphBounds(new mxRectangle(0,0,1200,1200));
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try {
            int x = 0;
            int y = 0;
            HashMap<String, Object> vertices = new HashMap<>();
            for(Country country: map.getAllCountries()){
                x +=10;
                y +=10;

                vertices.put(country.getName(),insertVertex(country.getName(), x, y));
            }
            Country country;
            for (String vertexName : vertices.keySet()) {
                country = map.getCountry(vertexName);
                for (Country neighbor : country.getNeighbors()) {
                    insertEdge(vertices.get(neighbor.getName()),vertices.get(vertexName));
                }
            }

            for(Continent continent: map.getContinents()){
                for(Country country1: continent.getCountries()){
                    graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, continent.getColor(), new Object[]{vertices.get(country1.getName())});
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
        layout.setForceConstant(45);
        layout.setUseInputOrigin(false);
        layout.setInitialTemp(500);
//        layout.setMinDistanceLimit(1);

//        mxGraphLayout layout = new mxOrganicLayout(graph, new Rectangle(500,500));
        //layout.setDisableEdgeStyle(false);
//        mxGraphLayout layout = new mxCircleLayout(graph);
        layout.execute(parent);

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);
        getContentPane().add(graphComponent);
    }

    /**
     * Helper function for the creation of the insertion of vertices.
     *
     * @param name      the name of the vertex
     * @param xPosition .
     * @param yPosition .
     * @return the new vertex.
     */
    private Object insertVertex(String name, int xPosition, int yPosition) {
        return graph.insertVertex(graph.getDefaultParent(), name, name, xPosition, yPosition, WIDTH, HEIGHT, getVertexStyle(name));
    }

    /**
     * Creates an edge between two vertices.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    private void insertEdge(Object vertex1, Object vertex2) {
        graph.insertEdge(graph.getDefaultParent(), "", "", vertex1, vertex2, EDGE_STYLE);
    }

    private String getVertexStyle(String name){
        if(name.length()>10 && name.split(" ").length ==1){
            return VERTEX_STYLE_ONE_WORD;
        }
        return VERTEX_STYLE;
    }

    public static void main(String[] args) {
        FrameTest frame = new FrameTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1800);
        frame.setVisible(true);
    }

}