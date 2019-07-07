#Author: Sirinian Aram Emmanouil

################# pipelining combined with merge-join ##################

pipelinedMergeJoin(R, S):
	bank = [];
	result = [];

	while (!isEmpty(R) or !isEmpty(S)):
		subsetOfR = getSubset(R);
		subsetOfS = getSubset(S);

		R.remove(subsetOfR);
		S.remove(subsetOfS);
		result.append( sameSubsetJoin(subsetOfR, subsetOfS) );
		bank.append( [subsetOfR, subsetOfS] );

	while (bank > 1):
		subsetOfBank = getSubset(bank);
		subsetOfR = [];
		subsetOfS = [];

		result.append( betweenSubsetsJoin(subsetOfBank, [&subsetOfR,
		&subsetOfS]) );
		bank.remove(subsetOfBank);
		bank.append( [subsetOfR, subsetOfS] );
	return result;

sameSubsetJoin(subsetOfR, subsetOfS):
	result = [];
	RM = [];
	SM = [];

	while (!isEmpty(subsetOfR) or !isEmpty(subsetOfS)):
		r = subsetOfR[0];
		s = subsetOfS[0];
		if (isEmpty(subsetOfS) or ( !isEmpty(subsetOfR) and r<=s )):
			RM.append(r);
			result.append( query(SM, r) );
			subsetOfR.remove(r);
		else:
			SM.append(s);
			result.append( query(RM, s) );
			subsetOfS.remove(s);
	return result;


####subsetOfBank = {[subsetOfR, subsetOfS], ..., [subsetOfR, subsetOfS]}
betweenSubsetsJoin(subsetOfBank, [&subsetOfR, &subsetOfS]):
	result = [];
	RM = [];
	SM = [];

	while (!areAllSubsetsOfREmpty() or !areAllSubsetsOfSEmpty()):
		r = getSmallestStartingTuple(subsetOfR);
		s = getSmallestStartingTuple(subsetOfS);
		choosenPositionR = getPositionOfSubsetOfRThatStartsWithTuple(r);
		choosenPositionS = getPositionOfSubsetOfSThatStartsWithTuple(s);
		if (isEmpty(subsetOfBank[choosenPositionS][1]) or
		(!isEmpty(subsetOfBank[choosenPositionR][0]) and r<=s)):
			RM.append(r);
			tempBucket = [];
			tempBucket.append( query(SM, r) );
			for x in subsetOfBank[choosenPositionR][1]:
				if ( tempBucket.contains([r, x]) ):
					tempBucket.remove([r, x]);

			result.append(tempBucket);
			subsetOfBank[choosenPositionR][0].remove(r);
			subsetOfR.append(r);
		else:
			SM.append(s);

			tempBucket = [];
			tempBucket.append( query(RM, s) );
			for x in subsetOfBank[choosenPositionS][0]:
				if ( tempBucket.contains([x, s]) ):
					tempBucket.remove([x, s]);

			result.append(tempBucket);
			subsetOfBank[choosenPositionS][1].remove(s);
			subsetOfS.append(s);
	return result;


isEmpty(someList):
	if (len(someList) == 0):
		return TRUE;
	return FALSE;


query(array, element):
	bucket = [];
	for u in array:
		if(u < element):
			bucket.append(u);
	array.remove(bucket);

	bucket = [];
	for u in array:
		if(u == element):
			bucket.append(u);
	return bucket;

###################### merge-join with two inputs ######################

mergeJoinWithGroups(R, S, T):
	RG = createGroupList(R);
	SG = createGroupList(S);
	TG = createGroupList(T);
	PRG = 0;
	PSG = 0;
	PTG = 0;
	do:
		if ( checkIfGroupPointersNeedToBeUpdated(PRG, PSG, PTG) ):
			updateGroupPointers(PRG, PSG, PTG);
		else:
			PR = RG[PRG].startPosition;
			PS = SG[PSG].startPosition;
			PT = TG[PTG].startPosition;
			outputJoinsWithR(PR, PS, PT);
			PRG++;
			PSG++;
			PTG++;
	while ( !checkGroupPointersEOF(PRG, PSG, PTG) )


checkIfGroupPointerNeedToBeUpdated(PRG, PSG, PTG):
	if (PRG == PSG and PRG == PTG):
		return FALSE;
	return TRUE;


updateGroupPointers(PRG, PSG, PTG):
	if (RG[PRG].gID > [PSG].gID and [PRG].gID > [PTG].gID):
		PSG++;
		PTG++;
	else if (SG[PSG].gID > RG[PRG].gID and SG[PSG].gID > TG[PTG].gID):
		PRG++;
		PTG++;
	else if (TG[PTG].gID > RG[PRG].gID and TG[PTG].gID > SG[PSG].gID):
		PRG++;
		PSG++;
	else if (RG[PRG].gID == SG[PSG].gID and RG[PRG].gID > TG[PTG].gID):
		PTG++;
	else if (RG[PRG].gID == TG[PTG].gID and RG[PRG].gID > SG[PSG].gID):
		PSG++;
	else if (SG[PSG].gID == TG[PTG].gID and SG[PSG].gID > RG[PRG].gID):
		PRG++;


checkGroupPointersEOF(PRG, PSG, PTG):
	if (RG[PRG] == EOF or SG[PSG] == EOF or TG[PTG] == EOF):
		return TRUE;
	return FALSE;


outputJoinsWithR(PR, PS, PT):
	do:
		outputJoinsWithS(PR, PS, PT);
		PR++;
		if (RG[PRG] == EOF or checkIfPointerEnteredNextGroup(R, PR, RG, PRG)):
			PR = RG[PRG].startPosition;
			break;
	while(TRUE)


outputJoinsWithS(PR, PS, PT):
	do:
		outputJoinsWithT(PR, PS, PT);
		PS++;
		if (SG[PSG] == EOF or checkIfPointerEnteredNextGroup(S, PS, SG, PSG)):
			PS = SG[PSG].startPosition;
			break;
	while(TRUE)


outputJoinsWithT(PR, PS, PT):
	do:
		output(joinTuples(PR, PS, PT));
		PT++;
		if (TG[PTG] == EOF or checkIfPointerEnteredNextGroup(T, PT, TG, PTG):
			PT = TG[PTG].startPosition;
			break;
	while(TRUE)


checkIfPointerEnteredNextGroup(A, PA, AG, PAG):
	if(A[PA].x != AG[PAG].groupID):
		return TRUE;
	return FALSE;


########################################################################
########################################################################
