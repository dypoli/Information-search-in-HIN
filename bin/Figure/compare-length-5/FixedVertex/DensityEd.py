
import matplotlib.pyplot as plt

plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedEd = [ 0.9444444444444444, 1.575809199318569, 2.2518301610541727, 2.857241857241857, 3.6401486988847584, 4.282835302293259, 4.905714285714286]#线1的纵坐标
NormalEd = [0.8888888888888888, 1.6105371900826446, 2.2878667724028547, 2.9044493070751276, 3.698069498069498, 4.180415828303152, 4.9221341023792355]#线2的纵坐标
plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="ImprovedEd")#s-:方形
plt.plot(x,NormalEd,'--',linewidth='4',color = 'black',label="NormalEd")#o-:圆形

plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Density",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()