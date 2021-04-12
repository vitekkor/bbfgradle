// Original bug: KT-3387

public class Constructor(var i : Int = 1, var s: String = "")

fun main(args : Array<String>){
    var c1 = Constructor(2)
    var c2 = Constructor(s = "xyz")
}
