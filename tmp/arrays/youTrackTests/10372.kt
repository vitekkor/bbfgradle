// Original bug: KT-5956

public fun CharSequence.charAt(index: Int): Char = (this as java.lang.CharSequence).charAt(index)
public fun CharSequence.length(): Int = (this as java.lang.CharSequence).length()
