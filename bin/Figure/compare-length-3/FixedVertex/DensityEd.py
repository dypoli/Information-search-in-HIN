
import matplotlib.pyplot as plt

plt.figure(figsize=(7.4,4.8))
x = [2,4,6,8,10,12,14]#点的横坐标
ImprovedEd = [ 1.9511343804537522,  3.7257844474761255, 5.643304130162703, 7.219631901840491, 9.341880341880342, 10.951865222623345, 13.106971153846153]#线1的纵坐标
NormalEd = [1.9511343804537522, 3.732142857142857, 5.643304130162703, 7.219631901840491, 9.341880341880342, 10.951865222623345, 13.106971153846153]#线2的纵坐标
plt.plot(x,ImprovedEd,'-',linewidth='4',color = 'black',label="ImprovedEd")#s-:方形
plt.plot(x,NormalEd,'--',linewidth='4',color = 'black',label="NormalEd")#o-:圆形
plt.xlabel("Edge Number",fontsize=30)#横坐标名字
plt.ylabel("Density",fontsize=30)   # 纵坐标label
plt.xticks(fontsize=25)
plt.yticks(fontsize=20)
plt.show()