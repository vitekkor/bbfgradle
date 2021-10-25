// Original bug: KT-5502

public class Foo(protected val maxParsingTimeInMillis: Long?) {

    var parsingStartTimeStamp = 0L

    protected fun checkForParsingTimeout(): Boolean {
        if (maxParsingTimeInMillis == null)
            return true
        if (System.currentTimeMillis() - parsingStartTimeStamp > maxParsingTimeInMillis)
            throw IndexOutOfBoundsException()
        return true
    }
}
