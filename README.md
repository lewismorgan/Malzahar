# Malzahar
A reactive Kotlin library for Riot Games' League of Legends API.

## Features
* Response handling for invalid HTTP Responses as specified by the Riot API (non-200 status codes)
* Rate Limits and Throttling (TODO)
* All method calls returns RxJava Observables for reactive programming principles
* Modular, only use what you need keeping a very small dependency
* Multi-Platform: Use it in your Android apps or other JVM projects

## Goal
The entire goal of this project is a learning experiment for Reactive
programming using the RxJava and RxKotlin extension. I've never touched functional
programming, nonetheless RxJava before.