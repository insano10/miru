#!/bin/sh

function result()
{
   if [ $? -eq 0 ]; then
      echo "1"
   else
      echo "0"
   fi
}

echo "Using properties file $1"
# source the properties
. $1

#location config
buildDir="build"
dataFolder="data"
dataFile="$dataFolder/$projectName.csv"

sourceCompile=0
testCompile=0
testRun=0

#start in miru root folder
cd ..

#clean buildDir
rm -rf $buildDir
mkdir $buildDir

echo "compiling sources..."
find $sources -name "*.java" | xargs javac -d $buildDir -cp "$classpath"
sourceCompile=$(result)

echo "compiling tests..."
find $tests -name "*Test.java" | xargs javac -d $buildDir -cp "$buildDir:$classpath"
testCompile=$(result)

echo "running tests..."
find $buildDir -name "*Test.class" | sed -e "s/.class//; s/$buildDir\///; s/\//./g;" | xargs java -cp "$buildDir:$classpath" org.junit.runner.JUnitCore >/dev/null 2>&1
testRun=$(result)

echo ""
echo "Results"
echo "--------"
echo "source compile: $sourceCompile"
echo "test compile:   $testCompile"
echo "tests pass:     $testRun"

#create data folder if it doesn't exist
if [ ! -d $dataFolder ]; then
    mkdir $dataFolder
fi

#create data file with header if it doesn't exist
if [ ! -f $dataFile ]; then
    echo "#CSV format: sourceCompile, testCompile, testRun" > ${dataFile}
fi

#append data to project data file
echo "$sourceCompile, $testCompile, $testRun" >> ${dataFile}
