import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


//由于Contract契约API还是Experimental，所以需要使用ExperimentalContracts注解声明
@ExperimentalContracts
//inline 内联对预期的性能影响并不重要。内联功能最适合具有函数类型参数的函数
public inline  fun isNotEmptyWithContract(s: String?): Boolean {
    // val a = 1
    // 这里契约的意思是: 调用 isNotEmptyWithContract 函数，
    // 会产生这样的效果: 如果返回值是true, 那就意味着 s != null.
    // 把这个契约行为告知到给编译器，编译器就知道了下次碰到这种情形，你的 s 就是非空的，
    // 自然就smart cast了。
    contract {
        returns(true) implies (s != null)
    }
    return s != null && s != ""
}

@ExperimentalContracts
public inline fun <T> Collection<T>?.isNullOrEmptyss(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmptyss != null)
    }

    return this == null || this.isEmpty()
}



