// Original bug: KT-19606

import java.util.HashMap

class TestMethodReference {
    private val hashMap = HashMap<String, String>()

    fun update(key: String, msg: String) {
        hashMap.merge(key, msg, { obj, str -> obj + str })
    }
}
