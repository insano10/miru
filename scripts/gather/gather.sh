#!/bin/bash

function result()
{
   if [ $? -eq 0 ]; then
      echo "1"
   else
      echo "0"
   fi
}

function usage()
{
	echo "Usage: $0 <path to miru.properties>"
	exit 1
}

function sourceProperties()
{
    propertyFilePath=$1

    if [ ! -f ${propertyFilePath} ]; then
        echo "Cannot find $propertyFilePath"
        exit 1
    fi

    echo "Using properties file $1"
    . $1

    echo "projectName=$projectName"
    echo "sources=$sources"
    echo "tests=$tests"
    echo "classpath=$classpath"
    echo ""
}

function clean()
{
    rm -rf $1
    mkdir $1
}

function compileSources()
{
    find ${sources} -name "*.java" | xargs javac -d $1 -cp "$classpath"
    echo $(result)
}

function compileTests()
{
    find ${tests} -name "*Test.java" | xargs javac -d $1 -cp "$1:$classpath"
    echo $(result)
}

function runTests()
{
    find $1 -name "*Test.class" | sed -e "s/.class//; s/$1\///; s/\//./g;" | xargs java -cp "$1:$classpath" org.junit.runner.JUnitCore >/dev/null 2>&1
    echo $(result)
}

function saveDataToCSV()
{
    dataFolder="data"
    dataFile="$dataFolder/$projectName.csv"

    #create data folder if it doesn't exist
    if [ ! -d ${dataFolder} ]; then
        mkdir ${dataFolder}
    fi

    #create data file with header if it doesn't exist
    if [ ! -f $dataFile ]; then
        echo "#CSV format: sourceCompile, testCompile, testRun" > ${dataFile}
    fi

    #append data to project data file
    echo "$1, $2, $3" >> ${dataFile}
}

function main()
{
    buildDir="build"

    clean ${buildDir}
    sourceProperties $1
    sourceCompile=$(compileSources ${buildDir})
    testCompile=$(compileTests ${buildDir})
    testRun=$(runTests ${buildDir})

    saveDataToCSV ${sourceCompile} ${testCompile} ${testRun}

    echo "Results"
    echo "--------"
    echo "source compile: $sourceCompile"
    echo "test compile:   $testCompile"
    echo "tests pass:     $testRun"
}

if [ "$#" -ne 1 ]; then
    usage
fi

main $1
