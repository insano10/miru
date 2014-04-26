#!/bin/bash

#gather stats every 5 seconds
while true;
do
    sleep 5
    ./scripts/gather/gather.sh $1

done