compilar:limpiar
	mkdir bin
	find src -name *.java | xargs javac -cp bin -d bin
ejecutar:compilar
	java -cp bin Principal
limpiar:
	rm -rf bin
jar:compilar
	jar cvfm ap-personas.jar Manifest.txt -C bin .