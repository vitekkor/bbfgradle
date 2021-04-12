// Original bug: KT-34276

package bugs

import org.junit.jupiter.api.*
import kotlin.reflect.KProperty
import bugs.LazyEnumDelegationBug.*

class LazyDelegationBug<TT:Comparable<TT>>(val iv:TT,val foo:Byte) {
  var vv:TT = iv

  operator fun getValue(bug:LazyEnumDelegationBug,property: KProperty<*>): TT { return vv }
  operator fun setValue(bug:LazyEnumDelegationBug,property: KProperty<*>,any: Any) { if (iv::class == any::class) vv = any as TT }
}

enum class LazyEnumDelegationBug {
  AA,
  BB;

  // with "by spc", a java NullPointerException occurs: apparently the delegation does not require spc to be non-null before proceeding
  // without "by spc" and the equivalent get() and set(value) functions, everything works
  val spc by lazy { lazySpc() }
  var vv  by spc
    //get(  ) = spc.getValue(this,spc::vv)
    //set(xv) = spc.setValue(this,spc::vv,xv)

  private fun lazySpc():LazyDelegationBug<*> {
    return when (this) {
      AA -> LazyDelegationBug(0UL,-1)
      BB -> LazyDelegationBug(0  ,+1)
    }
  }
}

class LazyEnumDelegationBugTest() {
  @Test
  fun runTest() {
    AA.vv = -7
    BB.vv = 8
    println("AA(${AA.spc}) = ${AA.spc.vv}, BB(${BB.spc}) = ${BB.spc.vv}")
    AA.vv = 17UL
    BB.vv = 18
    println("AA(${AA.spc}) = ${AA.spc.vv}, BB(${BB.spc}) = ${BB.spc.vv}")

    for (bug in values()) println(bug.toString())
  }
}

