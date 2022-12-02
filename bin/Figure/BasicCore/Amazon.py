
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [1, 3, 5, 7, 9, 11]#点的横坐标
#ImprovedEd = [ 9.421, 38.5, 82.92, 190.26, 256.571]#线1的纵坐标
#NormalEd = [16.12, 128.484, 186.973, 621.971, 622.714]#线2的纵坐标

ImprovedEd = [ 895.1539708265802,  895.8893396073341,  901.1753118844387,  917.055084011779, 935.2296505756412, 907.2305986696231]#线1的纵坐标
plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="BasicCore")#s-:方形
plt.xlabel("Core number",fontsize=30)#横坐标名字
plt.ylabel("Density",fontsize=30)   # 纵坐标label

x_major_locator=MultipleLocator(1)

ax=plt.gca()

ax.xaxis.set_major_locator(x_major_locator)

plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()