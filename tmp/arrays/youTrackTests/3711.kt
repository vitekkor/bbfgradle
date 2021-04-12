// Original bug: KT-37391

class Marker {
    fun foo(y: Int) : Any  = TODO()
    val foo: Marker = TODO()            
    operator fun invoke(x: Int) : Any =  TODO()  //(1)
}

fun Marker.foo(x:Int) :Any= TODO()  //(2)

fun case4(marker : Marker?) {
    Marker().foo(x=1)        //resolved to (1)

    marker?.foo(x=1)         //resolved to (2) (!!!)

    if (marker!= null)
        marker.foo(x=1)      //resolved to (1)
}


