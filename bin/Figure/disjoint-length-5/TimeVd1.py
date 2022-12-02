import matplotlib.pyplot as plt
import numpy as np

data_X = ['ET1', 'ET2', 'ET3', 'MovieLens', ]
BasicAppro = [ 11.230, 53.529,116.715, 1.298 ]
ApproVd = [ 5.625, 24.268, 69.581, 0.775 ]
BasicExact = [ 10800, 10800, 10800, 2.737 ]
ImprovedVd = [ 79.267, 234.881, 82.085, 0.871 ]
NormalVd = [135.586, 340.845, 102.449, 0.244]


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
