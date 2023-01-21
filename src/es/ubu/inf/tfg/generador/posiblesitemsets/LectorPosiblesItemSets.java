package es.ubu.inf.tfg.generador.posiblesitemsets;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LectorPosiblesItemSets {
	
	Scanner leer = new Scanner(System.in);
	
	private static final int minNumItemSets = 10;
	private static final int maxNumItemSets = 20;
	
	private static final int minTamItemSets = 3;
	private static final int maxTamItemSets = 4;
	
	public ConfigPosiblesItemSets leerConfig() {
		int numItemSets;
		int tamItemSets;
		
		while (true) {
		    try {
		        System.out.println("Elige cantidad de item sets (Min: " + minNumItemSets + ", Max: " + maxNumItemSets + ")");
		        numItemSets = leer.nextInt();
		        if (numItemSets >= minNumItemSets && numItemSets <= maxNumItemSets) {
		            break;
		        } else {
		            System.out.println("El valor introducido no es valido, ingresa un número entre " + minNumItemSets + " y " + maxNumItemSets);;
		        }
		    } catch (InputMismatchException e) {
		        System.out.println("El valor introducido no es valido, ingresa un número entero");
		        leer.next();
		    }
		}

		while (true) {
		    try {
		        System.out.println("Elige tamaño de los item sets (Min: " + minTamItemSets + ", Max: " + maxTamItemSets + ")");
		        tamItemSets = leer.nextInt();
		        if (tamItemSets >= 3 && tamItemSets <= 4) {
		            break;
		        } else {
		            System.out.println("El valor introducido no es valido, ingresa un número entero entre " + minTamItemSets + "y" + maxTamItemSets);
		        }
		    } catch (InputMismatchException e) {
		        System.out.println("El valor introducido no es valido, ingresa un número entero entre " + minTamItemSets + "y" + maxTamItemSets);
		        leer.next();
		    }
		}
		
		return new ConfigPosiblesItemSets(numItemSets, tamItemSets);
	}
}
