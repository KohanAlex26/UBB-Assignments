/* A Bison parser, made by GNU Bison 2.7.  */

/* Bison implementation for Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2012 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.7"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */
/* Line 371 of yacc.c  */
#line 1 "sspascal.y"

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


/* Line 371 of yacc.c  */
#line 89 "sspascal.tab.c"

# ifndef YY_NULL
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULL nullptr
#  else
#   define YY_NULL 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "sspascal.tab.h".  */
#ifndef YY_YY_SSPASCAL_TAB_H_INCLUDED
# define YY_YY_SSPASCAL_TAB_H_INCLUDED
/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     LIST = 258,
     CONST = 259,
     ELSE = 260,
     IF = 261,
     WRITE = 262,
     PROGRAM = 263,
     READ = 264,
     THEN = 265,
     VAR = 266,
     WHILE = 267,
     FOR = 268,
     ID = 269,
     CONST_INT = 270,
     CONST_REAL = 271,
     CONST_CAR = 272,
     CONST_BOOL = 273,
     CONST_SIR = 274,
     CHAR = 275,
     INTEGER = 276,
     REAL = 277,
     BOOLEAN = 278,
     ATRIB = 279,
     NE = 280,
     LE = 281,
     GE = 282,
     EQ = 283,
     MOD = 284,
     DIV = 285,
     OR = 286,
     AND = 287,
     NOT = 288
   };
#endif


#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{
/* Line 387 of yacc.c  */
#line 22 "sspascal.y"

  	int l_val;
	char *p_val;


/* Line 387 of yacc.c  */
#line 171 "sspascal.tab.c"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */

#endif /* !YY_YY_SSPASCAL_TAB_H_INCLUDED  */

/* Copy the second part of user declarations.  */

/* Line 390 of yacc.c  */
#line 199 "sspascal.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(N) (N)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int yyi)
#else
static int
YYID (yyi)
    int yyi;
#endif
{
  return yyi;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)				\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack_alloc, Stack, yysize);			\
	Stack = &yyptr->Stack_alloc;					\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (YYID (0))
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  31
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   293

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  50
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  33
/* YYNRULES -- Number of rules.  */
#define YYNRULES  89
/* YYNRULES -- Number of states.  */
#define YYNSTATES  166

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   288

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
      41,    42,    31,    29,    48,    30,    47,    32,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    38,
      39,    49,    40,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    45,     2,    46,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    43,     2,    44,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    33,    34,    35,    36,    37
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     5,     7,     8,    13,    17,    24,    30,
      36,    38,    40,    42,    44,    46,    48,    52,    56,    60,
      64,    68,    72,    74,    76,    80,    82,    84,    86,    88,
      92,    94,    98,   101,   102,   104,   106,   108,   110,   112,
     114,   116,   118,   120,   124,   126,   131,   135,   137,   141,
     145,   149,   153,   157,   161,   163,   164,   167,   172,   176,
     181,   185,   187,   191,   198,   204,   207,   209,   213,   215,
     219,   223,   227,   230,   232,   234,   236,   238,   240,   242,
     244,   250,   260,   265,   267,   271,   273,   275,   280,   282
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      51,     0,    -1,    52,    -1,    61,    -1,    -1,    54,    58,
      14,    38,    -1,    56,    14,    38,    -1,     3,    39,    56,
      40,    14,    38,    -1,    56,    14,    24,    66,    38,    -1,
      56,    14,    24,    19,    38,    -1,    57,    -1,    21,    -1,
      22,    -1,    20,    -1,    23,    -1,    59,    -1,    58,    29,
      58,    -1,    58,    30,    58,    -1,    58,    31,    58,    -1,
      58,    32,    58,    -1,    58,    34,    58,    -1,    58,    33,
      58,    -1,    14,    -1,    60,    -1,    41,    58,    42,    -1,
      15,    -1,    16,    -1,    17,    -1,    18,    -1,    43,    62,
      44,    -1,    63,    -1,    62,    38,    63,    -1,    62,    63,
      -1,    -1,    64,    -1,    70,    -1,    76,    -1,    61,    -1,
      81,    -1,    78,    -1,    77,    -1,    55,    -1,    53,    -1,
      65,    24,    66,    -1,    14,    -1,    14,    45,    66,    46,
      -1,    14,    47,    14,    -1,    67,    -1,    66,    29,    66,
      -1,    66,    30,    66,    -1,    66,    31,    66,    -1,    66,
      32,    66,    -1,    66,    34,    66,    -1,    66,    33,    66,
      -1,    14,    -1,    -1,    60,    68,    -1,    14,    41,    69,
      42,    -1,    41,    66,    42,    -1,    14,    45,    66,    46,
      -1,    14,    47,    14,    -1,    66,    -1,    69,    48,    66,
      -1,     6,    41,    72,    42,    63,    71,    -1,     6,    41,
      72,    42,    63,    -1,     5,    63,    -1,    73,    -1,    66,
      75,    66,    -1,    74,    -1,    73,    36,    73,    -1,    73,
      35,    73,    -1,    41,    72,    42,    -1,    37,    74,    -1,
      49,    -1,    39,    -1,    40,    -1,    25,    -1,    26,    -1,
      27,    -1,    28,    -1,    12,    41,    72,    42,    63,    -1,
      13,    41,    64,    38,    72,    38,    64,    42,    63,    -1,
       7,    41,    79,    42,    -1,    80,    -1,    79,    48,    80,
      -1,    66,    -1,    19,    -1,     9,    41,    82,    42,    -1,
      65,    -1,    82,    48,    65,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint8 yyrline[] =
{
       0,    67,    67,    69,    72,    72,    76,    77,    78,    79,
      82,    84,    85,    86,    87,    89,    90,    97,   104,   111,
     112,   113,   115,   116,   117,   119,   124,   129,   134,   140,
     142,   143,   144,   146,   147,   148,   149,   150,   151,   152,
     153,   154,   155,   157,   159,   160,   161,   163,   164,   165,
     166,   167,   168,   169,   171,   172,   172,   173,   174,   175,
     176,   178,   179,   181,   182,   185,   187,   188,   190,   191,
     192,   194,   195,   197,   198,   199,   200,   201,   202,   203,
     205,   207,   209,   211,   212,   214,   215,   217,   219,   220
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "LIST", "CONST", "ELSE", "IF", "WRITE",
  "PROGRAM", "READ", "THEN", "VAR", "WHILE", "FOR", "ID", "CONST_INT",
  "CONST_REAL", "CONST_CAR", "CONST_BOOL", "CONST_SIR", "CHAR", "INTEGER",
  "REAL", "BOOLEAN", "ATRIB", "NE", "LE", "GE", "EQ", "'+'", "'-'", "'*'",
  "'/'", "MOD", "DIV", "OR", "AND", "NOT", "';'", "'<'", "'>'", "'('",
  "')'", "'{'", "'}'", "'['", "']'", "'.'", "','", "'='", "$accept",
  "prog_sursa", "bloc", "decl_const", "$@1", "decl_var", "tip",
  "tip_simplu", "expr_stat", "factor_stat", "constanta", "instr_comp",
  "lista_instr", "instr", "instr_atrib", "variabila", "expresie", "factor",
  "$@2", "lista_expr", "instr_if", "ramura_else", "conditie",
  "expr_logica", "factor_logic", "op_rel", "instr_while", "instr_for",
  "instr_print", "lista_elem", "element", "instr_read", "lista_variab", YY_NULL
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,    43,
      45,    42,    47,   284,   285,   286,   287,   288,    59,    60,
      62,    40,    41,   123,   125,    91,    93,    46,    44,    61
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    50,    51,    52,    54,    53,    55,    55,    55,    55,
      56,    57,    57,    57,    57,    58,    58,    58,    58,    58,
      58,    58,    59,    59,    59,    60,    60,    60,    60,    61,
      62,    62,    62,    63,    63,    63,    63,    63,    63,    63,
      63,    63,    63,    64,    65,    65,    65,    66,    66,    66,
      66,    66,    66,    66,    67,    68,    67,    67,    67,    67,
      67,    69,    69,    70,    70,    71,    72,    72,    73,    73,
      73,    74,    74,    75,    75,    75,    75,    75,    75,    75,
      76,    77,    78,    79,    79,    80,    80,    81,    82,    82
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     1,     0,     4,     3,     6,     5,     5,
       1,     1,     1,     1,     1,     1,     3,     3,     3,     3,
       3,     3,     1,     1,     3,     1,     1,     1,     1,     3,
       1,     3,     2,     0,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     3,     1,     4,     3,     1,     3,     3,
       3,     3,     3,     3,     1,     0,     2,     4,     3,     4,
       3,     1,     3,     6,     5,     2,     1,     3,     1,     3,
       3,     3,     2,     1,     1,     1,     1,     1,     1,     1,
       5,     9,     4,     1,     3,     1,     1,     4,     1,     3
};

/* YYDEFACT[STATE-NAME] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     4,     0,     2,     3,     0,     0,     0,     0,     0,
       0,    44,    13,    11,    12,    14,    42,     0,    41,     0,
      10,    37,     4,    30,    34,     0,    35,    36,    40,    39,
      38,     1,     0,     0,     0,     0,     0,     0,     0,     0,
      22,    25,    26,    27,    28,     0,     0,    15,    23,     0,
       4,    29,    32,     0,     0,    54,     0,     0,    55,     0,
      47,     0,    66,    68,    86,     0,    85,     0,    83,    88,
       0,     0,     0,     0,    46,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     6,    31,    43,     0,     0,     0,
       0,     0,    72,     0,     0,    56,    76,    77,    78,    79,
       0,     0,     0,     0,     0,     0,    74,    75,    73,     0,
       4,     0,     0,     0,    82,     0,    87,     0,     4,     0,
      45,    24,     5,    16,    17,    18,    19,    21,    20,     0,
       0,     0,    61,     0,     0,    60,    58,    71,    48,    49,
      50,    51,    53,    52,    67,    64,    70,    69,    84,    89,
      80,     0,     9,     8,     7,    57,     0,    59,     4,    63,
       0,    62,    65,     0,     4,    81
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     3,    16,    17,    18,    19,    20,    46,    47,
      58,    21,    22,    23,    24,    25,    59,    60,    95,   133,
      26,   159,    94,    62,    63,   109,    27,    28,    29,    67,
      68,    30,    70
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -42
static const yytype_int16 yypact[] =
{
     -37,   137,     9,   -42,   -42,   -26,   -20,    -9,    -6,     2,
      20,   -33,   -42,   -42,   -42,   -42,   -42,   147,   -42,    54,
     -42,   -42,   164,   -42,   -42,    51,   -42,   -42,   -42,   -42,
     -42,   -42,    59,   104,     1,    69,   104,    69,   175,    71,
     -42,   -42,   -42,   -42,   -42,   147,    15,   -42,   -42,   -13,
     137,   -42,   -42,   175,    46,   -14,    14,   104,   -42,   217,
     -42,    45,   -12,   -42,   -42,   175,   259,     8,   -42,   -42,
      11,    47,    52,   165,   -42,   229,    55,   147,   147,   147,
     147,   147,   147,   112,   -42,   -42,   259,    80,   175,   175,
      81,   104,   -42,   192,    56,   -42,   -42,   -42,   -42,   -42,
     175,   175,   175,   175,   175,   175,   -42,   -42,   -42,   175,
      94,    14,    14,   243,   -42,     1,   -42,    69,    94,   104,
     -42,   -42,   -42,    78,    78,   -42,   -42,   -42,   -42,    64,
     249,    66,   259,    12,   206,   -42,   -42,   -42,   172,   172,
     -42,   -42,   -42,   -42,   259,   100,    77,   -42,   -42,   -42,
     -42,    85,   -42,   -42,   -42,   -42,   175,   -42,    94,   -42,
      69,   259,   -42,    91,    94,   -42
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
     -42,   -42,   -42,   -42,   -42,   -42,   102,   -42,   -41,   -42,
     -15,   135,   -42,   -22,   -36,   -25,   -31,   -42,   -42,   -42,
     -42,   -42,   -28,   -35,    83,   -42,   -42,   -42,   -42,   -42,
      32,   -42,   -42
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -34
static const yytype_int16 yytable[] =
{
      52,    72,    48,    66,    75,    61,     1,    73,    71,    31,
      69,    83,    38,    32,    39,    55,    41,    42,    43,    44,
      64,    33,    86,   111,   112,    84,    93,    88,    85,    76,
      48,    89,    34,    90,   113,    35,   123,   124,   125,   126,
     127,   128,    65,    36,    77,    78,    79,    80,    81,    82,
     114,    56,   130,   116,   155,    91,   115,   132,   134,   117,
     156,    37,    48,    48,    48,    48,    48,    48,    49,   138,
     139,   140,   141,   142,   143,    53,   146,   147,   144,    12,
      13,    14,    15,    11,    66,    74,    87,   110,   145,   118,
     119,   151,   149,   122,   131,   135,   150,     5,   137,   -33,
       6,     7,   152,     8,   154,   158,     9,    10,    11,    79,
      80,    81,    82,   112,    12,    13,    14,    15,    55,    41,
      42,    43,    44,   160,   163,   161,    55,    41,    42,    43,
      44,   129,   -33,   164,    54,     4,   162,     1,   -33,    92,
       5,    56,   165,     6,     7,    57,     8,   148,     0,     9,
      10,    11,     0,    65,     0,     0,     0,    12,    13,    14,
      15,    40,    41,    42,    43,    44,     0,     5,     0,     0,
       6,     7,     0,     8,     0,   -33,     9,    10,    11,     0,
       1,   -33,     0,     0,    12,    13,    14,    15,    45,    55,
      41,    42,    43,    44,   100,   101,   102,   103,   104,   105,
       0,     0,    50,   102,   103,   104,   105,     1,    51,     0,
       0,   120,     0,     0,     0,     0,    65,    96,    97,    98,
      99,   100,   101,   102,   103,   104,   105,     0,     0,     0,
       0,   106,   107,     0,   136,   100,   101,   102,   103,   104,
     105,   108,    96,    97,    98,    99,   100,   101,   102,   103,
     104,   105,   157,     0,     0,     0,   106,   107,    77,    78,
      79,    80,    81,    82,     0,     0,   108,     0,     0,     0,
       0,   121,   100,   101,   102,   103,   104,   105,   100,   101,
     102,   103,   104,   105,     0,   136,     0,   153,   100,   101,
     102,   103,   104,   105
};

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-42)))

#define yytable_value_is_error(Yytable_value) \
  YYID (0)

static const yytype_int16 yycheck[] =
{
      22,    37,    17,    34,    45,    33,    43,    38,    36,     0,
      35,    24,    45,    39,    47,    14,    15,    16,    17,    18,
      19,    41,    53,    35,    36,    38,    57,    41,    50,    14,
      45,    45,    41,    47,    65,    41,    77,    78,    79,    80,
      81,    82,    41,    41,    29,    30,    31,    32,    33,    34,
      42,    37,    83,    42,    42,    41,    48,    88,    89,    48,
      48,    41,    77,    78,    79,    80,    81,    82,    14,   100,
     101,   102,   103,   104,   105,    24,   111,   112,   109,    20,
      21,    22,    23,    14,   115,    14,    40,    42,   110,    42,
      38,   119,   117,    38,    14,    14,   118,     3,    42,     5,
       6,     7,    38,     9,    38,     5,    12,    13,    14,    31,
      32,    33,    34,    36,    20,    21,    22,    23,    14,    15,
      16,    17,    18,    38,   160,   156,    14,    15,    16,    17,
      18,    19,    38,    42,    32,     0,   158,    43,    44,    56,
       3,    37,   164,     6,     7,    41,     9,   115,    -1,    12,
      13,    14,    -1,    41,    -1,    -1,    -1,    20,    21,    22,
      23,    14,    15,    16,    17,    18,    -1,     3,    -1,    -1,
       6,     7,    -1,     9,    -1,    38,    12,    13,    14,    -1,
      43,    44,    -1,    -1,    20,    21,    22,    23,    41,    14,
      15,    16,    17,    18,    29,    30,    31,    32,    33,    34,
      -1,    -1,    38,    31,    32,    33,    34,    43,    44,    -1,
      -1,    46,    -1,    -1,    -1,    -1,    41,    25,    26,    27,
      28,    29,    30,    31,    32,    33,    34,    -1,    -1,    -1,
      -1,    39,    40,    -1,    42,    29,    30,    31,    32,    33,
      34,    49,    25,    26,    27,    28,    29,    30,    31,    32,
      33,    34,    46,    -1,    -1,    -1,    39,    40,    29,    30,
      31,    32,    33,    34,    -1,    -1,    49,    -1,    -1,    -1,
      -1,    42,    29,    30,    31,    32,    33,    34,    29,    30,
      31,    32,    33,    34,    -1,    42,    -1,    38,    29,    30,
      31,    32,    33,    34
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    43,    51,    52,    61,     3,     6,     7,     9,    12,
      13,    14,    20,    21,    22,    23,    53,    54,    55,    56,
      57,    61,    62,    63,    64,    65,    70,    76,    77,    78,
      81,     0,    39,    41,    41,    41,    41,    41,    45,    47,
      14,    15,    16,    17,    18,    41,    58,    59,    60,    14,
      38,    44,    63,    24,    56,    14,    37,    41,    60,    66,
      67,    72,    73,    74,    19,    41,    66,    79,    80,    65,
      82,    72,    64,    66,    14,    58,    14,    29,    30,    31,
      32,    33,    34,    24,    38,    63,    66,    40,    41,    45,
      47,    41,    74,    66,    72,    68,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    39,    40,    49,    75,
      42,    35,    36,    66,    42,    48,    42,    48,    42,    38,
      46,    42,    38,    58,    58,    58,    58,    58,    58,    19,
      66,    14,    66,    69,    66,    14,    42,    42,    66,    66,
      66,    66,    66,    66,    66,    63,    73,    73,    80,    65,
      63,    72,    38,    38,    38,    42,    48,    46,     5,    71,
      38,    66,    63,    64,    42,    63
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  However,
   YYFAIL appears to be in use.  Nevertheless, it is formally deprecated
   in Bison 2.4.2's NEWS entry, where a plan to phase it out is
   discussed.  */

#define YYFAIL		goto yyerrlab
#if defined YYFAIL
  /* This is here to suppress warnings from the GCC cpp's
     -Wunused-macros.  Normally we don't worry about that warning, but
     some users do, and we want to make it easy for users to remove
     YYFAIL uses, which will produce warnings from Bison 2.5.  */
#endif

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))

/* Error token number */
#define YYTERROR	1
#define YYERRCODE	256


/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */
#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
        break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
#else
static void
yy_stack_print (yybottom, yytop)
    yytype_int16 *yybottom;
    yytype_int16 *yytop;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULL, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULL;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - Assume YYFAIL is not used.  It's too flawed to consider.  See
       <http://lists.gnu.org/archive/html/bison-patches/2009-12/msg00024.html>
       for details.  YYERROR is fine as it does not invoke this
       function.
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULL, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
        break;
    }
}




/* The lookahead symbol.  */
int yychar;


#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval YY_INITIAL_VALUE(yyval_default);

/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       `yyss': related to states.
       `yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;

	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),
		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss_alloc, yyss);
	YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
/* Line 1792 of yacc.c  */
#line 67 "sspascal.y"
    {printf("%d\n",0);}
    break;

  case 3:
/* Line 1792 of yacc.c  */
#line 69 "sspascal.y"
    {printf("%d\n",1);}
    break;

  case 4:
/* Line 1792 of yacc.c  */
#line 72 "sspascal.y"
    {sp=0;}
    break;

  case 5:
/* Line 1792 of yacc.c  */
#line 72 "sspascal.y"
    {
		printf("*** %d %g ***\n", (yyvsp[(2) - (4)].l_val), pop());
					}
    break;

  case 6:
/* Line 1792 of yacc.c  */
#line 76 "sspascal.y"
    {printf("%d\n",2);}
    break;

  case 7:
/* Line 1792 of yacc.c  */
#line 77 "sspascal.y"
    {printf("%d\n",3);}
    break;

  case 8:
/* Line 1792 of yacc.c  */
#line 78 "sspascal.y"
    {printf("%d\n",4);}
    break;

  case 9:
/* Line 1792 of yacc.c  */
#line 79 "sspascal.y"
    {printf("%d\n",5);}
    break;

  case 10:
/* Line 1792 of yacc.c  */
#line 82 "sspascal.y"
    {printf("%d\n",6);}
    break;

  case 11:
/* Line 1792 of yacc.c  */
#line 84 "sspascal.y"
    {printf("%d\n",7);}
    break;

  case 12:
/* Line 1792 of yacc.c  */
#line 85 "sspascal.y"
    {printf("%d\n",8);}
    break;

  case 13:
/* Line 1792 of yacc.c  */
#line 86 "sspascal.y"
    {printf("%d\n",9);}
    break;

  case 14:
/* Line 1792 of yacc.c  */
#line 87 "sspascal.y"
    {printf("%d\n",10);}
    break;

  case 15:
/* Line 1792 of yacc.c  */
#line 89 "sspascal.y"
    {printf("%d\n",11);}
    break;

  case 16:
/* Line 1792 of yacc.c  */
#line 90 "sspascal.y"
    {
							printf("%d\n",12);
			if((yyvsp[(1) - (3)].l_val)==TIP_REAL || (yyvsp[(3) - (3)].l_val)==TIP_REAL) (yyval.l_val)=TIP_REAL;
			else if((yyvsp[(1) - (3)].l_val)==TIP_CAR) (yyval.l_val)=TIP_CAR;
				else (yyval.l_val)=TIP_INT;
			push(pop()+pop());
						}
    break;

  case 17:
/* Line 1792 of yacc.c  */
#line 97 "sspascal.y"
    {
							printf("%d\n",13);
			if((yyvsp[(1) - (3)].l_val)==TIP_REAL || (yyvsp[(3) - (3)].l_val)==TIP_REAL) (yyval.l_val)=TIP_REAL;
			else if((yyvsp[(1) - (3)].l_val)==TIP_CAR) (yyval.l_val)=TIP_CAR;
				else (yyval.l_val)=TIP_INT;
			push(-pop()+pop());
						}
    break;

  case 18:
/* Line 1792 of yacc.c  */
#line 104 "sspascal.y"
    {
							printf("%d\n",14);
			if((yyvsp[(1) - (3)].l_val)==TIP_REAL || (yyvsp[(3) - (3)].l_val)==TIP_REAL) (yyval.l_val)=TIP_REAL;
			else if((yyvsp[(1) - (3)].l_val)==TIP_CAR) (yyval.l_val)=TIP_CAR;
				else (yyval.l_val)=TIP_INT;
			push(pop()*pop());
						}
    break;

  case 19:
/* Line 1792 of yacc.c  */
#line 111 "sspascal.y"
    {printf("%d\n",15);}
    break;

  case 20:
/* Line 1792 of yacc.c  */
#line 112 "sspascal.y"
    {printf("%d\n",16);}
    break;

  case 21:
/* Line 1792 of yacc.c  */
#line 113 "sspascal.y"
    {printf("%d\n",17);}
    break;

  case 22:
/* Line 1792 of yacc.c  */
#line 115 "sspascal.y"
    {printf("%d\n",18);}
    break;

  case 23:
/* Line 1792 of yacc.c  */
#line 116 "sspascal.y"
    {printf("%d\n",19);}
    break;

  case 24:
/* Line 1792 of yacc.c  */
#line 117 "sspascal.y"
    {printf("%d\n",20);(yyval.l_val) = (yyvsp[(2) - (3)].l_val);}
    break;

  case 25:
/* Line 1792 of yacc.c  */
#line 119 "sspascal.y"
    {
							printf("%d\n",21);
			(yyval.l_val) = TIP_INT;
			push(atof((yyvsp[(1) - (1)].p_val)));
				}
    break;

  case 26:
/* Line 1792 of yacc.c  */
#line 124 "sspascal.y"
    {
							printf("%d\n",22);
			(yyval.l_val) = TIP_REAL;
			push(atof((yyvsp[(1) - (1)].p_val)));
				}
    break;

  case 27:
/* Line 1792 of yacc.c  */
#line 129 "sspascal.y"
    {
							printf("%d\n",23);
			(yyval.l_val) = TIP_CAR;
			push((double)(yyvsp[(1) - (1)].p_val)[0]);
				}
    break;

  case 28:
/* Line 1792 of yacc.c  */
#line 134 "sspascal.y"
    {
							printf("%d\n",83);
			(yyval.l_val) = TIP_BOOL;
			push((double)(yyvsp[(1) - (1)].p_val)[0]);
				}
    break;

  case 29:
/* Line 1792 of yacc.c  */
#line 140 "sspascal.y"
    {printf("%d\n",24);}
    break;

  case 30:
/* Line 1792 of yacc.c  */
#line 142 "sspascal.y"
    {printf("%d\n",25);}
    break;

  case 31:
/* Line 1792 of yacc.c  */
#line 143 "sspascal.y"
    {printf("%d\n",26);}
    break;

  case 32:
/* Line 1792 of yacc.c  */
#line 144 "sspascal.y"
    {printf("%d\n",27);}
    break;

  case 34:
/* Line 1792 of yacc.c  */
#line 147 "sspascal.y"
    {printf("%d\n",28);}
    break;

  case 35:
/* Line 1792 of yacc.c  */
#line 148 "sspascal.y"
    {printf("%d\n",29);}
    break;

  case 36:
/* Line 1792 of yacc.c  */
#line 149 "sspascal.y"
    {printf("%d\n",30);}
    break;

  case 37:
/* Line 1792 of yacc.c  */
#line 150 "sspascal.y"
    {printf("%d\n",31);}
    break;

  case 38:
/* Line 1792 of yacc.c  */
#line 151 "sspascal.y"
    {printf("%d\n",32);}
    break;

  case 39:
/* Line 1792 of yacc.c  */
#line 152 "sspascal.y"
    {printf("%d\n",33);}
    break;

  case 40:
/* Line 1792 of yacc.c  */
#line 153 "sspascal.y"
    {printf("%d\n",34);}
    break;

  case 41:
/* Line 1792 of yacc.c  */
#line 154 "sspascal.y"
    {printf("%d\n",35);}
    break;

  case 42:
/* Line 1792 of yacc.c  */
#line 155 "sspascal.y"
    {printf("%d\n",36);}
    break;

  case 43:
/* Line 1792 of yacc.c  */
#line 157 "sspascal.y"
    {printf("%d\n",37);}
    break;

  case 44:
/* Line 1792 of yacc.c  */
#line 159 "sspascal.y"
    {printf("%d\n",38);}
    break;

  case 45:
/* Line 1792 of yacc.c  */
#line 160 "sspascal.y"
    {printf("%d\n",39);}
    break;

  case 46:
/* Line 1792 of yacc.c  */
#line 161 "sspascal.y"
    {printf("%d\n",40);}
    break;

  case 47:
/* Line 1792 of yacc.c  */
#line 163 "sspascal.y"
    {printf("%d\n",41);}
    break;

  case 48:
/* Line 1792 of yacc.c  */
#line 164 "sspascal.y"
    {printf("%d\n",42);}
    break;

  case 49:
/* Line 1792 of yacc.c  */
#line 165 "sspascal.y"
    {printf("%d\n",43);}
    break;

  case 50:
/* Line 1792 of yacc.c  */
#line 166 "sspascal.y"
    {printf("%d\n",44);}
    break;

  case 51:
/* Line 1792 of yacc.c  */
#line 167 "sspascal.y"
    {printf("%d\n",45);}
    break;

  case 52:
/* Line 1792 of yacc.c  */
#line 168 "sspascal.y"
    {printf("%d\n",46);}
    break;

  case 53:
/* Line 1792 of yacc.c  */
#line 169 "sspascal.y"
    {printf("%d\n",47);}
    break;

  case 54:
/* Line 1792 of yacc.c  */
#line 171 "sspascal.y"
    {printf("%d\n",48);}
    break;

  case 55:
/* Line 1792 of yacc.c  */
#line 172 "sspascal.y"
    {}
    break;

  case 56:
/* Line 1792 of yacc.c  */
#line 172 "sspascal.y"
    {printf("%d\n",49);}
    break;

  case 57:
/* Line 1792 of yacc.c  */
#line 173 "sspascal.y"
    {printf("%d\n",50);}
    break;

  case 58:
/* Line 1792 of yacc.c  */
#line 174 "sspascal.y"
    {printf("%d\n",51);}
    break;

  case 59:
/* Line 1792 of yacc.c  */
#line 175 "sspascal.y"
    {printf("%d\n",52);}
    break;

  case 60:
/* Line 1792 of yacc.c  */
#line 176 "sspascal.y"
    {printf("%d\n",53);}
    break;

  case 61:
/* Line 1792 of yacc.c  */
#line 178 "sspascal.y"
    {printf("%d\n",54);}
    break;

  case 62:
/* Line 1792 of yacc.c  */
#line 179 "sspascal.y"
    {printf("%d\n",55);}
    break;

  case 63:
/* Line 1792 of yacc.c  */
#line 181 "sspascal.y"
    {printf("%d\n",56);}
    break;

  case 64:
/* Line 1792 of yacc.c  */
#line 182 "sspascal.y"
    {printf("%d\n",57);}
    break;

  case 65:
/* Line 1792 of yacc.c  */
#line 185 "sspascal.y"
    {printf("%d\n",58);}
    break;

  case 66:
/* Line 1792 of yacc.c  */
#line 187 "sspascal.y"
    {printf("%d\n",59);}
    break;

  case 67:
/* Line 1792 of yacc.c  */
#line 188 "sspascal.y"
    {printf("%d\n",60);}
    break;

  case 68:
/* Line 1792 of yacc.c  */
#line 190 "sspascal.y"
    {printf("%d\n",61);}
    break;

  case 69:
/* Line 1792 of yacc.c  */
#line 191 "sspascal.y"
    {printf("%d\n",62);}
    break;

  case 70:
/* Line 1792 of yacc.c  */
#line 192 "sspascal.y"
    {printf("%d\n",63);}
    break;

  case 71:
/* Line 1792 of yacc.c  */
#line 194 "sspascal.y"
    {printf("%d\n",64);}
    break;

  case 72:
/* Line 1792 of yacc.c  */
#line 195 "sspascal.y"
    {printf("%d\n",65);}
    break;

  case 73:
/* Line 1792 of yacc.c  */
#line 197 "sspascal.y"
    {printf("%d\n",66);}
    break;

  case 74:
/* Line 1792 of yacc.c  */
#line 198 "sspascal.y"
    {printf("%d\n",67);}
    break;

  case 75:
/* Line 1792 of yacc.c  */
#line 199 "sspascal.y"
    {printf("%d\n",68);}
    break;

  case 76:
/* Line 1792 of yacc.c  */
#line 200 "sspascal.y"
    {printf("%d\n",69);}
    break;

  case 77:
/* Line 1792 of yacc.c  */
#line 201 "sspascal.y"
    {printf("%d\n",70);}
    break;

  case 78:
/* Line 1792 of yacc.c  */
#line 202 "sspascal.y"
    {printf("%d\n",71);}
    break;

  case 79:
/* Line 1792 of yacc.c  */
#line 203 "sspascal.y"
    {printf("%d\n",72);}
    break;

  case 80:
/* Line 1792 of yacc.c  */
#line 205 "sspascal.y"
    {printf("%d\n",73);}
    break;

  case 81:
/* Line 1792 of yacc.c  */
#line 207 "sspascal.y"
    {printf("%d\n",74);}
    break;

  case 82:
/* Line 1792 of yacc.c  */
#line 209 "sspascal.y"
    {printf("%d\n",75);}
    break;

  case 83:
/* Line 1792 of yacc.c  */
#line 211 "sspascal.y"
    {printf("%d\n",76);}
    break;

  case 84:
/* Line 1792 of yacc.c  */
#line 212 "sspascal.y"
    {printf("%d\n",77);}
    break;

  case 85:
/* Line 1792 of yacc.c  */
#line 214 "sspascal.y"
    {printf("%d\n",78);}
    break;

  case 86:
/* Line 1792 of yacc.c  */
#line 215 "sspascal.y"
    {printf("%d\n",79);}
    break;

  case 87:
/* Line 1792 of yacc.c  */
#line 217 "sspascal.y"
    {printf("%d\n",80);}
    break;

  case 88:
/* Line 1792 of yacc.c  */
#line 219 "sspascal.y"
    {printf("%d\n",81);}
    break;

  case 89:
/* Line 1792 of yacc.c  */
#line 220 "sspascal.y"
    {printf("%d\n",82);}
    break;


/* Line 1792 of yacc.c  */
#line 2121 "sspascal.tab.c"
      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


/* Line 2055 of yacc.c  */
#line 223 "sspascal.y"


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

