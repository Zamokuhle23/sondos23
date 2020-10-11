
file = open('book2.txt' ,'rt',encoding='utf8')
data = file.read()
data = data.lower()
temp = data.split(" ")
m = 0
print("The number of words are: " + str(len(temp)))
for i in range(len(temp)):
    m += len(temp[i])

print("The average words is " + str(round( m /len(temp) ,2)))
data = data.replace(" ","")
print("There are " + str(len(data)) + " letters")
frequency = {}
for i in data:
    if i in frequency: frequency[i] += 1
    else: frequency[i] = 1

dict1 = sorted(frequency.items() , reverse=True, key=lambda x: x[1])

dict2 = []
for i in range(len(dict1)):
 dict2.append(dict1[i][0])
print("Top ten most frequent letters are: " + str(dict2[:10]))

frequency1 = {}
for i in temp:
     if i in frequency1:
         frequency1[i] += 1
     else:
         frequency1[i] = 1

dict4 = sorted(frequency1.items() , reverse=True, key=lambda x: x[1])
dict3 = []
for i in range(len(dict4)):
 dict3.append(dict4[i][0])

print("Top ten most frequent words are: " + str(dict3[:10]))

print("There are " + str(len(open('book2.txt',encoding='utf8').readlines())) + " Lines in the text")
file.close()
fin = open("book2.txt", "rt",encoding='utf8')
data = fin.read()
data = data.replace('Анна Павловна','Anna Pavlovna')
fin.close()
fin = open("book2.txt", "wt",encoding='utf8')
fin.write(data)
fin.close()

