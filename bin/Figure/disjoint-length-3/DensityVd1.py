
import matplotlib.pyplot as plt
import numpy as np

data_X = ['Foursquare', 'Amazon', 'DBPedia','IMDB']
BasicAppro = [0.5, 0.9981691688026364, 7.285714285714286, 16.95744680851064]
ApproVd = [ 0.5, 1.0006038647342994, 8.3, 17.338983050847457]
BasicExact = [0, 0, 7.285714285714286, 16.95744680851064]
ImprovedVd = [0.5, 1.25, 8.3, 17.338983050847457]
NormalVd = [0.5, 1, 8.275862068965518, 17.34453781512605]


x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(12,4.8))


width = 0.15  # 设置数据条宽度
plt.bar(x , BasicAppro, width, color = "w",edgecolor = "k", hatch = "oo", label="BasicAppro")
plt.bar(x + 1*width, ApproVd, width, color = "w",edgecolor = "k", hatch = "//",label='ApproVd')
plt.bar(x + 2*width, BasicExact, width, color = "w",edgecolor = "k", hatch = "..", label='BasicExact')
plt.bar(x + 3*width, ImprovedVd, width,color = "k",edgecolor = "k", label='ImprovedVd')
plt.bar(x + 4*width, NormalVd, width,color = "w",edgecolor = "k", label='NormalVd')

plt.xticks(x+2*width, data_X)
plt.ylabel("Density",fontsize=25)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)

plt.show()