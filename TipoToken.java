package scanner;

public enum TipoToken {
    // Crear un tipoToken por palabra reservada
    // Palabras clave:
    AND, ELSE, FALSE, FOR, FUN, IF, NULL, OR, PRINT, RETURN, TRUE, VAR, WHILE,
    
    // Crear un tipoToken: identificador, una cadena y numero
    IDENTIFICADOR, //PARA NOMBRES DE FUNCIONES
    CADENA,     //CADENA DE CARACTERES ""
    NUMERO,    //NUMEROS 0,1..9
    
    //Comentarios
    COMENTARIO,COMENTARIOEX, // "//" ó /* */
    // Crear un tipoToken por cada "Signo del lenguaje" (ver clase Scanner)
    PARENTESISABRE, PARENTESISCIERRA, //PARENTESIS ()
    LLAVEABRE, LLAVECIERRA,     //LLAVES {}
    COMA,   //COMA ,
    PUNTO,  //PUNTOS .
    PUNTOYCOMA, //PUNTO Y COMA ;
    DOBLEPUNTO, // :
    MULTIPLICACION, //*
    DIVISION, // "/"
    NEGACIONLOGICA, // !
    DIFERENTEDE, // !=
    MENORQUE, // <
    MENORIGUALQUE, // <=
    MAYORQUE, // >
    MAYORIGUALQUE, // >=
    SUMA, // +
    RESTA, // -
    MODULO, // %
    IGUAL, // =
    INCREMENTO, DECREMENTO, // ++ ó --
    
    IGUALA, // ==
    O,Y, // "||" , "&&"
    
    // Final de cadena
    EOF //TOKEN FINAL
}
