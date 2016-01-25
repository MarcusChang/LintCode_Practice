import java.util.ArrayList;

/**
 * Created by Marcus_Chang on 2016/1/25.
 */
public class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>();}

}
