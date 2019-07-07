#Name:  Sirinian Aram Emmanouil
#
#   Tested with python 3.7.1

import sys
import random
import optparse

K = 0
N = 0
n = 10
A = []
S = []
h = []
fullSimA = []
fullSimS = []
inputFilePath = ""
n = 10

def compute_A_and_dimensions(inputfile):
    global K
    global N
    global A
    firstVisitFlag = False

    try:
        with open(inputfile) as f:
            for line in f:
                if(not firstVisitFlag):
                    N = len(line.split())
                    firstVisitFlag = True
                else:
                    tempN = len(line.split())
                    if(tempN == 0):
                        continue
                    if(N != tempN):
                        print("Error in format of ", inputfile)
                        print("Error at computing array's N dimension")
                        sys.exit(-1)
                tempArray = [int(s) for s in line.split() if(s == '0' or s == '1')]
                if(N != len(tempArray)):
                    print("Error in format of ", inputfile)
                    print("Error in inpute file's symbols")
                    sys.exit(-2)
                A.append(tempArray)
                K += 1
    except FileNotFoundError:
        print("The file:" + inputFilePath + "does not exist")
        sys.exit(-3)
    except PermissionError:
        print("You don't have the permission to open the file: " + inputFilePath)
        sys.exit(-4)
    except Exception:
        print("Unexpected error occured")
        sys.exit(-5)
    if(K > 22):
        print("Error input file extends 22 lines")
        sys.exit(-3)

def compute_h():
    global n
    global h
    global K

    for i in range(n):
        a = random.choice(range(22)) + 1
        b = random.choice(range(22)) + 1
        tempList = []
        for x in range(K):
            tempList.append((((a*x)+b)%23))
        h.append(tempList)

def init_S():
    global n
    global N
    global S

    for i in range(n):
        tempList = []
        for j in range(N):
            tempList.append(sys.maxsize)
        S.append(tempList)

def compute_S():
    global K
    global N
    global n
    global A
    global h
    global S

    init_S()
    for i in range(K):
        for j in range(N):
            if (A[i][j]==1):
                for z in range(n):
                    if(S[z][j] > h[z][i]):
                        S[z][j] = h[z][i]

def simA(column1, column2):
    global A
    tempCounter1 = 0
    tempCounter2 = 0

    for i in range(len(A)):
        if(A[i][column1] == 0 and A[i][column2] == 0):
            continue
        if(A[i][column1] == A[i][column2]):
            tempCounter1 += 1
        tempCounter2 += 1
    return tempCounter1/float(tempCounter2)

def computeFullSimA():
    global A
    global fullSimA

    for i in range(N):
        tempArray = []
        for j in range(N):
            tempArray.append(simA(i,j))
        fullSimA.append(list(tempArray))

def simS(column1, column2):
    global S
    tempCounter1 = 0
    tempCounter2 = 0

    for i in range(len(S)):
        if(S[i][column1] == S[i][column2]):
            tempCounter1 += 1
        tempCounter2 += 1
    return tempCounter1/float(tempCounter2)

def computeFullSimS():
    global S
    global fullSimS

    for i in range(N):
        tempArray = []
        for j in range(N):
            tempArray.append(simS(i,j))
        fullSimS.append(list(tempArray))


def parse_args():
    global inputFilePath
    global n

    usage = """usage: %prog [options] [input file full path] [n]
    python2 assignment1Ex2.py ./myFile.txt 10"""
    parser = optparse.OptionParser(usage)
    _, args = parser.parse_args()
    if(len(args) != 2):
        print(parser.format_help())
        parser.exit()
    inputFilePath, temp_n = args
    def parse_n(temp_n):
        global n

        if(not temp_n.isdigit()):
            parser.error('n must be integer')
        n = int(temp_n)
        if(n > 22):
            parser.error('n must be integer <=22')
        if(n < 0):
            parser.error('n must be integer >=0')
    parse_n(temp_n)

parse_args()
compute_A_and_dimensions(inputFilePath)
compute_h()
compute_S()
computeFullSimA()
computeFullSimS()

print("Array A:")
print("")
for i in range(len(A)):
    tempString = ""
    for j in range(len(A[0])):
        tempString += format(A[i][j], '2d') + "   "
    print(tempString)
print("")
print("Array S:")
print("")
for i in range(len(S)):
    tempString = ""
    for j in range(len(S[0])):
        tempString += format(S[i][j], '2d') + "   "
    print(tempString)
print("")
print("Array of similarities for S:")
print("")
for i in range(len(fullSimS)):
    tempString = "S" + str(i) + ":   "
    for j in range(len(fullSimS[0])):
       tempString += format(fullSimS[i][j], '.4f') + "    "
    print(tempString)
print("")
print("Array of similarities for A:")
print("")
for i in range(len(fullSimA)):
    tempString = "S" + str(i) + ":   "
    for j in range(len(fullSimA[0])):
        tempString += format(fullSimA[i][j], '.4f') + "    "
    print(tempString)
print("")
