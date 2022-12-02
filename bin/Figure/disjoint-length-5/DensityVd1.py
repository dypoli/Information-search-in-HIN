
import matplotlib.pyplot as plt
import numpy as np

data_X = ['ET1', 'ET2', 'ET3', 'MovieLens' ]
BasicAppro = [2.612280701754386, 3.8092529389457717, 4.065321805955811, 2.375]
ApproVd = [ 2.736743605739239, 3.971694298422968, 4.8922305764411025, 2.5714285714285716]
BasicExact = [0, 0, 0, 2.375]
ImprovedVd = [2.8095872170439415, 4.307618591279349, 5.241678726483357, 2.5714285714285716 ]
NormalVd = [2.892986261749819, 4.16457399103139, 5.127955493741307, 2.5714285714285716]


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