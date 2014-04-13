#!/bin/sh

#start gathering
./scripts/gather/start-gather.sh properties/miru.properties > /dev/null 2>&1 &

#start webapp
java -DmiruProperties=properties/miru.properties -jar web/jetty-runner.jar web/miru-web.war > /dev/null 2>&1 &

echo "Miru is running..."