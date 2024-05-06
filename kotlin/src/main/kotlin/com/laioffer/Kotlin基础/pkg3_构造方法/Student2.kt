package Kotlin基础.pkg3_构造方法

/**
 * @description 如果构造方法拥有注解或是可见性修饰符，
 * 那么constructor关键字就是不能省略掉的，并且它位于修饰符后面。否则可以省略constructor关键字
 *
 * @date 2023/1/29 14:59
 */
class Student2 private constructor(username: String) {}

class Student22(username: String) {}
