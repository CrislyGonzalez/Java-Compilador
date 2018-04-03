package contextual;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;


public class TokenClase {
    public ParserRuleContext declaracion;
    public int nivel;
    public Token tok;
    public ArrayList<TokenObj> variablesClase;
    public ArrayList<TokenMetodos> metodosClase;

    public TokenClase(Token token, int nivel, ParserRuleContext declaracion)
    {
        this.tok = token;
        this.nivel = nivel;
        this.declaracion = declaracion;
    }



    public void addVariables(TokenObj var){
        variablesClase.add(var);
    }

    public void variablesExtendidas(TablaSimbolos extendidos){
        for (int i = 0; i < extendidos.tablaGeneralTokens.size(); i++)
            variablesClase.add(extendidos.tablaGeneralTokens.get(i));
    }




}
