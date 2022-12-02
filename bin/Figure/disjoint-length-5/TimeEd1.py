import matplotlib.pyplot as plt
import numpy as np

data_X = ['ET1', 'ET2', 'ET3', 'MovieLens' ]
BasicAppro = [9.356, 34.426, 72.439, 2.080]
ApproEd = [ 6.8, 27.033, 72.604, 4.348]
BasicExact = [ 10800, 10800, 10800, 1.294]
ImprovedEd = [ 59.935, 31.36, 83.938, 2.363]
NormalEd = [142.508, 328.348, 106.068, 2.739]


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


'''
for a, b in zip(x , BasicAppro):
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=3)
    
for a, b in zip(x +width, ApproVd):
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=3)
    
for a, b in zip(x +2*width, BasicExact):
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=3)
    
for a, b in zip(x +3*width, ImprovedVd):
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=3)
    
for a, b in zip(x +4*width, NormalVd):
    plt.text(a, b + 0.1, '%.2f' % b, ha='center', va='bottom', fontsize=3)
'''



plt.show()
