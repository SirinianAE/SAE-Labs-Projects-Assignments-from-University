#name:		Sirinian Aram Emmanouil

import sys;
import os.path;
from HelpfullDef import *;

foursomeBuffer = [];
foursome = [];
tempVariableNumber = 0;
quadNumber = 0;

def nextquad():
	nextquadNumber = quadNumber + 1;
	return nextquadNumber;

def genquad(op, x, y, z):
	global foursome;
	global quadNumber;
	foursome = [];
	foursome.append(str(op));
	foursome.append(str(x));
	foursome.append(str(y));
	foursome.append(str(z));
	quadNumber += 1;
	saveResults();

def newtemp():
	global tempVariableNumber;
	tempVariableNumber += 1;
	tempVariable = "T_" + str(tempVariableNumber);
	return tempVariable;

def emptylist():
	newTempList = list();
	return newTempList;

def makelist(x):
	newTempList = list();
	newTempList.append(x);
	return newTempList;

def merge(list1, list2):
	mergedList = list1 + list2;
	return mergedList;

def clearIntermediateCodeFile():
	if(os.path.isfile("./intermediateCode.int") == True):
		writeToCFile("", "./intermediateCode.int");

def saveResults():
	tempList = [str(quadNumber)] + foursome;
	foursomeBuffer.append(tempList);

def backpatch(inList, z):
	for list1 in inList:
		for list2 in foursomeBuffer:
			if(str(list2[0]) == str(list1)):
				list2[4] = str(z);

def writeIntermediateCode():
	for list1 in foursomeBuffer:
		appendToCFile(list1[0] + ": " + list1[1] + "," + list1[2] + "," + list1[3] + "," + list1[4] + "\n", "./intermediateCode.int");
