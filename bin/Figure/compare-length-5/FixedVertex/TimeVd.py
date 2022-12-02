
import matplotlib.pyplot as plt

plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedVd = [ 6.833, 28.452, 48.144, 67.639, 87.6, 134.4, 215.752]#线1的纵坐标
NormalVd = [0.316, 62.338, 78.334, 125.797, 139.600, 173.886, 341.426]#线2的纵坐标
plt.plot(x,ImprovedVd,'-.',linewidth='4',color = 'black',label="ImprovedVd")#s-:方形
plt.plot(x,NormalVd,':',linewidth='4',color = 'black',label="NormalVd")#o-:圆形
plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Time(s)",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()