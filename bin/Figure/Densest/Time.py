
from turtle import color
import matplotlib.pyplot as plt
import numpy as np

data_X = ['LastFM', 'MovieLens', 'Amazon', 'Foursquare', 'ET1', 'ET2', 'ET3']

FastExact = [10800, 10800, 10800, 279, 51, 578, 66]
EXACT = [10800, 10800, 10800, 1300, 142, 2000, 200]

x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(10,4))
plt.axes(yscale='log', ylim=[10e-1, 10e4])

width = 0.3  # 设置数据条宽度
plt.bar(x  , FastExact, width, color = "w",edgecolor = "k", label="FastExact")
plt.bar(x + width , EXACT, width, color="black", label='Exact')
plt.xticks(x+ width/2, data_X)
plt.ylabel("Time(s)",fontsize=20)   # 纵坐标label

plt.xticks(fontsize=14)
plt.yticks(fontsize=20)

plt.legend(fontsize=20)
'''
for a, b in zip(x , APPRO):
    b=float (b)
    plt.text(a, b + 0.1, '%.1f' % b, ha='center', va='bottom', fontsize=7)
for a, b in zip(x + width, EXACT):
    plt.text(a, b + 0.1, '%.1f' % b, ha='center', va='bottom', fontsize=7)
    '''
#plt.legend(ncol=4,fontsize=20)   # 给出图例
plt.show()