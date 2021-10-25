// Original bug: KT-8118

fun foo(x : Int, y : Int){
    when(x) {
        0 -> (if(y > 0) println("A")) // Warning: Type is cast to 'kotlin.Unit'. Please specify 'kotlin.Unit' as expected type, if you mean such cast
        else -> println("B")
    }
}
