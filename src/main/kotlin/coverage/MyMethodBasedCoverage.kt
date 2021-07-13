package coverage

import org.jetbrains.kotlin.utils.createConcurrentMultiMap
import java.util.concurrent.ConcurrentHashMap

object MyMethodBasedCoverage {
    val methodProbes = ConcurrentHashMap<CoverageEntry, Int>()

    @JvmStatic
    fun putEntry(entry: CoverageEntry) {
        val currentAmount = methodProbes[entry]
        if (currentAmount == null) {
            methodProbes[entry] = 1
        } else {
            methodProbes[entry] = currentAmount + 1
        }
    }
}