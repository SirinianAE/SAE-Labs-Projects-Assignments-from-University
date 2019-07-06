#name:		Sirinian Aram Emmanouil

import string;
import sys;
import os.path;
from HelpfullDef import *;

variableBuffer = [];
intermediateCodeLines = [];
tempList1 = [];
tempList2 = [];
tempList3 = [];

def intermediateCodeToCCode():
	global variableBuffer;
	global intermediateCodeLines;
	global tempList1;
	global tempList2;
	global tempList3;
	writeFirstProgramLines();
	for index1 in range(len(intermediateCodeLines)):
		writeTags(index1);
		decodeAndWriteIntermediateCode();
	writeLastProgramLines();
	writeVariableInitialization();
	
def decodeAndWriteIntermediateCode():
	global tempList1;
	global tempList2;
	global tempList3;
	if (tempList2[1] == ":="):
		tempString2 = tempList3[0] + "=" + tempList1[1] + ";";
		checkForNewVariable(tempList3[0]);
		checkForNewVariable(tempList1[1]);
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "jump"):
		tempString2 = "goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "<"):
		tempString2 = "if (" + tempList1[1] + "<" + tempList1[2] + ") goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == ">"):
		tempString2 = "if (" + tempList1[1] + ">" + tempList1[2] + ") goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "="):
		tempString2 = "if (" + tempList1[1] + "==" + tempList1[2] + ") goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "<>"):
		tempString2 = "if (" + tempList1[1] + "!=" + tempList1[2] + ") goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == ">="):
		tempString2 = "if (" + tempList1[1] + ">=" + tempList1[2] + ") goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "<="):
		tempString2 = "if (" + tempList1[1] + "<=" + tempList1[2] + ") goto L_" + tempList3[0] + ";";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "+"):
		tempString2 = tempList3[0] + "=" + tempList1[1] + tempList2[1] + tempList1[2] + ";";
		checkForNewVariable(tempList3[0]);
		checkForNewVariable(tempList1[1]);
		checkForNewVariable(tempList1[2]);
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "-"):
		tempString2 = tempList3[0] + "=" + tempList1[1] + tempList2[1] + tempList1[2] + ";";
		checkForNewVariable(tempList3[0]);
		checkForNewVariable(tempList1[1]);
		checkForNewVariable(tempList1[2]);
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "*"):
		tempString2 = tempList3[0] + "=" + tempList1[1] + tempList2[1] + tempList1[2] + ";";
		checkForNewVariable(tempList3[0]);
		checkForNewVariable(tempList1[1]);
		checkForNewVariable(tempList1[2]);
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "/"):
		tempString2 = tempList3[0] + "=" + tempList1[1] + tempList2[1] + tempList1[2] + ";";
		checkForNewVariable(tempList3[0]);
		checkForNewVariable(tempList1[1]);
		checkForNewVariable(tempList1[2]);
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "end_block"):
		tempString2 = "{}";
		appendToCFile(tempString2, "./test.c");
	elif (tempList2[1] == "out"):
		tempString2 = 'printf("%d\\n", ' + tempList1[1] + ");";
		appendToCFile(tempString2, "./test.c");
	appendToCFile("\n", "./test.c");

def writeTags(index1):
	global intermediateCodeLines;
	global tempList1;
	global tempList2;
	global tempList3;
	tempList1 = intermediateCodeLines[index1].split(",");
	tempList2 = tempList1[0].split(": ");
	tempString = "	L_" + str(tempList2[0]) + ": ";
	appendToCFile(tempString, "./test.c")
	tempList3 = tempList1[3].split("\n");
	
def writeLastProgramLines():
	appendToCFile("}", "./test.c")
		
def writeFirstProgramLines():
	global intermediateCodeLines;
	with open("./intermediateCode.int", 'r') as tempr:
		intermediateCodeLines = tempr.readlines();
	writeToCFile("#include <stdio.h>\n\nint main()\n", "./test.c");
	appendToCFile("{\n", "./test.c");

def writeVariableInitialization():
	global variableBuffer;
	positionForVariables = 4;
	tempStringOfVariables = "	int ";
	tempTestCodeLines = [];
	for index in range(len(variableBuffer)):
		if(index == len(variableBuffer) - 1):
			tempStringOfVariables += variableBuffer[index] + ";";
		else:
			tempStringOfVariables += variableBuffer[index] + ",";
	tempStringOfVariables += "\n";
	with open("./test.c", 'r') as tempr:
		tempTestCodeLines = tempr.readlines();
	writeToCFile("", "./test.c");
	with open("./test.c", 'a') as tempa:
		for index in range(len(tempTestCodeLines)):
			if(index == positionForVariables):
				tempa.write(tempStringOfVariables);
			tempa.write(tempTestCodeLines[index]);

def checkForNewVariable(stringThatMayBeInt):
	global variableBuffer;
	if(isNumber(stringThatMayBeInt) == False):
		if(stringThatMayBeInt in variableBuffer):
			pass;
		else:
			variableBuffer.append(stringThatMayBeInt);
