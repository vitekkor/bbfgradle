// Original bug: KT-28452

package test

class A

interface IFoo

class Index : IFoo

operator fun A.get(i: IFoo) = i.hashCode()

operator fun A.set(i: Index, v: Int) {
    println("i=$i, v=$v")
}

fun main(args: Array<String>) {
    A()[Index()] += 1
}
