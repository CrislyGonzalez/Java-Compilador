parser grammar ParserMiniJava;

options{
    tokenVocab = LexerMiniJava;
}

goal    : ( typeDeclaration )* mainClass EOF                            #goalAST
        ;

mainClass   :   CLASS identifier LLAVEIZQ PUBLIC STATIC VOID MAIN
                PARIZQ identifier CORIZQ CORDER identifier PARDER
                LLAVEIZQ ( statement )* LLAVEDER LLAVEDER               #mainClassAST
            ;

typeDeclaration : classDeclaration                                      #sinExtendsAST
                | classExtendsDeclaration                               #conExtendsAST
                ;

classDeclaration: CLASS identifier LLAVEIZQ ( varDeclaration )*
                  ( methodDeclaration )* LLAVEDER                       #classAST
                ;

classExtendsDeclaration : CLASS identifier EXTENDS identifier
                          LLAVEIZQ ( varDeclaration )* ( methodDeclaration )* LLAVEDER              #extendsAST
                        ;

varDeclaration  : type identifier PUNTOYCOMA                            #varAST
                ;

methodDeclaration   : PUBLIC type identifier PARIZQ ( formalParameterList )? PARDER
                      LLAVEIZQ ( varDeclaration )* ( statement )* RETURN expression PUNTOYCOMA
                      LLAVEDER                                                                      #methodAST
                    ;

formalParameterList :   formalParameter ( formalParameterRest )*        #parameterListAST
                    ;

formalParameter     :   type identifier                                 #parameterAST
                    ;

formalParameterRest :   COMA formalParameter                            #parameterRestAST
                    ;

type    :   simpleType                                                  #simpleTypeAST
        |   arrayType                                                   #arrayTypeAST
        |   identifier                                                  #idTypeAST
        ;

simpleType  :   CHAR                                                    #charTypeAST
            |   BOOLEAN                                                 #booleanTypeAST
            |   INT                                                     #integerTypeAST
            ;

arrayType   :   simpleType CORIZQ CORDER                                #arrayAST
            ;

//charType    :   CHAR                                                    #charAST
//            ;

//booleanType :   BOOLEAN                                                 #booleanAST
//            ;

//integerType :   INT                                                     #intAST
//            ;

statement   :   block                                                   #blockStat
            |   assignmentStatement                                     #assignStat
            |   arrayAssignmentStatement                                #arrayStat
            |   ifStatement                                             #ifStat
            |   whileStatement                                          #whileStat
            |   printStatement                                          #printStat
            |   readStatement                                           #readStat
            ;

block   :   LLAVEIZQ ( statement )* LLAVEDER                            #blockAST
        ;

assignmentStatement :   identifier IGUAL expression PUNTOYCOMA          #varAssignAST
                    ;

arrayAssignmentStatement    :   identifier CORIZQ expression CORDER
                                IGUAL expression PUNTOYCOMA             #arrayAssignAST
                            ;

ifStatement :   IF PARIZQ expression PARDER
                statement ( ELSE statement )?                           #ifAST
            ;

whileStatement  :   WHILE PARIZQ expression PARDER statement            #whileAST
                ;

printStatement  :   PRINT PARIZQ expression PARDER PUNTOYCOMA           #printAST
                ;


readStatement   : ( identifier IGUAL )? READ PARIZQ PARDER PUNTOYCOMA   #readAST
                ;

expression      : andExpression                                         #andExpre
                | compareExpression                                     #compExpre
                | plusExpression                                        #plusExpre
                | minusExpression                                       #minusExpre
                | timesExpression                                       #timesExpre
                | arrayLookup                                           #lookupExpre
                | arrayLength                                           #lengthExpre
                | messageSend                                           #mesageExpre
                | primaryExpression                                     #primExpre
                ;

andExpression   : primaryExpression AND primaryExpression               #andAST
                ;

compareExpression   : primaryExpression MENORQUE primaryExpression      #compareAST
                    ;

plusExpression  : primaryExpression MAS primaryExpression               #plusAST
                ;

minusExpression : primaryExpression MENOS primaryExpression             #minusAST
                ;

timesExpression : primaryExpression MULT primaryExpression              #timesAST
                ;

arrayLookup     : primaryExpression CORIZQ primaryExpression CORDER     #lookupAST
                ;

arrayLength     : primaryExpression PUNTO LENGTH                        #lengthAST
                ;

messageSend     : primaryExpression PUNTO identifier PARIZQ ( expressionList )? PARDER      #messageAST
                ;

expressionList  : expression ( expressionRest )*                        #expreListAST
                ;

expressionRest  : COMA expression                                       #expreRestAST
                ;

primaryExpression   : INTEGERLITERAL                                    #intExpre
                    | CHARLITERAL                                       #charExpre
                    | STRINGLITERAL                                     #stringExpre
                    | TRUE                                              #trueExpre
                    | FALSE                                             #falseExpre
                    | identifier                                        #idExpre
                    | THIS                                              #thisExpre
                    | arrayAllocationExpression                         #arrayAllocExpre
                    | allocationExpression                              #allocExpre
                    | notExpression                                     #notExpre
                    | bracketExpression                                 #bracketExpre
                    ;

//integerLiteral      : INTEGERLITERAL ;

//charConstant        : CHARLITERAL ;

//stringConstant      : STRINGLITERAL ;

//trueLiteral         : TRUE ;

//falseLiteral        : FALSE ;

//thisLiteral         : THIS ;

arrayAllocationExpression   : NEW simpleType CORIZQ expression CORDER   #arrayAllocationAST
                            ;

allocationExpression        : NEW identifier PARIZQ PARDER              #allocationAST
                            ;

notExpression       : NOT expression                                    #notAST
                    ;

bracketExpression   : PARIZQ expression PARDER                          #bracketAST
                    ;
                    
identifier
locals [ParserRuleContext decl=null]    : ID                            #ident
                                        ;