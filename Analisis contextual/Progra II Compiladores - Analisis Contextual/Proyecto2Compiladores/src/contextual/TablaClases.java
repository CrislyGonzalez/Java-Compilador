package contextual;


import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class TablaClases {
     public ArrayList<TokenClase> tablaClases;
     int nivelTabla;

    public TablaClases()
    {

        this.tablaClases = new ArrayList<TokenClase>();
    }


    public void insertar(int tipo,String nombre ,ParserRuleContext decla)
    {
        TokenClase var = null;
        Token t;


        t = new CommonToken(tipo, nombre);
        var = new TokenClase(t, nivelTabla, decla);
        tablaClases.add(var);

    }


    public TokenClase buscar2(String nombre)
    {
        for (int i =0; i<tablaClases.size();i++){
            TokenClase token = tablaClases.get(i);


            if(token.tok.getText().equals( nombre)){
                return tablaClases.get(i);
            }
        }
        return null;
    }

    public boolean buscar(String nombre)
    {
        for (int i =0; i<tablaClases.size();i++){
            TokenClase token = tablaClases.get(i);


            if(token.tok.getText().equals( nombre)){
                return true;
            }
        }
        return false;
    }

    public void buscarVariable(String nombreClase, String nombreVar)
    {

    }

    public void imprimir()
    {
        System.out.println("----- Tabla de clases -----");
        for (int i = 0; i < tablaClases.size(); i++)
        {
            TokenClase s = (TokenClase) tablaClases.get(i);
            System.out.println("Nombre: " + s.tok.getText());
            if (s.tok.getType() == 0) System.out.println("\tTipo: Indefinido");
            else if (s.tok.getType() == 1) System.out.println("\tTipo: Integer\n");
            else if (s.tok.getType() == 2) System.out.println("\tTipo: String\n");
        }
    }


}
