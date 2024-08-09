# Music - Project 1 

This project is on the Music app by Sismics. The original repository can be found [here](https://github.com/sismics/music). 
We, a team of 4 from IIIT Hyderabad have refactored and extended this open sournce web based music applicaiton, improving code quality and functionality by integrating third-party APIs of Spotify and LastFM for music searching and recommendation. The project involved using UML diagrams, OOP principles, and design patterns to eliminate design smells and reduce technical debt. New features, including public/private/collaborative playlists and song recommendations, were added to enhance user engagement and maintainability. The project demonstrates strong software design skills and effective use of external APIs to enrich the user experience.

**Below are the basic information about the project, include how to run this app on your system. The documents regarding the refactoring and statistics depicting improvements in the code complexities can be found in the [docs](/docs/) folder.**


## What is Music?

Music is an open source, Web-based music server.

Music is written in Java, and may be run on any operating system with Java support. We will be working with the web application of Music. 

## Requirements 
Music requires JDK 8, Maven 3 and npm to run. We recommend working with a Linux or Unix-based OS. 

Below are the instructions for installing the requirements on Ubuntu (and most Debian-based operating systems). 

```
sudo apt install openjdk-8-jdk
sudo apt install maven
sudo apt install npm
```

If you're using Mac, Windows, or any other OS, and need any help, feel free to contact us. 

## Changing Java Version
You will need two versions of Java to work on this project (1.8 and 11). Music requires Java 1.8 and Sonarqube requires Java 11. So you will have to change Java versions for working on different parts.

### For Globally Changing Java Version (Only on Ubuntu) 
* Run the following command and select the version of Java you want to use.
  ```
  sudo update-alternatives --config java
  ```
* Similarly for javac.
  ```
  sudo update-alternatives --config javac
  ```
> Make sure you set the same version for both.  

### For Updating in Specific Runtime  (Mac & Linux)
* **We recommend this method**
* Instead of globally updating your Java version, it is better to temporarily change the Java version i.e. for as long as the terminal is open.
* This is done by setting the path variable JAVA_HOME to the version of Java you want to use (1.8 for music and 11 for Sonarqube).
* The command would be ```export JAVA_HOME=<path to java installation>``` for Mac and Linux.

> The paths mentioned here are only sample paths. Make sure you find out the actual path for your JDK and use that.


**On Mac**  
The command would look something like this - 
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_321.jdk/Contents/Home/
```

**On Linux**  
The command would look something like this -  
```
export JAVA_HOME=/usr/lib/jvm/<jdk-version-something>
```


**On Windows**  
Windows users, this is your cross to bear. [Here's](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html) a guide that might be of use. Again, feel free to contact us for any help.  
 

## Building the project  

Build the project from the root directory using:

```
mvn clean -DskipTests install  
```


You can then launch the web application from the ```music-web``` directory using  
```
mvn jetty:run
``` 

This launches the application on ```localhost:8080/music-web/src/``` by default. Feel to play around from here!   

