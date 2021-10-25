// Original bug: KT-19536

fun foo(name: String) {
    // no argument assertion for the extension receiver of 'zap'
    System.getProperty(name).zap() 
}

private val strings = ArrayList<String>() 

private fun String.zap() {
    // no parameter assertion on extension receiver
    strings.add(this) // null propagates
}
