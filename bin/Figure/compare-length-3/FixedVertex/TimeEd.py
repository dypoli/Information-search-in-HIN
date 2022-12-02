
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator
plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedEd = [ 5.223, 22, 31, 35, 42.646, 54.088, 82.231]#线1的纵坐标
NormalEd = [7.017, 50, 60, 70, 91, 110.124, 160.425]#线2的纵坐标


plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="ImprovedEd")#s-:方形
plt.plot(x,NormalEd,'--',linewidth='4',color = 'black',label="NormalEd")#o-:圆形

plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Time(s)",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)

plt.show()