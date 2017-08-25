#!/bin/bash
while true; do
    OUTPUT=$(ps -ef | grep "/Applications/Safari.app/Contents/MacOS/Safari" | grep -v "grep")
    if [[ $OUTPUT == *"/Applications/Safari.app/Contents/MacOS/Safari"* ]]; then
        break
    fi
    sleep 1
done
sleep 2
open -a Safari
