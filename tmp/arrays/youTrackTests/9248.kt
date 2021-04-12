// Original bug: KT-15447

fun method()
{
    var methodVar = "foo"

    fun localMethod()
    {
        // This generates compiler backend error.
        var getMethodVarValue = lazy { methodVar }::value

        // FYI: This works.
        val _workaroundHelper = lazy { methodVar }
        var getMethodVarValueViaMoreIndirection = _workaroundHelper::value

        // FYI: This works also (but doesn't apply to my actual, non-example code).
        val localMethodVar = "foo"
        var getLocalMethodVarValue = lazy { localMethodVar }::value
    }
}
