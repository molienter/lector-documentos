package app;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class LectorOCR {
	
	
	public String leerTextoOCR(PDDocument document) {
		
		//Valor de DPI del renderizador OCR.
		int DPI = 600;
		
		//Creamos el renderizador y el tesseract y el StringBuilder
		PDFRenderer renderer = new PDFRenderer(document);		
		Tesseract tesseract = new Tesseract();
		StringBuilder texto = new StringBuilder();
		
		//Ajustamos el path e idioma del tesseract
		tesseract.setDatapath("D:\\Tesseract\\tessdata");
		tesseract.setLanguage("spa");
		
		//Ajustamos el modo de segmentación
		tesseract.setPageSegMode(6);
		
		//Bloque try
		try {
			//Recorremos las páginas
			for(int i=0; i<document.getNumberOfPages();i++) {
				
				//Obtenemos la imagen de cada página
				BufferedImage imagen = renderer.renderImageWithDPI(i,DPI);
				//Añadimos cada página al texto a devolver
				texto.append(tesseract.doOCR(imagen));
				texto.append("\n");	
			}			
		} catch (IOException | TesseractException e) {
			//e.printStackTrace();
			return "";
		}
		
		//Si todo sale bien, devolvemos el texto como un string
		return texto.toString();	
	}
}
