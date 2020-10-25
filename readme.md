# Overview
### Projector Solutions Utils
This repository contains all public utils, used in Projector's products.
We use this tools for increasing speed of development, for keep code clean and simple and to decrease duplications.
Utils are updated regularly by Projector development team. If you have an idea, you can state it by creating an issue.
This project is open source, so after discussing and approving the requirements for the innovation, we can start working together to improve the utilities.
Our main rule is 3S: safety, security and stability. Write good tests, check your code perfomance and keep it simple [stupid].
JavaDoc is available on Github Pages: 
[Utils 0.0.3 docs](https://projector-solutions.github.io/docs/utils/0.0.3/index.html?overview-summary.html "Utils 0.0.3 JavaDoc").
[Utils 0.0.4 docs](https://projector-solutions.github.io/docs/utils/0.0.4/index.html?overview-summary.html "Utils 0.0.4 JavaDoc").
Now project documentation is not actual and nice (foo). But we works on improving it. Also you can view discussed innovations and ideas, which are most likely to be included in future versions
at [Utils Info page](https://projector-solutions.github.io/docs/utils/roadmap.html "Utils Info page").

# Using
You can use this tools by dependency for Gradle or Maven.
## Maven
At first, describe connection to our public maven repository, located at Github repository:
```xml
<repositories>
    <repository>
        <id>KirillBogatikov Public</id>
        <url>https://raw.githubusercontent.com/KirillBogatikov/maven-public/raw/master</url>
    </repository>
    <repository>
        <id>Magic-Library</id>
        <url>https://raw.githubusercontent.com/Magic-Library/maven-public/raw/master</url>
    </repository>
    <repository>
        <id>Public-Projector</id>
        <url>https://raw.githubusercontent.com/Projector-Solutions/maven-public/raw/master</url>
    </repository>
</repositories>
```
You can describe it at once in maven config or parent project pom.xml.
Then, add dependencies to necessary tools:
```xml
<dependency>
    <groupId>org.projector.utils</groupId>
    <artifactId>common</artifactId>
    <version>0.0.3</version>
</dependency>
<dependency>
  	<groupId>org.projector.utils</groupId>
   	<artifactId>functional</artifactId>
   	<version>0.0.3</version>
</dependency>
<dependency>
  	<groupId>org.projector.utils</groupId>
   	<artifactId>nullable</artifactId>
   	<version>0.0.3</version>
</dependency>
<dependency>
  	<groupId>org.projector.utils</groupId>
   	<artifactId>dsv</artifactId>
   	<version>0.0.3</version>
</dependency>
```
You don't need to define all of these dependencies, you can take only one.
## Gradle
Describe repositories url in root gradle.build file:
```groovy
maven {
    url "https://raw.githubusercontent.com/KirillBogatikov/maven-public/raw/master"
}

maven {
    url "https://raw.githubusercontent.com/Magic-Library/maven-public/raw/master"
}

maven {
    url "https://raw.githubusercontent.com/Projector-Solutions/maven-public/raw/master"
}
```
Now you can define dependencies. Remember, you should define all dependencies, necessary for your modules.
```groovy
implementation "org.projector.utils:common:0.0.3"
implementation "org.projector.utils:functional:0.0.3"
implementation "org.projector.utils:nullable:0.0.3"
implementation "org.projector.utils:dsv:0.0.3"
```
# Changelog
## v. 0.0.1 (Argali) - first stable release
#### 1. Utilitary types:
- Duet - a pair of two values (A & B)
- Trio - simple structure for three values (A, B and C)
- NotNullValue - object, which allows only not-null value and automatically checks value.

#### 2. Common interfaces:
- Consumer - allows processing some data and converting it to another object
- DeafConsumer - allows only generating new value without source object

#### 3. Utilitary static classes:
- Equaling - utils for smart and safe comparing objects. No NPE, no errors, flex interface.
- Functional - more functional tricks. Provides select and map methods.
- Nullable - smart and safe checks for null value. Allows to execute different Consumers if value is null
and if value is not null. Provides methods for collections - firstOrDefault and lastOrDefault.

## v. 0.0.2 (Bermuda) - improvements, magic, tables!
#### 1. Table type - complex object, contains table structure. Allows "torn matrices". Works fast and safe.
#### 2. New interfaces:
- VoidConsumer - eats data, but does not return anything.
- Loop - controller of loop. Allows skip iteration, break loop or continue processing.
- Selector - consumer, created for functional utils. Creates value based on source object. Has access to Loop.
- Stream - big, smart and very tasty interface for functional programming. Analog of Java Stream API, but more functional

#### 3. Implementations. New package.
- DefaultLoop - simple implementation of Loop interface. Used in utils.
- DefaultStream - simple implementation of Stream based on List<T> collection. Powerful, fase, safe. But you can do better.

#### 4. Improvements:
- Nullable checkAllNotNull for arrays! Check array and it's content for null values.
- Functional select and map: simplified, rewritten with Stream.
- Functional createStream and createJoinedStream for simple generating Stream of strange values.

## v. 0.0.3 (Camel) - DSV tables, String joining and safety
#### 1. DSV - Delimiter Separated Values
Data structure based of CSV (comma separated values, RFC 4180).
Now project contains tools for reading and writing DSV to file or stream. See org.projector.dsv package.
#### 2. String Joiner - CharSequence implementation for simple joining strings with specified delimiter.
Projector's String Joiner is a analog of java.util.StringJoiner class for platforms without this class (i.e. Android OS).  
  
## v. 0.0.4 (Democracy) - Bugs, Vulnerabilities and Features
### New features:
#### 1. New Stream aggregate functions. 
Stream minDuet() and maxDuet() returns Duet of item and it's double equivalent.
Old min() and max() saved, but was refactored.
#### 2. Improved NotNull and Nullable annotations.
Added new Javadoc information to annotations. This information can be exported with your code in IDE or to JavaDOC.
#### 3. Update in DSV module structure.
DsvCell moved to org.projector.dsv.data
DsvTable moved to org.projector.dsv.data
SimpleDeserializer renamed to DefaultDeserializer and moved to org.projector.dsv.impl
SimpleSerializer renamed to DefaultSerializer and moved to org.projector.dsv.impl
Extracted DsvReader and DsvWriter interfaces.
DsvReader renamed to DefaultDsvReader and moved to org.projector.dsv.impl. Now this class implements DsvReader interface.
DsvWriter renamed to DefaultDsvWriter and moved to org.projector.dsv.impl. Now this class implements DsvWriter interface.
#### 4. Added DsvTable toValueStream() and toCellStream() methods. Methods packs all cells or values from cells on each other to stream. For example, table
Header A | Header B | Header C
  Anton  |    19    |  Moscow
  Lizzy  |    24    | New York
  Mike   |    65    | Hong Kong
  
will be packed into stream: (Header A, Header B, Header C, Anton, 19, Moscow, Lizzy, 24, New York, Mike, 65, Hong Kong)
#### 5. Stream Mutability.
Now some streams can be mutable by using remove methods. In a future, will be improved methods for adding and replacing values.
In 0.0.4 was deleted Stream.next(), Stream.hasNext(), added Stream.remove(int), Stream.remove(int, ValueType) and Stream.iterate() methods. 
Added StreamIterator and MutableStreamIterator interface.
StreamIterator<I> should has next methods:
    - hasNext():boolean
    - next():I
    - skip(int):void
MutableStreamIterator<I> extends StreamIterator<I> interface and provides next methods:
    - remove():void
    - clear():void
Stream.iterate() - concurrent but safe method. It can provide millions of iterators, but Stream.remove(int, I), used by
MutableStreamIterator checks item availability.
Added Stream.isMutable() and Stream.setMutable(boolean) methods. 
This methods determine required type of StreamIterator created by Stream.iterate() method.
### Bugs and vulnerabilities
#### 6. JUnit vulnerability fix (bump to 4.13.3)
#### 7. Fixed bugs in Nullable checks. Previously Nullable.checkAllNotNull did not use specified value name.

## v. 0.0.5 (Even better) - SCHEDULING, January - February 2021

## v. 0.0.6 (Fierce) - ANNOUNCED, May - June 2021

# DEVELOPERS!
#### Make your code clean and nice. Comments is not required if your code is simple and clear.
#### Name your branches correctly! For example, branch for issue #6 in milestone "v. 0.0.4" should be named "v004-6" or "v004-6-dsvtable-tostream"
#### Write good tests! If you can not analyze your code, ask a friend to read changes and find vulnerabilities. WE WRITE TESTS FOR ALL CODE!!!
#### Run your tests! All tests will be executed before release on different machines and JDKs. Don't pass the responsibility for your code on to other people.
#### Merge your branches into current dev-branch, such as v0.0.4, v0.0.5, etc. only after pull request! You can not approve request itself. Help to make our project safe and clean - review code, write comments to request, don't merge branches into master :)