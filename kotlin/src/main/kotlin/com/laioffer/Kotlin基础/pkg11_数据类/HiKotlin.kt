package Kotlin基础.pkg11_数据类

// 数据类 - data class，或者叫作domain、model、实体类 (lombok:为了解决setter/getter问题)
data class Person(val name: String, var age: Int, var address: String)
// 这就相当于Java中，定义一个Person类，定义了3个私有属性，并为其生成setter和getter方法。

fun main() {
  val p = Person("张三", 10, "beijing")
  println(p) // Person(name=张三, age=10, address=beijing)
}


// 数据类需要满足如下要求：
// 1.主构造方法至少要有一个参数。(上面的Person数据类中有3个参数，name、age、address)
// 2.所有的主构造方法参数都需要被标记为var或是val，如果不标记的话，仅仅表示方法的参数。写上var/val才表示属性
// 3.数据类不能是抽象的、open的(不能被继承的)、sealed的以及inner的(内部类)
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
// 对于数据类，编译器会自动生成如下内容：
// 1.equals/hashCode
// 2.toString()方法，形式为Person(name=..., age=..., address=...)
// 3.针对属性的componentN方法，并且是按照属性的声明顺序来生成的


// 反编译，查看字节码:
// (1) 先build
// (2) 进入out/production/HiKotlin目录
// (3) javap -c Kotlin基础.pkg10_数据类.Person
/*
public final class Kotlin基础.pkg11_数据类.Person {

  // Person的构造方法
  public Kotlin基础.pkg11_数据类.Person(java.lang.String, int, java.lang.String);
    Code:
       0: aload_1
       1: ldc           #9                  // String name
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: aload_3
       7: ldc           #17                 // String address
       9: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
      12: aload_0
      13: invokespecial #20                 // Method java/lang/Object."<init>":()V
      16: aload_0
      17: aload_1
      18: putfield      #23                 // Field name:Ljava/lang/String;
      21: aload_0
      22: iload_2
      23: putfield      #27                 // Field age:I
      26: aload_0
      27: aload_3
      28: putfield      #29                 // Field address:Ljava/lang/String;
      31: return

  // 由于name是val修饰的，所以只有getName()方法，没有setName()方法
  public final java.lang.String getName();
    Code:
       0: aload_0
       1: getfield      #23                 // Field name:Ljava/lang/String;
       4: areturn

  public final int getAge();
    Code:
       0: aload_0
       1: getfield      #27                 // Field age:I
       4: ireturn

  public final void setAge(int);
    Code:
       0: aload_0
       1: iload_1
       2: putfield      #27                 // Field age:I
       5: return

  public final java.lang.String getAddress();
    Code:
       0: aload_0
       1: getfield      #29                 // Field address:Ljava/lang/String;
       4: areturn

  public final void setAddress(java.lang.String);
    Code:
       0: aload_1
       1: ldc           #42                 // String <set-?>
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: aload_0
       7: aload_1
       8: putfield      #29                 // Field address:Ljava/lang/String;
      11: return

  public final java.lang.String component1();
    Code:
       0: aload_0
       1: getfield      #23                 // Field name:Ljava/lang/String;
       4: areturn

  public final int component2();
    Code:
       0: aload_0
       1: getfield      #27                 // Field age:I
       4: ireturn

  public final java.lang.String component3();
    Code:
       0: aload_0
       1: getfield      #29                 // Field address:Ljava/lang/String;
       4: areturn

  public final Kotlin基础.pkg11_数据类.Person copy(java.lang.String, int, java.lang.String);
    Code:
       0: aload_1
       1: ldc           #9                  // String name
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: aload_3
       7: ldc           #17                 // String address
       9: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
      12: new           #2                  // class Kotlin基础/pkg11_数据类/Person
      15: dup
      16: aload_1
      17: iload_2
      18: aload_3
      19: invokespecial #49                 // Method "<init>":(Ljava/lang/String;ILjava/lang/String;)V
      22: areturn

  public static Kotlin基础.pkg11_数据类.Person copy$default(Kotlin基础.pkg11_数据类.Person, java.lang.String, int, java.lang.String, int, java.lang.Object);
    Code:
       0: iload         4
       2: iconst_1
       3: iand
       4: ifeq          12
       7: aload_0
       8: getfield      #23                 // Field name:Ljava/lang/String;
      11: astore_1
      12: iload         4
      14: iconst_2
      15: iand
      16: ifeq          24
      19: aload_0
      20: getfield      #27                 // Field age:I
      23: istore_2
      24: iload         4
      26: iconst_4
      27: iand
      28: ifeq          36
      31: aload_0
      32: getfield      #29                 // Field address:Ljava/lang/String;
      35: astore_3
      36: aload_0
      37: aload_1
      38: iload_2
      39: aload_3
      40: invokevirtual #53                 // Method copy:(Ljava/lang/String;ILjava/lang/String;)LKotlin基础/pkg11_数据类/Person;
      43: areturn

  public java.lang.String toString();
    Code:
       0: new           #56                 // class java/lang/StringBuilder
       3: dup
       4: invokespecial #57                 // Method java/lang/StringBuilder."<init>":()V
       7: ldc           #59                 // String Person(name=
       9: invokevirtual #63                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      12: aload_0
      13: getfield      #23                 // Field name:Ljava/lang/String;
      16: invokevirtual #63                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      19: ldc           #65                 // String , age=
      21: invokevirtual #63                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      24: aload_0
      25: getfield      #27                 // Field age:I
      28: invokevirtual #68                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      31: ldc           #70                 // String , address=
      33: invokevirtual #63                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      36: aload_0
      37: getfield      #29                 // Field address:Ljava/lang/String;
      40: invokevirtual #63                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      43: bipush        41
      45: invokevirtual #73                 // Method java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
      48: invokevirtual #75                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      51: areturn

  public int hashCode();
    Code:
       0: aload_0
       1: getfield      #23                 // Field name:Ljava/lang/String;
       4: invokevirtual #80                 // Method java/lang/String.hashCode:()I
       7: istore_1
       8: iload_1
       9: bipush        31
      11: imul
      12: aload_0
      13: getfield      #27                 // Field age:I
      16: invokestatic  #85                 // Method java/lang/Integer.hashCode:(I)I
      19: iadd
      20: istore_1
      21: iload_1
      22: bipush        31
      24: imul
      25: aload_0
      26: getfield      #29                 // Field address:Ljava/lang/String;
      29: invokevirtual #80                 // Method java/lang/String.hashCode:()I
      32: iadd
      33: istore_1
      34: iload_1
      35: ireturn

  public boolean equals(java.lang.Object);
    Code:
       0: aload_0
       1: aload_1
       2: if_acmpne     7
       5: iconst_1
       6: ireturn
       7: aload_1
       8: instanceof    #2                  // class Kotlin基础/pkg11_数据类/Person
      11: ifne          16
      14: iconst_0
      15: ireturn
      16: aload_1
      17: checkcast     #2                  // class Kotlin基础/pkg11_数据类/Person
      20: astore_2
      21: aload_0
      22: getfield      #23                 // Field name:Ljava/lang/String;
      25: aload_2
      26: getfield      #23                 // Field name:Ljava/lang/String;
      29: invokestatic  #93                 // Method kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
      32: ifne          37
      35: iconst_0
      36: ireturn
      37: aload_0
      38: getfield      #27                 // Field age:I
      41: aload_2
      42: getfield      #27                 // Field age:I
      45: if_icmpeq     50
      48: iconst_0
      49: ireturn
      50: aload_0
      51: getfield      #29                 // Field address:Ljava/lang/String;
      54: aload_2
      55: getfield      #29                 // Field address:Ljava/lang/String;
      58: invokestatic  #93                 // Method kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
      61: ifne          66
      64: iconst_0
      65: ireturn
      66: iconst_1
      67: ireturn
}

*/
