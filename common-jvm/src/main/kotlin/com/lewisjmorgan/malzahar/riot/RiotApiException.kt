package com.lewisjmorgan.malzahar.riot

class RiotApiException(code: Int, message: String) : Exception("HTTP Response $code: $message")

class RiotRateLimitException(retry: Long) : Exception("Rate Limit Exceeded. Try again in ${retry}s.")

class RiotRateLimitTypeException(type: String) : Exception("Did not expect X-Rate-Limit-Type $type")