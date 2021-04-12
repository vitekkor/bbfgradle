// Original bug: KT-1777

import java.util.ArrayList
import java.util.Collections

public class Node( nodeId : Int ) {
    public val id : Int = nodeId
    public var edges : MutableList<Int> = ArrayList<Int>()

    public fun addEdge( connectedNode : Int ) {
        edges.add( connectedNode )
        Collections.sort( edges )
    }
}
