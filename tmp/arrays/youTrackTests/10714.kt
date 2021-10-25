// Original bug: KT-2482

public fun test() {
    if ( 0 == 0 ) { // Does not crash if either this...
        if ( 0 == 0 ) { // ...or this is changed to if ( true )
            // Does not crash if the following is uncommented.
            //println("foo")
        }
    }
}
