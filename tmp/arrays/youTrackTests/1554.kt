// Original bug: KT-10904

public inline fun <reified T> jaggedArrayOfNulls(rows: Int, cols: Int): Array<Array<T?>>
        = Array(rows) { arrayOfNulls<T>(cols) }

public fun jaggedIntArray(rows: Int, cols: Int): Array<IntArray>
        = Array(rows) { IntArray(cols) }

// etc for primitives
