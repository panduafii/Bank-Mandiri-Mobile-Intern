# Demo
An Android app built with Jetpack Compose and the latest android libraries. Demo in here : https://youtube.com/shorts/4Phg1KJWdy8?feature=share

# Bank Mandiri Mobile Intern

<img width="300" alt="OB_W1" src="https://github.com/user-attachments/assets/bf2f626c-9a9d-4772-a225-1ae990cf0893">
<img width="300" alt="OB_W2" src="https://github.com/user-attachments/assets/4efa12b2-705a-41a4-bce2-d02cfc5c6088">
<img width="300" alt="OB_W3" src="https://github.com/user-attachments/assets/6b134829-1c3f-4551-bf85-5edf39d82259">
<img width="300" alt="HOME_W" src="https://github.com/user-attachments/assets/242e9b11-a187-41e5-8477-a4ff63325942">
<img width="300" alt="DETAIL_W" src="https://github.com/user-attachments/assets/919a146b-983e-42f1-a9ee-d4ddc0a08bb8">
<img width="300" alt="SEARCH_W" src="https://github.com/user-attachments/assets/95c1b7c7-9f6b-482f-8f4a-0c1f9c675f1e">
<img width="300" alt="BM_W" src="https://github.com/user-attachments/assets/807d7f85-a2b0-41e7-bb50-21566bd11c1d">
<img width="300" alt="SHR_W" src="https://github.com/user-attachments/assets/10301588-354a-4bde-92b5-5188dc7b482b">
<img width="300" alt="OB_B1" src="https://github.com/user-attachments/assets/b2710b82-3583-4760-8856-d2aaf4513f40">
<img width="300" alt="OB_B2" src="https://github.com/user-attachments/assets/a22e3b4e-ca1f-4f2d-9980-4849edba9cba">
<img width="300" alt="OB_B3" src="https://github.com/user-attachments/assets/aa291a6b-86c7-478c-a8e7-bba38f9fcc33">
<img width="300" alt="HOME_B" src="https://github.com/user-attachments/assets/c81e3344-fbd9-4995-9d8b-0e77cf6436f6">
<img width="300" alt="DETAIL_B" src="https://github.com/user-attachments/assets/249fa9e1-facf-4d2b-b0d3-cff95c481cef">
<img width="300" alt="SEARCH_B" src="https://github.com/user-attachments/assets/93b3cce6-ac5e-4d61-ad2c-d0fcc908f090">
<img width="300" alt="BM_B" src="https://github.com/user-attachments/assets/2f5e2faa-b383-4c16-b3e5-1b3dd3ddb11c">
<img width="300" alt="SHR_B" src="https://github.com/user-attachments/assets/33847d82-f8c1-4cc7-bcd8-25e69b0985cf">

# Clean Architecture Implementation in the App Structure
![architecture0](https://github.com/user-attachments/assets/2d60bb71-93e2-443b-802c-168ce11cad80)


The architecture follows a modular Clean Architecture approach, dividing the codebase into three main layers: Presentation, Data, and Domain. This separation allows for better scalability, maintainability, and testing.

- Presentation Layer (Blue): This layer contains the user interface components and ViewModels that handle UI logic. It interacts with the Domain and Data layers through dependency injection (DI). Components like Views and ViewModels manage the UI state and handle user actions.

- Data Layer (Green): This layer handles data retrieval and transformation. It interacts with remote and local data sources (Api, DataSource) and mappers that convert remote models into domain models. It also contains the repository implementations that are responsible for data management and caching.

- Domain Layer (Pink): This layer contains the core business logic, independent of any external dependencies. It defines the UseCases, repositories (interfaces), and domain models. The UseCases define the business operations that the app performs and act as a bridge between the Presentation and Data layers.

Each layer has clearly defined responsibilities, ensuring that changes in one layer don’t affect the others, which ultimately improves code maintainability and testability. The separation of concerns allows each part of the app to evolve independently, making it easier to manage dependencies and scale the application in the future.

This architecture is well-suited for modern Android development, where modularity and separation of concerns are critical to building robust and maintainable apps.

# Technologies i used to build this app
- Jetpack Compose
- Clean Architecture
- MVVM & MVI
- Paging 3
- Retrofit
- Room
- DataStore Preferences
- Dagger Hilt
