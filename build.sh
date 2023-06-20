#!/usr/bin/bash
rm -rf ./target
javac -encoding utf-8 -d ./target ./modbus/*.java -cp "lib/;resources/icon.png"
cp -r lib ./target/lib
mkdir ./target/resources
cp resources/icon.png ./target/resources/icon.png
cp resources/Manifest.txt target/Manifest.txt
pushd ./target
jar cfm ./application.jar ./Manifest.txt ./modbus/* ./resources/*
popd
launch4jc ./resources/execonfig.xml
rm -f ./ModbusCRC.exe
mv ./target/ModbusCRC.exe .
rm -rf ./target
exec ./ModbusCRC.exe