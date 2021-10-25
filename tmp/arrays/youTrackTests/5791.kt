// Original bug: KT-17933

//import java.util.*

val testData = mapOf (
        "Annotation" to {Annotation::class},
        "Any" to {Any::class},
        "Array" to {Array<Any>::class}, //<Any>
        "AssertionError" to {AssertionError::class},
        "Boolean" to {Boolean::class},
        "BooleanArray" to {BooleanArray::class},
        "Byte" to {Byte::class},
        "ByteArray" to {ByteArray::class},
        "Char" to {Char::class},
        "CharArray" to {CharArray::class},
        "CharSequence" to {CharSequence::class},
        "ClassCastException" to {ClassCastException::class},
        "Comparable" to {Comparable::class},
        "Comparator" to {Comparator::class},
        "ConcurrentModificationException" to {ConcurrentModificationException::class}, //java.util
        "Deprecated" to {Deprecated::class},
        "DeprecationLevel" to {DeprecationLevel::class},
        "Double" to {Double::class},
        "DoubleArray" to {DoubleArray::class},
        "DslMarker" to {DslMarker::class},
        "Enum" to {Enum::class},
        "Error" to {Error::class},
        "Exception" to {Exception::class},
        "ExtensionFunctionType" to {ExtensionFunctionType::class},
        "Float" to {Float::class},
        "FloatArray" to {FloatArray::class},
        "Function" to {Function::class},
        "IllegalArgumentException" to {IllegalArgumentException::class},
        "IllegalStateException" to {IllegalStateException::class},
        "IndexOutOfBoundsException" to {IndexOutOfBoundsException::class},
        "Int" to {Int::class},
        "IntArray" to {IntArray::class},
        "KotlinVersion" to {KotlinVersion::class},
        "Lazy" to {Lazy::class},
        "LazyThreadSafetyMode" to {LazyThreadSafetyMode::class},
        "Long" to {Long::class},
        "LongArray" to {LongArray::class},
        "NoSuchElementException" to {NoSuchElementException::class},
        "Nothing" to {Nothing::class},
        "NotImplementedError" to {NotImplementedError::class},
        "NoWhenBranchMatchedException" to {NoWhenBranchMatchedException::class},
        "NullPointerException" to {NullPointerException::class},
        "Number" to {Number::class},
        "NumberFormatException" to {NumberFormatException::class},
        "Pair" to {Pair::class},
        "ParameterName" to {ParameterName::class},
        "PublishedApi" to {PublishedApi::class},
        "ReplaceWith" to {ReplaceWith::class},
        "RuntimeException" to {RuntimeException::class},
        "Short" to {Short::class},
        "ShortArray" to {ShortArray::class},
        "SinceKotlin" to {SinceKotlin::class},
        "String" to {String::class},
        "Suppress" to {Suppress::class},
        "Synchronized" to {Synchronized::class},
        "Throwable" to {Throwable::class},
        "Triple" to {Triple::class},
        "Unit" to {Unit::class},
        "UnsafeVariance" to {UnsafeVariance::class},
        "UnsupportedOperationException" to {UnsupportedOperationException::class},
        "Volatile" to {Volatile::class}
)
fun test(name : String, f: () -> Any) : String = try {
    f().toString()
} catch (e : Throwable) {
    "!!! - $e"
}

fun main(args: Array<String>) {

    for ((name, function) in testData) {
        val res = test(name, function).toString()
        val comment = when {
            res.startsWith("!!!") -> "Exception!"
            !res.contains(name) -> "No real name"
            else -> "OK"
        }

        println("|$name|$res|$comment|")
    }

}
