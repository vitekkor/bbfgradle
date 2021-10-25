// Original bug: KT-10555

public inline fun <reified INNER> array2d(
        sizeOuter: Int, 
        sizeInner: Int, 
        init: (Int, Int)->INNER
): Array<Array<INNER>> = 
        Array(sizeOuter) { outer -> Array<INNER>(sizeInner, {inner -> init(outer, inner)} ) }
