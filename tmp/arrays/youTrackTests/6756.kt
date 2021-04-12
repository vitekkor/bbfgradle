// Original bug: KT-21213

/**
 * @param test a very long line, which at some point should be broken into a next line
 * because otherwise it does not look nicely in an editor.
 */
fun foo(test: String){}
