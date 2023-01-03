package ReglasAsociacion;
//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
//import weka.core.converters.ArffSaver;

public class Pregunta {

  public static void main(String[] args) throws IOException {
    // Crea la lista de atributos del conjunto de datos
	ArrayList<Attribute> atributos = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
    System.out.print("Ingrese el número de atributos: ");
    int numAtributos = sc.nextInt();
    
    System.out.print("Ingrese el número de instancias aleatorias: ");
    int numInstancias = sc.nextInt();
    
    for (int i = 0; i < numAtributos; i++) {
//        System.out.print("Ingrese el nombre del atributo " + (i + 1) + ": ");
        String nombreAtributo = Character.toString((char) ('A' + i));
        // El atributo es booleano
        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("T");
        etiquetas.add("F");
        atributos.add(new Attribute(nombreAtributo, etiquetas));
      }

    Instances datos = new Instances("datos", atributos, 0);

    Random rand = new Random();

    for (int i = 0; i < numInstancias; i++) {

      Instance instancia = new DenseInstance(numAtributos);
      instancia.setDataset(datos); 
      
      for (int j = 0; j < numAtributos; j++) {
    	  instancia.setValue(j, (rand.nextInt(2) == 0) ? "T" : "F");
      }
      // Añade la instancia al conjunto de datos
      datos.add(instancia);
    }
    System.out.println("Fin");

    // Guarda el conjunto de datos en un archivo ARFF
//    ArffSaver saver = new ArffSaver();
//    saver.setInstances(data);
//    saver.setFile(new File("datos.arff"));
//    saver.writeBatch();
  }
}
 


