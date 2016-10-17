#!/usr/bin/env bash

echo '=> Demonstration du simulateur'
echo '=> Fermez la simulation pour lancer la suivante'

printf '\n=> Transmission parfaite d un signal aléatoire\n'
printf '******************************************\n'
./simulateur -mess 200 -s

printf '\n=> Transmission analogique du signal 00000000000\n'
printf '***************************************************************\n'
./simulateur -mess 00000000000 -s

printf '\n=> Transmission analogique du signal 11111111111\n'
printf '***************************************************************\n'
./simulateur -mess 11111111111 -s

printf '\n=> Transmission analogique du signal 011010110110\n'
printf '***************************************************************\n'
./simulateur -mess 011010110110 -s
