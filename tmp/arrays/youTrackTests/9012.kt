// Original bug: KT-12269

fun <T> foo(element: T) {
    if (element is CharSequence?) {
                            // ^ Non-null type is checked for instance of nullable type
    }
    
    // also no smartcast here, besides element is not-null here
}
