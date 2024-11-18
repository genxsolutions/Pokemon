# PokemonApp

Welcome to Pokemon sample app, presents list of pokemon and detail, built with MVVM architecture and Jetpack Compose.

## Major Highlights

- **Jetpack Compose** for modern UI
- **MVVM architecture** for a clean and scalable codebase
- **Kotlin** and **Kotlin DSL**
- **Dagger Hilt** for efficient dependency injection.
- **Retrofit** for seamless networking
- **Coroutines** and **Flow** for asynchronous programming
- **StateFlow** for streamlined state management
- **Unit tests** and **UI tests** for robust code coverage
- **Navigation** for smooth transitions between screens
- **Coil** for efficient image loading
- **multi module** just for concept of separating utilities into separate module (use android multimodule template for feature level separation)

<p align="center">
<img alt="screenshots"  src="https://github.com/genxsolutions/Pokemon/blob/main/assets/Pokemon_app_architecture.jpeg">
</p>

## Features Implemented

- Show list of pokemon as card
- show detail when click on card item
- accessibility support
- dark and light mode support
- central dependency management
- modularised concept

## Features planned as backlog
- **Pagination** to efficiently load and display list 
- **Offline caching** with a **single source of truth**
- **Room DB** for local storage of listings
- **work manager** for background caching
- detailed testing and handling the low memory and restart scenarios 
- no network and error screen with retry functionality
- config change handling

## Dependency Use

- Jetpack Compose for UI: Modern UI toolkit for building native Android UIs
- Coil for Image Loading: Efficiently loads and caches images
- Retrofit for Networking: A type-safe HTTP client for smooth network requests
- Dagger Hilt for Dependency Injection: Simplifies dependency injection
- Paging Compose for Pagination: Simplifies the implementation of paginated lists
- Mockito, JUnit, Turbine for Testing: Ensures the reliability of the application

## How to Run the Project

- Clone the Repository:
```
git clone https://github.com/genxsolutions/Pokemon.git
cd Pokemon

Note: seleclt JDK 17 in Android build gradle setting if there is JDK image compilation issue
```
- Build and run the Pokemon App.

<div style="display: flex; justify-content: space-between;">
    <img alt="cake list"  src="https://github.com/genxsolutions/Pokemon/blob/main/assets/pokemonList.png" width="400" height="600">
    <img alt="description" src="https://github.com/genxsolutions/Pokemon/blob/main/assets/PokemonDetail.png" width="400" height="600">
    <img alt="error" src="https://github.com/genxsolutions/Cakes/Pokemon/main/assets/error.png" width="400" height="600">

</div>
