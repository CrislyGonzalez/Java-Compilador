package main;

import generated.LexerMiniJava;
import generated.ParserMiniJava;
import gui.TPEditor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

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
        catch (Exception e){
            System.out.println("El archivo no existe");
        }

        inst.removeErrorListeners();
        inst.addErrorListener(ErrorListenerReport.INSTANCE);
        parser.removeErrorListeners();
        parser.addErrorListener(ErrorListenerReport.INSTANCE);

        List<Token> lista = (List<Token>) inst.getAllTokens();

        for (Token t : lista)
            System.out.println(t.getType() + ":" + t.getText() + "\n");

        inst.reset();
        parser.goal();*/

        LexerMiniJava inst = null;
        ParserMiniJava parser = null;
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileReader("prueba.txt"));
            inst = new LexerMiniJava(input);
            CommonTokenStream tokens = new CommonTokenStream(inst);
            parser = new ParserMiniJava(tokens);
        }
        catch(Exception e){System.out.println("No hay archivo");}

        /*List<Token> lista = (List<Token>) inst.getAllTokens();

        for (Token t : lista)
            System.out.println(t.getType() + ":" + t.getText() + "\n");

        inst.reset();*/
        /*try {
            ParseTree tree = parser.goal();

            System.out.println("Compilación Exitosa!!\n");

            System.out.println(tree.toStringTree(parser));
            //ImprimirAST printVisitor = new ImprimirAST();
            //printVisitor.visit(tree);
            AnalisisContextual acVisitor = new AnalisisContextual();
            acVisitor.visit(tree);
            acVisitor.tablaClases.imprimir();
            acVisitor.tablaSimbolos.imprimir();
            acVisitor.tablaMetodosInstance.imprimir();

        }
        catch(RecognitionException e){
            System.out.println("Compilación Fallida!!");
        }*/

        //inst.reset();
        /*System.out.println("Cantidad de errores: " + ErrorListenerReport.contErrores);
        if (ErrorListenerReport.contErrores > 0) {
            System.out.println("¡Compilación Fallida!");
        }
        else{
            ParseTree tree = parser.goal();

            System.out.println("¡Compilación Exitosa!\n");

            System.out.println(tree.toStringTree(parser));
            ImprimirAST visitor = new ImprimirAST();
            visitor.visit(tree);
        }*/


        //construye la GUI en el EDT (Event Dispatch Thread)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                programa.jFrame.setVisible(true);    //hace visible la GUI creada por la clase TPEditor
            }
        });

    }

}
