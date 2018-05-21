package com.zcf.app2

import android.widget.Toast
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun aa() {
//        val sum = sum(2, 3)
//        System.out.print(sum)
//fun sum(a: Int, b: Int) = a + b//自动
//        val name: String
//        val id: Int
//        val a = 3
//
////        val   不可变的数据类型
//
//        var b = 3
//        b += 1
//
//
//        var s1 = "aa is $b"
////        System.out.print(s1)
//
//
//        val items = listOf("apple", "banana", "kiwifruit")
//
//        for (a in items) {
//            println(a)
//        }
//
//        for (index in items.indices) {
//            println("item $index  is ${items[index]}")
//        }


//        println(describe(3.14))


//        val x = 10
//        val y = 9
//        if (x in 1..y+1) {
//            println("fits in range")
//        }


//        for (x in 1..5) {
//            print(x)
//        }

//
//        for (x in 1..10 step 3) {
//            print(x)
//        }
//
//        println()
//        for (x in 9 downTo 2 step 3) {
//            print(x)
//        }

//        val items = setOf("apple", "banana", "kiwifruit")
//        for (index in items.indices) {
//            print("index $items[index]")
//        }

        val a: String = "aa"

        when (a) {
            "aa" -> print("aaa")

        }


    }

    fun describe(obj: Any): String =
            when (obj) {
                1 -> "One"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknown"
            }


}
