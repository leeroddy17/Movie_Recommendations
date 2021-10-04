import argparse
import csv

#This file will be used differently than the other, which is used for formatting.
#This is more for inter-file corrections
class ForeignKeyChecker():
    def __init__(self, file1, file2, PK):
        self.file1 = file1
        self.file2 = file2
        self.Keys = set()
        self.PK = PK #PK is primary key

    def run(self):
        #This will run through all of the rows in file2 and determine if they contain any keys that arent in file1
        #Iterating through file1
        self.Keys= set()
        PKSpot = 0
        with open(self.file1, encoding='utf-8') as csvfile:
            filePtr = csv.reader(csvfile)
            Header = next(filePtr) #This is why the first entry cannot be blank
            for idx, i in enumerate(Header):
                if i==self.PK:
                    PKSpot = idx
            for row in filePtr:
                self.Keys.add(row[PKSpot])
        
        #iterating through file2
        Data = []
        with open(self.file2, encoding='utf-8') as csvfile:
            filePtr=csv.reader(csvfile)
            Header = next(filePtr)
            for idx, i in enumerate(Header):
                if i==self.PK:
                    PKSpot = idx
            
            Data.append(Header)
            for row in filePtr:
                if row[PKSpot] not in self.Keys:
                    print(row)
                    continue
                else:
                    Data.append(row)
        
        #Writing to the output
        newfileptr = '.\FKChecked\\'+self.file2.split('\\')[-1]
        with open(newfileptr, 'w', newline='', encoding='utf-8') as f:
            newfile =  csv.writer(f)
            for i in Data:
                newfile.writerow(i)

if (__name__=="__main__"):
    #parsing the input arguments
    parser = argparse.ArgumentParser(description='Uses the primary key from file one and removes instances from file 2 that do not exist in file1')
    parser.add_argument('-f1', '-FileToCompare', metavar='-f1', type=str, nargs=1)
    parser.add_argument('-f2', '-FileToRemove', metavar='-f2', type=str, nargs=1)
    parser.add_argument('-pk','-PrimaryKey', metavar='-pk', type=str, nargs=1)

    args = parser.parse_args()
    print(args)
    Cln=ForeignKeyChecker(args.f1[0], args.f2[0], args.pk[0])
    Cln.run()
    print('CLEANED')

    
  