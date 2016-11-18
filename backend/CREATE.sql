CREATE TABLE `Usuario` (
`Nome`	TEXT NOT NULL,
`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
)

CREATE TABLE `TipoAtendimento` (
`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
`Label`	INTEGER NOT NULL
)

CREATE TABLE "Atendimento" (
`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
`Tipo`	INTEGER NOT NULL,
`IDUsuario`	INTEGER NOT NULL,
`Mensagem`	TEXT,
`Data`	TEXT NOT NULL,
FOREIGN KEY(`Tipo`) REFERENCES `TipoAtendimento`(`ID`),
FOREIGN KEY(`IDUsuario`) REFERENCES `Usuario(ID)`
)