package contextual;


import contextual.*;
import generated.ParserMiniJava;
import generated.ParserMiniJavaBaseVisitor;
import main.*;

public class AnalisisContextual extends ParserMiniJavaBaseVisitor<Object> {
    public TablaClases tablaClases = new TablaClases();
    public TablaMetodos tablaMetodosInstance = new TablaMetodos();
    public TablaSimbolos tablaSimbolos = new TablaSimbolos();
    public String claseActual;
    public String metodoActual;
    public String claseLlamada;
    public String metodoLlamado;
    public String identicadorGlobal;
    private int lineaIdent, columnaIdent;
    private int contadorParametros;
    public int erroresContextuales = 0;

    @Override public Object visitGoalAST(ParserMiniJava.GoalASTContext ctx) {
        tablaMetodosInstance.insertarCHR();
        tablaMetodosInstance.insertarORD();
        for (int i = 0; i < ctx.typeDeclaration().size(); i++)
            visit(ctx.typeDeclaration(i));
        visit(ctx.mainClass());

        return null;
    }


    @Override public Object visitMainClassAST(ParserMiniJava.MainClassASTContext ctx) {
        String identClase = (String) visit(ctx.identifier(0));
        tablaClases.insertar(CONSTANTES.TClass, identClase, ctx);
        claseActual = identClase;

        tablaSimbolos.openScope();
        tablaSimbolos.openScope();

        String identParametro = (String) visit(ctx.identifier(1));

        tablaSimbolos.insertarSimbolos(identParametro, CONSTANTES.TArregloString, ctx, null/*claseActual*/);
        //Object result = null;
        for (int i = 0; i < ctx.statement().size(); i++)
            visit(ctx.statement(i));

        tablaSimbolos.closeScope();
        tablaSimbolos.closeScope();
        return null;
    }


    @Override public Object visitSinExtendsAST(ParserMiniJava.SinExtendsASTContext ctx) {
        Object result = null;
        visit(ctx.classDeclaration());
        return result;
    }


    @Override public Object visitConExtendsAST(ParserMiniJava.ConExtendsASTContext ctx) {
        Object result = null;
        visit(ctx.classExtendsDeclaration());
        return result;
    }


    @Override public Object visitClassAST(ParserMiniJava.ClassASTContext ctx) {
        String identClase = (String) visit(ctx.identifier());
        claseActual = identClase;
        TokenClase tClase = tablaClases.buscar2(identClase);
        if (tClase != null){
            System.out.println("Error en la línea: " + ctx.CLASS().getSymbol().getLine() + " columna: "
                    + ctx.CLASS().getSymbol().getCharPositionInLine() + ". La clase " + identClase + " ya ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + ctx.CLASS().getSymbol().getLine() + " columna: "
                    + ctx.CLASS().getSymbol().getCharPositionInLine() + ". La clase " + identClase + " ya ha sido declarada");
            erroresContextuales++;
        }
        else {
            tablaClases.insertar(CONSTANTES.TClass, identClase, ctx);
            tablaSimbolos.openScope();
            for (int i = 0; i < ctx.varDeclaration().size(); i++) {
                visit(ctx.varDeclaration(i));
            }
            //tablaSimbolos.imprimir();
            for (int i = 0; i < ctx.methodDeclaration().size(); i++) {
                visit(ctx.methodDeclaration(i));
            }
            //tablaSimbolos.imprimir();

            tablaSimbolos.closeScope();
        }
        return null;
    }


    @Override public Object visitExtendsAST(ParserMiniJava.ExtendsASTContext ctx) {
        String identClase = (String) visit(ctx.identifier(0));
        claseActual = identClase;
        TokenClase tClase = tablaClases.buscar2(identClase);
        if (tClase != null){
            System.out.println("Error en la línea: " + ctx.CLASS().getSymbol().getLine() + " columna: "
                    + ctx.CLASS().getSymbol().getCharPositionInLine() + ". La clase " + identClase + " ya ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + ctx.CLASS().getSymbol().getLine() + " columna: "
                    + ctx.CLASS().getSymbol().getCharPositionInLine() + ". La clase " + identClase + " ya ha sido declarada");
            erroresContextuales++;
        }
        else {
            String identClaseExt = (String) visit(ctx.identifier(1));     // Clase de la que extiende
            TokenClase claseExtendida = tablaClases.buscar2(identClaseExt);
            if (claseExtendida == null) {
                System.out.println("Error en la línea: " + ctx.EXTENDS().getSymbol().getLine() + " columna: "
                        + ctx.EXTENDS().getSymbol().getCharPositionInLine() + ". La clase " + identClaseExt + " no ha sido declarada");
                Main.programa.appendErrorArea("Error en la línea: " + ctx.EXTENDS().getSymbol().getLine() + " columna: "
                        + ctx.EXTENDS().getSymbol().getCharPositionInLine() + ". La clase " + identClaseExt + " no ha sido declarada");
                erroresContextuales++;
            } else {
                tablaSimbolos.insertarVarExtendidas(identClase, identClaseExt);
            }

            tablaClases.insertar(CONSTANTES.TClass, identClase, ctx);
            tablaSimbolos.openScope();
            for (int i = 0; i < ctx.varDeclaration().size(); i++)
                visit(ctx.varDeclaration(i));
            //tablaSimbolos.imprimir();
            for (int i = 0; i < ctx.methodDeclaration().size(); i++)
                visit(ctx.methodDeclaration(i));
            tablaSimbolos.closeScope();
        }
        return null;
    }


    @Override public Object visitVarAST(ParserMiniJava.VarASTContext ctx) {
        String identVariable = (String) visit(ctx.identifier());
        try {
            int tipo = (Integer) visit(ctx.type());
            TokenObj token = tablaSimbolos.buscar(identVariable);
            if (token == null){
                tablaSimbolos.insertarSimbolos(identVariable, tipo, ctx, claseActual);
            }
            else{
                System.out.println("Error en la línea: " + ctx.PUNTOYCOMA().getSymbol().getLine() + " columna: "
                        + ctx.PUNTOYCOMA().getSymbol().getCharPositionInLine() + ". La variable " + identVariable + " ya ha sido declarada");
                Main.programa.appendErrorArea("Error en la línea: " + ctx.PUNTOYCOMA().getSymbol().getLine() + " columna: "
                        + ctx.PUNTOYCOMA().getSymbol().getCharPositionInLine() + ". La variable " + identVariable + " ya ha sido declarada");
                erroresContextuales++;
            }
        }catch (Exception e){
            String tipo = (String) visit(ctx.type());
            TokenClase tClase = tablaClases.buscar2(tipo);
            if (tClase != null){
                tablaSimbolos.insertarSimbolos(identVariable, CONSTANTES.TClass, ctx, claseActual);
            }
            else{
                System.out.println("Error en la línea: " + ctx.PUNTOYCOMA().getSymbol().getLine() + " columna: "
                        + ctx.PUNTOYCOMA().getSymbol().getCharPositionInLine() + ". No existe el tipo " + tipo);
                Main.programa.appendErrorArea("Error en la línea: " + ctx.PUNTOYCOMA().getSymbol().getLine() + " columna: "
                        + ctx.PUNTOYCOMA().getSymbol().getCharPositionInLine() + ". No existe el tipo " + tipo);
                erroresContextuales++;
            }
        }
        return null;
    }


    @Override public Object visitMethodAST(ParserMiniJava.MethodASTContext ctx) {
        int tipo = 0;
        String identificador = (String) visit(ctx.identifier());
        metodoActual = identificador;
        TokenMetodos tMetodo = tablaMetodosInstance.buscar(identificador, claseActual);
        if (tMetodo != null) {
            System.out.println("Error en la línea: " + ctx.PUBLIC().getSymbol().getLine() + " columna: "
                    + ctx.PUBLIC().getSymbol().getCharPositionInLine() + ". El método " + identificador + " ya ha sido declarado");
            Main.programa.appendErrorArea("Error en la línea: " + ctx.PUBLIC().getSymbol().getLine() + " columna: "
                    + ctx.PUBLIC().getSymbol().getCharPositionInLine() + ". El método " + identificador + " ya ha sido declarado");
            erroresContextuales++;
        }else {
            try {
                tipo = (Integer) visit(ctx.type());
                tablaMetodosInstance.insertar(identificador, tipo, ctx, claseActual);
            } catch (Exception e) {
                String tipoClase = (String) visit(ctx.type());
                TokenClase tClase = tablaClases.buscar2(tipoClase);
                if (tClase != null) {
                    tablaMetodosInstance.insertar(identificador, CONSTANTES.TClass, ctx, claseActual);
                } else {
                    System.out.println("Error en la línea: " + ctx.PUBLIC().getSymbol().getLine() + " columna: "
                            + ctx.PUBLIC().getSymbol().getCharPositionInLine() + ". No existe el tipo " + tipoClase);
                    Main.programa.appendErrorArea("Error en la línea: " + ctx.PUBLIC().getSymbol().getLine() + " columna: "
                            + ctx.PUBLIC().getSymbol().getCharPositionInLine() + ". No existe el tipo " + tipoClase);
                    erroresContextuales++;
                }
            }

            tablaSimbolos.openScope();
            if (ctx.formalParameterList() != null) {
                visit(ctx.formalParameterList());
            }

            //System.out.println("Hay " + ctx.varDeclaration().size() + " declaraciones");
            for (int i = 0; i < ctx.varDeclaration().size(); i++)
                visit(ctx.varDeclaration(i));

            for (int i = 0; i < ctx.statement().size(); i++)
                visit(ctx.statement(i));

            int tipoExpresion = (Integer) visit(ctx.expression());
            if (tipo != tipoExpresion) {
                System.out.println("Error en la línea: " + ctx.RETURN().getSymbol().getLine() + " columna: "
                        + ctx.RETURN().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                        + imprimirTipo(tipo) + " y " + imprimirTipo(tipoExpresion));
                Main.programa.appendErrorArea("Error en la línea: " + ctx.RETURN().getSymbol().getLine() + " columna: "
                        + ctx.RETURN().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                        + imprimirTipo(tipo) + " y " + imprimirTipo(tipoExpresion));
                erroresContextuales++;
            }
            //tablaSimbolos.imprimir();
            tablaSimbolos.closeScope();
        }
        return null;
    }


    @Override public Object visitParameterListAST(ParserMiniJava.ParameterListASTContext ctx) {
        visit(ctx.formalParameter());
        for (int i = 0; i < ctx.formalParameterRest().size(); i++)
            visit(ctx.formalParameterRest(i));
        return null;
    }


    @Override public Object visitParameterAST(ParserMiniJava.ParameterASTContext ctx) {
        String identParametro = (String) visit(ctx.identifier());
        TokenObj token = tablaSimbolos.buscar(identParametro);
        TokenMetodos tMetodo = tablaMetodosInstance.buscar(metodoActual,claseActual);

        if (token != null){
            System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent + ". La variable "
                    + identParametro + " ya ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent + ". La variable "
                    + identParametro + " ya ha sido declarada");
            erroresContextuales++;
            return null;
        }
        try {
            int tipo = (Integer) visit(ctx.type());
            tablaSimbolos.insertarSimbolos(identParametro, tipo, ctx, /*null*/claseActual);
            tMetodo.listaParametros.add(tipo);
        } catch (Exception e) {
            String tipo = (String) visit(ctx.type());
            TokenClase tClase = tablaClases.buscar2(tipo);
            if (tClase != null) {
                tablaSimbolos.insertarSimbolos(identParametro, CONSTANTES.TClass, ctx, /*null*/claseActual);
                tMetodo.listaParametros.add(CONSTANTES.TClass);
            } else {
                System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent + ". La clase "
                        + tipo + " no ha sido declarada");
                Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent + ". La clase "
                        + tipo + " no ha sido declarada");
                erroresContextuales++;
            }
        }
        return null;
    }


    @Override public Object visitParameterRestAST(ParserMiniJava.ParameterRestASTContext ctx)
    {
        return visit(ctx.formalParameter());
    }


    @Override public Object visitSimpleTypeAST(ParserMiniJava.SimpleTypeASTContext ctx)
    {
        return visit(ctx.simpleType());
    }


    @Override public Object visitArrayTypeAST(ParserMiniJava.ArrayTypeASTContext ctx) {
        return visit(ctx.arrayType());
    }


    @Override public Object visitIdTypeAST(ParserMiniJava.IdTypeASTContext ctx) {
        return  visit(ctx.identifier());
    }


    @Override public Object visitCharTypeAST(ParserMiniJava.CharTypeASTContext ctx) {
        return CONSTANTES.TChar;
    }


    @Override public Object visitBooleanTypeAST(ParserMiniJava.BooleanTypeASTContext ctx)
    {
        return CONSTANTES.TBoolean;
    }


    @Override public Object visitIntegerTypeAST(ParserMiniJava.IntegerTypeASTContext ctx) {
        return CONSTANTES.TInt;
    }


    @Override public Object visitArrayAST(ParserMiniJava.ArrayASTContext ctx) {
        int tipoArreglo = (Integer) visit(ctx.simpleType());
        tipoArreglo += 5;
        return tipoArreglo;
    }


    @Override public Object visitBlockStat(ParserMiniJava.BlockStatContext ctx)
    {
        return visit(ctx.block());
    }


    @Override public Object visitAssignStat(ParserMiniJava.AssignStatContext ctx)
    {
        return visit(ctx.assignmentStatement());
    }


    @Override public Object visitArrayStat(ParserMiniJava.ArrayStatContext ctx)
    {
        return visit(ctx.arrayAssignmentStatement());
    }


    @Override public Object visitIfStat(ParserMiniJava.IfStatContext ctx)
    {
        return visit(ctx.ifStatement());
    }


    @Override public Object visitWhileStat(ParserMiniJava.WhileStatContext ctx)
    {
        return visit(ctx.whileStatement());
    }


    @Override public Object visitPrintStat(ParserMiniJava.PrintStatContext ctx)
    {
        return visit(ctx.printStatement());
    }


    @Override public Object visitReadStat(ParserMiniJava.ReadStatContext ctx)
    {
        return visit(ctx.readStatement());
    }


    @Override public Object visitBlockAST(ParserMiniJava.BlockASTContext ctx) {
        tablaSimbolos.openScope();
        for (int i= 0; i< ctx.statement().size();i++){
            visit(ctx.statement(i));
        }
        tablaSimbolos.closeScope();

        return null;
    }


    @Override public Object visitVarAssignAST(ParserMiniJava.VarAssignASTContext ctx) {
        String identVariable = (String) visit(ctx.identifier());
        TokenObj tVariable = tablaSimbolos.buscar(identVariable);
        int tipoVariable = 0;
        if (tVariable == null){
            System.out.println("Error en la línea: " + ctx.IGUAL().getSymbol().getLine() + " columna: "
                    + ctx.IGUAL().getSymbol().getCharPositionInLine() + ". La variable " + identVariable + " no ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + ctx.IGUAL().getSymbol().getLine() + " columna: "
                    + ctx.IGUAL().getSymbol().getCharPositionInLine() + ". La variable " + identVariable + " no ha sido declarada");
            erroresContextuales++;
        }
        else {
            ctx.identifier().decl = tVariable.decl;
            tipoVariable = tVariable.tok.getType();
            int tipoExpresion = (Integer) visit(ctx.expression());
            if (tipoVariable != tipoExpresion){
                System.out.println("Error en la línea: " + ctx.IGUAL().getSymbol().getLine() + " columna: "
                        + ctx.IGUAL().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                        + imprimirTipo(tipoVariable) + " y " + imprimirTipo(tipoExpresion));
                Main.programa.appendErrorArea("Error en la línea: " + ctx.IGUAL().getSymbol().getLine() + " columna: "
                        + ctx.IGUAL().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                        + imprimirTipo(tipoVariable) + " y " + imprimirTipo(tipoExpresion));
                erroresContextuales++;
            }
        }
        return null;
    }


    @Override public Object visitArrayAssignAST(ParserMiniJava.ArrayAssignASTContext ctx) {
        String identificador = (String) visit(ctx.identifier());
        TokenObj tVariable = tablaSimbolos.buscar(identificador);
        int tipoVariable = 0;
        if(tVariable == null) {
            System.out.println("Error en la línea: " + lineaIdent + " columna: "
                    + columnaIdent + ". La variable " + identificador + " no ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: "
                    + columnaIdent + ". La variable " + identificador + " no ha sido declarada");
            erroresContextuales++;
        }
        else {
            ctx.identifier().decl = tVariable.decl;
            if (!((tipoVariable >= 6) && (tipoVariable <= 9))) {
                System.out.println("Error en la línea: " + lineaIdent + " columna: "
                        + columnaIdent + ". La variable a la que intenta acceder no es un Array ");
                Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: "
                        + columnaIdent + ". La variable a la que intenta acceder no es un Array ");
                erroresContextuales++;
            }
            else {
                tipoVariable = tVariable.tok.getType() - 5;
                int expEntero = (Integer) visit(ctx.expression(0));     // Modificar esto
                if (expEntero != CONSTANTES.TInt) {
                    System.out.println("Error en la línea: " + ctx.CORDER().getSymbol().getLine() + " columna: "
                            + ctx.CORDER().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                            + imprimirTipo(CONSTANTES.TInt) + " y " + imprimirTipo(expEntero));
                    Main.programa.appendErrorArea("Error en la línea: " + ctx.CORDER().getSymbol().getLine() + " columna: "
                            + ctx.CORDER().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                            + imprimirTipo(CONSTANTES.TInt) + " y " + imprimirTipo(expEntero));
                    erroresContextuales++;
                } else {
                    int exprResult = (Integer) visit(ctx.expression(1));
                    if (exprResult != tipoVariable) {
                        System.out.println("Error en la línea: " + ctx.IGUAL().getSymbol().getLine() + " columna: "
                                + ctx.IGUAL().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                                + imprimirTipo(tipoVariable) + " y " + imprimirTipo(exprResult));
                        Main.programa.appendErrorArea("Error en la línea: " + ctx.IGUAL().getSymbol().getLine() + " columna: "
                                + ctx.IGUAL().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                                + imprimirTipo(tipoVariable) + " y " + imprimirTipo(exprResult));
                        erroresContextuales++;
                    }
                }
            }
        }
        return null;
    }


    @Override public Object visitIfAST(ParserMiniJava.IfASTContext ctx) {
        int tipoExpresion = (Integer) visit(ctx.expression());
        if(tipoExpresion != CONSTANTES.TBoolean){
            System.out.println("Error en la línea: " + ctx.IF().getSymbol().getLine() + " columna: "
                    + ctx.IF().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TBoolean) + " y " + imprimirTipo(tipoExpresion));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.IF().getSymbol().getLine() + " columna: "
                    + ctx.IF().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TBoolean) + " y " + imprimirTipo(tipoExpresion));
            erroresContextuales++;
        }
        visit(ctx.statement(0));

        if (ctx.ELSE()!= null) // si no viene el token else
            visit(ctx.statement(1)); //statement else
        return null;
    }


    @Override public Object visitWhileAST(ParserMiniJava.WhileASTContext ctx) {
        int tipoExpresion = (Integer) visit(ctx.expression());
        if(tipoExpresion != CONSTANTES.TBoolean){
            System.out.println("Error en la línea: " + ctx.WHILE().getSymbol().getLine() + " columna: "
                    + ctx.WHILE().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TBoolean) + " y " + imprimirTipo(tipoExpresion));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.WHILE().getSymbol().getLine() + " columna: "
                    + ctx.WHILE().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TBoolean) + " y " + imprimirTipo(tipoExpresion));
            erroresContextuales++;
        }
        visit(ctx.statement());
        return null;
    }


    @Override public Object visitPrintAST(ParserMiniJava.PrintASTContext ctx) {
        int tipoExpresion = (Integer) visit(ctx.expression());
        if((tipoExpresion != CONSTANTES.TInt) || (tipoExpresion != CONSTANTES.TChar) || (tipoExpresion != CONSTANTES.TString)){
            System.out.println("Error en la línea: " + ctx.PRINT().getSymbol().getLine() + " columna: "
                    + ctx.PRINT().getSymbol().getCharPositionInLine()
                    + ". Solo es posible imprimir variables de tipo Integer, Char o cadenas de texto");
            Main.programa.appendErrorArea("Error en la línea: " + ctx.PRINT().getSymbol().getLine() + " columna: "
                    + ctx.PRINT().getSymbol().getCharPositionInLine()
                    + ". Solo es posible imprimir variables de tipo Integer, Char o cadenas de texto");
            erroresContextuales++;
        }
        return null;
    }


    @Override public Object visitReadAST(ParserMiniJava.ReadASTContext ctx) {

        if(ctx.identifier()!= null){
            String nombre = (String) visit(ctx.identifier());
            TokenObj tVariable = tablaSimbolos.buscar(nombre);
            if(tVariable == null){
                System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                        + ". La variable " + nombre + " no ha sido declarada");
                Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                        + ". La variable " + nombre + " no ha sido declarada");
                erroresContextuales++;
            }
            ctx.identifier().decl = tVariable.decl;
        }

        return null;


    }


    @Override public Object visitAndExpre(ParserMiniJava.AndExpreContext ctx) {

        return visit(ctx.andExpression());
    }


    @Override public Object visitCompExpre(ParserMiniJava.CompExpreContext ctx) {
        return visit(ctx.compareExpression());
    }


    @Override public Object visitPlusExpre(ParserMiniJava.PlusExpreContext ctx)
    {
        return visit(ctx.plusExpression());
    }


    @Override public Object visitMinusExpre(ParserMiniJava.MinusExpreContext ctx)
    {
        return visit(ctx.minusExpression());
    }


    @Override public Object visitTimesExpre(ParserMiniJava.TimesExpreContext ctx) {
        return visit(ctx.timesExpression());
    }


    @Override public Object visitLookupExpre(ParserMiniJava.LookupExpreContext ctx) {

        return visit(ctx.arrayLookup());
    }


    @Override public Object visitLengthExpre(ParserMiniJava.LengthExpreContext ctx) {

        return visit(ctx.arrayLength());
    }


    @Override public Object visitMesageExpre(ParserMiniJava.MesageExpreContext ctx) {

        return visit(ctx.messageSend());
    }


    @Override public Object visitPrimExpre(ParserMiniJava.PrimExpreContext ctx) {
        return visit(ctx.primaryExpression());
    }


    @Override public Object visitAndAST(ParserMiniJava.AndASTContext ctx) {
        int tipoExpression1 = (Integer) visit(ctx.primaryExpression(0));
        int tipoExpression2 = (Integer) visit(ctx.primaryExpression(1));
        if (tipoExpression1 != tipoExpression2){
            System.out.println("Error en la línea: " + ctx.AND().getSymbol().getLine() + " columna: "
                    + ctx.AND().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.AND().getSymbol().getLine() + " columna: "
                    + ctx.AND().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        return tipoExpression1;
    }


    @Override public Object visitCompareAST(ParserMiniJava.CompareASTContext ctx) {
        int tipoExpression1 = (Integer) visit(ctx.primaryExpression(0));
        int tipoExpression2 = (Integer) visit(ctx.primaryExpression(1));
        if (tipoExpression1 != tipoExpression2 ){
            System.out.println("Error en la línea: " + ctx.MENORQUE().getSymbol().getLine() + " columna: "
                    + ctx.MENORQUE().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.MENORQUE().getSymbol().getLine() + " columna: "
                    + ctx.MENORQUE().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        /*else if (tipoExpression1 != CONSTANTES.TBoolean){
            System.out.println("Error en la línea: " + ctx.MENORQUE().getSymbol().getLine() + " columna: "
                    + ctx.MENORQUE().getSymbol().getCharPositionInLine() + ". Tipo de expresión incompatible entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(CONSTANTES.TBoolean));
            return CONSTANTES.TIndefinido;
        }*/
        else {
            return tipoExpression1;
        }
    }


    @Override public Object visitPlusAST(ParserMiniJava.PlusASTContext ctx) {
        int tipoExpression1 = (Integer) visit(ctx.primaryExpression(0));
        int tipoExpression2 = (Integer) visit(ctx.primaryExpression(1));
        if (tipoExpression1 != tipoExpression2) {
            System.out.println("Error en la línea: " + ctx.MAS().getSymbol().getLine() + " columna: "
                    + ctx.MAS().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.MAS().getSymbol().getLine() + " columna: "
                    + ctx.MAS().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        return tipoExpression1;
    }


    @Override public Object visitMinusAST(ParserMiniJava.MinusASTContext ctx) {
        int tipoExpression1 = (Integer) visit(ctx.primaryExpression(0));
        int tipoExpression2 = (Integer) visit(ctx.primaryExpression(1));
        if (tipoExpression1 != tipoExpression2) {
            System.out.println("Error en la línea: " + ctx.MENOS().getSymbol().getLine() + " columna: "
                    + ctx.MENOS().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.MENOS().getSymbol().getLine() + " columna: "
                    + ctx.MENOS().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(tipoExpression1) + " y " + imprimirTipo(tipoExpression2));
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        return tipoExpression1;
    }


    @Override public Object visitTimesAST(ParserMiniJava.TimesASTContext ctx) {
        int tipoExpresion1 = (Integer) visit(ctx.primaryExpression(0));
        int tipoExpresion2 = (Integer) visit(ctx.primaryExpression(1));
        if (tipoExpresion1 != tipoExpresion2) {
            System.out.println("Error en la línea: " + ctx.MULT().getSymbol().getLine() + " columna: "
                    + ctx.MULT().getSymbol().getCharPositionInLine() + "Tipos incompatibles entre "
                    + imprimirTipo(tipoExpresion1) + " y " + imprimirTipo(tipoExpresion2));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.MULT().getSymbol().getLine() + " columna: "
                    + ctx.MULT().getSymbol().getCharPositionInLine() + "Tipos incompatibles entre "
                    + imprimirTipo(tipoExpresion1) + " y " + imprimirTipo(tipoExpresion2));
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        return tipoExpresion1;
    }

    @Override public Object visitLookupAST(ParserMiniJava.LookupASTContext ctx) {
        int tipoExpresion1 = (Integer) visit(ctx.primaryExpression(0));
        if ((tipoExpresion1 >= 6) && (tipoExpresion1 <= 9)) {
            int tipoExpresion2 = (Integer) visit(ctx.primaryExpression(1));
            if (tipoExpresion2 != CONSTANTES.TInt) {
                System.out.println("Error en la línea: " + ctx.CORDER().getSymbol().getLine() + " columna: "
                        + ctx.CORDER().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                        + imprimirTipo(CONSTANTES.TInt) + " y " + imprimirTipo(tipoExpresion2));
                Main.programa.appendErrorArea("Error en la línea: " + ctx.CORDER().getSymbol().getLine() + " columna: "
                        + ctx.CORDER().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                        + imprimirTipo(CONSTANTES.TInt) + " y " + imprimirTipo(tipoExpresion2));
                erroresContextuales++;
                return CONSTANTES.TIndefinido;
            }
            return (tipoExpresion1 - 5);
        }
        System.out.println("Error en la línea: " + ctx.CORIZQ().getSymbol().getLine() + " columna: "
                + ctx.CORIZQ().getSymbol().getCharPositionInLine() + ". La variable a la que intenta acceder no es un Array ");
        Main.programa.appendErrorArea("Error en la línea: " + ctx.CORIZQ().getSymbol().getLine() + " columna: "
                + ctx.CORIZQ().getSymbol().getCharPositionInLine() + ". La variable a la que intenta acceder no es un Array ");
        erroresContextuales++;
        return CONSTANTES.TIndefinido;
    }


    @Override public Object visitLengthAST(ParserMiniJava.LengthASTContext ctx) {
        int tipoExpresion = (Integer) visit(ctx.primaryExpression());
        if ((tipoExpresion >= 6) && (tipoExpresion <= 9)){
            return CONSTANTES.TInt;
        }
        System.out.println("Error en la línea: " + ctx.PUNTO().getSymbol().getLine() + " columna: " + ctx.PUNTO().getSymbol().getCharPositionInLine()
                + ". Una variable de tipo " + imprimirTipo(tipoExpresion) + " no cuenta con la propiedad 'length'");
        Main.programa.appendErrorArea("Error en la línea: " + ctx.PUNTO().getSymbol().getLine() + " columna: " + ctx.PUNTO().getSymbol().getCharPositionInLine()
                + ". Una variable de tipo " + imprimirTipo(tipoExpresion) + " no cuenta con la propiedad 'length'");
        erroresContextuales++;
        return CONSTANTES.TIndefinido;
    }


    @Override public Object visitMessageAST(ParserMiniJava.MessageASTContext ctx) {
        int isThis = (Integer) visit(ctx.primaryExpression());
        if (isThis != 10){
            String identClase = identicadorGlobal;
            TokenObj tInstancia = tablaSimbolos.buscar(identClase);
            if (tInstancia == null) {
                System.out.println("Error en la línea: " + lineaIdent + " columna: "
                        + columnaIdent + ". La variable " + identClase + " no ha sido declarada");
                Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: "
                        + columnaIdent + ". La variable " + identClase + " no ha sido declarada");
                erroresContextuales++;
                return CONSTANTES.TIndefinido;
            }
        }
        //System.out.println("La clase llamada es: " + claseLlamada);

        String identMetodo = (String) visit(ctx.identifier());
        metodoLlamado = identMetodo;
        TokenMetodos tMetodo =  tablaMetodosInstance.buscar(identMetodo, claseLlamada);
        if (tMetodo == null){
            System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                    + ". La clase " + claseLlamada + " no tiene el método " + identMetodo);
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                    + ". La clase " + claseLlamada + " no tiene el método " + identMetodo);
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        ctx.identifier().decl = tMetodo.decl;
        int tipoIdentificador = tMetodo.tok.getType();
        contadorParametros = 0;
        //System.out.println("El método " + identMetodo + " tiene " + tMetodo.listaParametros.size() + " parámetros");
        for (int i = 0; i < tMetodo.listaParametros.size(); i++){
            contadorParametros++;
        }
        if (contadorParametros > 0) {
            if (ctx.expressionList() != null) {
                visit(ctx.expressionList());
            } else {
                System.out.println("Error en la línea: " + ctx.PARIZQ().getSymbol().getLine() + " columna: "
                        + ctx.PARIZQ().getSymbol().getCharPositionInLine() + ". Carencia de parámetros");
                Main.programa.appendErrorArea("Error en la línea: " + ctx.PARIZQ().getSymbol().getLine() + " columna: "
                        + ctx.PARIZQ().getSymbol().getCharPositionInLine() + ". Carencia de parámetros");
                erroresContextuales++;
                return CONSTANTES.TIndefinido;
            }
        }
        else {
            if (ctx.expressionList() != null) {
                System.out.println("Error en la línea: " + ctx.PARIZQ().getSymbol().getLine() + " columna: "
                        + ctx.PARIZQ().getSymbol().getCharPositionInLine() + ". Exceso de parámetros");
                Main.programa.appendErrorArea("Error en la línea: " + ctx.PARIZQ().getSymbol().getLine() + " columna: "
                        + ctx.PARIZQ().getSymbol().getCharPositionInLine() + ". Exceso de parámetros");
                erroresContextuales++;
                return CONSTANTES.TIndefinido;
            }
        }
        return tipoIdentificador;
    }


    @Override public Object visitExpreListAST(ParserMiniJava.ExpreListASTContext ctx) {
        int contadorEnviados = 1;
        for (int i = 0; i < ctx.expressionRest().size(); i++){
            contadorEnviados++;
        }
        if (contadorEnviados != contadorParametros){
            System.out.println("Error en la línea: " + lineaIdent + ". Se esperaban " + contadorParametros + " parámetro(s)");
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + ". Se esperaban " + contadorParametros + " parámetro(s)");
            erroresContextuales++;
            return null;
        }
        int tipoParametro = (Integer) visit(ctx.expression());
        TokenMetodos t = tablaMetodosInstance.buscar(metodoLlamado, claseLlamada);
        int posicionArray = t.listaParametros.get(0);

        if(tipoParametro != posicionArray){
            System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                    + ". Tipos incompatibles entre " + imprimirTipo(posicionArray) + " y " + imprimirTipo(tipoParametro));
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                    + ". Tipos incompatibles entre " + imprimirTipo(posicionArray) + " y " + imprimirTipo(tipoParametro));
            erroresContextuales++;
        }

        for(int i = 0; i < ctx.expressionRest().size(); i++){
            posicionArray = t.listaParametros.get(i + 1);
            tipoParametro = (Integer) visit(ctx.expressionRest(i));
            if(tipoParametro != posicionArray){
                System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                        + ". Tipos incompatibles entre " + imprimirTipo(posicionArray) + " y " + imprimirTipo(tipoParametro));
                Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                        + ". Tipos incompatibles entre " + imprimirTipo(posicionArray) + " y " + imprimirTipo(tipoParametro));
                erroresContextuales++;
            }
        }
        return null;
    }


    @Override public Object visitExpreRestAST(ParserMiniJava.ExpreRestASTContext ctx)
    {
        return visit(ctx.expression());
    }


    @Override public Object visitIntExpre(ParserMiniJava.IntExpreContext ctx)
    {
        lineaIdent = ctx.INTEGERLITERAL().getSymbol().getLine();
        columnaIdent = ctx.INTEGERLITERAL().getSymbol().getCharPositionInLine();
        return CONSTANTES.TInt;
    }


    @Override public Object visitCharExpre(ParserMiniJava.CharExpreContext ctx)
    {
        lineaIdent = ctx.CHARLITERAL().getSymbol().getLine();
        columnaIdent = ctx.CHARLITERAL().getSymbol().getCharPositionInLine();
        return CONSTANTES.TChar;
    }


    @Override public Object visitStringExpre(ParserMiniJava.StringExpreContext ctx)
    {
        lineaIdent = ctx.STRINGLITERAL().getSymbol().getLine();
        columnaIdent = ctx.STRINGLITERAL().getSymbol().getLine();
        return CONSTANTES.TString;
    }


    @Override public Object visitTrueExpre(ParserMiniJava.TrueExpreContext ctx)
    {
        lineaIdent = ctx.TRUE().getSymbol().getLine();
        columnaIdent = ctx.TRUE().getSymbol().getLine();
        return CONSTANTES.TBoolean;
    }


    @Override public Object visitFalseExpre(ParserMiniJava.FalseExpreContext ctx)
    {
        lineaIdent = ctx.FALSE().getSymbol().getLine();
        columnaIdent = ctx.FALSE().getSymbol().getLine();
        return CONSTANTES.TBoolean;
    }


    @Override public Object visitIdExpre(ParserMiniJava.IdExpreContext ctx)
    {
        String identificador = (String) visit(ctx.identifier());
        TokenObj tIdent = tablaSimbolos.buscar(identificador);
        if (tIdent == null){
            System.out.println("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                    + ". La variable " + identificador + " no ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: " + columnaIdent
                    + ". La variable " + identificador + " no ha sido declarada");
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        else{
            ctx.identifier().decl = tIdent.decl;
            ParserMiniJava.VarASTContext declaracion = ((ParserMiniJava.VarASTContext) ctx.identifier().decl);
            try {
                claseLlamada = (String) visit(declaracion.type());
            }catch (Exception e){}
            int tipoIdent = tIdent.tok.getType();
            identicadorGlobal = identificador;
            return tipoIdent;
        }
    }


    @Override public Object visitThisExpre(ParserMiniJava.ThisExpreContext ctx) {
        claseLlamada = claseActual;
        return 10;
    }


    @Override public Object visitArrayAllocExpre(ParserMiniJava.ArrayAllocExpreContext ctx)
    {
        return visit(ctx.arrayAllocationExpression());
    }


    @Override public Object visitAllocExpre(ParserMiniJava.AllocExpreContext ctx)
    {
        return visit(ctx.allocationExpression());
    }


    @Override public Object visitNotExpre(ParserMiniJava.NotExpreContext ctx)
    {
        return visit(ctx.notExpression());
    }


    @Override public Object visitBracketExpre(ParserMiniJava.BracketExpreContext ctx)
    {
        return visit(ctx.bracketExpression());
    }


    @Override public Object visitArrayAllocationAST(ParserMiniJava.ArrayAllocationASTContext ctx)
    {
        int tipoArreglo = (Integer) visit(ctx.simpleType());
        tipoArreglo += 5;
        int tipoExpresion = (Integer) visit(ctx.expression());
        if (tipoExpresion != CONSTANTES.TInt){
            System.out.println("Error en la línea: " + ctx.CORIZQ().getSymbol().getLine() + " columna: "
                    + ctx.CORIZQ().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TInt) + " y " + imprimirTipo(tipoExpresion));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.CORIZQ().getSymbol().getLine() + " columna: "
                    + ctx.CORIZQ().getSymbol().getCharPositionInLine() + ". Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TInt) + " y " + imprimirTipo(tipoExpresion));
            erroresContextuales++;
        }
        return tipoArreglo;
    }


    @Override public Object visitAllocationAST(ParserMiniJava.AllocationASTContext ctx)
    {
        String identClase = (String) visit(ctx.identifier());
        TokenClase tClase = tablaClases.buscar2(identClase);
        if (tClase == null) {
            System.out.println("Error en la línea: " + lineaIdent + " columna: "
                    + columnaIdent + ". La clase " + identClase + " no ha sido declarada");
            Main.programa.appendErrorArea("Error en la línea: " + lineaIdent + " columna: "
                    + columnaIdent + ". La clase " + identClase + " no ha sido declarada");
            erroresContextuales++;
            return CONSTANTES.TIndefinido;
        }
        ctx.identifier().decl = tClase.declaracion;
        return tClase.tok.getType();
    }


    @Override public Object visitNotAST(ParserMiniJava.NotASTContext ctx)
    {
        int tipoExpresion = (Integer) visit(ctx.expression());
        if (tipoExpresion != CONSTANTES.TBoolean){
            System.out.println("Error en la línea: " + ctx.NOT().getSymbol().getLine() + " columna: "
                    + ctx.NOT().getSymbol().getCharPositionInLine() + "Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TBoolean) + " y " + imprimirTipo(tipoExpresion));
            Main.programa.appendErrorArea("Error en la línea: " + ctx.NOT().getSymbol().getLine() + " columna: "
                    + ctx.NOT().getSymbol().getCharPositionInLine() + "Tipos incompatibles entre "
                    + imprimirTipo(CONSTANTES.TBoolean) + " y " + imprimirTipo(tipoExpresion));
            erroresContextuales++;
        }
        return tipoExpresion;
    }


    @Override public Object visitBracketAST(ParserMiniJava.BracketASTContext ctx)
    {
        return visit(ctx.expression());
    }


    @Override public Object visitIdent(ParserMiniJava.IdentContext ctx)
    {
        identicadorGlobal = ctx.ID().getSymbol().getText();
        lineaIdent = ctx.ID().getSymbol().getLine();
        columnaIdent = ctx.ID().getSymbol().getCharPositionInLine();
        return ctx.ID().getSymbol().getText();
    }

    public String imprimirTipo(int t) {
        if (t == 1) return "Integer";
        else if (t == 2) return "String";
        else if (t == 3) return "Boolean";
        else if (t == 4) return "Character";
        else if (t == 5) return "Class";
        else if (t == 6) return "Array<Integer>";
        else if (t == 7) return "Array<String>";
        else if (t == 8) return "Array<Boolean>";
        else if (t == 9) return "Array<Char>";
        else if ((t < 1) || (t > 9)){
            return "Indefinido";
        }
        return "";
    }
}
