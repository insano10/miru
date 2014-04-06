#!/bin/sh

#start gathering
./gather/scripts/gather.sh properties/miru.properties

#start webapp
java -jar web/miru-web.jar