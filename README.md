# AndroidAPIService

##前言
目前移动端去访问服务器的方式，除了比较古老的WebService方式，还有一种就是利用RESTfulAPI 

使用RESTfulAPI可以大大减低与服务器交互的复杂程度，这里本人做了一点小demo，供测试与服务器的数据交互问题的，如果有什么问题，希望大家能及时告知。

##如何访问服务器
通过httpClient对RESTfulAPI进行访问，这里需要注意的是参数和中文编码问题；

`httpClient.getParams().setContentCharset("UTF-8");`

以及如何填充参数，这里针对get和post方法分成两种填充方式，一种就是作为queryString，另外一种就是常见的NameValuePair。

##结果集封装

结果集用泛型进行封装，可以接受来自服务器的任何数据类型，但是前提是客户端和服务器需要统一数据模型。

##Json数据解析
RESTfulAPI不仅在给客户端提供给服务，还在给浏览器版本提供服务，服务器返回的都是JSON数据，这里需要对JSON数据进行解析：

这里，用的`jackson` 包对JSON进行解析
