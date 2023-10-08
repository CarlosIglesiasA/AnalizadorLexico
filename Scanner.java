package scanner;

//LIBRERIAS
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//CLASE SCANNER MAIN
public class Scanner {
    
    //VARIABLES DE ENTRADA
    private final String source; 
    
    private final List<Token> tokens = new ArrayList<>();
    
    private static final Map<String, TipoToken> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("if",  TipoToken.IF);
        palabrasReservadas.put("else",  TipoToken.ELSE);
        palabrasReservadas.put("switch",  TipoToken.SWITCH);
        palabrasReservadas.put("case",  TipoToken.CASE);
        palabrasReservadas.put("default",  TipoToken.DEFAULT);
        palabrasReservadas.put("while",  TipoToken.WHILE);
        palabrasReservadas.put("do",  TipoToken.DO);
        palabrasReservadas.put("for",  TipoToken.FOR);
        palabrasReservadas.put("break",  TipoToken.BREAK);
        palabrasReservadas.put("continue",  TipoToken.CONTINUE);
        palabrasReservadas.put("return",  TipoToken.RETURN);
        palabrasReservadas.put("class",  TipoToken.CLASE);
        palabrasReservadas.put("interface",  TipoToken.INTERFAZ);
        palabrasReservadas.put("enum",  TipoToken.ENUM);
        palabrasReservadas.put("extends",  TipoToken.EXTENDS);
        palabrasReservadas.put("implements",  TipoToken.IMPLEMENTS);
        palabrasReservadas.put("new",  TipoToken.NEW);
        palabrasReservadas.put("this",  TipoToken.THIS);
        palabrasReservadas.put("super",  TipoToken.SUPER);
        palabrasReservadas.put("static",  TipoToken.STATIC);
        palabrasReservadas.put("final",  TipoToken.FINAL);
        palabrasReservadas.put("abstract",  TipoToken.ABSTRACT);
        palabrasReservadas.put("public",  TipoToken.PUBLIC);
        palabrasReservadas.put("private",  TipoToken.PRIVATE);
        palabrasReservadas.put("protected",  TipoToken.PROTECTED);
        palabrasReservadas.put("try",  TipoToken.TRY);
        palabrasReservadas.put("catch",  TipoToken.CATCH);
        palabrasReservadas.put("finally",  TipoToken.FINALLY);
        palabrasReservadas.put("throws",  TipoToken.THROWS);
        palabrasReservadas.put("null",  TipoToken.NULL);
        palabrasReservadas.put("true",  TipoToken.TRUE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("package",  TipoToken.PACKAGE);
        palabrasReservadas.put("import",  TipoToken.IMPORT);
        palabrasReservadas.put("instanceof",  TipoToken.INSTANCEOF);
        palabrasReservadas.put("int",  TipoToken.INT);
        palabrasReservadas.put("double",  TipoToken.DOUBLE);
        palabrasReservadas.put("float",  TipoToken.FLOAT);
        palabrasReservadas.put("long",  TipoToken.LONG);
        palabrasReservadas.put("short",  TipoToken.SHORT);
        palabrasReservadas.put("byte",  TipoToken.BYTE);
        palabrasReservadas.put("boolean",  TipoToken.BOOLEAN);
        palabrasReservadas.put("char",  TipoToken.CHAR);
        palabrasReservadas.put("void",  TipoToken.VOID);
        palabrasReservadas.put("String",  TipoToken.STRING);
        palabrasReservadas.put("!=",  TipoToken.DIFERENTEDE);
        palabrasReservadas.put("<=",  TipoToken.MAYORIGUALQUE);
        palabrasReservadas.put(">=",  TipoToken.MAYORIGUALQUE);
        palabrasReservadas.put("++",  TipoToken.INCREMENTO);
        palabrasReservadas.put("--",  TipoToken.DECREMENTO);
        palabrasReservadas.put("==",  TipoToken.IGUALA);
        palabrasReservadas.put("||",  TipoToken.Y);
        palabrasReservadas.put("&&",  TipoToken.O);
    }

    Scanner(String source){
        this.source = source+" ";
    }
    
    public List<Token> scanTokens(){
        int estado = 0;
        String lexema = "";
        int inicioLexema = 0;
        char car;

        for(int i = 0; i<source.length();i++){
            car = source.charAt(i);
            switch(estado)
            {
                case 0: //OPERADORES
                    switch(car){
                        case '-':
                            tokens.add(new Token(TipoToken.RESTA, "-",  i+1));
                            break;
                        case '+':
                            tokens.add(new Token(TipoToken.SUMA, "+",  i+1));
                            break;
                        case '*':
                            tokens.add(new Token(TipoToken.MULTIPLICACION, "*",  i+1));
                            break;
                        case '/':
                            if (car == '/') {
                                // Verificar si hay otro '/' consecutivo
                                if (i + 1 < source.length() && source.charAt(i + 1) == '/') {
                                    // Generar un token para el '/'
                                    //tokens.add(new Token(TipoToken.DIVISION, "/", inicioLexema + 1));
                                    estado = 4; // Mover al caso 5 para reconocer comentarios
                                    i++; // Avanzar al siguiente carácter
                                } else {
                                    tokens.add(new Token(TipoToken.DIVISION,"/",i+1));
                                }
                            }
                            //tokens.add(new Token(TipoToken.DIVISION,"/",i+1));
                            break;
                        case '%':
                            tokens.add(new Token(TipoToken.MODULO,"%",i+1));
                            break;
                        case '=':
                            tokens.add(new Token(TipoToken.IGUAL,"=",i+1));
                            break;
                        case '<':
                            tokens.add(new Token(TipoToken.MENORQUE,"<",i+1));
                            break;
                        case '>':
                            tokens.add(new Token(TipoToken.MAYORQUE,">",i+1));
                            break;
                        case '!':
                            tokens.add(new Token(TipoToken.NEGACIONLOGICA,"!",i+1));
                            break;
                        //VARIANTES A OTROS ESTADOS
                        //Cadena
                        case '"':
                            estado = 1;
                            lexema = lexema+car;
                            inicioLexema=i;
                            break;
                        //SIMBOLOS DEL LENGUAJE
                        case '(':
                            tokens.add(new Token(TipoToken.PARENTESISABRE,"(",i+1));
                            break;
                        case '[':
                            tokens.add(new Token(TipoToken.CORCHETEABRE,"[",i+1));
                            break;
                        case '{':
                            tokens.add(new Token(TipoToken.LLAVEABRE,"{",i+1));
                            break;
                        case ')':
                            tokens.add(new Token(TipoToken.PARENTESISCIERRA,")",i+1));
                            break;
                        case ']':
                            tokens.add(new Token(TipoToken.CORCHETECIERRA,"]",i+1));
                            break;
                        case '}':
                            tokens.add(new Token(TipoToken.LLAVECIERRA,"}",i+1));
                            break;
                        case ',':
                            tokens.add(new Token(TipoToken.COMA,",",i+1));
                            break;
                        case '.':
                            tokens.add(new Token(TipoToken.PUNTO,".",i+1));
                            break;
                        case ';':
                            tokens.add(new Token(TipoToken.PUNTOYCOMA,";",i+1));
                            break;
                        case ':':
                            tokens.add(new Token(TipoToken.DOBLEPUNTO,":",i+1));
                            break;
                        
                    }
                    //Reservadas
                    if(Character.isAlphabetic(car)){
                        estado = 2;
                        lexema=lexema+car;
                        inicioLexema = i;
                    } else if(Character.isDigit(car)) {
                        estado = 3;
                        lexema=lexema+car;
                        inicioLexema = i;
                    }
                    break;
                case 1://Cadenas
                    if (car != '"') {
                        lexema = lexema + car;
                    } else {
                        tokens.add(new Token(TipoToken.CADENA, lexema, inicioLexema + 1));
                        estado = 0;
                        lexema = "";
                        inicioLexema = 0;
                    }
                    break;
                case 2 : //Reservadas
                    if(Character.isAlphabetic(car) || Character.isDigit(car)){
                        lexema=lexema+car;
                    } else {
                        TipoToken tt = palabrasReservadas.get(lexema);
                        if(tt ==  null){ 
                            tokens.add(new Token(TipoToken.IDENTIFICADOR,lexema,inicioLexema+1));
                        } else {
                            tokens.add(new Token(tt,lexema,inicioLexema+1));
                        }
                        estado = 0;
                        lexema = "";
                        inicioLexema=0;
                        i--;
                    }
                          
                    break;
                case 3: //Numeros
                    if(Character.isDigit(car)){
                        lexema=lexema+car;
                    } else {
                        tokens.add(new Token(TipoToken.NUMERO,lexema,inicioLexema+1));
                        estado=0;
                        lexema="";
                        inicioLexema=0;
                        i--;
                    }
                    break;
                case 4:
                    if(car != '\n'){
                        lexema = lexema+car;
                    } else {
                        tokens.add(new Token(TipoToken.COMENTARIO,lexema,inicioLexema+1));
                        estado=0;
                        lexema="";
                        inicioLexema=0;
                    }
                    break;
            }
        }
        tokens.add(new Token(TipoToken.EOF, "",  source.length())); //Fin del archivo
        return tokens; 
    }
}

/*
Signos o símbolos del lenguaje:
(
)
{
}
,
.
;
-
+
*
/
!
!=
=
==
<
<=
>
>=
// -> comentarios (no se genera token)
/* ... * / -> comentarios (no se genera token)
Identificador,
Cadena
Numero
Cada palabra reservada tiene su nombre de token

 */