Este es un proyecto personal, utilizado para obtener el texto de un pdf y poder buscar patrones concretos. Se utilizan dos herramientas para obtener dicha información:
  - Apache PDFBox, que permite leer/extraer texto desde un pdf.
  - Tesseract OCR, que permite extraer texto de aquellos pdfs escaneados, en aquellos casos en los que PDFBox no puede extraer ningún dato.

La idea del programa, es escoger una carpeta, y leer los documentos PDF que tiene en su interior (o los que están en las carpetas que contiene, de forma recursiva) para obtener la información que el usuario necesite de cada uno de los documentos, permitiendo realizar una búsqueda rápida, eliminando la información que no sea necesaria. Esto permite ver de una forma rápida y clara la información buscada en cada documento, o incluso darle como salida del programa un archivo de texto que almacene dicha información para su posterior tratamiento.

Este proyecto está en una etapa muy temprana, y al ser un proyecto de carácter personal, no he necesitado incluir ningún aspecto gráfico ni generalizado para facilitar el uso del programa al usuario, ya que, con simples cambios en el código, podía ajustar el programa al enfoque que necesitaba. Se ha considerado que estas opciones, tales como modificar los datos a buscar, ofrecer una interfaz, por ejemplo, para permitir la selección de la carpeta con PDFs a analizar, y otras funcionalidades, son secundarias con respecto a la funcionalidad principal mencionada en el inicio del documento.

La idea es continuar el desarrollo del proyecto e ir terminando y mejorando las funcionalidades existentes, para poder seguir añadiendo funciones que puedan ser necesarias o que mejoren la calidad de vida del proyecto.
