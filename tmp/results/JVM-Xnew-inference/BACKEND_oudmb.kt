class Thing(
 delegate: CharSequence) : CharSequence {
    override fun subSequence( nullableString: Int,end: Int) = TODO()
override fun get(index: Int)  = TODO()
    override val length: Int get() = 1
}
val txt = Thing((::A))