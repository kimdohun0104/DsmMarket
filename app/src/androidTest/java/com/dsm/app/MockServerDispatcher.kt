package com.dsm.app

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class MockServerDispatcher {
    class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            when (request.path) {
                "/auth/login" -> MockResponse().setResponseCode(200).setBody("{\"nick\":\"nick\", \"access_token\":\"access_token\", \"refresh_token\":\"refresh_token\"}")
                "/account/join" -> MockResponse().setResponseCode(200).setBody("{}")
                else -> MockResponse().setResponseCode(404)
            }
    }

    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }
}