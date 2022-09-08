#!/bin/bash

trap ctrl_c INT

JAR_NAME=$1

function ctrl_c() {
  rm $JAR_NAME.jar
  exit 0
}

echo "Compiling jar file..."

./gradlew jar

cp build/libs/bbf-1.0.jar $1.jar

echo "Start BBF inf..."

while :
do
  echo "Run BBF..."
  java -jar $1.jar
  echo "done"
done
