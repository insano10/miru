# miru


Miru is a tool to monitor the progress of Java based coding dojo sessions.

It comprises of a set of scripts that continuously monitor the status of the project directory and a web front end to visualise progress


# Usage

miru can be run either from the command line or from within IntelliJ IDEA

## Command line - release tar

1. Download Miru-1.0-SNAPSHOT-release.tar.gz
1. Unpack the tar with 'tar zxvf Miru-1.0-SNAPSHOT-release.tar.gz'
1. configure your project properties in properties/miru.properties
1. ./miru-start-all.sh
1. http://localhost:8080/miru

## Command line - IntelliJ IDEA project

1. Navigate to the root of the miru project directory
1. configure your project properties in src/main/resources/miru.properties
1. mvn clean install jetty:run
1. ./scripts/gather/start-gather.sh src/main/resources/miru.properties
1. http://localhost:8080/miru


## From IntelliJ IDEA

Create run configurations for the scripts and front end

* Scripts
  1. Create a new bash run configuration called 'start-gather'
  1. Script = /home/jenny/code/miru/scripts/gather/start-gather.sh
  1. Program Arguments = src/main/resources/miru.properties
* Front end
  1. Create a new maven run configuration called 'front-end'
  1. Command line = clean install jetty:run -DmiruProperties=src/main/resources/miru.properties
  1. configure your project properties in src/main/resources/miru.properties
  1. Run 'start-gather' to start collecting data into the data/ directory
  1. Run 'front-end' to start the web app on port 8080



# Project Properties

* projectName   - the name of your project. This can have any value
* projectRoot   - the absolute path to the root of the project you want to monitor
* sources       - the absolute path to the directory containing the Java sources of the project. This will typically be projectRoot/src/main/java
* tests         - the absolute path to the directory containing the unit tests of the project. This will typically be projectRoot/src/test/java
* classpath     - the absolute path to the directory containing all dependencies required by the project to run. This may be projectRoot/lib/*
* vcs           - the version control system used by the project. miru currently supports svn, git and hg



# Rebuilding the release tar

The project comes with a maven assembly configuration in src/assembly/assembly.xml which can be used to recreate the miru
tar with maven target 'assembly:assembly'