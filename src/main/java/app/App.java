package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App 
{
	
    public static void main( String[] args )
    {
    	//Creación de variables
    	String ruta = "D:\\Documentos\\Trabajo\\01_MATERIALES";
    	Scanner scanner = new Scanner(System.in);
    	
    	//Preguntamos por la subcarpeta de la cual leemos los PDF
    	System.out.println("¿Qué subcarpeta desea ver?");
    	String subcarpeta = scanner.nextLine();
    	ruta+="\\"+subcarpeta;
    	
    	//Inicializamos el lector PDF y la carpeta:
    	LectorPDF lector = new LectorPDF();    	
    	File carpeta = new File(ruta);
    	
    	//Inicializamos el txt de salida que almacena los textos
    	File salidaTxt = new File("D:\\Documentos\\Trabajo\\TextoSalida\\"+subcarpeta+".txt");
    	
    	//Inicializamos el bufferedWriter para escribir en el txt de salida
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(salidaTxt))) {
    		
    		//Recorremos la carpeta recursivamente para leer sus pdf
    	    recorrerCarpeta(carpeta, lector, bw, 0);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
    	scanner.close();
    }
    
    public static void obtenerFechas(String texto){
    	
    	//Array que almacena las líneas de texto de cada página
    	String[] lineas = texto.split("\\R");
    	
    	//Lista de patrones a buscar
		List<Pattern> patrones = List.of(
				Pattern.compile("suministra", Pattern.CASE_INSENSITIVE),
				Pattern.compile("fecha", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\\b(0?[1-9]|[12][0-9]|3[01])[/-](0?[1-9]|1[0-2])[/-](\\d{2}|(19|20)\\d{2})\\b"),
			    Pattern.compile("\\b\\d{4}-\\d{2}-\\d{2}\\b"),
			    Pattern.compile("\\b\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\b"),			    
			    Pattern.compile("\\b\\d{1,2}\\s+de\\s+" + "(enero|febrero|marzo|abril|mayo|junio|julio|agosto|septiembre|setiembre|octubre|noviembre|diciembre)" +"\\s+de\\s+\\d{4}\\b",Pattern.CASE_INSENSITIVE)
		);
		
		//primerasLineas(lineas);
		
		//Seleccionamos cada linea del texto para:
		for (String linea : lineas) {
			
			//Ir buscando cada uno de los patrones en la respectiva línea:
			for(Pattern patron : patrones) {
				
				Matcher matcher = patron.matcher(linea);				
				if(matcher.find()) {
					//Si se encuentra alguna coincidencia de patrón, imprimimos la línea
					System.out.println("		"+linea);
					//Salimos del bucle para no imprimir la misma línea si tiene dos o más patrones
					break;
				}		
			} 			
		}		  	
    }    
    
    public static void recorrerCarpeta(File carpeta, LectorPDF lector, BufferedWriter bw, int contadorCapitulo) {
    	
    	int contadorArchivos = 0;

    	//Creamos una lista con los archivos/Directorios de la carpeta
    	File archivos[] = carpeta.listFiles();
    	
    	//Si no hay archivos, finaliza la función
    	if(archivos == null) {
    		System.out.println("Carpeta vacía");
    		return;
    	}
	
		//Almacenamos el nombre del capítulo
		if(contadorCapitulo == 0) {
			//Depuración
			System.out.println("CAPITULO: "+carpeta.getName());  
			try {
				bw.write("CAPITULO: "+ carpeta.getName() + "\n"); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
		}
		
		//Almacenamos el nombre del "Material"
		if(contadorCapitulo == 1) {
			//Depuración
			System.out.println("MATERIAL: "+carpeta.getName()); 
			
			try {
				
				//Almacenamos el nombre del "material" al que pertenecen los pdf
				bw.write("MATERIAL: "+ carpeta.getName() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		if(contadorCapitulo >1) {
			//Depuración
			System.out.println("SUBCARPETA: "+carpeta.getName()); 
			
			try {
				
				//Almacenamos el nombre del "material" al que pertenecen los pdf
				bw.write("SUBCARPETA: "+ carpeta.getName() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
		}
		
		//Para cada archivo/directorio entontrado:
		for(File archivo : archivos) {
		
			//Obtenemos el nombre del archivo/directorio
			String nombreArchivo = archivo.getName();
			
			//Si el archivo es un directorio, accedemos recursivamente a los archivos de su interior
			if(archivo.isDirectory()) {    				
				
				if(contadorCapitulo>1) {
					System.out.println("SUBCARPETA: "+archivo.getName());
				}
				
				//Llamamos recursivamente a la función para buscar en subcarpetas
				recorrerCarpeta(archivo, lector, bw, contadorCapitulo +1);				
			}
			//En caso contrario, buscamos los archivos pdf para leerlos
			else if(nombreArchivo.toLowerCase().endsWith(".pdf")) {
				
				contadorArchivos++;
				//Imprimimos el nombre del archivo y de la carpeta a la que pertenece
				System.out.println("------------------------------------------------");
				System.out.println(" - ARCHIVO " + contadorArchivos +": " + nombreArchivo);
				
				//Obtenemos el texto del archivo
				String texto = lector.leerTexto(archivo);
				
				//Llamamos a la función que busca fechas en el pdf
				obtenerFechas(texto);
				
				//Almacenamos el texto en salidasTxt
				
				try {
					bw.write("===========================================\n\n");
					bw.write("ARCHIVO: "+ nombreArchivo +"\n\n");
					bw.write(texto+"\n");
					bw.write("===========================================\n\n"); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				   				
			} 			    		
    	}
		System.out.println("------------------------------------------------");	
    }
    
    public static void primerasLineas(String[] lineas) {
    	
    	for(int i=0; i<4; i++){
    		System.out.println(lineas[i]);
    	}
    }
}
