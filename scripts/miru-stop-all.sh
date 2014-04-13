#!/bin/sh

kill $(ps aux | grep '[g]ather/start-gather.sh' | awk '{print $2}') > /dev/null 2>&1
kill $(ps aux | grep ' [w]eb/jetty-runner.jar web/miru-web.war' | awk '{print $2}') > /dev/null 2>&1

echo "Miru has been stopped"