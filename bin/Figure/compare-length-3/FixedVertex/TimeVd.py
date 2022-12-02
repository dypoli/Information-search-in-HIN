
import matplotlib.pyplot as plt

plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedVd = [ 5.424, 26.209, 41.105, 34.381, 47.772, 55.163, 67.870]#线1的纵坐标
NormalVd = [6.691, 50.387, 54.8, 70.74, 93.977, 110.124, 140.425]#线2的纵坐标


plt.plot(x,ImprovedVd,'-.',linewidth='4',color = 'black',label="ImprovedVd")#s-:方形
plt.plot(x,NormalVd,':',linewidth='4',color = 'black',label="NormalVd")#o-:圆形
plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Time(s)",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)

plt.show()