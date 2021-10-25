// Original bug: KT-30963

fun checkRunBehaviour(s: String?, t: String?): Boolean{

    var b = false

    s?.let {
        b = true
        t?.let {

        } /*?: false*/
    } ?: run {
        b= false
    }

    return b
}
