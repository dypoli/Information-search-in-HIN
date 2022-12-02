
import matplotlib.pyplot as plt
import numpy as np

data_X = ['DBLP', 'LastFM', 'DBLP2', 'MovieLens']
BasicAppro = [ 24.030935519940364, 22.816946442845722, 41.38782608695652, 49.93319194]
ApproVd = [ 24.53183377811666, 50.41237113402062, 52.24786324786325, 90.50391644908616]
BasicExact = [0, 0, 41.38782608695652, 0]
ImprovedVd = [24.59682416731216, 50.41237113402062, 52.24786324786325, 90.50391644908616]
NormalVd = [0, 50.28019323671498, 52.17142857142857, 86.9939393939394]

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