// Original bug: KT-36237

fun main() {
    println("Same:");

    proxySame<Int>();
    directSame<String, Int>();

    println("\nDifferent:");

    proxyDiff<Int>();
    directDiff<String, Int>();
}

inline fun <reified P> proxySame() = directSame<String, P>();
inline fun <reified P, reified Z> directSame(
    func: () -> Unit = {
        println(Z::class.java.simpleName)
    }
) = func();


inline fun <reified P> proxyDiff() = directDiff<String, P>();
inline fun <reified Q, reified Z> directDiff(
    func: () -> Unit = {
        println(Z::class.java.simpleName)
    }
) = func();
