// Original bug: KT-32387

open class Parent {

   companion object {

      init { println("init Parent 1") }

      val child = Child.Companion

      init { println("init Parent 2") }
   }


   class Child : Parent() {

      companion object {
         init { println("init Child") }
      }
   }
}
