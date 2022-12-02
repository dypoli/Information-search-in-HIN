
import matplotlib.pyplot as plt
import numpy as np

data_X = ['DBLP','LastFM','DBLP2','MovieLens']
BasicAppro = [ 24.030935519940364, 22.816946442845722, 41.38782608695652, 49.93319194061506]
ApproEd = [24.43349168646081, 50.28019323671498, 52.17142857142857, 87.65051020408163]
BasicExact = [0, 0, 41.38782608695652,0]
ImprovedEd = [24.832269050659857, 50.40196078431372, 52.17142857142857, 93.28977272727273]
NormalEd = [ 0, 50.28019323671498, 50.28019323671498, 86.9939393939394]

x = np.arange(len(data_X))  # 设定步长
plt.figure(figsize=(12,4.8))


width = 0.15  # 设置数据条宽度
plt.bar(x , BasicAppro, width, color = "w",edgecolor = "k", hatch = "oo",label="BasicAppro")
plt.bar(x + 1*width, ApproEd, width, color = "w",edgecolor = "k", hatch = "//", label='ApproEd')
plt.bar(x + 2*width, BasicExact, width, color = "w",edgecolor = "k", hatch = "..", label='BasicExact')
plt.bar(x + 3*width, ImprovedEd, width, color = "k",edgecolor = "k", label='ImprovedEd')
plt.bar(x + 4*width, NormalEd, width, color = "w",edgecolor = "k", label='NormalEd')

plt.xticks(x+2*width, data_X)
plt.ylabel("Density",fontsize=25)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
#plt.legend(fontsize=15)   # 给出图例
plt.show()