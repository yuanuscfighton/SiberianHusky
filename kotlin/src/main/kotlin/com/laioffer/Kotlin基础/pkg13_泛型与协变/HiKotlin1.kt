package Kotlin基础.pkg13_泛型与协变

/*
 * 协变（covariant）与逆变（controvariant）

  Java中，
  List<Object> 接收Object类型的list
  List<String> 只接收String类型的list

  List<String>并不是List<Object>的子类型。i.e.
      List<String> list = new ArrayList();
      List<Object> list2 = list; // ❌ 编译失败
  不可以这么做的原因是:
      因为如果可以这么做，当向list2中添加一个Date类型的元素，i.e. list2.add(new Date())，
      然而当我们在从list中取出该元素的时候，会出现类型转换异常，i.e. String str = list.get(0); (❌ 会出现类型转换异常)

  解决方案: 通配符
      List<? extends Object> list ... 表示:list可以存储Object及其子类型的元素

  举例:
      // ❌ demo
      interface Collection<E> {
          // 将另外一个集合的内容，全部添加到这个集合的里面
          void addAll(Collection<E> items);
      }

      void copyAll(Collection<Object> to, Collection<String> from) {
          to.addAll(from); ❌ 编译失败!!
      }
      编译失败原因: copyAll方法是将from集合的元素全部添加到to集合里面，但是from集合的元素类型是String，to集合的元素类型是Object
      根据上面的讲解的 Collection<String>并不是Collection<Object>的子类型


      // ✅ demo
      interface Collection<E> {
        void addAll(Collection<? extends E> items);
      }
      *
      void copyAll(Collection<Object> to, Collection<String> from) {
          to.addAll(from); ✅ 编译成功~~
          // 从from中添加到to中的元素，都被当作Object看待，而不是String类型看待，
          // 因为?既可以是String，也可以是Integer等其它类型，所以不能当作任何一个具体的子类型看待，而是当作to集合所声明的类型看待
      }

  总结:
      ? extends E:
      （1）可以接受E及其E的子类型，这种表示形式 限制了类型的上界。这种情况我们就叫作协变
      （2）Collection<String>就是Collection<? extends Object>的子类型。

      如何理解协变的概念?
        我们只能从集合或者泛型的容器中 读取相关的内容，读取的时候，我们把每个元素当做问号extends后面的那个类型
          (如，? extends E，就当成E类型看待)，而不当成实际类型判断。

  --------------------------------------------------------------------------------------------------
  --------------------------------------------------------------------------------------------------
  super限制的是下界。
      List<? super String> 表示这个list只能是把String或者String层次体系上面的类型放到list中。 这种情况就叫作逆变。

      协变用于读取，逆变用于写入。


  我们如果只从中读取数据，而不往里面写入内容，那么这样的对象叫做生产者；如果只向里面写入数据，而不从中读取数据，那么这样的数据叫做消费者。
    生产者使用: ? extends E; 消费者使用: ? super E
 */

/**
 * 类的描述: 协变（covariant）与逆变（controvariant）
 * Created by 春夏秋冬在中南 on 2023/4/9 18:05
 */
class MyClass1<T>(t: T) {
  private var t: T

  init {
    this.t = t
  }

  fun get(): T = this.t // get方法只涉及读取t的值，没有修改t的值，只涉及了协变，需要在MyClass声明的时候增加out关键字，[见HiKotlin2.kt]
}

fun myTest(myClz: MyClass1<String>) {
  val myObj: MyClass1<String> = myClz
  println(myObj.get())

  // val obj2 : MyClass<Any> = myClass 类型不匹配
}

fun main() {
  val myClz = MyClass1<String>("abc")
  myTest(myClz)
}
