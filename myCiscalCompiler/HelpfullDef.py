#name:		Sirinian Aram Emmanouil

def appendToCFile(stringToAppend, fileName):
	with open(fileName, 'a') as tempa:
		tempa.write(stringToAppend);

def writeToCFile(stringToWrite, fileName):
	with open(fileName, 'w') as tempw:
		tempw.write(stringToWrite);

def isNumber(stringThatMayBeInt):
	try:
		int(stringThatMayBeInt);
		return True;
	except ValueError:
		return False;
