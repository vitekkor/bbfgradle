// Original bug: KT-20373

import java.util.Stack

fun sumSubsets(arr: MutableList<Int>, num: Int): MutableList<MutableList<Int>> {
  class Subset(val indexes: IntArray) {
    fun add(index: Int) = Subset(indexes + index)

    fun values() = indexes.map { arr[it] }
    fun sum() = values().sum()
    fun missingValues() = (0 until arr.size).minus(indexes.toTypedArray())
  }

  val solutions = mutableListOf<MutableList<Int>>()

  val arrSum = arr.sum()
  if (arrSum >= num) {
    val stack = Stack<Subset>()
    stack.push(Subset(IntArray(0)))

    while (stack.isNotEmpty()) {
      val subset = stack.pop()
      val sum = subset.sum()

      if (sum == num)
        solutions.add(subset.values().toMutableList())
      else if (sum < num) {
        stack.addAll(subset.missingValues().map { subset.add(it) })
      }
    }
  }

  return solutions
}

fun main(args: Array<String>) {
  println(sumSubsets(mutableListOf(1, 2, 3, 4, 5), 5))
  println(sumSubsets(mutableListOf(1, 2, 2, 3, 4, 5), 5))
  println(sumSubsets(mutableListOf(), 0))
  println(sumSubsets(mutableListOf(1, 1, 1, 1, 1, 1, 1, 1, 1), 9))
  println(sumSubsets(mutableListOf(1, 1, 2, 2, 2, 5, 5, 6, 8, 8), 9))
  println(sumSubsets(mutableListOf(1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20), 36))
}
