// Original bug: KT-26991

@Deprecated("", replaceWith = ReplaceWith("X"))
object O {
    
}

object X {
}


fun f() {
    val o = O // there is strike over O but no intention available
}
