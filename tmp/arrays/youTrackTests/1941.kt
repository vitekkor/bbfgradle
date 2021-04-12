// Original bug: KT-43012

package test

annotation class Ann

interface IFoo {
    @Ann val foo: String
}

class DFoo(d: IFoo) : IFoo by d

// JVM:
//  IFoo$DefaultImpls:
//      public static synthetic getFoo$annotations()V

// JVM_IR:
//  IFoo$DefaultImpls:
//      public static synthetic getFoo$annotations()V
//  DFoo:
//      public static synthetic getFoo$annotations()V

fun main() {
    println(DFoo::foo.annotations.toList())
}

// JVM_IR: [@test.Ann()]
// JVM: []
