// Original bug: KT-24918

sealed class Bar{
    object Barish: Bar()
    object Bariz: Bar()
}
fun <R> foo(a: () -> R, b: () -> R){}
fun test(){
    foo({ Bar.Barish }, { Bar.Bariz }) //that's fine
    foo({ Bar.Barish }, { if(true) Bar.Barish else Bar.Bariz }) // Bar.Bariz not ok, Bar.Barish required
    foo({ Bar.Barish }, { if(true) Bar.Bariz else Bar.Barish }) // Bar.Bariz not ok, Bar.Barish required
    foo({ Bar.Barish }, { if(true) Bar.Bariz as Bar else Bar.Barish }) // Bar not ok, Bar.Barish required
    foo({ Bar.Barish as Bar }, { if(true) Bar.Bariz else Bar.Barish }) // that's ok
}
