import math

def merge_list(*args):
    b = []
    for i in args:
        if type(i) == str or i is None:
            b.append(i)
        else:
            for j in i:
                b.append(j)
    return b
print(merge_list([0,0], 'nice', {5,4,1}, (12,9), None))




def merge_to_list(args):
    return [i for x in args for i in x]

print(merge_to_list([[1,2,3],[3,4,5,6],[5,6,7]]))

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
