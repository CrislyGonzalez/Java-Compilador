package contextual;


import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class TablaMetodos {
    public ArrayList<TokenMetodos> tablaTokenMetodos;
    private int nivelTablaM;

    public TablaMetodos() {

        this.tablaTokenMetodos = new ArrayList<TokenMetodos>();
    }


    public void insertar(String nombre, int tipo, ParserRuleContext decl, String clase)
    {

        Token t;
        TokenMetodos metodo;
        if (verificarNivelParaInsertarMetodos(nombre,clase)) {
            t = new CommonToken(tipo, nombre);
            metodo = new TokenMetodos(t, nivelTablaM,decl, clase);

            tablaTokenMetodos.add(metodo);
            //System.out.println("&&&&&&&&&&&&&&&&");

        }/*else{
            if (nivelTabla == 0){
                System.out.println("La variable: " + nombre + " ya fue declarada como Global");
            }else {
                System.out.println("La variable: " + nombre + " ya fue declarada como Local");
            }
            //System.out.println("........................................La variable: " + nombre + " ya fue declarada");
        }*/
    }

    public boolean verificarNivelParaInsertarMetodos(String pnombre, String clase){
        for(int i= 0; i<tablaTokenMetodos.size();i++){
            TokenMetodos token = tablaTokenMetodos.get(i);
            if(token.tok.getText().equals(pnombre) && (token.clase.equals(clase))){
                System.out.println("Mismo------------------------------------------");
                return false;
            }
            else{
                return true;
            }
        }
        return true;
    }


    public void insertarCHR(){
        this.insertar("chr", CONSTANTES.TChar, null, null);
        TokenMetodos token = this.buscar("chr", null);
        token.listaParametros.add(1);
    }


    public void insertarORD(){
        this.insertar("ord", CONSTANTES.TInt, null, null);
        TokenMetodos token = this.buscar("ord", null);
        token.listaParametros.add(4);
    }

    public TokenMetodos buscar(String nombre, String clase){
        for (int i =0; i<tablaTokenMetodos.size();i++){
            TokenMetodos token = tablaTokenMetodos.get(i);
            if ((token.tok.getText().equals(nombre)) && (nombre.equals("chr"))){
                return tablaTokenMetodos.get(i);
            }

            if ((token.tok.getText().equals(nombre)) && (nombre.equals("ord"))){
                return tablaTokenMetodos.get(i);
            }

            if((token.tok.getText().equals(nombre)) && (token.clase.equals(clase))){
                return tablaTokenMetodos.get(i);
            }
        }
        return null;
    }

    public void imprimir (){
        System.out.println("----- Tabla de métodos -----");
        System.out.println("Hay " + tablaTokenMetodos.size() + " métodos");
        for(int i=0; i<tablaTokenMetodos.size();i++){
            TokenMetodos token = tablaTokenMetodos.get(i);
            System.out.println("Nombre: " + token.tok.getText());
            if (token.tok.getType() == 0) System.out.println("\tTipo: Indefinido");
            else if (token.tok.getType() == CONSTANTES.TInt)System.out.println("Tipo: Integer");
            else if (token.tok.getType()  == CONSTANTES.TString)System.out.println("Tipo: String");
            else if (token.tok.getType() == CONSTANTES.TBoolean)System.out.println("Tipo: Boolean");
            else if (token.tok.getType()  == CONSTANTES.TChar)System.out.println("Tipo: Char");
            System.out.println("Clase: " + token.clase + "\n");
        }

    }
}
