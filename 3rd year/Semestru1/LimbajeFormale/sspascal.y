%{
#include <stdio.h>
#include <stdlib.h>
#define YYDEBUG 1

#define TIP_INT 1
#define TIP_REAL 2
#define TIP_CAR 3
#define TIP_BOOL 4

double stiva[20];
int sp;

void push(double x)
{ stiva[sp++]=x; }

double pop()
{ return stiva[--sp]; }

%}

%union {
  	int l_val;
	char *p_val;
}

%token LIST

%token CONST
%token ELSE
%token IF
%token WRITE
%token PROGRAM
%token READ
%token THEN
%token VAR
%token WHILE
%token FOR

%token ID
%token <p_val> CONST_INT
%token <p_val> CONST_REAL
%token <p_val> CONST_CAR
%token CONST_SIR

%token CHAR
%token INTEGER
%token REAL
%token BOOLEAN

%token ATRIB
%token NE
%token LE
%token GE
%token EQ

%left '+' '-'
%left DIV MOD '*' '/'
%left OR
%left AND
%left NOT

%type <l_val> expr_stat factor_stat constanta
%%
prog_sursa:	bloc 		{printf("%d\n", "prog_sursa");}
		;
bloc:		instr_comp 	{printf("%s\n", "bloc");}
		;

decl_const:	{sp=0;} expr_stat ID  ';'	{
		printf("*** %d %g ***\n", $2, pop());
					}
		;
decl_var:	tip ID ';'	{printf("%s\n", "");}
		|   	LIST '<'tip'>' ID ';'	{printf("%s\n", "declaratie lista");}
		|	tip ID ATRIB expresie ';'	{printf("%s\n", "declaratie variabila + atribuire expresie");}
		|	tip ID ATRIB CONST_SIR ';'	{printf("%s\n", "declaratie variabila + atribuire const sir");}
		;

tip:		tip_simplu	{printf("%s\n", "tip");}
		;
tip_simplu:	INTEGER		{printf("%s\n", "integer");}
		| REAL		{printf("%s\n", "real");}
		| CHAR		{printf("%s\n", "char");}
		| BOOLEAN	{printf("%s\n", "boolean");}
		;
expr_stat:	factor_stat
		| expr_stat '+' expr_stat	{
			{printf("%s\n", "adunare");}
			if($1==TIP_REAL || $3==TIP_REAL) $$=TIP_REAL;
			else if($1==TIP_CAR) $$=TIP_CAR;
				else $$=TIP_INT;
			push(pop()+pop());
						}
		| expr_stat '-' expr_stat	{
			{printf("%s\n", "scadere");}
			if($1==TIP_REAL || $3==TIP_REAL) $$=TIP_REAL;
			else if($1==TIP_CAR) $$=TIP_CAR;
				else $$=TIP_INT;
			push(-pop()+pop());
						}
		| expr_stat '*' expr_stat	{
			{printf("%s\n", "inmultire");}
			if($1==TIP_REAL || $3==TIP_REAL) $$=TIP_REAL;
			else if($1==TIP_CAR) $$=TIP_CAR;
				else $$=TIP_INT;
			push(pop()*pop());
						}
		| expr_stat '/' expr_stat	{printf("%s\n", "impartire");}	
		| expr_stat DIV expr_stat	{printf("%s\n", "impartire");}
		| expr_stat MOD expr_stat	{printf("%s\n", "modulo");}
		;
factor_stat:	ID		{printf("%s\n", "variabila");}
		| constanta	{printf("%s\n", "constanta");}
		| '(' expr_stat ')'	{$$ = $2;}
		;
constanta:	CONST_INT	{
			{printf("%s\n", "constanta int");}
			$$ = TIP_INT;
			push(atof($1));
				}
		| CONST_REAL	{
			{printf("%s\n", "constanta real");}
			$$ = TIP_REAL;
			push(atof($1));
				}
		| CONST_CAR	{
			{printf("%s\n", "constanta car");}
			$$ = TIP_CAR;
			push((double)$1[0]);
				}
		;
instr_comp:	'{' lista_instr '}'	{printf("%s\n", "instr comp");}
		;
lista_instr:	instr	{printf("%s\n", "instr");}
		| lista_instr ';' instr		{printf("%s\n", "instr");}
		| lista_instr instr		{printf("%s\n", "instr");}
		;
instr:		/* empty */
		| instr_atrib	{printf("%s\n", "instr atrib");}
		| instr_if	{printf("%s\n", "instr if");}
		| instr_while	{printf("%s\n", "instr while");}
		| instr_comp	{printf("%s\n", "instr comp");}
		| instr_read	{printf("%s\n", "instr read");}
		| instr_print	{printf("%s\n", "instr print");}
		| instr_for	{printf("%s\n", "instr for");}
		| decl_var	{printf("%s\n", "decl var");}
		| decl_const	{printf("%s\n", "decl const");}
		;
instr_atrib:	variabila ATRIB expresie	{printf("%s\n", "atrib");}
		;
variabila:	ID	{printf("%s\n", "variabila");}
		| ID '[' expresie ']'	{printf("%s\n", "element din vector");}
		| ID '.' ID
		;
expresie:	factor
		| expresie '+' expresie		{printf("%s\n", "adunare");}
		| expresie '-' expresie		{printf("%s\n", "scadere");}
		| expresie '*' expresie		{printf("%s\n", "inmultire");}
		| expresie '/' expresie		{printf("%s\n", "impartire");}
		| expresie DIV expresie		{printf("%s\n", "impartire");}
		| expresie MOD expresie		{printf("%s\n", "modulo");}
		;
factor:		ID
		| constanta {}
		| ID '(' lista_expr ')'
		| '(' expresie ')'
		| ID '[' expresie ']'
		| ID '.' ID
		;
lista_expr:	expresie
		| lista_expr ',' expresie
		;
instr_if:	IF '(' conditie ')' instr ramura_else		{printf("%s\n", "if si else");}
		|	IF '(' conditie ')' instr		{printf("%s\n", "if");}
		;
ramura_else:	/* empty */
		ELSE instr
		;
conditie:	expr_logica
		| expresie op_rel expresie
		;
expr_logica:	factor_logic
		| expr_logica AND expr_logica			{printf("%s\n", "and");}
		| expr_logica OR expr_logica			{printf("%s\n", "or");}
		;
factor_logic:	'(' conditie ')'
		| NOT factor_logic				{printf("%s\n", "not");}
		;
op_rel:		'='						{printf("%s\n", "=");}
		| '<'						{printf("%s\n", "<");}
		| '>'						{printf("%s\n", ">");}
		| NE						{printf("%s\n", "NE");}
		| LE						{printf("%s\n", "LE");}
		| GE						{printf("%s\n", "GE");}
		| EQ						{printf("%s\n", "EQ");}
		;
instr_while:	WHILE '(' conditie ')' instr			{printf("%s\n", "while");}
		;
instr_for:	FOR '(' instr_atrib ';' conditie ';' instr_atrib ')' instr	{printf("%s\n", "for");}
		;
instr_print:	WRITE '(' lista_elem ')'			{printf("%s\n", "write");}
		;
lista_elem:	element
		| lista_elem ',' element
		;
element:	expresie
		| CONST_SIR
		;
instr_read:	READ '(' lista_variab ')'			{printf("%s\n", "read");}
		;
lista_variab:	variabila
		| lista_variab ',' variabila
		;

%%

yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
  if(argc>1) yyin = fopen(argv[1], "r");
  if((argc>2)&&(!strcmp(argv[2],"-d"))) yydebug = 1;
  if(!yyparse()) fprintf(stderr,"Program syntactic correct.\n");
}

