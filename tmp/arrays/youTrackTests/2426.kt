// Original bug: KT-9720

/**
 * I do something useful.
 *  
 * > f(42)   // The user inputs REPL prompt and inputs f(42).
 * 43 : Int  // The expression is then evaluated and the result appears.
 */
fun f(x: Int) = x + 1
