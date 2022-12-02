
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [5,10,15,20,25]#点的横坐标

#ImprovedVd = [ 16.272, 66.807, 178.480, 331.796, 577.928]#线1的纵坐标
#NormalVd = [39.266, 191.292, 456.979, 888.011, 1497.283]#线2的纵坐标

ImprovedVd = [ 4.7, 11.6, 30.3, 89.108, 131]#线1的纵坐标
NormalVd = [5.9, 32.5, 78, 185.16, 281.282]#线2的纵坐标
plt.plot(x,ImprovedVd,'-.',linewidth='4',color = 'black',label="ImprovedVd")#s-:方形
plt.plot(x,NormalVd,':',linewidth='4',color = 'black',label="NormalVd")#o-:圆形
plt.xlabel("Vertex Number (k)",fontsize=30)#横坐标名字
plt.ylabel("Time(s)",fontsize=30)   # 纵坐标label

x_major_locator=MultipleLocator(5)

ax=plt.gca()

ax.xaxis.set_major_locator(x_major_locator)

plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()