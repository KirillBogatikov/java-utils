#Overview
###Projector Solutions Utils
This repository contains all public utils, used in Projector's products.  
We use this tools for increasing speed of development, for keep code clean and simple and to decrease duplications.  
Utils are updated regularly by Projector development team. If you have an idea, you can state it by creating an issue.  
This project is open source, so after discussing and approving the requirements for the innovation, we can start working together to improve the utilities.  
Our main rule is 3S: safety, security and stability. Write good tests, check your code perfomance and keep it simple [stupid].

#Using
You can use this tools by dependency for Gradle or Maven.
##Maven
At first, describe connection to our public maven repository, located at Github repository:
```xml

    <repositories>
        <repository>
            <id>KirillBogatikov Public</id>
            <url>https://github.com/KirillBogatikov/maven-public/raw/master</url>
        </repository>
        <repository>
            <id>Magic-Library</id>
            <url>https://github.com/Magic-Library/maven-public/raw/master</url>
        </repository>
        <repository>
            <id>Public-Projector</id>
            <url>https://github.com/Projector-Solutions/maven-public/raw/master</url>
        </repository>
    </repositories>```
You can describe it at once in maven config or parent project pom.xml.
Then, add dependencies to necessary tools:
`xml
   
    <dependency>
    	<groupId>org.projector.utils</groupId>
    	<artifactId>common</artifactId>
    	<version>0.0.2</version>
    </dependency>
    <dependency>
    	<groupId>org.projector.utils</groupId>
    	<artifactId>functional</artifactId>
    	<version>0.0.2</version>
    </dependency>
    <dependency>
    	<groupId>org.projector.utils</groupId>
    	<artifactId>nullable</artifactId>
    	<version>0.0.2</version>
    </dependency>`
    
You don't need to define all of these dependencies, you can take only one.
##Gradle
Describe repositories url in root gradle.build file:
`groovy
    
    maven {
        url "https://github.com/KirillBogatikov/MavenArtifacts/raw/master"
    }
    
    maven {
        url "https://github.com/Magic-Library/maven-repository/raw/master"
    }

    maven {
        url "https://github.com/Projector-Solutions/maven-public/raw/master"
    }`
Now you can define dependencies. Remember, you should define all dependencies, necessary for your modules.
`groovy
    
    implementation "org.projector.utils:common:0.0.2"
    implementation "org.projector.utils:functional:0.0.2"
    implementation "org.projector.utils:nullable:0.0.2"
    `

#Changelog
## v. 0.0.1 (Argali) - first stable release
####1. Utilitary types:
- Duet - a pair of two values (A & B)
- Trio - simple structure for three values (A, B and C)
- NotNullValue - object, which allows only not-null value and automatically checks value.

####2. Common interfaces:
- Consumer - allows processing some data and converting it to another object
- DeafConsumer - allows only generating new value without source object

####3. Utilitary static classes:
- Equaling - utils for smart and safe comparing objects. No NPE, no errors, flex interface.
- Functional - more functional tricks. Provides select and map methods.
- Nullable - smart and safe checks for null value. Allows to execute different Consumers if value is null
and if value is not null. Provides methods for collections - firstOrDefault and lastOrDefault.

## v. 0.0.2 (Bermuda) - improvements, magic, tables!
####1. Table type - complex object, contains table structure. Allows "torn matrices". Works fast and safe.
####2. New interfaces:
- VoidConsumer - eats data, but does not return anything.
- Loop - controller of loop. Allows skip iteration, break loop or continue processing.
- Selector - consumer, created for functional utils. Creates value based on source object. Has access to Loop.
- Stream - big, smart and very tasty interface for functional programming. Analog of Java Stream API, but more functional

####3. Implementations. New package.
- DefaultLoop - simple implementation of Loop interface. Used in utils.
- DefaultStream - simple implementation of Stream based on List<T> collection. Powerful, fase, safe. But you can do better.

####4. Improvements:
- Nullable checkAllNotNull for arrays! Check array and it's content for null values.
- Functional select and map: simplified, rewritten with Stream.
- Functional createStream and createJoinedStream for simple generating Stream of strange values.