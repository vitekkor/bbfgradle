// Original bug: KT-23170

class Point

open class Polygon(vertices: List<Point>) {
    open val vertices: List<Point>
    init {
        this.vertices = vertices.toList() 
        //   ^^^^^^^^ "Leaking this" reported here
    }
}
