package main;
import contextual.AnalisisContextual;
import generacionCodigo.GCodigo;
import generated.LexerMiniJava;
import generated.ParserMiniJava;
import gui.TPEditor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileReader;

public class Main {

    public static final TPEditor programa = new TPEditor();

    public static void main(String[] args){

        /*LexerMiniJava inst = null;
        ParserMiniJava parser = null;
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileReader("prueba.txt"));
            inst = new LexerMiniJava(input);
            CommonTokenStream tokens = new CommonTokenStream(inst);
            parser = new ParserMiniJava(tokens);
        }
        catch(Exception e){System.out.println("No hay archivo");}

        ParseTree tree = parser.goal();
        inst.reset();
        parser.reset();
        tree = parser.goal();*/

        //GCodigo generador = new GCodigo();
        //generador.visit(tree);

        //construye la GUI en el EDT (Event Dispatch Thread)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                programa.jFrame.setVisible(true);    //hace visible la GUI creada por la clase TPEditor
            }
        });

    }

}
