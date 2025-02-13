# Notes App - Android (Kotlin)

This is a simple Notes app built for Android using Kotlin. The app follows modern Android development practices and architecture, leveraging Retrofit for network operations, Hilt for dependency injection, and MVVM architecture for maintaining a clean separation of concerns.

## Features
- Add, update, and delete notes.
- Fetch notes from a remote API.
- Clean and intuitive UI for managing notes.
- Follows MVVM architecture for a well-structured and maintainable codebase.
- Hilt for dependency injection to manage dependencies easily.
- Retrofit for making network requests and integrating with APIs.

## Technologies Used
- **Kotlin**: The primary programming language for Android development.
- **MVVM Architecture**: Ensures a clean architecture with separation of concerns (Model, View, ViewModel).
- **Retrofit**: A type-safe HTTP client for Android that simplifies network calls to fetch data.
- **Hilt**: Dependency injection library for Android to make the codebase more modular and maintainable.
- **LiveData**: Used to manage and observe UI-related data in a lifecycle-conscious way.

## Project Structure
```
com.ken.notesappwithhilt/
├── api/
│   ├── NotesApi
│   ├── UserApi
├── di/
│   ├── AuthInterceptor
│   ├── NetworkModule
├── models/
│   ├── Note
│   ├── NoteRequest
│   ├── NoteResponse
│   ├── User
│   ├── UserRequest
│   ├── UserResponse
├── repository/
│   ├── NoteRepository
│   ├── UserRepository
├── ui/
│   ├── login/
│   ├── note/
├── utils/
│   ├── Constants
│   ├── NetworkResult
│   ├── TokenManager
├── MainActivity
├── NoteApplication
```
