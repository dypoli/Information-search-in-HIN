
from turtle import color
import matplotlib.pyplot as plt
import numpy as np

data_X = ['IMDB', 'DBLP', 'DBLP2', 'LastFM', 'Foursquare', 'DBPedia', 'MovieLens', 'Amazon']

APPRO = [19.19148936, 63.71428571, 91.17391304, 213.5939249, 221.7817372, 285.2798888, 455.04772, 1365]
EXACT = [19.19148936, 63.71428571, 91.17391304, 0, 221.7817372, 335, 0, 0]

x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(15,4.8))
plt.axes(yscale='log', ylim=[10e-1, 10e3])

width = 0.3  # 设置数据条宽度
plt.bar(x  ,width, color = "w",edgecolor = "k", label="BasicAppro")
plt.bar(x + width ,  width, color="black", label='BasicExact')
plt.xticks(x+ width/2)



'''
for a, b in zip(x , APPRO):
    b=float (b)
    plt.text(a, b + 0.1, '%.1f' % b, ha='center', va='bottom', fontsize=7)
for a, b in zip(x + width, EXACT):
    plt.text(a, b + 0.1, '%.1f' % b, ha='center', va='bottom', fontsize=7)
    '''
plt.legend(ncol=4,fontsize=30)   # 给出图例
plt.show()