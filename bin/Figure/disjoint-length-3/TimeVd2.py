import matplotlib.pyplot as plt
import numpy as np

data_X = ['DBLP', 'LastFM', 'DBLP2', 'MovieLens']
BasicAppro = [ 4.48, 12.554, 8.144, 306.602]
ApproVd = [ 7.636, 21.135, 8.885, 248.058]
BasicExact = [ 10800, 10800, 2012.676, 10800]
ImprovedVd = [ 7175.670, 153.387, 88.252, 939]
NormalVd = [10800, 132.165, 109.421, 900]


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
