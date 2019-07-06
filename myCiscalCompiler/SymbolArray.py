#name:		Sirinian Aram Emmanouil

import string;
import sys;
import os.path;

sp = 12;

class Scope:
	
	def __init__(self, nestingLevel):
		self.nestingLevel = nestingLevel;
		
	def toString(self):
		return str(self.nestingLevel);
		
class Entity:
	
	def __init__(self, entityType):
		if( ((entityType == 'variable') or (entityType == 'function') or (entityType == 'procedure') or (entityType == 'constant') or (entityType == 'parameter') or (entityType == 'temporary')) == False ):
			print("Entity type must be 'variable' or 'function' or 'constant' or 'parameter' or 'temporary'");
			exit;
		self.entityType = entityType;
		
	def setEntityTypeVariable(self, name):
		global sp;
		self.name = name;
		self.offset = sp;
		sp += 4;
		
	def setEntityTypeFunction(self, name):	
		self.name = name;
	
	def setEntityTypeProcedure(self, name):
		self.name = name;
		
	def setEntityTypeParameter(self, name, parMode):
		global sp;
		self.name = name;
		self.parMode = parMode;
		self.offset = sp;
		sp += 4;
	
	def setEntityTypeTemporaryVariable(self, name):
		global sp;
		self.name = name;
		self.offset = sp;
		sp += 4;
		
	def toString(self):
		tempString = "";
		tempString += "[entity type: " + self.entityType + " ";
		if(self.entityType == "variable"):
			tempString += "name: " + self.name + " " + "offset: " + str(self.offset) + " ";
		elif(self.entityType == "function"):
			tempString += "name: " + self.name + " ";
		elif(self.entityType == "procedure"):
			tempString += "name: " + self.name + " ";
		elif(self.entityType == "parameter"):
			tempString += "name: " + self.name + " " + "parMode: " + self.parMode + " " + "offset: " + str(self.offset) + " ";
		elif(self.entityType == "temporary"):
			tempString += "name: " + self.name + " " + "offset: " + str(self.offset) + " ";
		else:
			print("Error in toString() function of Entity");
			exit;
		tempString += "]";
		return tempString;


class Argument:
	
	global sp;
	def __init__(self, parMode):
		self.parMode = parMode;


class SymbolArray:
	
	def __init__(self):
		self.symbolList = [];
		
	def createNewLayer(self):
		global sp;
		tempScope = Scope(len(self.symbolList));
		newLayerList = [];
		newLayerList.append(tempScope);
		self.symbolList.append(newLayerList);
		sp = 12;
		self.toString();
		print("======================================================");
		
	def insertNewEntity(self, entity):
		lastLayer = len(self.symbolList) - 1;
		self.symbolList[lastLayer].append(entity);
		self.toString();
		print("======================================================");
		
	def deleteLastLayer(self):
		self.symbolList.pop();
		
	def toString(self):
		for index1 in range(len(self.symbolList)):
			tempString = "scope: ";
			tempList = self.symbolList[index1];
			for index2 in range(len(tempList)):
				tempString += tempList[index2].toString() + " ";
			print(tempString);
			print("");
