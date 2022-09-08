#!/bin/bash

trap ctrl_c INT

function ctrl_c() {
  rm bbf.jar
  exit 0
}

echo "Compiling jar file..."

./gradlew jar

cp build/libs/bbf-1.0.jar bbf.jar

echo "Start BBF inf..."

while :
do
  echo "Run BBF..."
  java -jar bbf.jar
  echo "done"
done
