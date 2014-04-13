#!/bin/bash

#gather stats every 10 seconds
while true;
do
    sleep 10
    ./scripts/gather/gather.sh $1

done