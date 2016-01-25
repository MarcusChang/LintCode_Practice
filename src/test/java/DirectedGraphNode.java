import java.util.ArrayList;

/**
 * Created by Marcus_Chang on 2016/1/25.
 */
public class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }

}
