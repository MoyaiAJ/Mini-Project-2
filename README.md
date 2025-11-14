# Offline-First App - User Directory

A simple offline-first Android app that fetches users from the JSONPlaceholder API, caches them locally with Room, and displays them in a searchable list using Jetpack Compose.

## Features
- Fetches users from `https://jsonplaceholder.typicode.com/users` using Retrofit.  
- Caches all users in a Room database (`OnConflictStrategy.REPLACE`).  
- UI always reads from Room via `Flow` (single source of truth).  
- Offline-first: cached data loads immediately; API refresh happens in background.  
- Local search (name or email) using a Room query.  
- Clean Compose UI with cards and a top search bar.

## Screenshots
* Opening app with internet

<img width="160" height="357" alt="Image" src="https://github.com/user-attachments/assets/9f9056a5-175f-4886-a7bc-06630a8e2526" />

* Opening app without internet

<img width="160" height="357" alt="Image" src="https://github.com/user-attachments/assets/a280d37c-731b-4dc8-b4bc-3e327c2c06cc" />

* Search bar at the top searching by name

<img width="160" height="357" alt="Image" src="https://github.com/user-attachments/assets/4d0782d4-9689-48a7-ad39-3c1da042caef" />


## Concepts Used
- **Room**: `User`, `UserDao`, `AppDatabase`  
- **Networking**: `ApiService`  
- **Repository**: handles API → DB sync  
- **ViewModel**: manages search, exposes a `StateFlow<List<User>>`  
- **UI**: `UserScreen`, `UserItem`  
- **Pattern**: MVVM + offline-first + StateFlow observation
