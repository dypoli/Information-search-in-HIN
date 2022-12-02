
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [5,10,15,20,25]#点的横坐标
#ImprovedEd = [ 9.421, 38.5, 82.92, 190.26, 256.571]#线1的纵坐标
#NormalEd = [16.12, 128.484, 186.973, 621.971, 622.714]#线2的纵坐标

ImprovedEd = [ 23, 94, 210, 263.460, 440.286]#线1的纵坐标
NormalEd = [33, 257.8, 377, 735.109, 699.072]#线2的纵坐标
plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="ImprovedEd")#s-:方形
plt.plot(x,NormalEd,'--',linewidth='4',color = 'black',label="NormalEd")#o-:圆形
plt.xlabel("Vertex Number (k)",fontsize=30)#横坐标名字
plt.ylabel("Time(s)",fontsize=30)   # 纵坐标label

x_major_locator=MultipleLocator(5)

ax=plt.gca()

ax.xaxis.set_major_locator(x_major_locator)

plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()