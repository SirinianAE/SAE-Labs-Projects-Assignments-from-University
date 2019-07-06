#name:		Sirinian Aram Emmanouil
#AM:		2537
#username:	cse32537

import sys;
import os.path;
import string;
from LexicalAnalyzer import *;
from SyntaxAnalyzer import *;
from IntermediateCodeProduction import *;
from SymbolArray import *;
from IntToC import *;

def createNecessaryFiles():
	if(os.path.isfile("./counterFile.txt") == False):
		writeToCFile("0", "./counterFile.txt");
	if(os.path.isfile("./lineFile.txt") == False):
		writeToCFile("0", "./lineFile.txt");
	if(os.path.isfile("./intermediateCode.int") == False):
		writeToCFile("", "./intermediateCode.int");
	if(os.path.isfile("./test.c") == False):
		writeToCFile("", "./test.c");

try:
	createNecessaryFiles();
	syntaxAnalyzer();
	intermediateCodeToCCode();
except:
	etype, foo, traceback = sys.exc_info();
	print("Fatal Error: %s" %foo);
	writeToCFile("0", "./counterFile.txt");
	writeToCFile("0", "./lineFile.txt");
	writeToCFile("", "./intermediateCode.int");
	writeToCFile("", "./test.c");
else:
	print("\n Program completed successfully :D !!!");
