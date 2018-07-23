此项目为ibatis2x的本人修改测试版本。
项目原地址 https://github.com/mybatis/ibatis-2

修改内容：
1.iBatis的dtd文档只是约束了xml的写法，select不加resultClass编译不会报错，运行时还是根据resultClass对应的类型来和查询结果做映射，既然方法签名上有返回值类型，能不能resultClass不写时，select结果直接和方法签名的返回类型做映射。
即sql.xml文件中的select标签写法不加resultClass时自动赋值为方法签名返回值类型
如果某个sql不设置resultMap，程序运行这个sql只在第一次调用的时候会影响一些性能

已完成：
2018/07/20 仅可以根据方法返回类型自动赋值

ps:
目前是通过反射的方法获取方法返回值类型，但是想要对使用者透明，只能通过执行堆栈获取执行方法全限定名，这样获取不到方法的参数列表，所以执行类中的方法不支持重载。

2.在dtd文件里，select中的元素把 resultType 或者 resultMap限定为必须有一个，在编译时就让用户知道，这两个你必须指定一个(且只能是一个)，而不是运行时再报错；