package app;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class LectorPDF {
	
	public String leerTexto(File pdf) {
		
        try (PDDocument document = Loader.loadPDF(pdf)) {

            PDFTextStripper stripper = new PDFTextStripper();
            
            String texto = stripper.getText(document);
            
            if(texto.isBlank()) {
            	LectorOCR lectorOCR = new LectorOCR();
                texto = lectorOCR.leerTextoOCR(document);
            }

            return texto; 
            
        } catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "";
		} 		
	}
}
