/**
 * ActionPerformer.java
 */

package gui;

import contextual.AnalisisContextual;
import generacionCodigo.GCodigo;
import generated.LexerMiniJava;
import generated.ParserMiniJava;
import main.ErrorListenerReport;
import main.ImprimirAST;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;
import java.io.*;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;

/**
 * Clase que ejecuta las operaciones solicitadas.
 *
 * @author Dark[byte]
 */
public class ActionPerformer {

    private static final String LAST_USED_FOLDER = "C:\\";
    private final TPEditor tpEditor;    //instancia de TPEditor (la clase principal)
    private String lastSearch = "";     //la última búsqueda de texto realizada, por defecto no contiene nada
    private Preferences prefs;

    private LexerMiniJava inst = null;
    private ParserMiniJava parser = null;
    private ParseTree tree;
    private AnalisisContextual acVisitor;

    /**
     * Constructor de la clase.
     *
     * @param tpEditor clase principal
     */
    public ActionPerformer(TPEditor tpEditor) {
        this.tpEditor = tpEditor;    //guarda la instancia de la clase TPEditor
    }

    /**
     * Opción seleccionada: "Nuevo".
     *
     * Reemplaza el documento actual por uno nuevo vacío.
     */
    public void actionNew() {
        if (tpEditor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
            //le ofrece al usuario guardar los cambios
            int option = JOptionPane.showConfirmDialog(tpEditor.getJFrame(), "¿Desea guardar los cambios?");

            switch (option) {
                case JOptionPane.YES_OPTION:       //si elige que si
                    actionSave();                  //guarda el archivo
                    break;
                case JOptionPane.CANCEL_OPTION:    //si elige cancelar
                    return;                        //cancela esta operación
                //en otro caso se continúa con la operación y no se guarda el documento actual
            }
        }

        tpEditor.getJFrame().setTitle("Java IDE - Sin Título");    //nuevo título de la ventana

        //limpia el contenido del area de edición
        tpEditor.getJTextArea().setText("");
        //limpia el contenido de las etiquetas en la barra de estado
        tpEditor.getJLabelFilePath().setText("");
        tpEditor.getJLabelFileSize().setText("");

        tpEditor.getUndoManager().die();    //limpia el buffer del administrador de edición
        tpEditor.updateControls();          //actualiza el estado de las opciones "Deshacer" y "Rehacer"

        //el archivo asociado al documento actual se establece como null
        tpEditor.setCurrentFile(null);
        //marca el estado del documento como no modificado
        tpEditor.setDocumentChanged(false);
    }

    /**
     * Opción seleccionada: "Abrir".
     *
     * Le permite al usuario elegir un archivo para cargar en el área de edición.
     */
    public void actionOpen() {
        if (tpEditor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
            //le ofrece al usuario guardar los cambios
            int option = JOptionPane.showConfirmDialog(tpEditor.getJFrame(), "¿Desea guardar los cambios?");

            switch (option) {
                case JOptionPane.YES_OPTION:     //si elige que si
                    actionSave();               //guarda el archivo
                    break;
                case JOptionPane.CANCEL_OPTION:  //si elige cancelar
                    return;                      //cancela esta operación
                //en otro caso se continúa con la operación y no se guarda el documento actual
            }
        }

        //presenta un dialogo modal para que el usuario seleccione un archivo
        JFileChooser fc = getJFileChooser();    //obtiene un JFileChooser

        int state = fc.showOpenDialog(tpEditor.getJFrame());

        if (state == JFileChooser.APPROVE_OPTION) {    //si elige abrir el archivo
            prefs.put(LAST_USED_FOLDER, fc.getSelectedFile().getParent());
            File f = fc.getSelectedFile();    //obtiene el archivo seleccionado

            try {
                //abre un flujo de datos desde el archivo seleccionado
                BufferedReader br = new BufferedReader(new FileReader(f));
                //lee desde el flujo de datos hacia el area de edición
                tpEditor.getJTextArea().read(br, null);
                br.close();    //cierra el flujo

                tpEditor.getJTextArea().getDocument().addUndoableEditListener(tpEditor.getEventHandler());

                tpEditor.getUndoManager().die();    //se limpia el buffer del administrador de edición
                tpEditor.updateControls();          //se actualiza el estado de las opciones "Deshacer" y "Rehacer"

                //nuevo título de la ventana con el nombre del archivo cargado
                tpEditor.getJFrame().setTitle("Java IDE - " + f.getName());

                //muestra la ubicación del archivo actual
                tpEditor.getJLabelFilePath().setText(shortPathName(f.getAbsolutePath()));
                //muestra el tamaño del archivo actual
                tpEditor.getJLabelFileSize().setText(roundFileSize(f.length()));

                //establece el archivo cargado como el archivo actual
                tpEditor.setCurrentFile(f);
                //marca el estado del documento como no modificado
                tpEditor.setDocumentChanged(false);
            } catch (IOException ex) {    //en caso de que ocurra una excepción
                //presenta un dialogo modal con alguna información de la excepción
                JOptionPane.showMessageDialog(tpEditor.getJFrame(),
                        ex.getMessage(),
                        ex.toString(),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opción seleccionada: "Guardar".
     *
     * Guarda el documento actual en el archivo asociado actualmente.
     */
    public void actionSave() {
        if (tpEditor.getCurrentFile() == null) {    //si no hay un archivo asociado al documento actual
            actionSaveAs();    //invoca el método actionSaveAs()
        } else if (tpEditor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
            try {
                //abre un flujo de datos hacia el archivo asociado al documento actual
                BufferedWriter bw = new BufferedWriter(new FileWriter(tpEditor.getCurrentFile()));
                //escribe desde el flujo de datos hacia el archivo
                tpEditor.getJTextArea().write(bw);
                bw.close();    //cierra el flujo

                //marca el estado del documento como no modificado
                tpEditor.setDocumentChanged(false);
            } catch (IOException ex) {    //en caso de que ocurra una excepción
                //presenta un dialogo modal con alguna información de la excepción
                JOptionPane.showMessageDialog(tpEditor.getJFrame(),
                        ex.getMessage(),
                        ex.toString(),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opción seleccionada: "Guardar como".
     *
     * Le permite al usuario elegir la ubicación donde se guardará el documento actual.
     */
    public void actionSaveAs() {
        JFileChooser fc = getJFileChooser();    //obtiene un JFileChooser

        //presenta un dialogo modal para que el usuario seleccione un archivo
        int state = fc.showSaveDialog(tpEditor.getJFrame());
        if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
            prefs.put(LAST_USED_FOLDER, fc.getSelectedFile().getParent());
            File f = fc.getSelectedFile();    //obtiene el archivo seleccionado

            try {
                //abre un flujo de datos hacia el archivo asociado seleccionado
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                //escribe desde el flujo de datos hacia el archivo
                tpEditor.getJTextArea().write(bw);
                bw.close();    //cierra el flujo

                //nuevo título de la ventana con el nombre del archivo guardado
                tpEditor.getJFrame().setTitle("Java IDE - " + f.getName());

                //muestra la ubicación del archivo guardado
                tpEditor.getJLabelFilePath().setText(shortPathName(f.getAbsolutePath()));
                //muestra el tamaño del archivo guardado
                tpEditor.getJLabelFileSize().setText(roundFileSize(f.length()));

                //establece el archivo guardado como el archivo actual
                tpEditor.setCurrentFile(f);
                //marca el estado del documento como no modificado
                tpEditor.setDocumentChanged(false);
            } catch (IOException ex) {    //en caso de que ocurra una excepción
                //presenta un dialogo modal con alguna información de la excepción
                JOptionPane.showMessageDialog(tpEditor.getJFrame(),
                        ex.getMessage(),
                        ex.toString(),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Opción seleccionada: Compilar
     * Lexea y parsea el texto escrito en el textarea
     */
    public void actionCompile(){
        tpEditor.clearErrorArea();
        tpEditor.clearASTArea();
        tpEditor.errorArea.setForeground(Color.BLACK);
        tpEditor.setErrorArea("Compilando...\n");
        ErrorListenerReport.contErrores = 0;

        if (tpEditor.documentHasChanged() == true) { //si el documento esta marcado como modificado
            actionSave();
        }

        try {
            ANTLRInputStream input = new ANTLRInputStream(tpEditor.getJTextArea().getText());
            inst = new LexerMiniJava(input);

            CommonTokenStream tokens = new CommonTokenStream(inst);
            parser = new ParserMiniJava(tokens);
        }
        catch (Exception e) {    //en caso de que ocurra una excepción
            //presenta un dialogo modal con alguna información de la excepción
            JOptionPane.showMessageDialog(tpEditor.getJFrame(),
                    e.getMessage(),
                    e.toString(),
                    JOptionPane.ERROR_MESSAGE);
        }

        inst.removeErrorListeners();
        inst.addErrorListener(ErrorListenerReport.INSTANCE);
        parser.removeErrorListeners();
        parser.addErrorListener(ErrorListenerReport.INSTANCE);

        parser.goal();

        inst.reset();
        parser.reset();
        tree = parser.goal();
        acVisitor = new AnalisisContextual();
        acVisitor.visit(tree);

        //inst.reset();
        //parser.reset();


        //System.out.println("Cantidad de errores: " + ErrorListenerReport.contErrores);
        //tpEditor.appendErrorArea("Cantidad de errores: " + ErrorListenerReport.contErrores + "\n");
        if ((ErrorListenerReport.contErrores > 0) || (acVisitor.erroresContextuales > 0)) {
            //System.out.println("¡Compilación Fallida!");
            tpEditor.errorArea.setForeground(Color.RED);
            tpEditor.appendErrorArea("¡Compilación Fallida!\n");
        }
        else{
            tpEditor.appendErrorArea("¡Compilación Exitosa!\n");
            tpEditor.construirVentanaArbol();

            //System.out.println("¡Compilación Exitosa!\n");


            //System.out.println(tree.toStringTree(parser));
            ImprimirAST visitor = new ImprimirAST();
            visitor.visit(tree);
            tpEditor.ventanaAST.setVisible(true);
        }
    }


    /**
     * Opción seleccionada: Ejecutar
     * Lexea y parsea el texto escrito en el textarea
     */
    public void actionRun(){
        tpEditor.clearErrorArea();

        if ((ErrorListenerReport.contErrores > 0) || (acVisitor.erroresContextuales > 0)) {
            tpEditor.errorArea.setForeground(Color.RED);
            tpEditor.appendErrorArea("¡Compilación Fallida!\n");
        }
        else{
            tpEditor.errorArea.setForeground(Color.BLACK);
            inst.reset();
            parser.reset();
            tree = parser.goal();

            GCodigo generador = new GCodigo();
            generador.visit(tree);
        }
    }


    /**
     * Opción seleccionada: "Salir".
     *
     * Finaliza el programa.
     */
    public void actionExit() {
        if (tpEditor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
            //le ofrece al usuario guardar los cambios
            int option = JOptionPane.showConfirmDialog(tpEditor.getJFrame(), "¿Desea guardar los cambios?");

            switch (option) {
                case JOptionPane.YES_OPTION:     //si elige que si
                    actionSave();                //guarda el archivo
                    break;
                case JOptionPane.CANCEL_OPTION:  //si elige cancelar
                    return;                      //cancela esta operación
                //en otro caso se continúa con la operación y no se guarda el documento actual
            }
        }


        System.exit(0);    //finaliza el programa con el código 0 (sin errores)
    }

    /**
     * Opción seleccionada: "Deshacer".
     *
     * Deshace el último cambio realizado en el documento actual.
     */
    public void actionUndo() {
        try {
            //deshace el último cambio realizado sobre el documento en el área de edición
            tpEditor.getUndoManager().undo();
        } catch (CannotUndoException ex) {    //en caso de que ocurra una excepción
            System.err.println(ex);
        }

        //actualiza el estado de las opciones "Deshacer" y "Rehacer"
        tpEditor.updateControls();
    }

    /**
     * Opción seleccionada: "Rehacer".
     *
     * Rehace el último cambio realizado en el documento actual.
     */
    public void actionRedo() {
        try {
            //rehace el último cambio realizado sobre el documento en el área de edición
            tpEditor.getUndoManager().redo();
        } catch (CannotRedoException ex) {    //en caso de que ocurra una excepción
            System.err.println(ex);
        }

        //actualiza el estado de las opciones "Deshacer" y "Rehacer"
        tpEditor.updateControls();
    }

    /**
     * Opción seleccionada: "Buscar".
     *
     * Busca un texto especificado por el usuario en el documento actual. El texto queda 
     * guardado para búsquedas siguientes.
     */
    public void actionSearch() {
        //solicita al usuario que introduzca el texto a buscar
        String text = JOptionPane.showInputDialog(
                tpEditor.getJFrame(),
                "Texto:",
                "Java IDE - Buscar",
                JOptionPane.QUESTION_MESSAGE);

        if (text != null) {    //si se introdujo texto (puede ser una cadena vacía)
            String textAreaContent = tpEditor.getJTextArea().getText();    //obtiene todo el contenido del área de edición
            int pos = textAreaContent.indexOf(text);    //obtiene la posición de la primera ocurrencia del texto

            if (pos > -1) {    //si la posición es mayor a -1 significa que la búsqueda fue positiva
                //selecciona el texto en el área de edición para resaltarlo
                tpEditor.getJTextArea().select(pos, pos + text.length());
            }

            //establece el texto buscado como el texto de la última búsqueda realizada
            lastSearch = text;
        }
    }

    /**
     * Opción seleccionada: "Buscar siguiente".
     *
     * Busca el texto de la última búsqueda en el documento actual.
     */
    public void actionSearchNext() {
        if (lastSearch.length() > 0) {    //si la última búsqueda contiene texto
            String textAreaContent = tpEditor.getJTextArea().getText();    //se obtiene todo el contenido del área de edición
            int pos = tpEditor.getJTextArea().getCaretPosition();    //se obtiene la posición del cursor sobre el área de edición
            //buscando a partir desde la posición del cursor, se obtiene la posición de la primera ocurrencia del texto
            pos = textAreaContent.indexOf(lastSearch, pos);

            if (pos > -1) {    //si la posición es mayor a -1 significa que la búsqueda fue positiva
                //selecciona el texto en el área de edición para resaltarlo
                tpEditor.getJTextArea().select(pos, pos + lastSearch.length());
            }
        } else {    //si la última búsqueda no contiene nada
            actionSearch();    //invoca el método actionSearch()
        }
    }

    /**
     * Opción seleccionada: "Ir a la línea...".
     *
     * Posiciona el cursor en el inicio de una línea especificada por el usuario.
     */
    public void actionGoToLine() {
        //solicita al usuario que introduzca el número de línea
        String line = JOptionPane.showInputDialog(
                tpEditor.getJFrame(),
                "Número:",
                "Java IDE - Ir a la línea...",
                JOptionPane.QUESTION_MESSAGE);

        if (line != null && line.length() > 0) {    //si se introdujo un dato
            try {
                int pos = Integer.parseInt(line);    //el dato introducido se convierte en entero

                //si el número de línea esta dentro de los límites del área de texto
                if (pos >= 0 && pos <= tpEditor.getJTextArea().getLineCount()) {
                    //posiciona el cursor en el inicio de la línea
                    tpEditor.getJTextArea().setCaretPosition(tpEditor.getJTextArea().getLineStartOffset(pos));
                }
            } catch (NumberFormatException ex) {    //en caso de que ocurran excepciones
                System.err.println(ex);
            } catch (BadLocationException ex) {
                System.err.println(ex);
            }
        }
    }

    /**
     * Opción seleccionada: "Fuente de letra".
     *
     * Le permite al usuario elegir la fuente para la letra en el área de edición.
     */
    /*public void actionSelectFont() {
        //presenta el dialogo de selección de fuentes
        Font font = JFontChooser.showDialog(tpEditor.getJFrame(),
                "TextPad Demo - Fuente de letra:",
                tpEditor.getJTextArea().getFont());
        if (font != null) {    //si un fuente fue seleccionado
            //se establece como fuente del area de edición
            tpEditor.getJTextArea().setFont(font);
        }
    }*/

    /**
     * Opción seleccionada: "Color de letra".
     *
     * Le permite al usuario elegir el color para la letra en el área de edición.
     */
    /*public void actionSelectFontColor() {
        //presenta el dialogo de selección de colores
        Color color = JColorChooser.showDialog(tpEditor.getJFrame(),
                "TextPad Demo - Color de letra:",
                tpEditor.getJTextArea().getForeground());
        if (color != null) {    //si un color fue seleccionado
            //se establece como color del fuente y cursor
            tpEditor.getJTextArea().setForeground(color);
            tpEditor.getJTextArea().setCaretColor(color);
        }
    }*/

    /**
     * Opción seleccionada: "Color de fondo".
     *
     * Le permite al usuario elegir el color para el fondo del área de edición.
     */
    /*public void actionSelectBackgroundColor() {
        //presenta el dialogo de selección de colores
        Color color = JColorChooser.showDialog(tpEditor.getJFrame(),
                "TextPad Demo - Color de fondo:",
                tpEditor.getJTextArea().getForeground());
        if (color != null) {    //si un color fue seleccionado
            //se establece como color de fondo
            tpEditor.getJTextArea().setBackground(color);
        }
    }*/

    /**
     * Retorna la instancia de un JFileChooser, con el cual se muestra un dialogo que permite
     * seleccionar un archivo.
     *
     * @return un dialogo para seleccionar un archivo.
     */
    private JFileChooser getJFileChooser() {
        prefs = Preferences.userRoot().node(getClass().getName());
        JFileChooser fc = new JFileChooser(prefs.get(LAST_USED_FOLDER,
                new File(".java").getAbsolutePath()));                     //construye un JFileChooser
        fc.setDialogTitle("Java IDE - Elige un archivo:");    //se le establece un título
        fc.setMultiSelectionEnabled(false);                       //desactiva la multi-selección
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Archivos Java", "java");
        fc.setFileFilter(ff);                       //aplica un filtro de extensiones

        //fc.setCurrentDirectory(new File("C:\\"));
        return fc;    //retorna el JFileChooser
    }



    /**
     * Retorna la ruta de la ubicación de un archivo en forma reducida.
     *
     * @param longPath la ruta de un archivo
     * @return la ruta reducida del archivo
     */
    private static String shortPathName(String longPath) {
        //construye un arreglo de cadenas, donde cada una es un nombre de directorio
        String[] tokens = longPath.split(Pattern.quote(File.separator));

        //construye un StringBuilder donde se añadirá el resultado
        StringBuilder shortpath = new StringBuilder();

        //itera sobre el arreglo de cadenas
        for (int i = 0 ; i < tokens.length ; i++) {
            if (i == tokens.length - 1) {              //si la cadena actual es la última, es el nombre del archivo
                shortpath.append(tokens[i]);    //añade al resultado sin separador
                break;                          //termina el bucle
            } else if (tokens[i].length() >= 10) {     //si la cadena actual tiene 10 o más caracteres
                //se toman los primeros 3 caracteres y se añade al resultado con un separador
                shortpath.append(tokens[i].substring(0, 3)).append("...").append(File.separator);
            } else {                                   //si la cadena actual tiene menos de 10 caracteres
                //añade al resultado con un separador
                shortpath.append(tokens[i]).append(File.separator);
            }
        }

        return shortpath.toString();    //retorna la cadena resultante
    }

    /**
     * Redondea la longitud de un archivo en KiloBytes si es necesario.
     *
     * @param length longitud de un archivo
     * @return el tamaño redondeado  
     */
    private static String roundFileSize(long length) {
        //retorna el tamaño del archivo redondeado
        return (length < 1024) ? length + " bytes" : (length / 1024) + " Kbytes";
    }

}