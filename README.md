Este es un programa hecho para simular el juego de la vida de John Horton Conway.

Para hacerlo funcionar deberán de ejecutar el comando 'make ejecutar'.

Al instante aparecerá la tabla con la simulación que provendrá del archivo matriz.txt.

Posteriormente aparecerá la simulación hecha por el método de Montecarlo, donde se rellena la tabla con 1s y 0s seleccionados de manera aleatoria, y se verá como evoluciona.

Tuve problemas a la hora de poner el archivo principal en la carpeta aplicacion. No detectava el paquete dominio, aunque la carpeta estuviese puesta, y la línea del import también, por lo que mi profesor me recomendó sacar el archivo principal.java y así lo hice. Tras esto todo funcionó perfectamente.

Para ejecutar el javadoc deberán de situarse dentro de la carpeta src y escribir el comando 'javadoc -d doc *'.

En el anterior proyecto escribí el comando para ejecutar el javadoc dentro del makefile. Sin embargo como he escrito antes si no estás dentro de la carpeta src no se ejecuta. Lo cual, al estar dentro de esa carpeta no encontrará el archivo makefile, por lo que he decidido borrar el comando.