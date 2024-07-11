package com.laioffer.Kotlin基础.pkg4_继承与重写

// 在 Kotlin 中，所有类在默认情况下，都是无法继承的。
// 换句话说，在 Kotlin 中，所有类默认情况下都是 final 的
// class Parent(name: String, age: Int) {}

// ❌ This type is final, so it cannot be inherited from
// class Child(name: String, age: Int) : Parent(name, age) {}

open class Parent1(name: String, age: Int) {}

class Child1(name: String, age: Int) : Parent1(name, age) {}