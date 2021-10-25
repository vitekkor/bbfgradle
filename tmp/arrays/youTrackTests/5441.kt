// Original bug: KT-25902

package tmp

fun F(t: Throwable)
{
    t.printStackTrace()
    //^^^^^^^^^^^^^^^^^ Symbol is declared in module 'java.base' which does not export package 'kotlin'
}
