// Original bug: KT-2988

package somthing

import java.util.ArrayList

public class Cheese<T>(val collection: List<T>) {
    private val listeners = ArrayList<T>()

    public fun doSomething(): Int {
        var count = 0
        for (listener in listeners) {
            count += 1
        }
        return count
    }
}
