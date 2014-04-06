IntelliJ:
1) Miru-build
2) Miru-run
3) Miru-gather
4) http://localhost:8080/Miru


Command Line with maven:
1) mvn clean install jetty:run
2) ./scripts/gather/gather.sh src/resources/miru.properties
3) http://localhost:8080/Miru

From artifact:
1) Unpack Miru.tar.gz
2) ./miru-start-all.sh
3) http://localhost:8080


watch a project with:
./scripts/gather.sh <path to miru.properties>

start webapp with:
java -jar web/jetty-runner.jar web/miru-web.war



tar.gz deployment structure:

miru

-> miru-start-all.sh (script to start/stop/restart miru)

-> gather
  -> scripts
    -> gather.sh

-> web
  -> jetty-runner.jar
  -> miru-web.war

-> properties
  -> miru.properties

