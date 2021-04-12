// Original bug: KT-41124


enum class Case1(var x: Int) {

    VAL1(1),
    VAL2(2);

    init {
        try {
            Case1.VAL1.x = 4 // green, NPE , idea suggestion: remove Redundant qualifier name which leads to the compile time error 
        } catch (e: NullPointerException) {
            //...
        }
    }
}
