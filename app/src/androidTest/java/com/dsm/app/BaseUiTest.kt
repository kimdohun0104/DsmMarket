package com.dsm.app

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

open class BaseUiTest {

    val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}