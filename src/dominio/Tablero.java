
/*
Copyright 2021 Javier García Carbia
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.
 */

/**
 * @version 1.0
 * @author Javier Garcia Carbia
 */
 
package dominio;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.*;

public class Tablero{
    private static int Dimension = 30;
    private int [][] estadoActual;
    private int [][] estadoSiguiente = new int [Dimension] [Dimension];
    public void leerEstadoActual(){
        try{
            estadoActual = new int [Dimension] [Dimension];
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

			File fichero = new File("matriz.txt");
            Scanner sc = new Scanner(fichero);
            int fila = 0;
            while(sc.hasNext()){
                String linea = sc.next();
                for(int i = 0; i < Dimension; i++){
                    char caracter = linea.charAt(i);
                    //System.out.print(caracter);
                    estadoActual[fila][i] = caracter;
                }
                //System.out.println("");
                fila++;
            }
		} catch(IOException ex){
	    }

    } 

    public void transitarAlEstadoSiguiente(){
        //coordenadas x y del tablero
        for(int y = 0; y < Dimension; y++){
            for(int x = 0; x < Dimension; x++){
                int nvecinasVivas = 0;

                //estado celula actual
                boolean celulaViva = false;
                if (estadoActual[x][y] == '1')
                    celulaViva = true;

                //coordenadas x y del alrededor de la celula
                for(int yy = -1; yy < 2; yy++){
                    for(int xx = -1; xx < 2; xx++){
                        //no podemos procesar la celula en si
                        if (xx != 0 || yy != 0){
                            //verificamos que el punto de alrededor es válido
                            boolean valido = esCoordenadaValida(x + xx, y + yy);
                            if (valido){
                                int valorCelula = estadoActual[x + xx][y + yy];
                                if (valorCelula == '1')
                                    nvecinasVivas++;
                            }    
                        }
                     }
                }
                
                //CALCULO DE NUEVO ESTADO PARA LA CELULA
                int nuevoEstado;
                if (celulaViva && (nvecinasVivas == 2 || nvecinasVivas == 3)){
                    //regla a
                    nuevoEstado = '1';
                } else if (!celulaViva && nvecinasVivas == 3){
                    //regla b
                    nuevoEstado = '1';
                } else{
                    //regla c
                    nuevoEstado = '0';
                }
                estadoSiguiente[x][y] = nuevoEstado;
            }
        }

        //salvamos el estado actual para el estado siguiente
        for(int i = 0; i < Dimension; i++){
            for(int j = 0; j < Dimension; j++){
                estadoActual[i][j] = estadoSiguiente[i][j];
            }
        }

    }
    public boolean esCoordenadaValida(int x, int y){
        if(x < 0 || x > 29 || y < 0 || y > 29){
            return false;
        } else{
            return true;
        }
    }

    public void generarEstadoActualPorMontecarlo(){
        for(int i = 0; i < Dimension; i++){
            for(int j = 0; j < Dimension; j++){
                double random = Math.random();
                int valor = '0';
                if (random > 0.5)
                    valor = '1';
                
                estadoActual[i][j] = valor;
            }
        }
        
    }
    
    @Override
    public String toString(){
        String estado = "";
        for(int i = 0; i < Dimension; i++){
            for(int j = 0; j < Dimension; j++){
                estado += (char)estadoActual[i][j];
            }
            estado += '\n';
        }
        return estado;
    }
}
