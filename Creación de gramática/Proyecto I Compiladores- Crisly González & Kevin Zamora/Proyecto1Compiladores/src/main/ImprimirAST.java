package main;

import generated.ParserMiniJava;
import generated.ParserMiniJavaBaseVisitor;

/**
 * Created by Zamora on 15/09/2016.
 */
public class ImprimirAST extends ParserMiniJavaBaseVisitor<Object>{

    private int cont = 0;

    private void printTab(int n){
        for (int num = n; num != 0; num--)
            if (num == 1) {
                //System.out.print(" |--");
                Main.programa.appendASTArea(" |--");
            }
            else {
                //System.out.print("    ");
                Main.programa.appendASTArea("    ");
            }

        //System.out.print(">");
        Main.programa.appendASTArea(">");
    }


    @Override
    public Object visitGoalAST(ParserMiniJava.GoalASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.mainClass());
        for (int i = 0; i < ctx.typeDeclaration().size(); i++)
            visit(ctx.typeDeclaration(i));
        visit(ctx.EOF());
        cont--;

        return null;
    }


    @Override
    public Object visitMainClassAST(ParserMiniJava.MainClassASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        //visit(ctx.CLASS());
        //visit(ctx.IDENTIFIER(0));
        printTab(cont);
        //System.out.println(ctx.CLASS().getSymbol().getText());
        Main.programa.appendASTArea(ctx.CLASS().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER(0).getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER(0).getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.VOID().getSymbol().getText());
        Main.programa.appendASTArea(ctx.VOID().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.MAIN().getSymbol().getText());
        Main.programa.appendASTArea(ctx.MAIN().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER(1).getSymbol().getText() + ctx.CORIZQ().getSymbol().getText() + ctx.CORDER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER(1).getSymbol().getText() + ctx.CORIZQ().getSymbol().getText() + ctx.CORDER().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER(2).getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER(2).getSymbol().getText() + "\n");
        for (int i = 0; i < ctx.statement().size(); i++)
            visit(ctx.statement(i));
        cont--;

        return null;
    }


    @Override
    public Object visitSinExtendsAST(ParserMiniJava.SinExtendsASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.classDeclaration());
        cont--;

        return null;
    }


    @Override
    public Object visitConExtendsAST(ParserMiniJava.ConExtendsASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.classExtendsDeclaration());
        cont--;

        return null;
    }


    @Override
    public Object visitClassAST(ParserMiniJava.ClassASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.CLASS().getSymbol().getText());
        Main.programa.appendASTArea(ctx.CLASS().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        for (int i = 0; i < ctx.varDeclaration().size(); i++)
            visit(ctx.varDeclaration(i));
        for (int i = 0; i < ctx.methodDeclaration().size(); i++)
            visit(ctx.methodDeclaration(i));
        cont--;

        return null;
    }


    @Override
    public Object visitExtendsAST(ParserMiniJava.ExtendsASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.CLASS().getSymbol().getText());
        Main.programa.appendASTArea(ctx.CLASS().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER(0).getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER(0).getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.EXTENDS().getSymbol().getText());
        Main.programa.appendASTArea(ctx.EXTENDS().getSymbol().getText() + "\n");
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER(1).getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER(1).getSymbol().getText() + "\n");
        for (int i = 0; i < ctx.varDeclaration().size(); i++)
            visit(ctx.varDeclaration(i));
        for (int i = 0; i < ctx.methodDeclaration().size(); i++)
            visit(ctx.methodDeclaration(i));
        cont--;

        return null;
    }


    @Override
    public Object visitVarAST(ParserMiniJava.VarASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.type());
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitMethodAST(ParserMiniJava.MethodASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.type());
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        if (ctx.formalParameterList() != null)
            visit(ctx.formalParameterList());
        for (int i = 0; i < ctx.varDeclaration().size(); i++)
            visit(ctx.varDeclaration(i));
        for (int i = 0; i < ctx.statement().size(); i++)
            visit(ctx.statement(i));
        printTab(cont);
        //System.out.println(ctx.RETURN().getSymbol().getText());
        Main.programa.appendASTArea(ctx.RETURN().getSymbol().getText() + "\n");
        visit(ctx.expression());
        cont--;

        return null;
    }


    @Override
    public Object visitParameterListAST(ParserMiniJava.ParameterListASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.formalParameter());
        for (int i = 0; i < ctx.formalParameterRest().size(); i++)
            visit(ctx.formalParameterRest(i));
        cont--;

        return null;
    }


    @Override
    public Object visitParameterAST(ParserMiniJava.ParameterASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.type());
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitParameterRestAST(ParserMiniJava.ParameterRestASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.formalParameter());
        cont--;

        return null;
    }


    @Override
    public Object visitSimpleTypeAST(ParserMiniJava.SimpleTypeASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.simpleType());
        cont--;

        return null;
    }


    @Override
    public Object visitArrayTypeAST(ParserMiniJava.ArrayTypeASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.arrayType());
        cont--;

        return null;
    }


    @Override
    public Object visitIdTypeAST(ParserMiniJava.IdTypeASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitCharTypeAST(ParserMiniJava.CharTypeASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.CHAR().getSymbol().getText());
        Main.programa.appendASTArea(ctx.CHAR().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitBooleanTypeAST(ParserMiniJava.BooleanTypeASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.BOOLEAN().getSymbol().getText());
        Main.programa.appendASTArea(ctx.BOOLEAN().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitIntegerTypeAST(ParserMiniJava.IntegerTypeASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.INT().getSymbol().getText());
        Main.programa.appendASTArea(ctx.INT().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitArrayAST(ParserMiniJava.ArrayASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.simpleType());
        printTab(cont);
        //System.out.println(ctx.CORIZQ().getSymbol().getText() + ctx.CORDER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.CORIZQ().getSymbol().getText() + ctx.CORDER().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitBlockStat(ParserMiniJava.BlockStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.block());
        cont--;

        return null;
    }


    @Override
    public Object visitAssignStat(ParserMiniJava.AssignStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.assignmentStatement());
        cont--;

        return null;
    }


    @Override
    public Object visitArrayStat(ParserMiniJava.ArrayStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.arrayAssignmentStatement());
        cont--;

        return null;
    }


    @Override
    public Object visitIfStat(ParserMiniJava.IfStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.ifStatement());
        cont--;

        return null;
    }


    @Override
    public Object visitWhileStat(ParserMiniJava.WhileStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.whileStatement());
        cont--;

        return null;
    }


    @Override
    public Object visitPrintStat(ParserMiniJava.PrintStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.printStatement());
        cont--;

        return null;
    }


    @Override
    public Object visitReadStat(ParserMiniJava.ReadStatContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.readStatement());
        cont--;

        return null;
    }


    @Override
    public Object visitBlockAST(ParserMiniJava.BlockASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.statement().size(); i++)
            visit(ctx.statement(i));
        cont--;

        return null;
    }


    @Override
    public Object visitVarAssignAST(ParserMiniJava.VarAssignASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        visit(ctx.expression());
        cont--;

        return null;
    }


    @Override
    public Object visitArrayAssignAST(ParserMiniJava.ArrayAssignASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        for (int i = 0; i < ctx.expression().size(); i++)
            visit(ctx.expression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitIfAST(ParserMiniJava.IfASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        //printTab(cont);
        ////System.out.println(ctx.IF().getSymbol().getText());
        visit(ctx.expression());
        visit(ctx.statement(0));
        if (ctx.ELSE() != null) {
            printTab(cont);
            //System.out.println(ctx.ELSE().getSymbol().getText());
            Main.programa.appendASTArea(ctx.ELSE().getSymbol().getText() + "\n");
            visit(ctx.statement(1));
        }
        cont--;

        return null;
    }


    @Override
    public Object visitWhileAST(ParserMiniJava.WhileASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.expression());
        visit(ctx.statement());
        cont--;

        return null;
    }


    @Override
    public Object visitPrintAST(ParserMiniJava.PrintASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.expression());
        cont--;

        return null;
    }


    @Override
    public Object visitReadAST(ParserMiniJava.ReadASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        if (ctx.IDENTIFIER() != null) {
            printTab(cont);
            //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
            Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        }
        cont--;

        return null;
    }


    @Override
    public Object visitAndExpre(ParserMiniJava.AndExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.andExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitCompExpre(ParserMiniJava.CompExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.compareExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitPlusExpre(ParserMiniJava.PlusExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.plusExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitMinusExpre(ParserMiniJava.MinusExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.minusExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitTimesExpre(ParserMiniJava.TimesExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.timesExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitLookupExpre(ParserMiniJava.LookupExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.arrayLookup());
        cont--;

        return null;
    }


    @Override
    public Object visitLengthExpre(ParserMiniJava.LengthExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.arrayLength());
        cont--;

        return null;
    }



    @Override
    public Object visitMesageExpre(ParserMiniJava.MesageExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.messageSend());
        cont--;

        return null;
    }


    @Override
    public Object visitPrimExpre(ParserMiniJava.PrimExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.primaryExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitAndAST(ParserMiniJava.AndASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.primaryExpression().size(); i++)
            visit(ctx.primaryExpression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitCompareAST(ParserMiniJava.CompareASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.primaryExpression().size(); i++)
            visit(ctx.primaryExpression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitPlusAST(ParserMiniJava.PlusASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.primaryExpression().size(); i++)
            visit(ctx.primaryExpression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitMinusAST(ParserMiniJava.MinusASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.primaryExpression().size(); i++)
            visit(ctx.primaryExpression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitTimesAST(ParserMiniJava.TimesASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.primaryExpression().size(); i++)
            visit(ctx.primaryExpression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitLookupAST(ParserMiniJava.LookupASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        for (int i = 0; i < ctx.primaryExpression().size(); i++)
            visit(ctx.primaryExpression(i));
        cont--;

        return null;
    }


    @Override
    public Object visitLengthAST(ParserMiniJava.LengthASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.primaryExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitMessageAST(ParserMiniJava.MessageASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.primaryExpression());
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        if (ctx.expressionList() != null)
            visit(ctx.expressionList());
        cont--;

        return null;
    }


    @Override
    public Object visitExpreListAST(ParserMiniJava.ExpreListASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.expression());
        for (int i = 0; i < ctx.expressionRest().size(); i++)
            visit(ctx.expressionRest(i));
        cont--;

        return null;
    }


    @Override
    public Object visitExpreRestAST(ParserMiniJava.ExpreRestASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.expression());
        cont--;

        return null;
    }


    @Override
    public Object visitIntExpre(ParserMiniJava.IntExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.INTEGERLITERAL().getSymbol().getText());
        Main.programa.appendASTArea(ctx.INTEGERLITERAL().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitCharExpre(ParserMiniJava.CharExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.CHARLITERAL().getSymbol().getText());
        Main.programa.appendASTArea(ctx.CHARLITERAL().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitStringExpre(ParserMiniJava.StringExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.STRINGLITERAL().getSymbol().getText());
        Main.programa.appendASTArea(ctx.STRINGLITERAL().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitTrueExpre(ParserMiniJava.TrueExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        /*cont++;
        printTab(cont);
        //System.out.println(ctx.TRUE().getSymbol().getText());
        cont--;*/

        return null;
    }


    @Override
    public Object visitFalseExpre(ParserMiniJava.FalseExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        /*cont++;
        printTab(cont);
        //System.out.println(ctx.FALSE().getSymbol().getText());
        cont--;*/

        return null;
    }


    @Override
    public Object visitIdExpre(ParserMiniJava.IdExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitThisExpre(ParserMiniJava.ThisExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        /*cont++;
        printTab(cont);
        //System.out.println(ctx.THIS().getSymbol().getText());
        cont--;*/

        return null;
    }


    @Override
    public Object visitArrayAllocExpre(ParserMiniJava.ArrayAllocExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.arrayAllocationExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitAllocExpre(ParserMiniJava.AllocExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.allocationExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitNotExpre(ParserMiniJava.NotExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.notExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitBracketExpre(ParserMiniJava.BracketExpreContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.bracketExpression());
        cont--;

        return null;
    }


    @Override
    public Object visitArrayAllocationAST(ParserMiniJava.ArrayAllocationASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.simpleType());
        visit(ctx.expression());
        cont--;

        return null;
    }


    @Override
    public Object visitAllocationAST(ParserMiniJava.AllocationASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        printTab(cont);
        //System.out.println(ctx.IDENTIFIER().getSymbol().getText());
        Main.programa.appendASTArea(ctx.IDENTIFIER().getSymbol().getText() + "\n");
        cont--;

        return null;
    }


    @Override
    public Object visitNotAST(ParserMiniJava.NotASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.expression());
        cont--;

        return null;
    }


    @Override
    public Object visitBracketAST(ParserMiniJava.BracketASTContext ctx) {
        printTab(cont);
        //System.out.println(ctx.getClass().getSimpleName());
        Main.programa.appendASTArea(ctx.getClass().getSimpleName() + "\n");

        cont++;
        visit(ctx.expression());
        cont--;

        return null;
    }
}