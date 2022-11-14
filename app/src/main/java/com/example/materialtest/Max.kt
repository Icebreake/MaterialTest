package com.example.materialtest

import java.lang.RuntimeException

//使用了vararg关键字，也就是说它现在可以接收任意多个整型参数
fun max(vararg  nums: Int): Int {
    //记录所有数的最大值，并在一开始将它复杂成了整型范围的最小值
    var maxNum = Int.MIN_VALUE
    //循环遍历nums参数列表，如果发现当前遍历的数字比maxNum更大，就将maxNum的值更新成这个数
    for (num in nums) {
        maxNum = kotlin.math.max(maxNum, num)
    }
    return maxNum
}

fun <T: Comparable<T>> max(vararg nums: T): T {
    if (nums.isEmpty()) throw  RuntimeException("Params can not be empty.")
    //将maxNum的值赋值成nums参数列表中第一个参数的值
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    return maxNum
}

fun <T: Comparable<T>> min(vararg nums: T): T {
    if (nums.isEmpty()) throw  RuntimeException("Params can not be empty.")
    var minNum = nums[0]
    for (num in nums) {
        if (num < minNum) {
            minNum = num
        }
    }
    return minNum
}

fun main() {
    val a = 1.2
    val b = 0.2
    val c = 4.3
    val smallest = min(a, b, c)
    println("The smallest number is $smallest")
}