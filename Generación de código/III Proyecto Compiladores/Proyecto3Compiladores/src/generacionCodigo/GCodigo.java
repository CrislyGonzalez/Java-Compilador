package generacionCodigo;


import com.sun.xml.internal.ws.org.objectweb.asm.*;
import contextual.*;
import generated.ParserMiniJava;
import generated.ParserMiniJavaBaseVisitor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class GCodigo extends ParserMiniJavaBaseVisitor<Object> implements Opcodes{
    public TablaClases tablaClases = new TablaClases();
    public TablaMetodos tablaMetodosInstance = new TablaMetodos();
    public TablaSimbolos tablaSimbolos = new TablaSimbolos();

    ClassWriter cw ;
    FieldVisitor fv;
    MethodVisitor mv;

    String claseActual;
    String metodoActual;
    String claseInstanciada;

    Boolean hayCondicion = false;
    Boolean hayComparacion = false;
    Boolean hayNegacion = false;

    Label salto1;
    Label salto2;

    ArrayList<Variable> variablesLocales;
    ArrayList<Variable> variablesGlobales = new ArrayList<>();
    ArrayList<Metodo> listaMetodos = new ArrayList<>();

    @Override public Object visitGoalAST(ParserMiniJava.GoalASTContext ctx) {
        for (int i = 0; i < ctx.typeDeclaration().size(); i++){
            visit(ctx.typeDeclaration(i));
        }
        visit(ctx.mainClass());
        return null;
    }


    @Override public Object visitMainClassAST(ParserMiniJava.MainClassASTContext ctx) {
        String nombreClase = (String) visit(ctx.identifier(0));
        claseActual = nombreClase;
        cw = new ClassWriter(0);
        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, nombreClase, null, "java/lang/Object", null);
        cw.visitSource(construirClaseGenerar(nombreClase), null);

        //CONTRUCTOR DE CLASE
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);  //System.out.println("ALOAD, 0");
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");     //System.out.println("INVOKESPECIAL, \"java/lang/Object\", \"<init>\", \"()V\")");
        mv.visitInsn(RETURN);       //System.out.println("RETURN");
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        //METODO PUBLIC STATIC VOID MAIN
        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        for (int i = 0; i < ctx.statement().size(); i++)
            visit(ctx.statement(i));

        mv.visitInsn(RETURN);       //System.out.println("RETURN)");
        mv.visitMaxs(99, 99);
        mv.visitEnd();

        //System.out.println('\n');
        //CIERRE DE CLASE
        cw.visitEnd();
        //return cw.toByteArray();

        escribirArchivo(nombreClase, cw.toByteArray());

        DynamicClassLoader loader = new DynamicClassLoader();
        Class<?> helloFactorialClass = loader.define(nombreClase, cw.toByteArray());

        Method method = null;
        try {
            method = helloFactorialClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            method.invoke(null, (Object) new String[] {});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            String [] cmd = {"javap","-v", nombreClase+".class"};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            //System.out.println (ioe);
        }

        return null;
    }


    @Override public Object visitSinExtendsAST(ParserMiniJava.SinExtendsASTContext ctx) {
        visit(ctx.classDeclaration());

        byte[] aux = (byte[]) visit(ctx.classDeclaration());
        escribirArchivo(claseActual, aux);

        return null;
    }


    @Override public Object visitConExtendsAST(ParserMiniJava.ConExtendsASTContext ctx) {
        visit(ctx.classExtendsDeclaration());

        byte[] aux = (byte[]) visit(ctx.classExtendsDeclaration());
        escribirArchivo(claseActual, aux);

        return null;
    }


    @Override public Object visitClassAST(ParserMiniJava.ClassASTContext ctx) {
        String nombreClase = (String) visit(ctx.identifier());
        claseActual = nombreClase;
        cw = new ClassWriter(0);
        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, nombreClase, null, "java/lang/Object", null);
        cw.visitSource(construirClaseGenerar(nombreClase), null);

        //CONTRUCTOR DE CLASE
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);      //System.out.println("ALOAD, 0");
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");     //System.out.println("INVOKESPECIAL, \"java/lang/Object\", \"<init>\", \"()V\")");
        mv.visitInsn(RETURN);           //System.out.println("RETURN");
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        for (int i = 0; i < ctx.varDeclaration().size(); i++) {
            visit(ctx.varDeclaration(i));
        }

        for (int i = 0; i < ctx.methodDeclaration().size(); i++) {
            visit(ctx.methodDeclaration(i));
        }

        //System.out.println('\n');
        //CIERRE DE CLASE
        cw.visitEnd();

        return cw.toByteArray();
    }


    @Override public Object visitExtendsAST(ParserMiniJava.ExtendsASTContext ctx) {
        String nombreClase = (String) visit(ctx.identifier(0));
        claseActual = nombreClase;
        String claseExtendida = (String) visit(ctx.identifier(1));

        cw = new ClassWriter(0);
        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, nombreClase, null, claseExtendida, null);
        cw.visitSource(construirClaseGenerar(nombreClase), null);

        //CONTRUCTOR DE CLASE
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);      //System.out.println("ALOAD, 0");
        mv.visitMethodInsn(INVOKESPECIAL, claseExtendida, "<init>", "()V");     //System.out.println("INVOKESPECIAL, " + claseExtendida + ", <init>, ()V");
        mv.visitInsn(RETURN);           //System.out.println("RETURN");
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        for (int i = 0; i < ctx.varDeclaration().size(); i++) {
            visit(ctx.varDeclaration(i));
        }

        for (int i = 0; i < ctx.methodDeclaration().size(); i++) {
            visit(ctx.methodDeclaration(i));
        }

        //System.out.println('\n');
        //CIERRE DE CLASE
        cw.visitEnd();

        return cw.toByteArray();
    }


    @Override public Object visitVarAST(ParserMiniJava.VarASTContext ctx) {
        String nombre = (String) visit(ctx.identifier());
        String tipo = (String) visit(ctx.type());

        try {
            variablesLocales.add(new Variable(null, nombre, tipo));
        } catch (NullPointerException e) {
            fv = cw.visitField(ACC_PUBLIC, nombre, tipo, null, null);
            fv.visitEnd();
            variablesGlobales.add(new Variable(claseActual, nombre, tipo));
        }
        return null;
    }


    @Override public Object visitMethodAST(ParserMiniJava.MethodASTContext ctx) {
        variablesLocales = new ArrayList<>();
        variablesLocales.add(new Variable(null, "this", claseActual));

        String params = "";
        String tipo = (String) visit(ctx.type());
        if(ctx.formalParameterList() != null){
            params = (String) visit(ctx.formalParameterList());
        }
        String descriptor = "(" + params + ")" + tipo;

        String nombreMetodo = (String) visit(ctx.identifier());
        mv = cw.visitMethod(ACC_PUBLIC, nombreMetodo, descriptor, null, null);  //System.out.println("ACC_PUBLIC "+nombreMetodo+", "+descriptor);
        mv.visitCode();

        listaMetodos.add(new Metodo(claseActual, nombreMetodo, descriptor));

        for (int i = 0; i < ctx.varDeclaration().size(); i++){
            visit(ctx.varDeclaration(i));
        }
        for (int e = 0; e < ctx.statement().size(); e++){
            visit(ctx.statement(e));
        }

        String retorno = (String) visit(ctx.expression());
        if ((retorno.equals("I")) || (retorno.equals("C")) || (retorno.equals("Z"))){
            mv.visitInsn(IRETURN);      //System.out.println("IRETURN");
        }
        else /*if ((retorno instanceof int[]))*/{
            mv.visitInsn(ARETURN);      //System.out.println("ARETURN");
        }

        mv.visitMaxs(99, variablesLocales.size());
        mv.visitEnd();

        variablesLocales = null;
        return null;
    }


    @Override public Object visitParameterListAST(ParserMiniJava.ParameterListASTContext ctx) {
        String tipo = (String) visit(ctx.formalParameter());
        String tipo2;

        for (int i = 0; i < ctx.formalParameterRest().size(); i++){
            tipo2 = (String) visit(ctx.formalParameterRest(i));
            tipo += tipo2;
        }
        return tipo;
    }


    @Override public Object visitParameterAST(ParserMiniJava.ParameterASTContext ctx) {
        String nombre = (String) visit(ctx.identifier());
        String tipo = (String) visit(ctx.type());
        variablesLocales.add(new Variable(null, nombre, tipo));
        return tipo;
    }


    @Override public Object visitParameterRestAST(ParserMiniJava.ParameterRestASTContext ctx)
    {
        return visit(ctx.formalParameter());
    }


    @Override public Object visitSimpleTypeAST(ParserMiniJava.SimpleTypeASTContext ctx)
    {
        return visit(ctx.simpleType());
    }


    @Override public Object visitArrayTypeAST(ParserMiniJava.ArrayTypeASTContext ctx)
    {
        return visit(ctx.arrayType());
    }


    @Override public Object visitIdTypeAST(ParserMiniJava.IdTypeASTContext ctx) {
        return  visit(ctx.identifier());
    }


    @Override public Object visitCharTypeAST(ParserMiniJava.CharTypeASTContext ctx) {
        return "C";
    }


    @Override public Object visitBooleanTypeAST(ParserMiniJava.BooleanTypeASTContext ctx)
    {
        return "Z";
    }


    @Override public Object visitIntegerTypeAST(ParserMiniJava.IntegerTypeASTContext ctx) {
        return "I";
    }


    @Override public Object visitArrayAST(ParserMiniJava.ArrayASTContext ctx) {
        String corchete = "[";
        String variable = corchete + (String) visit(ctx.simpleType()) ;

        return variable;
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
        for (int i =0; i<ctx.statement().size();i++){
            visit(ctx.statement(i));
        }
        return null ;
    }


    @Override public Object visitVarAssignAST(ParserMiniJava.VarAssignASTContext ctx) {
        String nombre = (String) visit(ctx.identifier());
        int posicion = indiceDe(nombre);
        String tipo = tipoDe(nombre);

        if (posicion == 99){
            mv.visitVarInsn(ALOAD, 0);          //System.out.println("ALOAD, 0");
            visit(ctx.expression());
            mv.visitFieldInsn(PUTFIELD, claseActual, nombre, tipo);    //System.out.println("PUTFIELD "+ nombre);
        }
        else {
            visit(ctx.expression());

            if (tipo.equals("I") || tipo.equals("C") || tipo.equals("Z")) {
                mv.visitVarInsn(ISTORE, posicion);
                //System.out.println("ISTORE, " + posicion);
            } else {
                mv.visitVarInsn(ASTORE, posicion);
                //System.out.println("ASTORE, " + posicion);
            }
        }
        return null;
    }


    @Override public Object visitArrayAssignAST(ParserMiniJava.ArrayAssignASTContext ctx) {
        String nombre = (String) visit(ctx.identifier());
        int posicion = this.indiceDe(nombre);
        String tipo = this.tipoDe(nombre);

        if (posicion == 99){
            mv.visitVarInsn(ALOAD, 0);          //System.out.println("ALOAD, 0");
            mv.visitFieldInsn(GETFIELD, claseActual, nombre, tipo);    //System.out.println("GETFIELD "+ nombre);
        }
        else {
            mv.visitVarInsn(ALOAD, posicion);
            //System.out.println("ALOAD, " + posicion);
        }
        visit(ctx.expression(0));
        visit(ctx.expression(1));

        switch (tipo){
            case "[I": mv.visitInsn(IASTORE);              //System.out.println("IASTORE");
                break;
            case "[C": mv.visitInsn(CASTORE);              //System.out.println("CASTORE");
                break;
            case "[Z": mv.visitInsn(BASTORE);              //System.out.println("BASTORE");
                break;
            default: break;
        }

        return null;
    }


    @Override public Object visitIfAST(ParserMiniJava.IfASTContext ctx) {
        hayCondicion = true;
        salto1 = new Label();
        salto2 = new Label();

        visit(ctx.expression());

        hayCondicion = false;

        visit(ctx.statement(0));

        mv.visitJumpInsn(GOTO, salto2);     //System.out.println("GOTO, salto2");

        mv.visitLabel(salto1);              //System.out.println("salto1");
        if (ctx.ELSE() != null){
            visit(ctx.statement(1));
        }

        mv.visitLabel(salto2);              //System.out.println("salto2");

        return null;
    }


    @Override public Object visitWhileAST(ParserMiniJava.WhileASTContext ctx) {
        hayCondicion = true;
        salto1 = new Label();
        salto2 = new Label();

        mv.visitLabel(salto2);              //System.out.println("salto2");
        visit(ctx.expression());

        hayCondicion = false;

        visit(ctx.statement());
        mv.visitJumpInsn(GOTO, salto2);     //System.out.println("GOTO, salto2");

        mv.visitLabel(salto1);              //System.out.println("salto1");

        return null;
    }


    @Override public Object visitPrintAST(ParserMiniJava.PrintASTContext ctx) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        //System.out.println("GETSTATIC, \"java/lang/System\", \"out\", \"Ljava/io/PrintStream\")");

        String expresion = (String) visit(ctx.expression());
        if (expresion.equals("I")) {
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V");
        }
        else if (expresion.equals("C")){
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(C)V");
        }
        else if (expresion.equals("Z")){
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Z)V");
        }
        else{
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
        //System.out.println("INVOKEVIRTUAL, \"java/io/PrintStream\", \"println\", \"(Ljava/lang/String;)V");

        return null;
    }


    @Override public Object visitReadAST(ParserMiniJava.ReadASTContext ctx) {
        mv.visitMethodInsn(INVOKESTATIC,"java/lang/System", "console", "()Ljava/io/Console;");      //System.out.println("INVOKESTATIC java/lang/System console ()Ljava/io/Console;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Console", "readLine", "()Ljava/lang/String;");   //System.out.println("INVOKEVIRTUAL java/io/Console readLine ()Ljava/lang/String;");

        if (ctx.identifier() != null) {
            String nombre = (String) visit(ctx.identifier());
            int posicion = this.indiceDe(nombre);

            String tipo = tipoDe(nombre);
            if (tipo.equals("I") ) {
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I");     //System.out.println("INVOKESTATIC java/lang/Integer parseInt (Ljava/lang/String;)I");
                mv.visitVarInsn(ISTORE, posicion);      //System.out.println("ISTORE " + posicion);
            }
            else if (tipo.equals("C")){
                mv.visitInsn(ICONST_0);     //System.out.println("ICONST_0");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "charAt", "(I)C");        //System.out.println("INVOKEVIRTUAL java/lang/String charAt (I)C");
                mv.visitVarInsn(ISTORE, posicion);      //System.out.println("ISTORE " + posicion);
            }
            else if (tipo.equals("[C")){
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "toCharArray", "()[C");   //System.out.println("INVOKEVIRTUAL java/lang/String toCharArray ()[C");
                mv.visitVarInsn(ASTORE, posicion);      //System.out.println("ASTORE " + posicion);
            }
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
        visit(ctx.primaryExpression(0));
        visit(ctx.primaryExpression(1));

        return "Z";
    }


    @Override public Object visitCompareAST(ParserMiniJava.CompareASTContext ctx) {
        hayComparacion = true;

        visit(ctx.primaryExpression(0));
        visit(ctx.primaryExpression(1));
        mv.visitJumpInsn(IF_ICMPGE, salto1);        //System.out.println("IF_ICMPGE, salto1)");

        hayComparacion = false;
        return "Z";
    }


    @Override public Object visitPlusAST(ParserMiniJava.PlusASTContext ctx) {
        visit(ctx.primaryExpression(0));
        visit(ctx.primaryExpression(1));
        mv.visitInsn(IADD);  //System.out.println("IADD");

        return "I";
    }


    @Override public Object visitMinusAST(ParserMiniJava.MinusASTContext ctx) {
        visit(ctx.primaryExpression(0));
        visit(ctx.primaryExpression(1));
        mv.visitInsn(ISUB);  //System.out.println("ISUB");

        return "I";
    }


    @Override public Object visitTimesAST(ParserMiniJava.TimesASTContext ctx) {
        visit(ctx.primaryExpression(0));
        visit(ctx.primaryExpression(1));
        mv.visitInsn(IMUL);  //System.out.println("IMUL");

        return "I";
    }

    @Override public Object visitLookupAST(ParserMiniJava.LookupASTContext ctx) {
        String tipo = (String) visit(ctx.primaryExpression(0));
        visit(ctx.primaryExpression(1));
        switch (tipo){
            case "[I": mv.visitInsn(IALOAD);       //System.out.println("IALOAD");
                return "I";
            case "[C": mv.visitInsn(CALOAD);       //System.out.println("CALOAD");
                return "C";
            case "[Z": mv.visitInsn(BALOAD);       //System.out.println("BALOAD");
                return "Z";
            default:
                return null;
        }
    }


    @Override public Object visitLengthAST(ParserMiniJava.LengthASTContext ctx)
    {
        visit(ctx.primaryExpression());
        mv.visitInsn(ARRAYLENGTH);  //System.out.println("ARRAYLENGTH");
        return "I";
    }


    @Override public Object visitMessageAST(ParserMiniJava.MessageASTContext ctx)
    {
        visit(ctx.primaryExpression());
        String nombreMetodo = (String) visit(ctx.identifier());
        if (ctx.expressionList() != null) {
            visit(ctx.expressionList());
        }
        String descriptor = descriptor(claseInstanciada, nombreMetodo);
        String retorno = descriptor.split("\\)")[1];
        mv.visitMethodInsn(INVOKEVIRTUAL, claseInstanciada, nombreMetodo, descriptor);
        //System.out.println("INVOKEVIRTUAL, "+claseInstanciada+", "+nombreMetodo+", "+descriptor);

        return retorno;
    }


    @Override public Object visitExpreListAST(ParserMiniJava.ExpreListASTContext ctx) {
        String desc = (String) visit(ctx.expression());
        for (int i = 0; i < ctx.expressionRest().size(); i++){
            desc += (String) visit(ctx.expressionRest(i));
        }
        return desc;
    }


    @Override public Object visitExpreRestAST(ParserMiniJava.ExpreRestASTContext ctx)
    {
        return visit(ctx.expression());
    }


    @Override public Object visitIntExpre(ParserMiniJava.IntExpreContext ctx)
    {
        int numero = Integer.parseInt(ctx.INTEGERLITERAL().getSymbol().getText());

        if (numero == 0) { mv.visitInsn(ICONST_0); }//System.out.println("ICONST_0"); }
        else if (numero == 1) { mv.visitInsn(ICONST_1); }//System.out.println("ICONST_1"); }
        else if (numero == 2) { mv.visitInsn(ICONST_2); }//System.out.println("ICONST_2"); }
        else if (numero == 3) { mv.visitInsn(ICONST_3); }//System.out.println("ICONST_3"); }
        else if (numero == 4) { mv.visitInsn(ICONST_4); }//System.out.println("ICONST_4"); }
        else if (numero == 5) { mv.visitInsn(ICONST_5); }//System.out.println("ICONST_5"); }
        else if (numero <= 127) { mv.visitIntInsn(BIPUSH, numero); }//System.out.println("BIPUSH, "+numero); }
        else { mv.visitIntInsn(SIPUSH, numero); }//System.out.println("SIPUSH, "+numero); }

        return "I";
    }


    @Override public Object visitCharExpre(ParserMiniJava.CharExpreContext ctx)
    {
        char caracter = ctx.CHARLITERAL().getSymbol().getText().charAt(1);
        mv.visitIntInsn(BIPUSH, (int) caracter);
        //System.out.println("BIPUSH, "+(int) caracter);

        return "C";
    }


    @Override public Object visitStringExpre(ParserMiniJava.StringExpreContext ctx)
    {
        String cadena = ctx.STRINGLITERAL().getSymbol().getText();
        cadena = cadena.replaceAll("\"", "");

        mv.visitLdcInsn(cadena);
        //System.out.println("mv.visitLdcInsn("+cadena+")");

        return "Ljava/lang/String;";
    }


    @Override public Object visitTrueExpre(ParserMiniJava.TrueExpreContext ctx)
    {
        mv.visitInsn(ICONST_1);     //System.out.println("mv.visitInsn(ICONST_1)");
        return "Z";
    }


    @Override public Object visitFalseExpre(ParserMiniJava.FalseExpreContext ctx)
    {
        mv.visitInsn(ICONST_0);     //System.out.println("mv.visitInsn(ICONST_0)");
        return "Z";
    }


    @Override public Object visitIdExpre(ParserMiniJava.IdExpreContext ctx)
    {
        String nombre = (String) visit(ctx.identifier());
        int posicion = indiceDe(nombre);
        String tipo = tipoDe(nombre);

        if (posicion == 99){
            mv.visitVarInsn(ALOAD, 0);          //System.out.println("ALOAD, 0");
            mv.visitFieldInsn(GETFIELD, claseActual, nombre, tipo);    //System.out.println("GETFIELD "+ nombre);
        }
        else {
            if (tipo.equals("I") || tipo.equals("C") || tipo.equals("Z")) {
                mv.visitVarInsn(ILOAD, posicion);
                //System.out.println("ILOAD, " + posicion);
            } else {
                mv.visitVarInsn(ALOAD, posicion);
                //System.out.println("ALOAD, " + posicion);
            }
        }
        if (hayCondicion && !hayComparacion && !hayNegacion){
            mv.visitJumpInsn(IFEQ, salto1);     //System.out.println("IFEQ, salto1");
        }

        return tipo;
    }


    @Override public Object visitThisExpre(ParserMiniJava.ThisExpreContext ctx) {
        mv.visitVarInsn(ALOAD, 0);  //System.out.println("ALOAD, 0");
        claseInstanciada = claseActual;
        return claseActual;
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
        visit(ctx.expression());
        String tipo = (String) visit(ctx.simpleType());
        if (tipo.equals("I")) { mv.visitIntInsn(NEWARRAY, T_INT); //System.out.println("NEWARRAY, T_INT");
            return "[I";
        }
        else if (tipo.equals("C")) { mv.visitIntInsn(NEWARRAY, T_CHAR); //System.out.println("NEWARRAY, T_CHAR");
            return "[C";
        }
        else if (tipo.equals("Z")) { mv.visitIntInsn(NEWARRAY, T_BOOLEAN); //System.out.println("NEWARRAY, T_BOOLEAN");
            return "[Z";
        }

        return null;
    }


    @Override public Object visitAllocationAST(ParserMiniJava.AllocationASTContext ctx)
    {
        claseInstanciada = (String) visit(ctx.identifier());
        mv.visitTypeInsn(NEW, claseInstanciada);        //System.out.println("NEW, "+claseInstanciada+")");
        mv.visitInsn(DUP);                              //System.out.println("DUP");
        mv.visitMethodInsn(INVOKESPECIAL, claseInstanciada, "<init>", "()V");   //System.out.println("INVOKESPECIAL, "+claseInstanciada+", \"<init>\", \"()V\")");
        return claseInstanciada;
    }


    @Override public Object visitNotAST(ParserMiniJava.NotASTContext ctx) {
        if (!hayCondicion){
            salto1 = new Label();
            salto2 = new Label();
        }
        hayNegacion = true;
        visit(ctx.expression());
        mv.visitJumpInsn(IFNE, salto1);     //System.out.println("IFNE, salto1");
        hayNegacion = false;
        if (!hayCondicion){
            mv.visitInsn(ICONST_1);             //System.out.println("ICONST_1");
            mv.visitJumpInsn(GOTO, salto2);     //System.out.println("GOTO, salto2");
            mv.visitLabel(salto1);              //System.out.println("salto1");
            mv.visitInsn(ICONST_0);             //System.out.println("ICONST_0");
            mv.visitLabel(salto2);              //System.out.println("salto2");
        }
        return "Z";
    }


    @Override public Object visitBracketAST(ParserMiniJava.BracketASTContext ctx)
    {
        return visit(ctx.expression());
    }


    @Override public Object visitIdent(ParserMiniJava.IdentContext ctx)
    {
        String identicador = ctx.ID().getSymbol().getText();
        return identicador;
    }


    public String construirClaseGenerar(String nombre){

        String resultado = nombre + ".java";
        return resultado;
    }


    public int indiceDe(String n){
        int posicion = 99;
        for (int i = 0; i < variablesLocales.size(); i++){
            Variable var = variablesLocales.get(i);
            if (var.nombre.equals(n)){
                posicion = variablesLocales.indexOf(var);
                return posicion;
            }
        }
        return posicion;
    }

    public String tipoDe(String n){
        String tipo = "";
        Variable var;
        for (int i = 0; i < variablesLocales.size(); i++){
            var = variablesLocales.get(i);
            if (var.nombre.equals(n)){
                tipo = var.tipo;
                return tipo;
            }
        }
        for (int i = 0; i < variablesGlobales.size(); i++){
            var = variablesGlobales.get(i);
            if (var.nombre.equals(n)){
                tipo = var.tipo;
                return tipo;
            }
        }
        return tipo;
    }

    public String descriptor(String c, String n){
        String desc = null;
        for (int i = 0; i < listaMetodos.size(); i++){
            Metodo var = listaMetodos.get(i);
            if (var.clase.equals(c) && var.nombre.equals(n)){
                desc = var.descriptor;
                return desc;
            }
        }
        return desc;
    }


    public static void escribirArchivo(String filename, byte[] bytecode){
        FileOutputStream p = null;
        try {
            p = new FileOutputStream("out/production/Proyecto3Compiladores/"+filename+".class");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            p.write(bytecode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class DynamicClassLoader extends ClassLoader {

        public Class<?> define(String className, byte[] bytecode) {
            return super.defineClass(className, bytecode, 0, bytecode.length);
        }

    };
}


