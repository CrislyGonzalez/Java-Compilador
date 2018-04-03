package main;

import gui.TPEditor;
import org.antlr.v4.runtime.*;

public class ErrorListenerReport extends BaseErrorListener {

    public static final ErrorListenerReport INSTANCE = new ErrorListenerReport();
    public static int contErrores = 0;

    public static String tokensEsperados(String cadena){
        int i=0;
        while(i<cadena.length()){
            if(cadena.charAt(i) == '{'){
                break;
            }
            i++;

        }
        return cadena.substring(i+1,cadena.length()-1);
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        String[] parts = msg.split(" ");
        String ingresado  = "";
        String errorReport= "";

        if (e instanceof org.antlr.v4.runtime.NoViableAltException) {

            errorReport = "Error de parser en la línea: " + line + ", columna: " + (charPositionInLine) +
                    ". El token: " + parts[4] + " no es una alternativa viable";
            //throw new RecognitionException(errorReport, recognizer, null, null);
            contErrores++;
        }

        else if (e instanceof org.antlr.v4.runtime.LexerNoViableAltException){
            errorReport = "Error de scanner en la línea: " + line + ", columna: " + (charPositionInLine) +
                    ". El símbolo: " + parts[4] + " no es un token válido";
            //throw new RecognitionException(errorReport, recognizer, null, null);
            contErrores++;
        }

        else if( e instanceof FailedPredicateException){
            errorReport = "Error de parser en la línea: " + line + ", columna: " + (charPositionInLine) +
                    ". La semántica de la expresión es inválida";
            //throw new RecognitionException(errorReport, recognizer, null, null);
            contErrores++;
        }

        String esperadoT = "";
        if (e instanceof InputMismatchException){

            for (int i=0; i<parts.length; i++){
                if(parts[i].equals("mismatched") && parts[i+1].equals("input")){
                    ingresado = parts[i+2];
                    if(parts[i+4] != "{"){
                        esperadoT= parts[i+4];
                    }else{
                        esperadoT = tokensEsperados(msg);
                    }

                }

            }
            errorReport= "Error de parser en la línea: " + line + ", columna: " + (charPositionInLine) +
                    ". Se esperaba: " + esperadoT + " y en su lugar se encontró " + ingresado;
            //throw new RecognitionException(errorReport, recognizer, null, null);
            contErrores++;
        }

        Main.programa.appendErrorArea(errorReport);
        //System.out.println(errorReport);

    }
}
