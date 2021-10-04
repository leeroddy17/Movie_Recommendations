import argparse
import csv

#The purpose of this function is to clean up the 
#CSV's so that they can be converted To SQL databases effectively
class Cleaner():
    def __init__(self, filename, PK=None):
        self.DBPK={} #dictionary containing the primary keys and a link to Data location
        self.Header=[] #contains the header row
        self.filename=filename #filename
        self.PK=PK #string equivalent of the primary key, the row will be indicated later

    def run(self):
        #Helper function that updates the data array, uses the primary key location to determine if the first row should be removed
        #This is the only part that is custom to the data provided
        def _addToData(line, primaryKeyLoc, arr, lineNo):
            #Primary key location is used to determine if ID
            if lineNo==len(arr):
                if primaryKeyLoc!=0:
                    arr.append(line[1:])
                else:
                    arr.append([lineNo]+line[1:])

            else:
                if primaryKeyLoc!=0:
                    arr[lineNo]=(line[1:])
                else:
                    arr[lineNo]=[lineNo]+line[1:]
                    

        #The specifics for this cleaner are as follows:
        #1.The Cleaner will replace the first column with ID's if a PK is not specified
        #   Otherwise will just remove row 1 (indirectly done by ignoring)
        #2.With a primary key specified, the cleaner will appropriately use that column and check
        #   for duplicates, a cmd line prompt will indicate which to keep unless this is disabled.
        data = []
        with open(self.filename, encoding='utf-8') as csvfile:
            #initialization section
            filePtr=csv.reader(csvfile)
            lineNo=1 #line location
            PKSpot = 0 #location of the primary key
            self.Header = next(filePtr)
            #Adding a Primary Key to those files when it is needed
            #This row will be overwritten anyhow
            if self.PK==None:
                self.Header[0]='ID'
                data.append(self.Header)
            else:
                #Using the PK location for the header
                for idx, i in enumerate(self.Header):
                    print(self.Header[idx])
                    if i==self.PK:
                        PKSpot=idx
                        break
                data.append(self.Header[1:]) #skipping the first column
            for row in filePtr:
                #Iterating through the file
                #Checks the primary key against known primary keys
                if row[PKSpot] in self.DBPK:
                    #UNCOMMENT THIS BLOCK FOR TITLES
                    # if int(data[self.DBPK[row[PKSpot]]][7])>int(row[8]):
                    #     continue
                    # else:
                    #     _addToData(row, PKSpot, data, self.DBPK[row[PKSpot]])

                    #UNCOMMENT THIS FOR EVERYTHING ELSE, CONFLICTS SHOULD NOT BE COMMON, this gives the user control
                    print('There is a Primary Key Conflict, press [1] to keep the original [2] to use the new line')
                    print('##########################################################################################')
                    print('Old: \n', data[self.DBPK[row[PKSpot]]]) #assumes that I can reference line numbers
                    print('New:\n', row)
                    print('##########################################################################################')
                    check = int(input())
                    if check==2:
                        _addToData(row, PKSpot, data, self.DBPK[row[PKSpot]])
                    continue
                self.DBPK[row[PKSpot]]=lineNo
                _addToData(row, PKSpot, data, lineNo)
                lineNo+=1

        #Writing to the output
        newfileptr = '.\Cleaned\\'+self.filename.split('\\')[-1]
        with open(newfileptr, 'w', newline='', encoding='utf-8') as f:
            newfile =  csv.writer(f)
            for i in data:
                newfile.writerow(i)

        self.DBPK={} #dictionary containing the primary keys and a link to Data location
        self.Header=[] #contains the header row

if (__name__=="__main__"):
    #parsing the arguments to send to the 
    parser = argparse.ArgumentParser(description='Cleaning up CSV files before they are used \
    in the database selection.')
    parser.add_argument('Filename', metavar='-f', type=str, nargs=1)
    parser.add_argument('-pk','--PrimaryKey', metavar='-k', type=str, nargs=1, required=False, default=[None])

    args = parser.parse_args()
    Cln=Cleaner(args.Filename[0], args.PrimaryKey[0])
    Cln.run()
    print('CLEANED')

    
  