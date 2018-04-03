lexer grammar LexerMiniJava;

// Símbolos

IGUAL   : '=' ;
PARIZQ  : '(' ;
PARDER  : ')' ;
CORIZQ  : '[' ;
CORDER  : ']' ;
LLAVEIZQ: '{' ;
LLAVEDER: '}' ;
PUNTO   : '.' ;
COMA    : ',' ;
AND     : '&&' ;
MENORQUE: '<' ;
MAYORQUE: '>' ;
MAS     : '+' ;
MENOS   : '-' ;
MULT    : '*' ;
NOT     : '!' ;
PUNTOYCOMA  : ';' ;



// Palabras reservadas

CLASS       : 'class' ;
EXTENDS     : 'extends';
PUBLIC      : 'public' ;
STATIC      : 'static' ;
VOID        : 'void' ;
MAIN        : 'main' ;
IF          : 'if' ;
ELSE        : 'else' ;
PRINT       : 'Print' ;
READ        : 'Read' ;
LENGTH      : 'length' ;
TRUE        : 'true' ;
FALSE       : 'false' ;
THIS        : 'this' ;
NEW         : 'new' ;
WHILE       : 'while' ;
BREAK       : 'break' ;     // eXTRAS
FINAL       : 'const' ;     // EXTRAS
RETURN      : 'return';



// Tipos de datos

INT         : 'int' ;
//STRING      : 'String' ;    // por resolver
BOOLEAN     : 'boolean' ;
CHAR        : 'char' ;


INTEGERLITERAL  : '0'
                | '1'..'9' ('0'..'9')* ;

CHARLITERAL     : '\'' SINGLECHAR '\''
                | '\'' ESCAPESEQ '\'' ;

STRINGLITERAL   : '"' STRINGCHARS? '"' ;

fragment
SINGLECHAR      : ~['\\] ;

fragment
STRINGCHARS     : STRINGCHAR+ ;

fragment
STRINGCHAR      : ~["\\]
                |   ESCAPESEQ ;

fragment
ESCAPESEQ       : '\\' [btnfr"'\\] ;



// Identificadores (ID)

ID
    :   JAVALETTER JAVALETTERORDIGIT*
    ;

fragment
JAVALETTER          :   [a-zA-Z$_] // these are the "java letters" below 0x7F
                    |   // covers all characters above 0x7F which are not a surrogate
                        ~[\u0000-\u007F\uD800-\uDBFF]
                        {Character.isJavaIdentifierStart(_input.LA(-1))}?
                    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
                        [\uD800-\uDBFF] [\uDC00-\uDFFF]
                        {Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
                    ;

fragment
JAVALETTERORDIGIT   :   [a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
                    |   // covers all characters above 0x7F which are not a surrogate
                        ~[\u0000-\u007F\uD800-\uDBFF]
                        {Character.isJavaIdentifierPart(_input.LA(-1))}?
                    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
                        [\uD800-\uDBFF] [\uDC00-\uDFFF]
                        {Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
                    ;



// Elementos omitidos

LINE_COMMENT:   '//' .*? '\r'? '\n' -> skip ;

SPECIAL_COMMENT
 : '/*' ( SPECIAL_COMMENT | DELIMITED_COMMENT | . )*? '*/' -> skip
 ;

fragment DELIMITED_COMMENT
 : '/*' .*? '*/'
 ;

WS          :   [ \t\n\r]+ -> skip ;