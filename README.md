# AndroidAPIService

##前言
目前移动端去访问服务器的方式，除了比较古老的WebService方式，还有一种就是利用RestfulAPI 

使用RESTfulAPI可以大大减低与服务器交互的复杂程度，这里本人做了一点小demo，供测试与服务器的数据交互问题的，如果有什么问题，希望大家能及时告知。

##如何访问服务器
通过httpClient对RESTfulAPI进行访问，这里需要注意的是参数和中文编码问题；

`httpClient.getParams().setContentCharset("UTF-8");`

以及如何填充参数，这里针对get和post方法分成两种填充方式，一种就是作为queryString，另外一种就是常见的NameValuePair。
