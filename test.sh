#!/usr/bin/env bash

BIN_OUTPUT='bin/'
CLASSPATH="$BIN_OUTPUT:$(find lib -name '*.jar' | tr '\n' ':')"

[ -e bin/ ] && rm -rf bin/

echo 'Compiling...'
./simulateur 2&> /dev/null

echo 'Running test suite...'
java -cp "$CLASSPATH" org.junit.runner.JUnitCore AllTests
