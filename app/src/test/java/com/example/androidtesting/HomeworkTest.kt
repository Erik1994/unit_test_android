package com.example.androidtesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeworkTest {
    @Test
    fun `input 0 retrun 0 is true`() {
        val result = Homework.fib(0) == 0L
        assertThat(result).isTrue()
    }

    @Test
    fun `fib(n) = fib(n-2) + fib(n-1)  is true`() {
        val number1 = Homework.fib(5)
        val number2 = Homework.fib(3)
        val number3 = Homework.fib(4)
        val sum = number2 + number3

        val result = number1 == sum
        assertThat(result).isTrue()
    }

    @Test
    fun `input 1 retrun 1 is true`() {
        val result = Homework.fib(1) == 1L
        assertThat(result).isTrue()
    }

    @Test
    fun `user input string with wrong count of braces return false`() {
        val result = Homework.checkBraces(
            "(ERik))"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `user input string withouth braces return false`() {
        val result = Homework.checkBraces(
            "ERik"
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `user input string with proper braces false return true`() {
        val result = Homework.checkBraces(
            "((ERik))"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `user input empty string return false`() {
        val result = Homework.checkBraces(
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `user input string wit wrong order of braces return false`() {
        val result = Homework.checkBraces(
            ")Ripa("
        )
        assertThat(result).isFalse()
    }
}