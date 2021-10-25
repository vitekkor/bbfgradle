// Original bug: KT-7614

open class MyNode(val parent: MyNode?)
class ExtendedNode(parent: MyNode?): MyNode(parent)
fun my(ee: MyNode?) {
    var e = ee
    if (e is ExtendedNode) {
        e = e.parent
        if (e != null) {
            // Here we try to smart cast e to ExtendedNode
            // and get an exception because of it
            e = e.parent
            my(e)
        }
    }
}
