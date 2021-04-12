// Original bug: KT-34573

public fun foo(): Int {
    return 42
} // foo

public fun bar(): Int
// bar
{
    return 42
}

// (action messed up with indentation)
public fun baz(): Int
        /*
         * baz
         */ {
    return 42
         }
