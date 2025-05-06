# GOT App by Hussain Mehboob for CreateFuture Tech Task

## Project Configuration

Following lines have been added in this project's "local.properties" file:

```
BASE_URL = https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com
AUTH_TOKEN = Bearer 754t!si@glcE2qmOFEcN
```

### Note:

Base URL and Auth Token have been put in the local.properties file to help, if we want to move towards CI/CD and to keep credentials secure and configureable.

## Android Component and Tech Used:

AndroidX, Jetpack Compose, Material 3, Coroutines, Hilt, Clean Architecture, MVI, Okhttp, Retrofit, Gson

## Unit Testing:

Junit4, MockK, Kotlin Coroutines Test Library

## UI Testing:

Compose UI Testing Library

## Code Quality:

SonarQube

## Next Steps:

- Setup CI/CD <br>
- Add Dark/Light Theme Support for better UI/UX
- Add feature branches instead of pushing directly to main branch or any single branch as the codebase grows

## Demo Video:

https://github.com/user-attachments/assets/e6bba8b9-572b-410e-8241-17c7e4d0097f

## Screenshots:

![GOT-WithSearch](https://github.com/user-attachments/assets/996b159f-0453-497f-b8bd-59a47a9053b3)
![GOT-Loading](https://github.com/user-attachments/assets/a6a9e842-e8c6-42fc-bab5-9b271a7e5a52)
![GOT-FilteredList](https://github.com/user-attachments/assets/7840d786-16b8-4ea9-96bf-b846a7a5d7f0)
![GOT-ErrorDialog](https://github.com/user-attachments/assets/660a0a2e-11b0-4d3a-b65f-5dac42f45d7c)

=================================================================================================

## Task Requirements:

You have been provided with an in-development Android app. The application uses an API to display lists of data about characters from the show "Game of Thrones". The project has some bugs and notable UI mismatches compared to the given designs.

## Some issues that have been reported

- App crashes on launch --> Fixed
- Major discrepancies with the designs e.g. white padding --> Fixed

## Improvements required

- A new feature needs to be added that would allow a user to search the list by character name. --> Done

## Resources

The API endpoint is available from:
[https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com/characters](https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com/characters)
Requests to that endpoint will require the following header:
"Authorization": "Bearer 754t!si@glcE2qmOFEcN"

Designs: 

![img_design_1.png](app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fimg_design_1.png) ![img_design_2.png](app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fimg_design_2.png)

## Criteria on which we will assess your submission --> Done

- Closeness to designs (pragmatism is encouraged and pixel perfection is NOT required)
- Code quality, included but not limited to, design patterns and organisation of the application code
- Scalability
- Error handling
- Unit tests

## Submission details

We would like you to fix the app's user facing issues (both documented and undocumented), add the additional search feature and submit the codebase in a more scalable state.

Please use version control. Import the supplied code as is to git and commit your changes through that. This will allow us to review the changes you have made.

We expect you should spend no more than 3 hours on this work. We appreciate you taking the time to work on this and understand that sometimes it's not possible to spend as much time as you would like. If there are any aspects of the codebase you would have liked to work on with more time, please detail these in the ReadME file to give us some insight in to your process.
