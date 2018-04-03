package contextual;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenMetodos {

    public int nivel;// si es 1 es dentro de la clase y si es 0 metodo fuera de clase global
    public Token tok;
    public ArrayList<Integer> listaParametros;
    public ParserRuleContext decl;
    public String clase;


    public TokenMetodos(Token t, int nivel, ParserRuleContext dec, String clase) {
        this.tok= t;
        this.nivel = nivel;
        this.decl = dec;
        this.clase = clase;
        listaParametros = new ArrayList<>();
    }
}
