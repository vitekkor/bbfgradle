fun foo(x: Int) =
        when (x) {
            1 -> ::baz
            else -> ""
        }