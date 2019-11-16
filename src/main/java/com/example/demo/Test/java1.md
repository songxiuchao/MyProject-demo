1.Class.forName（）---》装载，链接，校验，准备，解析，初始化:（在堆内存中创建对象，在方法区中拷贝常量带给堆中，赋值给堆内存）
ClassLoader.loadClass（）-->装载
2.newInstance: 弱类型。低效率。只能调用无参构造。
new: 强类型。相对高效。能调用任何public构造。
3.cglib：生成目标类类的接口
jdk:生成目标类的子类
4.final:类 不能被继承       方法：不能被重写       常量：不可以被改变  （例如String  ，不能用自定义String代替java.lang.String）
5.public class SingleTon(){
private static class   SingletonHolder(){
    private static final Singleton Instance=new Singleton();
}
private static final Singleton getInstance(){
    return SingletonHolder.getInstance;
}
}
6.存取方式上，数组可以顺序存取或者随机存取，而链表只能顺序存取;
存储位置上，数组逻辑上相邻的元素在物理存储位置上也相邻，而链表不一定；　
存储空间上，链表由于带有指针域，存储密度不如数组大；　
按序号查找时，数组可以随机访问，时间复杂度为O(1)，而链表不支持随机访问，平均需要O(n)；　
按值查找时，若数组无序，数组和链表时间复杂度均为O(1)，但是当数组有序时，可以采用折半查找将时间复杂度降为O(logn)；　
插入和删除时，数组平均需要移动n/2个元素，而链表只需修改指针即可;
7.error是错误
Exception：异常Java.lang.ClassNotFoundException ，Java.lang.NoSuchMetodException ，java.io.IOException
Java.lang.ArithmeticException
Java.lang.ArrayStoreExcetpion
Java.lang.ClassCastException
Java.lang.IndexOutOfBoundsException
Java.lang.NullPointerException
8.
堆（新生代 ，老年代，元数据区）：Eden  From(Survivor) to(Survivor) 
垃圾回收时，新生代没用的将会被回收，被用到的存到FromSurvivor中，当进行第二次垃圾回收时，回收新生代From和to倒换一下，时To总是为空
当有大对象时或者长时间为被回收的将会被移动到元数据区（我们常说的永久代）,当永久代满了的时候进行全部垃圾回收
垃圾回收器：
serial（标记复制算法）：新生代的单线程版本，会阻塞，内存消耗大
ParNew（标记复制算法）:新生代的多线程版本
ParallelScavenge（标记复制算法）：并行的多线程收集器（吞吐量）
SerialOld（标记整理）：单线程的老年代版本
ParallelOld（标记整理）：多线程的老年代版本
CMS（标记整理）；初始标记，并发标记，重新标记，并发清楚，配合ParNew使用：（吞吐量）
G1：注重停顿时间,jdk8以后默认
9.引用计数发：两个空对象互相引用
可达性分析法：类似二叉树一样查找
10.内存溢出：老年代被占满  持久代被占满 堆栈溢出
11.TransactionDefinition定义中包括了如下几个表示传播行为的常量：
TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。
