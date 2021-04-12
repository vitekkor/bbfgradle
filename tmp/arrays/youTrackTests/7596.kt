// Original bug: KT-26797

sealed class Direction {
    object North: Direction()
    object South: Direction()
    object East: Direction()
    object West: Direction()
}

fun main(args:Array<String>) {
   // WANTED: prints "Direction: North" 
   // ACTUAL: prints "Direction: Direction$North@28a418fc"
   println("Direction: " + Direction.North)
    
}   
