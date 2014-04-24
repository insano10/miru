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
    echo "projectRoot=$projectRoot"
    echo "sources=$sources"
    echo "tests=$tests"
    echo "classpath=$classpath"
    echo "vcs=$vcs"
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
    resultStr=$(find $1 -name "*Test.class" | sed -e "s/.class//; s/$1\///; s/\//./g;" | xargs java -cp "$1:$classpath" org.junit.runner.JUnitCore)

    total=0
    pass=0
    fail=0

    if [[ ${resultStr} =~ .*OK\ \((.*)\ test.*\).* ]]
    then
        total=${BASH_REMATCH[1]};
        pass=${BASH_REMATCH[1]};
    elif [[ ${resultStr} =~ .*Tests\ run:\ (.*),\ .*Failures:\ (.*) ]]
    then
        total=${BASH_REMATCH[1]};
        fail=${BASH_REMATCH[2]};
        pass=$((total-fail))
    fi

    echo "${total} ${pass} ${fail}";
}

function ignoredTests()
{
    #exclude commented out annotations
    echo $(find ${tests} -name "*Test.java" | xargs grep "@Ignore" | grep -v // | wc -l);
}

function countSourceLines()
{
    #strip out imports and whitespace lines
    echo $(find ${sources} -name "*.java" | xargs cat | grep -v "^import" | egrep -v "^[[:space:]]*$|^#" | wc -l)
}

function countTestLines()
{
    echo $(find ${tests} -name "*.java" | xargs cat | grep -v "^import" | egrep -v "^[[:space:]]*$|^#" | wc -l)
}

function vcsChanges()
{
    if [ "${vcs}" == "svn"  ]
    then
        modifiedFileCount=$(svn st ${projectRoot} | grep ^M | wc -l)
        addedFileCount=$(svn st ${projectRoot} | grep ^A | wc -l)
        deletedFileCount=$(svn st ${projectRoot} | grep ^D | wc -l)
        unversionedFileCount=$(svn st ${projectRoot} | grep ^\? | wc -l)
        echo "${modifiedFileCount} ${addedFileCount} ${deletedFileCount} ${unversionedFileCount}"

    elif [ "${vcs}" == "hg" ]
    then
        modifiedFileCount=$(hg st ${projectRoot} | grep ^M | wc -l)
        addedFileCount=$(hg st ${projectRoot} | grep ^A | wc -l)
        deletedFileCount=$(hg st ${projectRoot} | grep ^R | wc -l)
        unversionedFileCount=$(hg st ${projectRoot} | grep ^\? | wc -l)
        echo "${modifiedFileCount} ${addedFileCount} ${deletedFileCount} ${unversionedFileCount}"
    else
        echo "Unsupported vcs property: ${vcs}"
        exit 1
    fi
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
        echo "#CSV format: sourceCompile, testCompile, totalTestsRun, totalTestsPass, totalTestsFail, totalTestsIgnored, sourceLineCount, testLineCount, filesModified, filesAdded, filesDeleted, unversionedFiles" > ${dataFile}
    fi

    #add tests run to tests ignored for total test count
    totalTests=$(($3+$6))

    #append data to project data file (3/4/5 all come from testRun)
    echo "$(($(date +%s%N)/1000000)), $1, $2, $totalTests, $4, $5, $6, $7, $8, $9, ${10}, ${11}, ${12}" >> ${dataFile}
}

function main()
{
    buildDir="build"

    clean ${buildDir}
    sourceProperties $1
    sourceCompile=$(compileSources ${buildDir})
    testCompile=$(compileTests ${buildDir})
    testsIgnored=$(ignoredTests)
    testRun=$(runTests ${buildDir})
    sourceLines=$(countSourceLines)
    testLines=$(countTestLines)
    vcsChangeCounts=$(vcsChanges)

    saveDataToCSV ${sourceCompile} ${testCompile} ${testRun} ${testsIgnored} ${sourceLines} ${testLines} ${vcsChangeCounts}

    echo "Results"
    echo "--------"
    echo "source compile: $sourceCompile"
    echo "test compile:   $testCompile"
    echo "tests pass:     $testRun"
    echo "tests ignored:  $testsIgnored"
    echo "source lines:   $sourceLines"
    echo "test lines:     $testLines"
    echo "vcs changes:    $vcsChangeCounts"
}

if [ "$#" -ne 1 ]; then
    usage
fi

main $1
