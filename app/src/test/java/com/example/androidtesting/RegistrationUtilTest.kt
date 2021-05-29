package com.example.androidtesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistration(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns ture`() {
        val result = RegistrationUtil.validateRegistration(
            "Phillipp",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }


    @Test
    fun `username already exists returns false`() {
        val result = RegistrationUtil.validateRegistration(
            "Carl",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username empty password return false`() {
        val result = RegistrationUtil.validateRegistration(
            "Erik",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username incorrectly repeated password return false`() {
        val result = RegistrationUtil.validateRegistration(
            "Erik",
            "123",
            "456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username password contains less than 2 digits return false`() {
        val result = RegistrationUtil.validateRegistration(
            "Erik",
            "absd1",
            "abcd1"
        )
        assertThat(result).isFalse()
    }
}