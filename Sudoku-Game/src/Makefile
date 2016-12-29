all: classes run manifest

run: $(classes)
	echo "#!/bin/bash" > run.sh
	echo "java -jar Main.jar" >> run.sh
	chmod 754 run.sh 

classes:
	javac sudoku/*.java

manifest: $(classes)
	echo "Manifest-Version: 1.0" > Manifest.txt
	echo "Class-Path: ." >> Manifest.txt l
	echo "Main-Class: sudoku.GridController" >> Manifest.txt 
	jar cfm Main.jar Manifest.txt sudoku/*.class

clean:
	rm sudoku/*.class
	rm *.jar
	rm Manifest.txt
	rm *.sh
