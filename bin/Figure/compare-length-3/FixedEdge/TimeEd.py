
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [5,10,15,20,25]#点的横坐标
#ImprovedEd = [ 15.117, 67.444, 170.023, 316.447, 527.098]#线1的纵坐标
#NormalEd = [38.452, 188.804, 473.076, 892.842, 1500.269]#线2的纵坐标

ImprovedEd = [ 4.40, 11, 44, 83, 106]#线1的纵坐标
NormalEd = [6.90, 32, 81, 185, 283]#线2的纵坐标

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
