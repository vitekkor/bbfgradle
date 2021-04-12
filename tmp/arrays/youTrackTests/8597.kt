// Original bug: KT-13932

fun test1(): Unit = Unit
//returns 'undefined'
//  test1: function () {
//      Kotlin.kotlin.Unit;
//  }

fun test2(): Unit? = test1()
//returns 'undefined'
//  test2: function () {
//      return _.test.test1();
//  }

fun test3(): Unit? = Unit
//returns 'kotlin.Unit'
//  test3: function () {
//      return Kotlin.kotlin.Unit;
//  }
