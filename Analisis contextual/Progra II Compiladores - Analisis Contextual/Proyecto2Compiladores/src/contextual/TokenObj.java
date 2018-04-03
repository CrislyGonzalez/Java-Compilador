package contextual;


import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class TokenObj {

    public Token tok;
    //public int tipo;
    public int nivel;
    //public String nombre;
    public ParserRuleContext decl;
    public String nombreClase;
    //public int arreglo;
    //public int constante;


    public TokenObj(Token t, int nivel,ParserRuleContext decl,String clase) {
        //Token token = new CommonToken(tipo,nombre);
        this.tok = t;
        //this.tipo = tipo;
        this.nivel = nivel;
        //this.nombre = nombre;
        this.decl = decl;
        this.nombreClase = clase;
    }
}
