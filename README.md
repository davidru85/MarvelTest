# Marvel Test App

Marvel Demo App with Dagger Hilt, MVVM, Clean Architecture...

## Installation
[Marvel API](https://developer.marvel.com/) needs a logged user with both public and private keys.
The project uses them located in a "gradle.properties" into the /home/.gradle/ directory

## Issues
As Dagger Hilt is not ready yet for dynamic features modules, there are 2 different branches.

### feature/detaildynamicfeature
First approach using different modules but not working because Hilt.

### feature/detailpackage
Second approach spliting features into packages instead of modules.