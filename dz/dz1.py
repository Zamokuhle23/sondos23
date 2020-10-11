dbase = [
	'+5(4671)849-6225',
	'+394(63)128-1148',
	'535(02)715-68-89',
	'+72(23)661-8520',
	'+194(455)628-2961',
	'092158146777',
	'+862(179)416-6138',
	'+3(2697)794-4711',
	'+98(393)874-4458',
	'+5(07)958-7521',
	'+3(632)626-8042',
	'+7611528-9013',
	'+88135130-7172',
	'94(3005)670-58-48',
	'+264925558-7301',
	'58(6929)091-8491',
	'+581(067)254-6659',
	'+4(838)997-1720',
	'+7(163)228-1948',
	'72236618520',
	'507958-7521',
	'967(28)959-4951',
	'+53(2121)207-3793',
	'+80(922)2856718',
	'7121-2173999'
]


base = dbase
for i in range(len(dbase)):
    base[i] = base[i].replace("+","").replace(")","").replace("(","").replace("-","")

for i in range(len(dbase)):
    dbase[i] = (dbase[i][:-10],dbase[i][-10:-7],dbase[i][-7:])

print("2. There are " + str(len(dbase)) + " numbers in the database")

a =[]
for i in range(len(dbase)):
 a.append(dbase[i][0])



uniq = set(a)


print("there are " + str(len(uniq)) + " countries in the database")


n = 0
m = 0
a = ""
b = ""
for i in range(len(dbase)):
 if n > m:
	 m = n
	 b = a

 a = ""
 for j in range(3):
	 n += len(dbase[i][j])
	 a += dbase[i][j]



print("The longest number is " + b + " and is " + str(m) + " digits long")

base = set(dbase)

print(base)
list = []
m = ''


for i in range(len(dbase)):
 if dbase[i][0] == "7":
  if m != "": list.append(m)
  m = ""
  for j in range(3):
   m += dbase[i][j]



print(list)
