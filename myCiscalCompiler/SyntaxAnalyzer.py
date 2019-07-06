#name:		Sirinian Aram Emmanouil

#idLetter= 38, idDigit= 39, EOF=40

from LexicalAnalyzer import *;
from IntermediateCodeProduction import *;
from SymbolArray import *;
import sys;

tempRelationalOper = " ";
callStatID = " ";
callStatList = [];
token = [];
entityRecord = [];
scopeRecord = [];
argumentRecord = [];
symbolArray = SymbolArray();

def syntaxAnalyzer():
	clearIntermediateCodeFile();
	updateToken();
	while(1):
		if(token[1] == getToken("program")):
			program();
		elif(token[1] == "40"):
			break;
		else:
			print('"program" was expected');
			break;
	writeIntermediateCode();


def program():
	symbolArray.createNewLayer();
	if(token[1] == getToken("program")):
		updateToken();
		if(token[1] == "38"):
			blockName = token[0];
			genquad("begin_block", blockName, " ", " ");
			updateToken();
			block();
			genquad("halt", " ", " ", " ");
			genquad("end_block", blockName, " ", " ");
		else:
			print("program name was expected");
			sys.exit();
	else:
		print("the keyword 'program' was expected");
		sys.exit(); 

def block():
	if(token[1] == getToken("{")):
		updateToken();
		declarations();
		subprograms();
		sequence();
		if(token[1] == getToken("}")):
			updateToken();
		else:
			print("'}' was expected in block()");
			sys.exit();
	else:
		print("'{' was expected in block()");
		sys.exit();

def declarations():
	if(token[1] == getToken("declare")):
		updateToken();
		varlist();
		if(token[1] == getToken("enddeclare")):
			updateToken();
		else:
			print("enddeclare was expected");
			sys.exit();

def varlist():
	if(token[1] == "38"):
		newEntity = Entity("variable");
		newEntity.setEntityTypeVariable(token[0]);
		symbolArray.insertNewEntity(newEntity);
		updateToken();
		while(token[1] == getToken(",")):
			updateToken();
			varlist();

def subprograms():
	while(token[1] == getToken("procedure") or token[1] == getToken("function")):
		func();

def func():
	if(token[1] == getToken("procedure")):
		updateToken();
		if(token[1] == "38"):
			newEntity = Entity("procedure");
			newEntity.setEntityTypeProcedure(token[0]);
			symbolArray.insertNewEntity(newEntity);
			symbolArray.createNewLayer();
			updateToken();
			funcbody();
		else:
			print("procedure name was expected");
		symbolArray.deleteLastLayer();
	elif(token[1] == getToken("function")):
		updateToken();
		if(token[1] == "38"):
			newEntity = Entity("function");
			newEntity.setEntityTypeFunction(token[0]);
			symbolArray.insertNewEntity(newEntity);
			symbolArray.createNewLayer();
			updateToken();
			funcbody();
		else:
			print("function name was expected");
			sys.exit();
		symbolArray.deleteLastLayer();
	else:
		print("the keyword 'procedure' or 'function' was expected");
		sys.exit();

def funcbody():
	formalpars();
	block();

def formalpars():
	if(token[1] == getToken("(")):
		updateToken();
		if(token[1] == getToken("in") or token == getToken("inout")):
			formalparlist();
		if(token[1] == getToken(")")):
			updateToken();
		else:
			print("')' was expected");
			sys.exit();
	else:
		print("'(' was expected");
		sys.exit();

def formalparlist():
	if(token[1] == getToken("in") or token[1] == getToken("inout")):
		formalparitem();
		while(token[1] == getToken(",")):
			updateToken();
			formalparitem();

def formalparitem():
	if(token[1] == getToken("in")):
		updateToken();
		if(token[1] == "38"):
			newEntity = Entity("parameter");
			newEntity.setEntityTypeParameter(token[0], "in");
			symbolArray.insertNewEntity(newEntity);
			updateToken();
		else:
			print("parameter name was expected");
			sys.exit();
	elif(token[1] == getToken("inout")):
		updateToken();
		if(token[1] == "38"):
			newEntity = Entity("parameter");
			newEntity.setEntityTypeParameter(token[0], "inout");
			symbolArray.insertNewEntity(newEntity);
			updateToken();
		else:
			print("parameter name was expected");
			sys.exit();
	else:
		print("the keyword 'in' or 'inout' was expected");
		sys.exit();

def sequence():
	while(token[1] == "38" or token[1] == getToken("if") or token[1] == getToken("do")
	 or token[1] == getToken("while") or token[1] == getToken("select") or 
	 token[1] == getToken("exit") or token[1] == getToken("return") or 
	 token[1] == getToken("print") or token[1] == getToken("call")):
		statement();
		if(token[1] == getToken(";")):
			updateToken();
		else:
			print("';' was expected in sequence()");
			sys.exit();

def bracketsSeq():
	if(token[1] == getToken("{")):
		updateToken();
		sequence();
		if(token[1] == getToken("}")):
			updateToken();
		else:
			print("'}' was expected in bracketsSeq()");
			sys.exit();
	else:
		print("'{' was expected in bracketsSeq()");
		sys.exit();

def brackOrStat():
	if(token[1] == getToken("{")):
		bracketsSeq();
	else:
		statement();

def statement():
	if(token[1] == "38"):
		assignmentStat();
	elif(token[1] == getToken("if")):
		ifStat();
	elif(token[1] == getToken("do")):
		doWhileStat();
	elif(token[1] == getToken("while")):
		whileStat();
	elif(token[1] == getToken("select")):
		selectStat();
	elif(token[1] == getToken("exit")):
		exitStat();
	elif(token[1] == getToken("return")):
		returnStat();
	elif(token[1] == getToken("print")):
		printStat();
	elif(token[1] == getToken("call")):
		callStat();
	else:
		pass;

def assignmentStat():
	if(token[1] == "38"):
		tempID = token[0];
		tempE = token[0];
		updateToken();
		if(token[1] == getToken(":=")):
			updateToken();
			tempE = expression();
			genquad(":=", tempE, " ", tempID);
		else:
			print("':=' was expected");
			sys.exit();
	else:
		print("variable name was expected");
		sys.exit();

def ifStat():
	if(token[1] == getToken("if")):
		updateToken();
		if(token[1] == getToken("(")):
			updateToken();
			tempList = condition();
			if(token[1] == getToken(")")):
				backpatch(tempList[0], nextquad());
				updateToken();
				brackOrStat();
				ifStatList = makelist(nextquad());
				genquad("jump", " ", " ", " ");
				backpatch(tempList[1], nextquad());
				elsepart();
				backpatch(ifStatList, nextquad());
			else:
				print("')' was expected");
				sys.exit();
		else:
			print("'(' was expected");
			sys.exit();

def elsepart():
	if(token[1] == getToken("else")):
		updateToken();
		brackOrStat();

def whileStat():
	if(token[1] == getToken("while")):
		bquad = nextquad();
		updateToken();
		if(token[1] == getToken("(")):
			updateToken();
			tempList = condition();
			if(token[1] == getToken(")")):
				backpatch(tempList[0], nextquad());
				updateToken();
				brackOrStat();
				genquad("jump", " ", " ", bquad);
				backpatch(tempList[1], nextquad());
			else:
				print("')' was expected");
				sys.exit();
		else:
			print("'(' was expected");
			sys.exit();
	else:
		print("the keyword 'while' was expected");
		sys.exit();

def selectStat():
	if(token[1] == getToken("select")):
		exitList = emptylist();
		updateToken();
		if(token[1] == getToken("(")):
			updateToken();
			if(token[1] == "38"):
				id1 = token[0];
				updateToken();
				if(token[1] == getToken(")")):
					updateToken();
					if(token[1] == "39"):
						while(token[1] == "39"):
							id2 = token[0];
							updateToken();
							if(token[1] == getToken(":")):
								t = makelist(nextquad());
								genquad("<>", id1, id2, " ");
								updateToken();
								brackOrStat();
								e = makelist(nextquad());
								exitList.append(nextquad());
								genquad("jump", " ", " ", " ");
								merge(exitList, e);
								backpatch(t, nextquad());
							else:
								print("':' was expected");
								sys.exit();
						if(token[1] == getToken("default")):
							updateToken();
							if(token[1] == getToken(":")):
								updateToken();
								brackOrStat();
								backpatch(exitList, nextquad());
							else:
								print("':' was expected");
								sys.exit();
						else:
							print("the keyword 'default was expected'");
							sys.exit();
					elif(token[1] == getToken("default")):
						updateToken();
						if(token[1] == getToken(":")):
							updateToken();
							brackOrStat();
						else:
							print("':' was expected");
							sys.exit();
					else:
						print("the keyword 'default or a constant was expected'");
						sys.exit();
				else:
					print("')' was expected");
					sys.exit();
			else:
				print("selectStat id was expected");
				sys.exit();
		else:
			print("'(' was expected");
			sys.exit();
	else:
		print("the keyword 'select' was expected");
		sys.exit();

def doWhileStat():
	if(token[1] == getToken("do")):
		sQuad = nextquad();
		updateToken();
		brackOrStat();
		if(token[1] == getToken("while")):
			updateToken();
			if(token[1] == getToken("(")):
				updateToken();
				tempList = condition();
				if(token[1] == getToken(")")):
					backpatch(tempList[0], sQuad);
					backpatch(tempList[1], nextquad());
					updateToken();
				else:
					print("')' was expected");
					sys.exit();
			else:
				print("'(' was expected");
				sys.exit();
		else:
			print("the keyword 'while' was expected");
			sys.exit();
	else:
		print("the keyword 'do' was expected");
		sys.exit();

def exitStat():
	if(token[1] == getToken("exit")):
		updateToken();
	else:
		print("the keyword 'exit' was expected");
		sys.exit();

def returnStat():
	if(token[1] == getToken("return")):
		updateToken();
		if(token[1] == getToken("(")):
			updateToken();
			tempList = expression();
			if(token[1] == getToken(")")):
				updateToken();
				genquad("retv", tempList, " ", " ");
			else:
				print("')' was expected");
				sys.exit();
		else:
			print("'(' was expected");
			sys.exit();
	else:
		print("the keyword 'return' was expected");
		sys.exit();

def printStat():
	if(token[1] == getToken("print")):
		updateToken();
		if(token[1] == getToken("(")):
			updateToken();
			tempE = expression();
			if(token[1] == getToken(")")):
				updateToken();
				genquad("out", tempE, " ", " ");
			else:
				print("')' was expected");
				sys.exit();
		else:
			print("'(' was expected");
			sys.exit();
	else:
		print("the keyword 'print' was expected");
		sys.exit();

def callStat():
	global callStatID;
	global callStatList;
	callStatList = [];
	if(token[1] == getToken("call")):
		updateToken();
		if(token[1] == "38"):
			callStatID = token[0];
			updateToken();
			actualpars();
			genquad("call", callStatID, " ", " ");
		else:
			print("call name was expected");
			sys.exit();
	else:
		print("the keyword 'call' was expected");
		sys.exit();

def actualpars():
	if(token[1] == getToken("(")):
		updateToken();
		if(token[1] == getToken("in") or token[1] == getToken("inout")):
			actualparlist();
			if(token[1] == getToken(")")):
				updateToken();
			else:
				print("')' was expected");
				sys.exit();
		elif(token[1] == getToken(")")):
			updateToken();
		else:
			print("')' was expected or the keywords 'in' and 'inout'");
			sys.exit();
	else:
		print("'(' was expected");
		sys.exit();

def actualparlist():
	actualparitem();
	while(token[1] == getToken(",")):
		updateToken();
		actualparitem();

def actualparitem():
	if(token[1] == getToken("in")):
		updateToken();
		tempList = expression();
		genquad("par", tempList, "CV", " ");
	elif(token[1] == getToken("inout")):
		updateToken();
		if(token[1] == "38"):
			genquad("par", token[0], "REF", " ");
			callStatList = ["inout", token[0]];
			updateToken();
		else:
			print("parameter name was expected");
			sys.exit();
	else:
		print("the keyword 'in' or 'inout' was expected");
		sys.exit();

def condition():
	tempList = boolterm();
	conditionTrueList = tempList[0];
	conditionFalseList = tempList[1];
	while(token[1] == getToken("or")):
		backpatch(conditionFalseList, nextquad());
		updateToken();
		tempList = boolterm();
		conditionTrueList = merge(conditionTrueList, tempList[0]);
		conditionFalseList = tempList[1];
	tempList = [];
	tempList.append(conditionTrueList);
	tempList.append(conditionFalseList);
	return tempList;

def boolterm():
	tempList = boolfactor();
	booltermTrueList = tempList[0];
	booltermFalseList = tempList[1];
	while(token[1] == getToken("and")):
		backpatch(booltermTrueList, nextquad());
		updateToken();
		tempList = boolfactor();
		booltermFalseList = merge(booltermFalseList, tempList[1]);
		booltermTrueList = tempList[0];
	tempList = [];
	tempList.append(booltermTrueList);
	tempList.append(booltermFalseList);
	return tempList;

def boolfactor():
	if(token[1] == getToken("not")):
		updateToken();
		if(token[1] == getToken("[")):
			updateToken();
			tempList = condition();
			if(token[1] == getToken("]")):
				updateToken();
			else:
				print("']' was expected");
				sys.exit();
		else:
			print("'[' was expected");
			sys.exit();
		tempList2 = [];
		tempList2.append(tempList[1]);
		tempList2.append(tempList[0]);
		return tempList2;
	elif(token[1] == getToken("[")):
		updateToken();
		tempList = condition();
		if(token[1] == getToken("]")):
			updateToken();
		else:
			print("']' was expected");
			sys.exit();
		return tempList;
	else:
		tempE1 = expression();
		tempRelationalOper = relationalOper();
		tempE2 = expression();
		boolfactorTrueList = makelist(nextquad());
		genquad(tempRelationalOper, tempE1, tempE2, " ");
		boolfactorFalseList = makelist(nextquad());
		genquad("jump", " ", " ", " ");
		tempList = [];
		tempList.append(boolfactorTrueList);
		tempList.append(boolfactorFalseList);
		return tempList;

def expression():
	tempE = " ";
	tempT1 = " ";
	tempT2 = " ";
	optionalSign();
	tempT1 = term();
	while(token[1] == getToken("+") or token[1] == getToken("-")):
		if(token[1] == getToken("+")):
			addOper();
			tempT2 = term();
			w = newtemp();
			newEntity = Entity("temporary");
			newEntity.setEntityTypeTemporaryVariable(w);
			symbolArray.insertNewEntity(newEntity);
			genquad("+", tempT1, tempT2, w);
			tempT1 = w;
		else:
			addOper();
			tempT2 = term();
			w = newtemp();
			newEntity = Entity("temporary");
			newEntity.setEntityTypeTemporaryVariable(w);
			symbolArray.insertNewEntity(newEntity);
			genquad("-", tempT1, tempT2, w);
			tempT1 = w;
	tempE = tempT1;
	return tempE;

def term():
	tempT = " ";
	tempF1 = " ";
	tempF2 = " ";
	tempF1 = factor();
	while(token[1] == getToken("*") or token[1] == getToken("/")):
		if(token[1] == getToken("*")):
			mulOper();
			tempF2 = factor();
			w = newtemp();
			newEntity = Entity("temporary");
			newEntity.setEntityTypeTemporaryVariable(w);
			symbolArray.insertNewEntity(newEntity);
			genquad("*", tempF1, tempF2, w);
			tempF1 = w;
		else:
			mulOper();
			tempF2 = factor();
			w = newtemp();
			newEntity = Entity("temporary");
			newEntity.setEntityTypeTemporaryVariable(w);
			symbolArray.insertNewEntity(newEntity);
			genquad("/", tempF1, tempF2, w);
			tempF1 = w;
	tempT = tempF1;
	return tempT;

def factor():
	tempFactor = " ";
	if(token[1] == "39"):
		tempFactor = token[0];
		updateToken();
	elif(token[1] == getToken("(")):
		updateToken();
		tempFactor = expression();
		if(token[1] == getToken(")")):
			updateToken();
		else:
			print("')' was expected");
			sys.exit();
	elif(token[1] == "38"):
		tempFactor = token[0];
		updateToken();
		idtail();
	else:
		print("constant or '(' or factor id was expected");
		sys.exit();
	return tempFactor;

def idtail():
	if(token[1] == getToken("(")):
		actualpars();

def relationalOper():
	global tempRelationalOper;
	if(token[1] == getToken("=") or token[1] == getToken("<") or token[1] == getToken("<=") or token[1] == getToken("<>") or token[1] == getToken(">=") or token[1] == getToken(">")):
		tempRelationalOper = token[0];
		updateToken();
		return tempRelationalOper;
	else:
		print("'=' or '<' or '<=' or '<>' or '>=' or '>' was expected");
		sys.exit();

def addOper():
	if(token[1] == getToken("+") or token[1] == getToken("-")):
		updateToken();
	else:
		print("'+' or '-' was expected");
		sys.exit();

def mulOper():
	if(token[1] == getToken("*") or token[1] == getToken("/")):
		updateToken();
	else:
		print("'*' or '/' was expected");
		sys.exit();

def optionalSign():
	if(token[1] == getToken("+") or token[1] == getToken("-")):
		addOper();
		
def updateToken():
	global token;
	token = lex();
