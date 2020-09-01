# NBA_TeamViewer Application

## Architecture
MVVM architecture is being used in this application

## Features
1. List of teams are retrieved from the API using Retrofit and displayed on the landing screen
2. The list can be sorted (both ascending and descending) based on the team names, number of wins and number of losses
3. The details of the players in the team can be viewed on click of the team.

## Network Caching
Network caching is implemented using Retrofit, so that the API response is cached.

## Unit testing
Unit testing for the code functionality is implemented using **JUnit and Mockito**.

## UI tests
UI tests are generated using **Espresso** for the main funtionalities.
