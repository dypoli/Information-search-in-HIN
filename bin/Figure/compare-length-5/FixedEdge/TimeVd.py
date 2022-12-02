
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [5,10,15,20,25]#点的横坐标
ImprovedVd = [ 17, 86, 103.599, 259, 413]#线1的纵坐标
NormalVd = [25, 110, 192.640, 696.035, 712.2]#线2的纵坐标
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