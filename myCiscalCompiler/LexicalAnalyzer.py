#name:		Sirinian Aram Emmanouil
#AM:		2537
#username:	cse32537

import string;
import sys;
import os.path;
from HelpfullDef import *;

numberOfArguments = len(sys.argv);
if(numberOfArguments < 2):
	print("<Lexical Analyzer>Error source code must be given as an argument");
	sys.exit(-1);
argumentList = (sys.argv);
filePath = argumentList[1];
currentFilePositionPath = "counterFile.txt";
currentFileLinePath = "lineFile.txt";

sizeToRead = 1;
letters = list(string.ascii_lowercase) + list(string.ascii_uppercase);
digits = list("0123456789");
delims = list(string.whitespace); 
arithmeticΟperationSymbols = list("+-*/=");

identifiers = ["and", "exit", "if", "program", "declare",
 "procedure", "in", "or", "do", "function", "inout", "return",
 "else", "print", "not", "while", "enddeclare", "call", "select",
 "default"];
  
myTokenMap = {"and": "1", "exit": "2", "if": "3", "program": "4",
 "declare": "5", "procedure": "6", "in": "7", "or": "8", "do": "9", 
 "function": "10", "inout": "11", "return": "12", "else": "13",
 "print": "14", "not": "15", "while": "16", "enddeclare": "17",
 "call": "18", "select": "19", "default": "20", "+": "21", "-":"22",
 "*": "23", "/": "24", "=": "25", "<": "26", "<=": "27", "<>": "28",
 ">": "29", ">=": "27", ":": "28", ":=": "29", "{": "30", "}": "31",
 ",": "32", ";": "33", "(": "34", ")": "35", "[": "36", "]": "37"};
#idLetter= 38, idDigit= 39, EOF=40

def lex():
	state = 0; #stateOK == -1 stateError == -2
	result = list();
	strResult = str();
	inputCharacter = str();
	tempCharacter = str();
	tempCharacter2 = str();
	fileCounter = 0;
	lineCounter = 1;
	eofFlag = False;
	with open(currentFilePositionPath, 'r') as rb:
		counterFileContent = rb.readline();
		fileCounter = int(counterFileContent);

	with open(currentFileLinePath, 'r') as rb:
		currentFileLineContent = rb.readline();
		lineCounter = int(currentFileLineContent);

	with open(filePath, 'r') as a:
		a.seek(fileCounter);
		inputCharacter = a.read(sizeToRead);
		fileCounter += 1;

	while((state != -1) and (state != -2)):
		if(state == 0 and (inputCharacter in delims)):
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				inputCharacter = a.read(sizeToRead);
				fileCounter += 1;
				if(inputCharacter == ""):
					eofFlag = True;
					result = ["EOF", "40"];
					break;
				if(inputCharacter == '\n'):
					lineCounter += 1;
					writeToCFile(str(lineCounter), currentFileLinePath);	
		elif(state == 0 and (inputCharacter in letters)):
			state = 1;
			strResult += inputCharacter;
		elif(state == 0 and (inputCharacter in digits)):
			state = 2;
			strResult += inputCharacter;
		elif(state == 0 and (inputCharacter in arithmeticΟperationSymbols)):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == '<')):
			state = 3;
		elif(state == 0 and (inputCharacter == '>')):
			state = 4;	
		elif(state == 0 and (inputCharacter == ':')):
			state = 5;
		elif(state == 0 and (inputCharacter == '\\')):
			state = 6;
		elif(state == 0 and (inputCharacter == '{')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == '}')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == ',')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == ';')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == '(')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == ')')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == '[')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0 and (inputCharacter == ']')):
			strResult += inputCharacter;
			state = -1;
			result = [strResult, getToken(strResult)];
		elif(state == 0):
			strResult += inputCharacter;
			print("<Lexical Analyzer> Character %s is not supported in Ciscal." %strResult);
			state = -2;

		if(state == 1):
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				tempCharacter = a.read(sizeToRead);
			if((tempCharacter in letters) or (tempCharacter in digits)):
				strResult += tempCharacter;
				fileCounter += 1;
			else:
				state = -1;
				if(getToken(strResult) == None):
					result = [strResult, "38"];
				else:
					result = [strResult, getToken(strResult)];
				
		if(state == 2):
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				tempCharacter = a.read(sizeToRead);
			if(tempCharacter in digits):
				strResult += tempCharacter;
				fileCounter += 1;
			elif(tempCharacter in letters):
				print("<Lexical Analyzer> Error. Found variable starting with digits.");
				state = -2;
			else:
				if(int(strResult) > 32766):
					print("<Lexical Analyzer> Error. Found variable with value greater than 32766.");
					state = -2;
				else:
					state = -1;
					result = [strResult, "39"];

		if(state == 3):
			strResult += inputCharacter;
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				tempCharacter = a.read(sizeToRead);
			if(tempCharacter == '='):
				strResult += tempCharacter;
				fileCounter += 1;
				state = -1;
				result = [strResult, getToken(strResult)];
			elif(tempCharacter == '>'):
				strResult += tempCharacter;
				fileCounter += 1;
				state = -1;
				result = [strResult, getToken(strResult)];
			else:
				state = -1;
				result = [strResult, getToken(strResult)];

		if(state == 4):
			strResult += inputCharacter;
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				tempCharacter = a.read(sizeToRead);
			if(tempCharacter == '='):
				strResult += tempCharacter;
				fileCounter += 1;
				state = -1;
				result = [strResult, getToken(strResult)];
			else:
				state = -1;
				result = [strResult, getToken(strResult)];
				
		if(state == 5):
			strResult += inputCharacter;
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				tempCharacter = a.read(sizeToRead);
			if(tempCharacter == '='):
				strResult += tempCharacter;
				fileCounter += 1;
				state = -1;
				result = [strResult, getToken(strResult)];
			elif(tempCharacter == '{'):
				state = -1;
				result = [strResult, getToken(strResult)];
			else:
				print("<Lexical Analyzer> Error. ':' misplaced");
				state = -2;

		if((state == 6) or (state == 7)):
			with open(filePath, 'r') as a:
				a.seek(fileCounter);
				inputCharacter = a.read(sizeToRead);
				if(inputCharacter == ""):
					eofFlag = True;
					result = ["EOF", "40"];
					break;

			if((state == 6) and (inputCharacter == '*')):
				state = 7;
				fileCounter += 1;
				with open(filePath, 'r') as a:
					a.seek(fileCounter);
					inputCharacter = a.read(sizeToRead);
					if(inputCharacter == ""):
						eofFlag = True;
						result = ["EOF", "40"];
						break;
			elif((state == 6) and (inputCharacter != '*')):
				print("<Lexical Analyzer> ERROR");
				fileCounter += 1;
				state = -2;
		
			if((state == 7) and (inputCharacter == '*')):
				tempCharacter2 = '*';
				fileCounter += 1;
				with open(filePath, 'r') as a:
					a.seek(fileCounter);
					inputCharacter = a.read(sizeToRead);
					if(inputCharacter == ""):
						eofFlag = True;
						result = ["EOF", "40"];
						break;
			elif((state == 7) and (tempCharacter2 == '*') and (inputCharacter == '\\')):
				tempCharacter2 = str();
				state = 0;
				fileCounter += 1;
				with open(filePath, 'r') as a:
					a.seek(fileCounter);
					inputCharacter = a.read(sizeToRead);
				fileCounter +=1;
			else:	
				fileCounter += 1;
				with open(filePath, 'r') as a:
					a.seek(fileCounter);
					inputCharacter = a.read(sizeToRead);
					if(inputCharacter == ""):
						eofFlag = True;
						result = ["EOF", "40"];
						break;
	writeToCFile(str(fileCounter), currentFilePositionPath);
	if(eofFlag):
		writeToCFile("0", "./counterFile.txt");
		writeToCFile("0", "./lineFile.txt");
	return result;

def getToken(key):
	return myTokenMap.get(key);
