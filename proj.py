import math

def merge_list(list):
  return str([str([ch for ch in word ])[1:-1] for word in list]).replace("'",'')

print(merge_list([{0, 2, 3},[12,3,4,5],[3,4,5],["Sondos"]]))

def merge_to_list(list):
  return str([str([ch for ch in word])[1:-1] for word in list ]).replace("'",'')

print(merge_to_list([[0,2,3],[12,3,4,5],[3,4,5]]))

def multi_power(original, powers):
    return [math.pow(original[i],powers[i]) for i in range(len(original))]

print(multi_power([2,3,4], [1,2,3]))


def remove_duplicates(list):
    a = []
    [a.append(x) for x in list if x not in a]
    return a


print(remove_duplicates([[0,2,3],[12,3,4,5],[3,4,5],[0,2,3]]))

def expand(unchangeable, index, expandby):
    unchangeable[index] = unchangeable[index] + expandby
    return unchangeable


print(expand([[1,2,3],[2,3,4]],1,[0,0,0]))