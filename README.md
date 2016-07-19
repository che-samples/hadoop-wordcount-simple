# hadoop-wordcount-simple

# Developer Workspace

[![Contribute](http://beta.codenvy.com/factory/resources/codenvy-contribute.svg)](https://beta.codenvy.com/f?id=mzfk97kbqwwczjyv)

# Recipe

FROM [codenvy/ubuntu_jdk8](https://hub.docker.com/r/codenvy/hadoop-dev/)

# Commands

| #       | Description           | Command  |
| :------------- |:-------------| :-----|
| 1      | Build and Run | `cd ${current.project.path} && mvn clean install && mvn exec:java -Dexec.mainClass="com.javacodegeeks.examples.wordcount.WordCount"` |
