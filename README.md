# Malzahar
A reactive Kotlin library for Riot Games' League of Legends API.

The entire goal of this project is a continous learning experiment for me within Kotlin Multi-Platform projects and reactive design principles, as well as functional programming.

## Features
* Response handling for invalid HTTP Responses as specified by the Riot API (non-200 status codes)
* Rate Limits
* All method calls returns RxJava Observables for reactive programming principles
* Modular, only use what you need keeping a very small dependency
* Multi-Platform: Use it in your Android apps or other JVM projects
### TODO
* Automatically queue for failed requests, up to a defined amount.
* Add constants
* Wrap rest of API Endpoints (See Issue #10)
* A completed implementation of API within the desktop app
