package com.eeepay.zzq.utils

/**
 * 描述：静态的工具类
 * 作者：zhuangzeqin
 * 时间: 2021/3/18-17:12
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
object TestUtils {
    @JvmStatic
    fun isNotEmpty(x: String): Boolean {
        return x.isEmpty()
    }

    /**
     * 使用 toLowerCase 和 toUpperCase 等等方法会造成哪些影响？
    如何优雅的处理空字符串？
    为什么解构声明和数据类不能在一起使用？
    Kotlin 提供的高效的文件处理方法，以及原理解析？
    Sequence 和 Iterator 有哪些不同之处？
    便捷的 joinToString 方法的使用？
    如何用一行代码实现移除字符串的前缀和后缀？
     */
    fun IgnoreUppercase(s: String) {

        //我们比较两个字符串，需要忽略大小写的时候，通常的写法是调用 toLowerCase() 方法或者 toUpperCase()
        //方法转换成大写或者小写，然后再进行比较，但是这样的话有一个不好的地方，每次调用 toLowerCase() 方法或者 toUpperCase() 方法会创建一个新的字符串，然后再进行比较。
        val oldName = "hi DHL"
        val newName = "hi dhl"
        val result = oldName.equals(newName, ignoreCase = true)
        println("$result")
        //2 如何优雅的处理空字符串
        val target = ""
        val name = if (target.isEmpty()) "dhl" else target
        //使用 ifEmpty 方法，当字符串为空字符串时，返回一个默认值
        val name2 = target.ifEmpty { "dhl" }
//        val name3 = target.ifBlank {  ""}

        //将字符串转为数字
        val input = "123"
//    val input = "123ddd"
//    val input = ""
        val number = input.toIntOrNull() ?: 0


        val data2 = listOf("Java", "Kotlin", "C++", "Python")
            .joinToString(
                separator = " | ",
                prefix = "{",
                postfix = "}"

            ) {
                it.toUpperCase()
            }

        println(data2) // {JAVA | KOTLIN | C++ | PYTHON}


        var data = "**hi dhl**"

// 移除前缀
        println(data.removePrefix("**")) //  hi dhl**
// 移除后缀
        println(data.removeSuffix("**")) //  **hi dhl
// 移除前缀和后缀
        println(data.removeSurrounding("**")) // hi dhl

// 返回第一次出现分隔符后的字符串
        println(data.substringAfter("**")) // hi dhl**
// 如果没有找到，返回原始字符串
        println(data.substringAfter("--")) // **hi dhl**
// 如果没有找到，返回默认字符串 "no match"
        println(data.substringAfter("--", "no match")) // no match

        data = "{JAVA | KOTLIN | C++ | PYTHON}"

// 移除前缀和后缀
        println(data.removeSurrounding("{", "}")) // JAVA | KOTLIN | C++ | PYTHON
    }
}