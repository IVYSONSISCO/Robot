#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
mkdir -p build/classes
find src/main/java -name '*.java' -print > build/sources.list
javac -d build/classes @build/sources.list
java -cp build/classes robotecosystem.RobotEcosystemDemo
