import matplotlib.pyplot as plt
import numpy as np


data_X = ['Foursquare', 'Amazon', 'DBPedia','IMDB']
BasicAppro = [87.679, 79.738, 327.344, 26.716]
ApproVd = [39.855,47.935, 0.457, 10.6]
BasicExact = [10800, 10800, 646.6, 66.203]
ImprovedVd = [80.09, 60.7, 0.528, 13.244]
NormalVd = [42.873, 41.337, 0.293, 33.517]


x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(12,4.8))
plt.axes(yscale='log', ylim=[10e-2, 10e4])

width = 0.15  # 设置数据条宽度
plt.bar(x , BasicAppro, width, color = "w",edgecolor = "k", hatch = "oo", label="BasicAppro")
plt.bar(x + 1*width, ApproVd, width, color = "w",edgecolor = "k", hatch = "//",label='ApproVd')
plt.bar(x + 2*width, BasicExact, width, color = "w",edgecolor = "k", hatch = "..", label='BasicExact')
plt.bar(x + 3*width, ImprovedVd, width,color = "k",edgecolor = "k", label='ImprovedVd')
plt.bar(x + 4*width, NormalVd, width,color = "w",edgecolor = "k", label='NormalVd')

plt.xticks(x+2*width, data_X)
plt.ylabel("Time(s)",fontsize=25)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)


plt.show()
