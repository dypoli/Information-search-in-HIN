
import matplotlib.pyplot as plt
import numpy as np

data_X = ['ET1', 'ET2', 'ET3', 'MovieLens' ]
BasicAppro = [2.6181286549707603, 3.812665908229048, 4.069164265129683, 2.5]
ApproEd = [ 2.8162992651970606, 4.064775613886537, 5.038666666666667,2.7142857142857144]
BasicExact = [0, 0, 0, 2.5]
ImprovedEd = [2.907770515613653, 4.2712815715622074, 5.175808720112518, 2.7142857142857144]
NormalEd = [2.9269202087994035, 4.177186654643823, 5.132127955493742, 2.7142857142857144]


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