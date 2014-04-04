#!/bin/sh

function result() {
   if [ $? -eq 0 ]; then
      echo "OK"
   else
      echo "FAIL"
   fi
}

#location config
root="/home/jenny/IdeaProjects/RomanNumerals"
sources="${root}/src/main/java"
tests="${root}/src/test/java"
classpath="${root}/lib/*"
buildDir="bin"
mainClass="com/lmax/dojo/romanNumerals/RomanNumeralConverter"

sourceCompile=0
testCompile=0
testRun=0

rm -rf ${buildDir}
mkdir ${buildDir}

echo "compiling sources..."
find ${sources} -name "*.java" | xargs javac -d ${buildDir} -cp "${classpath}" 
sourceCompile=$(result)

echo "compiling tests..."
find ${tests} -name "*Test.java" | xargs javac -d ${buildDir} -cp "${buildDir}:${classpath}" 
testCompile=$(result)

echo "running tests..."
find . -name "*Test.class" | sed -e "s/.class//; s/.\/${buildDir}\///; s/\//./g;" | xargs java -cp "${buildDir}:${classpath}" org.junit.runner.JUnitCore >/dev/null 2>&1
testRun=$(result)

echo ""
echo "Results"
echo "--------"
echo "source compile: ${sourceCompile}"
echo "test compile:   ${testCompile}"
echo "tests pass:     ${testRun}"
