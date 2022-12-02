
import matplotlib.pyplot as plt

plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedEd = [6.4, 21.349, 38.249, 57.488, 74.2, 170.8, 387.5]#线1的纵坐标
NormalEd = [0.345, 59.465, 128.548, 132.932, 196.028, 220.88, 460.8]#线2的纵坐标
plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="ImprovedEd")#s-:方形
plt.plot(x,NormalEd,'--',linewidth='4',color = 'black',label="NormalEd")#o-:圆形
plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Time(s)",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()