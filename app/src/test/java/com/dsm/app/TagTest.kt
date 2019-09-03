package com.dsm.app

import org.junit.Test

class TagTest {

    @Test
    fun tagTest() {
        val inputString = "엄청난 굉장한 책 도서 알고리즘 "
        val after = " $inputString".trimEnd().replace(" ", "#")
        println(after)
    }
}