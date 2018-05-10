package com.lewisjmorgan.malzahar.riot

class RiotApiException(code: Int, message: String) : Exception("HTTP Response $code: $message")