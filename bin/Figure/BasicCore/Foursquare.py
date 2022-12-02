
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [20, 100, 180, 260, 340, 420]#点的横坐标
#ImprovedEd = [ 9.421, 38.5, 82.92, 190.26, 256.571]#线1的纵坐标
#NormalEd = [16.12, 128.484, 186.973, 621.971, 622.714]#线2的纵坐标

ImprovedEd = [ 220.66740088105726, 221.44911504424778,  221.70509977827052, 221.73883928571428,  221.73883928571428, 216.33179723502303]#线1的纵坐标
plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="BasicCore")#s-:方形
plt.xlabel("Core number",fontsize=30)#横坐标名字
plt.ylabel("Density",fontsize=30)   # 纵坐标label

x_major_locator=MultipleLocator(80)

ax=plt.gca()

ax.xaxis.set_major_locator(x_major_locator)

plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()