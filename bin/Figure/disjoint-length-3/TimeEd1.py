import matplotlib.pyplot as plt
import numpy as np

data_X = ['Foursquare','Amazon','DBPedia','IMDB']
BasicAppro = [66.613, 56.947, 245.280, 35.526]
ApproEd = [ 28.862, 6.849, 0.281, 10.724]
BasicExact = [ 10800, 10800, 248.112, 85.542]
ImprovedEd = [ 95.613, 55.627, 0.691, 12.847]
NormalEd = [37.856, 36.051, 0.321, 31.886]


x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(12,4.8))
plt.axes(yscale='log', ylim=[10e-2, 10e4])

width = 0.15  # 设置数据条宽度
plt.bar(x , BasicAppro, width, color = "w",edgecolor = "k", hatch = "oo",label="BasicAppro")
plt.bar(x + 1*width, ApproEd, width, color = "w",edgecolor = "k", hatch = "//", label='ApproEd')
plt.bar(x + 2*width, BasicExact, width, color = "w",edgecolor = "k", hatch = "..", label='BasicExact')
plt.bar(x + 3*width, ImprovedEd, width, color = "k",edgecolor = "k", label='ImprovedEd')
plt.bar(x + 4*width, NormalEd, width, color = "w",edgecolor = "k", label='NormalEd')

plt.xticks(x+2*width, data_X)
plt.ylabel("Time(s)",fontsize=25)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)

#plt.legend(loc='best',fontsize=15)   # 给出图例

plt.show()
