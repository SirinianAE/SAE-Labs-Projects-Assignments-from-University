#Author: Sirinian Aram Emmanouil

############################# a ######################################
########### (1) ###########

sortMergeSemiJoinWithGroups(R, S):
	sort(R on R.x);
	RG = createGroupList(R on R.x);
	sort(S on S.x);
	SG = createGroupList(S on S.x);
	PRG = 0;
	PSG = 0;
	do:
		if(RG[PRG].groupID == SG[PSG].groupID):
			PR = RG[PRG].startPosition;
			PS = SG[PSG].startPosition;
			lastOutput = NULL;
			do:
				if(lastOutput != R[PR]):
					lastOutput = R[PR];
					output(R[PR]);
				PR++;
			while( !checkIfPointerEnteredNextGroup(R, PR, RG, PRG) )
			PRG++;
			PSG++;
		else if(RG[PRG].groupID < SG[PSG].groupID):
			PRG++;
		else:
			PSG++;
	while(PRG != len(RG) and PSG != len(SG))

########### (2) ###########

hashSemiJoin(R, S):
	bucketsOfR = build_ht(R, x);
	bucketsOfS = build_ht(S, x);
	for k in range(1 to n):
		bucket;
		for each s in bucketsOfS[k]:
			bucket[h2(s.x)] = s;
		lastOutput = NULL;
		for each r in bucketsOfR[k]:
			for each s in bucket[h2(r.x)]:
				if (r.x == s.x) then:
					if (lastOutput != r):
						lastOutput = r;
						output(r);
						break;

############################# b ######################################
########### (1) ###########

sortMergeAntiSemiJoinWithGroups(R, S):
	sort(R on R.x);
	RG = createGroupList(R on R.x);
	sort(S on S.x);
	SG = createGroupList(S on S.x);
	PRG = 0;
	PSG = 0;
	do:
		if(RG[PRG].groupID == SG[PSG].groupID):
			PRG++;
		else if(RG[PRG].groupID > SG[PSG].groupID):
			PSG++;
		else:
			lastOutput = NULL;
			PR = RG[PRG].startPosition;
			do:
				if(lastOutput != R[PR]):
					lastOutput = R[PR];
					output(R[PR]);
				PR++;
			while( checkGroupPointerEOF(RG, PRG) or
			!checkIfPointerEnteredNextGroup(R, PR, RG, PRG) )
	while(PRG != len(RG))

########### (2) ###########

hashAntiSemiJoin(R, S):
	bucketsOfR = build_ht(R, x);
	bucketsOfS = build_ht(S, x);
	for k in range(1 to n):
		bucket;
		for each s in bucketsOfS[k]:
			bucket[h2(s.x)] = s;
		lastOutput;
		arrayListOfRkBlocks = buildArrayList(bucketsOfR[k]);
		for each r in bucketsOfR[k]:
			if(checkIfBucketItemsCanBeJoined( bucket[h2(r.x)], r ):
				arrayListOfRkBlocks.remove(r);
		for z in arrayListOfRkBlocks:
			if (lastOutput != z):
				lastOutput = z;
				output(z);

################ some of functions been used ################

checkIfPointerEnteredNextGroup(A, PA, AG, PAG):
	if(A[PA].x != AG[PAG].groupID):
		return TRUE;
	return FALSE;


checkGroupPointersEOF(array, pointer):
	if (array[pointer] == EOF):
		return TRUE;
	return FALSE;


checkIfBucketItemsCanBeJoined(bucket, item):
	for x in bucket:
		if (x == item):
			return TRUE;
	return FALSE;


buildArrayList(bucket):
	arrayList;
	for each r in bucket:
		arrayList.add(r);
	return arrayList;


build_ht(A, x):
	bucket[n];
	for each a in A:
		bucket[h1(a.x)] = a;
	return bucket;

######################################################################
######################################################################
