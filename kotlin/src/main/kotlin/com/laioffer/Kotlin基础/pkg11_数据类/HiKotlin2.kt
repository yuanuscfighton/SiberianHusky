package Kotlin基础.pkg11_数据类

// 在jvm平台上，如果生成的类需要拥有无参构造方法，那么就需要为所有属性指定默认值。
data class Person2(val name: String = "", var age: Int = 10, var address: String = "杭州")

// 解构声明:
// 在主构造方法中有多少个参数，就会依次生成对应的component1, component2, component3, ...
// 这些方法返回的就是对应字段的值，componentN方法是用来实现解构声明的
fun main() {
  val p2 = Person2("张三", 10, "北京")

  // 解构
  val (name, age, address) = p2
  println("$name, $age, $address")

//  var (name1, address1) = p2
//  println("$name1, $address1")
}

/*

public final class Kotlin基础.pkg10_数据类.Person2 {
  public Kotlin基础.pkg10_数据类.Person2(java.lang.String, int, java.lang.String);
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

  public Kotlin基础.pkg10_数据类.Person2(java.lang.String, int, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker);
    Code:
       0: iload         4
       2: iconst_1
       3: iand
       4: ifeq          10
       7: ldc           #34                 // String
       9: astore_1
      10: iload         4
      12: iconst_2
      13: iand
      14: ifeq          20
      17: bipush        10
      19: istore_2
      20: iload         4
      22: iconst_4
      23: iand
      24: ifeq          30
      27: ldc           #36                 // String 杭州
      29: astore_3
      30: aload_0
      31: aload_1
      32: iload_2
      33: aload_3
      34: invokespecial #38                 // Method "<init>":(Ljava/lang/String;ILjava/lang/String;)V
      37: return

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
       1: ldc           #49                 // String <set-?>
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

  public final Kotlin基础.pkg10_数据类.Person2 copy(java.lang.String, int, java.lang.String);
    Code:
       0: aload_1
       1: ldc           #9                  // String name
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: aload_3
       7: ldc           #17                 // String address
       9: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
      12: new           #2                  // class Kotlin学习/kotlin基础.pkg10_数据类/Person2
      15: dup
      16: aload_1
      17: iload_2
      18: aload_3
      19: invokespecial #38                 // Method "<init>":(Ljava/lang/String;ILjava/lang/String;)V
      22: areturn

  public static Kotlin基础.pkg10_数据类.Person2 copy$default(Kotlin学习.kotlin基础.pkg10_数据类.Person2, java.lang.String, int, java.lang.String, int, java.lang.Object);
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
      40: invokevirtual #58                 // Method copy:(Ljava/lang/String;ILjava/lang/String;)LKotlin学习/kotlin基础.pkg10_数据类/Person2;
      43: areturn

  public java.lang.String toString();
    Code:
       0: new           #61                 // class java/lang/StringBuilder
       3: dup
       4: invokespecial #62                 // Method java/lang/StringBuilder."<init>":()V
       7: ldc           #64                 // String Person2(name=
       9: invokevirtual #68                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      12: aload_0
      13: getfield      #23                 // Field name:Ljava/lang/String;
      16: invokevirtual #68                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      19: ldc           #70                 // String , age=
      21: invokevirtual #68                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      24: aload_0
      25: getfield      #27                 // Field age:I
      28: invokevirtual #73                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      31: ldc           #75                 // String , address=
      33: invokevirtual #68                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      36: aload_0
      37: getfield      #29                 // Field address:Ljava/lang/String;
      40: invokevirtual #68                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      43: bipush        41
      45: invokevirtual #78                 // Method java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
      48: invokevirtual #80                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      51: areturn

  public int hashCode();
    Code:
       0: aload_0
       1: getfield      #23                 // Field name:Ljava/lang/String;
       4: invokevirtual #85                 // Method java/lang/String.hashCode:()I
       7: istore_1
       8: iload_1
       9: bipush        31
      11: imul
      12: aload_0
      13: getfield      #27                 // Field age:I
      16: invokestatic  #90                 // Method java/lang/Integer.hashCode:(I)I
      19: iadd
      20: istore_1
      21: iload_1
      22: bipush        31
      24: imul
      25: aload_0
      26: getfield      #29                 // Field address:Ljava/lang/String;
      29: invokevirtual #85                 // Method java/lang/String.hashCode:()I
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
       8: instanceof    #2                  // class Kotlin学习/kotlin基础.pkg10_数据类/Person2
      11: ifne          16
      14: iconst_0
      15: ireturn
      16: aload_1
      17: checkcast     #2                  // class Kotlin学习/kotlin基础.pkg10_数据类/Person2
      20: astore_2
      21: aload_0
      22: getfield      #23                 // Field name:Ljava/lang/String;
      25: aload_2
      26: getfield      #23                 // Field name:Ljava/lang/String;
      29: invokestatic  #98                 // Method kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
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
      58: invokestatic  #98                 // Method kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
      61: ifne          66
      64: iconst_0
      65: ireturn
      66: iconst_1
      67: ireturn

  // 提供了一个没有参数的构造方法:
  // 在jvm平台上，如果生成的类需要拥有无参构造方法，那么就需要为所有属性指定默认值。
  // data class Person2(val name: String = "", var age: Int = 10, var address: String = "杭州")
  public Kotlin基础.pkg10_数据类.Person2();
    Code:
       0: aload_0
       1: aconst_null
       2: iconst_0
       3: aconst_null
       4: bipush        7
       6: aconst_null
       7: invokespecial #102                // Method "<init>":(Ljava/lang/String;ILjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
      10: return
}


*/
