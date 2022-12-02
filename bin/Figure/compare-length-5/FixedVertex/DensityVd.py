
import matplotlib.pyplot as plt

plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedVd = [  0.9285714285714286, 1.562245728234337, 2.2285100286532953, 2.839064649243466, 3.581625441696113, 4.2677219545767375, 4.818368745716244]#线1的纵坐标
NormalVd = [0.8333333333333334, 1.5939036381514258, 2.100790513833992, 2.91198224852071, 3.6992248062015505, 4.170212765957447, 4.922077922077922]#线2的纵坐标
plt.plot(x,ImprovedVd,'-.',linewidth='4',color = 'black',label="ImprovedVd")#s-:方形
plt.plot(x,NormalVd,':',linewidth='4',color = 'black',label="NormalVd")#o-:圆形
plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Density",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()