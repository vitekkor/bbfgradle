// Original bug: KT-498

class IdUnavailableException() : Exception() {}

fun <T : Any> T.getJavaClass() : Class<T> {
    return ((this as Object).getClass()) as Class<T> // Some error here, because of Exception() used above. ?!!!
}
