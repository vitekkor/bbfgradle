// Original bug: KT-43704

import java.util.*

fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in 0 until nums.size) {
        for (j in i until nums.size) {
            if (nums[i] + nums[j] == target)
                return intArrayOf(nums[i], nums[j])
        }
    }
    return intArrayOf()
}

fun main() {
    println("hello world")
    println(Arrays.toString(twoSum(intArrayOf(2, 7, 11, 15), 9)))
}

