# Kotlin学习

1.拥有主函数 fun main(){

}

- 函数名称应采用驼峰式大小写形式，并且应该是动词或动词短语。
- 每个语句都应单独占一行。
- 左花括号应出现在函数开始行的末尾。
- 左花括号前应有一个空格



const val 编译时常量

Map 键值对集合    Set  无重复的元素集合  val 不可改变的量，常量

修饰符const不适用局部变量，常量只能在函数外定义

看起来都是引用类型，编译器会自动转换成java基本类型

range 从哪里到哪里     eg:

if(number in 10..59){

println("不及格")

}

java 的if 是语句，kt的if 是表达式，有返回值，unit 代替java 中的关键字

when表达式相当于switch

" ` "为反引号，进行java互调或者特殊时刻的引用

#### 匿名函数Lamada

```
// 测试
fun main(args: Array<String>) {
    val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
    println(sumLambda(1,2))  // 输出 3
}
```

使用lamada作为参数就需要声明成内联

最后一行为返回值  ，不用写return

val inte:()->String//声明函数

inte={//对函数的实现

//返回值

“Derry”

}

##### 类型推断

不用写返回值，可以直接推断出返回值

方法名：必须指定参数类型和返回类型

方法名=类型推断返回类型

val inte ={ v1:Double,va:float,v3:Int ->

}

#### it关键字

匿名函数只有一个参数时，it代表这个参数的参数名称，类型会具有这个参数的类型变化

#### 函数中定义参数，函数为参数

java中需要用接口的方式实现

#### 包声明

```kotlin
package com.runoob.main

import java.util.*

fun test() {}
class Runoob {}
```

kotlin源文件不需要相匹配的目录和包，源文件可以放在任何文件目录。

以上例中 test() 的全名是 com.runoob.main.test、Runoob 的全名是 com.runoob.main.Runoob。

如果没有指定包，默认为 **default** 包。

#### 函数定义

函数定义关键字fun ,参数格式：参数:类型

fun sum(a:Int ,b:Int ): Int{

return a+b

}

#### 可变长参数函数

用vararg关键字进行标识，即参数数量可变

```kotlin
fun vars(vararg v:Int){
    for(vt in v){
        print(vt)
    }
}

// 测试
fun main(args: Array<String>) {
    vars(1,2,3,4,5)  // 输出12345
}
```

#### 定义常量和变量

var    name :    String ="Derry"

可读  变量名   类型      值

var <标识符>:<类型>=<初始值>

不可变变量定义：val 关键字，只能赋值一次的变量(类似Java中final修饰的变量)

val <标识符>:<类型>=<初始值>

常量与变量都可以没有初始化值,但是在引用前必须初始化

编译器支持自动类型判断,即声明时可以不指定类型,由编译器判断。

```kotlin
val a: Int = 1
val b = 1       // 系统自动推断变量类型为Int
val c: Int      // 如果不在声明时初始化则必须提供变量类型
c = 1           // 明确赋值


var x = 5        // 系统自动推断变量类型为Int
x += 1           // 变量可修改
```

#### 字符串模板

```kotlin
var a = 1
// 模板中的简单名称：
val s1 = "a is $a" 

a = 2
// 模板中的任意表达式：
val s2 = "${s1.replace("is", "was")}, but now is $a"
```

#### **NULL检查机制**

使用时进行空判断处理，有两种处理方式



