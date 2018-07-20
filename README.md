此项目为ibatis2x的本人修改测试版本。
项目原地址 https://github.com/mybatis/ibatis-2

修改内容：
1.iBatis的dtd文档只是约束了xml的写法，select不加resultClass编译不会报错，运行时还是根据resultClass对应的类型来和查询结果做映射，既然方法签名上有返回值类型，能不能resultClass不写时，select结果直接和方法签名的返回类型做映射。
即sql.xml文件中的select标签写法不加resultClass时自动赋值为方法签名返回值类型


已完成：
2018/07/20 仅可以根据方法返回类型自动赋值

目前还不能根据接收参数类型赋值
