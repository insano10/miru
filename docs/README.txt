Usage:

Command Line with maven:
1) mvn clean install jetty:run
2) ./scripts/gather/start-gather.sh src/main/resources/miru.properties
3) http://localhost:8080/miru

From release artifact:
1) Unpack Miru-1.0-SNAPSHOT-release.tar.gz
2) set properties in properties/miru.properties
2) ./miru-start-all.sh
3) http://localhost:8080/miru


tar.gz deployment structure:

miru

-> miru-start-all.sh
-> miru-stop-all.sh

-> scripts
  -> gather
    -> gather.sh
    -> start-gather.sh


-> web
  -> jetty-runner.jar
  -> miru-web.war

-> properties
  -> miru.properties

