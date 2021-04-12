// Original bug: KT-34316

package test.pkg

inline fun <T> a(t: T) { }
inline fun <reified T> b(t: T) { }
private inline fun <reified T> c(t: T) { } // hide
internal inline fun <reified T> d(t: T) { } // hide
public inline fun <reified T> e(t: T) { }
inline fun <reified T> T.f(t: T) { }
