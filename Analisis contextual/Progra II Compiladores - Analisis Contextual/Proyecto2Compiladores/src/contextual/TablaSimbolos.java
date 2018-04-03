package contextual;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class TablaSimbolos {

    public int x = 0;
    public int nivelTabla;
    ArrayList<TokenObj> tablaGeneralTokens;



    public TablaSimbolos() {

        this.tablaGeneralTokens = new ArrayList<TokenObj>();
    }



    public void insertarSimbolos(String nombre, int tipo,ParserRuleContext pDeclaracion, String clase){
    // incluir que no existan en el mismo nivel, si ya existe devuelme msj sino es permitido
        TokenObj var = null;
        Token t;
        /*if(verificarNivelParaInsertar(nombre)){
            if(tipo == CONSTANTES.TInt) {
                t = new CommonToken(tipo, nombre);
                var = new TokenObj(t, nivelTabla,pDeclaracion);

            }else if(tipo == CONSTANTES.TChar){
                var = new TokenObj(nombre, CONSTANTES.TChar, nivelTabla,pDeclaracion);

            }else if(tipo == CONSTANTES.TFloat){
                var = new TokenObj(nombre, CONSTANTES.TFloat, nivelTabla,pDeclaracion);
            }
            else if (tipo == CONSTANTES.TString){
                var = new TokenObj(nombre, CONSTANTES.TString, nivelTabla,pDeclaracion);
            }
            tablaGeneralTokens.add(var);
        }
        else {
            if(nivelTabla == 0){
                System.out.println("La variable: " + nombre + " ya fue declarada como Global");
            }else {
                System.out.println("La variable: " + nombre + " ya fue declarada como Local");
            }

        }*/
        if (verificarNivelParaInsertar(nombre,clase)) {
            t = new CommonToken(tipo, nombre);
            var = new TokenObj(t, nivelTabla, pDeclaracion,clase);
            tablaGeneralTokens.add(var);
        }else{
            /*if (nivelTabla == 0){
                System.out.println("La variable: " + nombre + " ya fue declarada como Global");
            }else {
                System.out.println("La variable: " + nombre + " ya fue declarada como Local");
            }*/
            System.out.println("La variable: " + nombre + " ya fue declarada");
        }

    }

    public void insertarVarExtendidas(String clase, String claseExtiende){
        nivelTabla++;
        for (int i = 0; i < tablaGeneralTokens.size(); i++){
            if (tablaGeneralTokens.get(i).nombreClase.equals(claseExtiende)){
                TokenObj token = tablaGeneralTokens.get(i);
                insertarSimbolos(token.tok.getText(), token.tok.getType(), token.decl, clase);
            }
        }
        nivelTabla--;
    }



    public TokenObj buscar(String nombre){
        for (int i =0; i<tablaGeneralTokens.size();i++){
            TokenObj token = tablaGeneralTokens.get(i);

            if(token.tok.getText().equals( nombre)){
                return tablaGeneralTokens.get(i);
            }
        }
        return null;
    }


    public void openScope(){

        nivelTabla++;
    }

    /*public void closeScope(){
        for(int i=0; i<tablaGeneralTokens.size();i++){
            //if((tablaGeneralTokens.get(i)).nivel == nivelTabla)
                //tablaGeneralTokens.remove(i);
        }
        nivelTabla--;
    }*/

    public void closeScope(){
       for (int i = this.tablaGeneralTokens.size()-1; i >= 0; i--){
           if ((this.tablaGeneralTokens.get(i).nivel == this.nivelTabla) && (this.tablaGeneralTokens.get(i).nivel != 1)){
               this.tablaGeneralTokens.remove(i);
           }
       }
       this.nivelTabla--;
    }


    /*false indica no se puede meter y true es metalo*/
    public boolean verificarNivelParaInsertar(String pnombre, String clase){
        for(int i= 0; i<tablaGeneralTokens.size();i++){
            TokenObj token = tablaGeneralTokens.get(i);
            if(token.tok.getText().equals(pnombre) && (token.nivel == nivelTabla) && token.nombreClase.equals(clase)){
                return false;
            }
            else{
                return true;
            }
        }
        return true;
    }

    public void limpiarTabla(int nivel){
        for(int i=0; i<tablaGeneralTokens.size();i++){
            TokenObj token = (TokenObj) tablaGeneralTokens.get(i);
            if(token.nivel == nivel){
                tablaGeneralTokens.remove(i);
                i--;
            }
        }
    }

    public void imprimir (){
        System.out.println("----- Tabla de variables -----");
        System.out.println("Hay " + tablaGeneralTokens.size());
        for(int i=0; i<tablaGeneralTokens.size();i++){
            TokenObj token = tablaGeneralTokens.get(i);
            if(token.tok.getType() == CONSTANTES.TInt){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "int" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TString){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "String" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else  if (token.tok.getType() == CONSTANTES.TBoolean){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "boolean" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TChar){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "char" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TClass){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "class" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TArregloInt){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "array<int>" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TArregloString){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "array<String>" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TArregloBoolean){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "array<boolean>" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else if(token.tok.getType() == CONSTANTES.TArregloChar){
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "array<char>" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }else {
                System.out.println("\nNombre: "+ token.tok.getText() + "\nTipo dato: " + "indefinido" + "\nnivel: " + token.nivel + "\nClase: " + token.nombreClase);
            }

        }
    }


}
