// Generated from C:/Users/Zamora/IdeaProjects/Proyecto1Compiladores/src\ParserMiniJava.g4 by ANTLR 4.5.3
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ParserMiniJava}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ParserMiniJavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code goalAST}
	 * labeled alternative in {@link ParserMiniJava#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoalAST(ParserMiniJava.GoalASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mainClassAST}
	 * labeled alternative in {@link ParserMiniJava#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClassAST(ParserMiniJava.MainClassASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sinExtendsAST}
	 * labeled alternative in {@link ParserMiniJava#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinExtendsAST(ParserMiniJava.SinExtendsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conExtendsAST}
	 * labeled alternative in {@link ParserMiniJava#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConExtendsAST(ParserMiniJava.ConExtendsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classAST}
	 * labeled alternative in {@link ParserMiniJava#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassAST(ParserMiniJava.ClassASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code extendsAST}
	 * labeled alternative in {@link ParserMiniJava#classExtendsDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsAST(ParserMiniJava.ExtendsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAST}
	 * labeled alternative in {@link ParserMiniJava#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAST(ParserMiniJava.VarASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodAST}
	 * labeled alternative in {@link ParserMiniJava#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodAST(ParserMiniJava.MethodASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameterListAST}
	 * labeled alternative in {@link ParserMiniJava#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterListAST(ParserMiniJava.ParameterListASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameterAST}
	 * labeled alternative in {@link ParserMiniJava#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterAST(ParserMiniJava.ParameterASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameterRestAST}
	 * labeled alternative in {@link ParserMiniJava#formalParameterRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterRestAST(ParserMiniJava.ParameterRestASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleTypeAST}
	 * labeled alternative in {@link ParserMiniJava#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleTypeAST(ParserMiniJava.SimpleTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayTypeAST}
	 * labeled alternative in {@link ParserMiniJava#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTypeAST(ParserMiniJava.ArrayTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idTypeAST}
	 * labeled alternative in {@link ParserMiniJava#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdTypeAST(ParserMiniJava.IdTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charTypeAST}
	 * labeled alternative in {@link ParserMiniJava#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharTypeAST(ParserMiniJava.CharTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanTypeAST}
	 * labeled alternative in {@link ParserMiniJava#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanTypeAST(ParserMiniJava.BooleanTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerTypeAST}
	 * labeled alternative in {@link ParserMiniJava#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerTypeAST(ParserMiniJava.IntegerTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAST}
	 * labeled alternative in {@link ParserMiniJava#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAST(ParserMiniJava.ArrayASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(ParserMiniJava.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(ParserMiniJava.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayStat(ParserMiniJava.ArrayStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(ParserMiniJava.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(ParserMiniJava.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(ParserMiniJava.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readStat}
	 * labeled alternative in {@link ParserMiniJava#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStat(ParserMiniJava.ReadStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockAST}
	 * labeled alternative in {@link ParserMiniJava#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockAST(ParserMiniJava.BlockASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAssignAST}
	 * labeled alternative in {@link ParserMiniJava#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignAST(ParserMiniJava.VarAssignASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAssignAST}
	 * labeled alternative in {@link ParserMiniJava#arrayAssignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssignAST(ParserMiniJava.ArrayAssignASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifAST}
	 * labeled alternative in {@link ParserMiniJava#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfAST(ParserMiniJava.IfASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileAST}
	 * labeled alternative in {@link ParserMiniJava#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileAST(ParserMiniJava.WhileASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printAST}
	 * labeled alternative in {@link ParserMiniJava#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintAST(ParserMiniJava.PrintASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readAST}
	 * labeled alternative in {@link ParserMiniJava#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadAST(ParserMiniJava.ReadASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpre(ParserMiniJava.AndExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpre(ParserMiniJava.CompExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusExpre(ParserMiniJava.PlusExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExpre(ParserMiniJava.MinusExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timesExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimesExpre(ParserMiniJava.TimesExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lookupExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLookupExpre(ParserMiniJava.LookupExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lengthExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthExpre(ParserMiniJava.LengthExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mesageExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMesageExpre(ParserMiniJava.MesageExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primExpre}
	 * labeled alternative in {@link ParserMiniJava#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimExpre(ParserMiniJava.PrimExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andAST}
	 * labeled alternative in {@link ParserMiniJava#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndAST(ParserMiniJava.AndASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareAST}
	 * labeled alternative in {@link ParserMiniJava#compareExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareAST(ParserMiniJava.CompareASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusAST}
	 * labeled alternative in {@link ParserMiniJava#plusExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusAST(ParserMiniJava.PlusASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusAST}
	 * labeled alternative in {@link ParserMiniJava#minusExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusAST(ParserMiniJava.MinusASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timesAST}
	 * labeled alternative in {@link ParserMiniJava#timesExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimesAST(ParserMiniJava.TimesASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lookupAST}
	 * labeled alternative in {@link ParserMiniJava#arrayLookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLookupAST(ParserMiniJava.LookupASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lengthAST}
	 * labeled alternative in {@link ParserMiniJava#arrayLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthAST(ParserMiniJava.LengthASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code messageAST}
	 * labeled alternative in {@link ParserMiniJava#messageSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageAST(ParserMiniJava.MessageASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expreListAST}
	 * labeled alternative in {@link ParserMiniJava#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpreListAST(ParserMiniJava.ExpreListASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expreRestAST}
	 * labeled alternative in {@link ParserMiniJava#expressionRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpreRestAST(ParserMiniJava.ExpreRestASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpre(ParserMiniJava.IntExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharExpre(ParserMiniJava.CharExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpre(ParserMiniJava.StringExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExpre(ParserMiniJava.TrueExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExpre(ParserMiniJava.FalseExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpre(ParserMiniJava.IdExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpre(ParserMiniJava.ThisExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAllocExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAllocExpre(ParserMiniJava.ArrayAllocExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocExpre(ParserMiniJava.AllocExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpre(ParserMiniJava.NotExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bracketExpre}
	 * labeled alternative in {@link ParserMiniJava#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketExpre(ParserMiniJava.BracketExpreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAllocationAST}
	 * labeled alternative in {@link ParserMiniJava#arrayAllocationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAllocationAST(ParserMiniJava.ArrayAllocationASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocationAST}
	 * labeled alternative in {@link ParserMiniJava#allocationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocationAST(ParserMiniJava.AllocationASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notAST}
	 * labeled alternative in {@link ParserMiniJava#notExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotAST(ParserMiniJava.NotASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bracketAST}
	 * labeled alternative in {@link ParserMiniJava#bracketExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketAST(ParserMiniJava.BracketASTContext ctx);
}