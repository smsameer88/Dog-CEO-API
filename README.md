# Dog Breeds App

This app uses [Dog CEO API](https://dog.ceo/dog-api/documentation/) to load data into the app. 

The architecture of this project is MMVM (Model View ViewModel) Clean Architecture. The app is architectured in a way that it would be easier to understand for other developers and reviewers. I have used clean architecture because it goes a step further in segregating the code base’s responsibilities and concerns.

The architecture of the app is divided into three layers.
- Ui Layer (Presentation Layer)
- Domain Layer
- Data Layer

### Jetpack libraries
- [Compose](https://developer.android.com/jetpack/androidx/releases/compose) - Defines UI programmatically with composable functions that describe its shape and data dependencies.
- [Compose Foundation](https://developer.android.com/jetpack/androidx/releases/compose-foundation) - Write Jetpack Compose applications with ready-to-use building blocks and extend the foundation to build your design system pieces.
- [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - Fundamental components of compose UI needed to interact with the device, including layout, drawing, and input.
- [Compose Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - Build Jetpack Compose UIs with ready-to-use Material Design Components.
- [Arch Core](https://developer.android.com/jetpack/androidx/releases/arch-core) - Helper for other arch dependencies, including JUnit test rules.
- [Room](https://developer.android.com/training/data-storage/room) - Create, store, and manage persistent data backed by an SQLite database. 
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection plays a central role in the architectural pattern used.
- [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - Build and structure your in-app UI, handle deep links, and navigate between screens.

### Other libraries
- [Kotlinx Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines. I used this for asynchronous programming to obtain data from the network.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client and supports coroutines out of the box.
- [Moshi](https://github.com/square/moshi) - JSON Parser, used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection plays a central role in the architectural pattern used.
- [JUnit](https://junit.org/junit4/) - This was used for unit testing the database, the use cases, and the ViewModels.
- [Mockk](https://mockk.io/) This is a mocking library for Kotlin. I used it to provide test doubles during testing.
- [Truth](https://truth.dev/) - Assertions Library, provides readability as far as assertions are concerned.
- [Espresso](https://developer.android.com/training/testing/espresso) - Used for writing Android UI tests for our DAO.
- [Coil](https://github.com/coil-kt/coil) - Image loading for Android backed by Kotlin Coroutines.
- [Accompanist Pager](https://google.github.io/accompanist/pager/) - A library that provides paging layouts for Jetpack Compose.
- [Accompanist Navigation Animation](https://google.github.io/accompanist/navigation-animation/) A library that provides Compose Animation support for Jetpack Navigation Compose.
- [Kotest Assertions](https://kotest.io/docs/assertions/assertions.html) -  I am using it in the test cases. Kotest calls types of state assertion functions matchers.








