// Original bug: KT-32351

fun <T> T.apply_notinline(block: T.() -> Unit): T {
    block()
    return this
}

// issue does not reproduce when 'inline' removed
inline fun <T, R> T.mylet(block: (T) -> R): R = block(this)

// issue does not reproduce when 'data' removed
data class Key(val s: String = "")

// issue does not reproduce when transformed to object
class Uniqued {
    val key = Key()
}

fun main() {
    val u = Uniqued()
  
    val cache = ViewStateCache()
    // issue does not reproduce without apply or when it is changed to something simple
    val ableView = View().apply_notinline { uniquedKey = u.key.toString() }

    cache.prepareToUpdate(u).saveOldView(ableView)
    cache.prepareToUpdate(u)
}

class View {
    var uniquedKey: String = ""
}

class ViewStateCache private constructor(
    private var viewStates: Map<String, String>
) {
    constructor() : this(emptyMap())

    interface UpdateTools {
        fun saveOldView(oldView: View) {}
        fun restoreNewView(newView: View) {}
    }

    fun prepareToUpdate(newStack: Uniqued): UpdateTools {
        val newScreenKey = newStack.key.toString()

        return viewStates[newScreenKey]
            ?.mylet {
                object : UpdateTools {
                    override fun restoreNewView(newView: View) {
                        viewStates -= it
                    }
                }
            }
            ?: object : UpdateTools {
                override fun saveOldView(oldView: View) {
                    val savedKey = oldView.uniquedKey
                    val pair = savedKey to savedKey
                    viewStates += pair
                }
            }
    }
}
