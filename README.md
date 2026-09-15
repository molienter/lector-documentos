Este es un proyecto personal, utilizado para obtener el texto de un pdf y poder buscar patrones concretos. Se utilizan dos herramientas para obtener dicha información:
  - Apache PDFBox, que permite leer/extraer texto desde un pdf.
  - Tesseract OCR, que permite extraer texto de aquellos pdfs escaneados, en aquellos casos en los que PDFBox no puede extraer ningún dato.

La idea del programa, es escoger una carpeta, y leer los documentos PDF que tiene en su interior (o los que están en las carpetas que contiene, de forma recursiva) para obtener la información que el usuario necesite de cada uno de los documentos, permitiendo realizar una búsqueda rápida, eliminando la información que no sea necesaria. Esto permite ver de una forma rápida y clara la información buscada en cada documento, o incluso darle como salida del programa un archivo de texto que almacene dicha información para su posterior tratamiento.

Este proyecto está en una etapa muy temprana, ya que, se ha utilizado para automatizar ciertos trabajos de búsqueda de información y categorización de PDFs, pero realmente su funcionalidad principal funciona correctamente, a falta de modificar el código complementario para tratar la información que se desea obtener y cómo tratar dicha información, según las necesidades del usuario específico al que se destine el programa.
