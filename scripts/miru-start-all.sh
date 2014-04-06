#!/bin/sh

#start gathering (once, need to put in a loop)
./gather/scripts/gather.sh properties/miru.properties

#start webapp (need to put in background
java -jar web/jetty-runner.jar web/miru-web.war