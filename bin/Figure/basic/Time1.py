import matplotlib.pyplot as plt
import numpy as np

data_X = ['IMDB', 'DBLP', 'DBLP2', 'LastFM','Foursquare', 'DBPedia', 'MovieLens', 'Amazon']
APPRO = [31.83, 84, 0.238, 1.728,0.066, 210, 0.289, 19.113]
EXACT = [37, 100, 1774, 10000, 152, 670, 10000, 10000]
x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(10,4))
plt.axes(yscale='log', ylim=[10e-3, 10e4])


width = 0.3  # 设置数据条宽度
plt.bar(x  , APPRO, width, color = "w",edgecolor = "k", label="BasicAppro")
plt.bar(x  + width, EXACT, width, color="black", label='BasicExact')
plt.xticks(x+width/2, data_X)
plt.ylabel("Time(s)",fontsize=20)   # 纵坐标label
plt.xticks(fontsize=14)
plt.yticks(fontsize=20)

'''
for a, b in zip(x , APPRO):
    b=float (b)
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=7)
for a, b in zip(x + width , EXACT):
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=7)
'''

#plt.legend(fontsize=15,loc="upper left") 
plt.show()