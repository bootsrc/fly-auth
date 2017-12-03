# fly-auth
## 简介
fly-auth是使用了JWT标准进行权限认证的框架

作者：Frank Liu (Liu Shaoming)

使用了如下技术:
1. JWT
2. spring boot
3. EventSource(HTML5特性)
4. 使用了jjwt.jar

## flylib-boot-starter项目详细说明(JWT)
JWT(json web token)机制，可以让微服务变成无状态的服务。fly-auth就是为完成这个功能而诞生的

JWT的应用场景是怎么产生的呢？

**分布式环境下session管理**

传统的单体应用，一个应用只部署在一个servlet容器中，即便部署在多个servlet容器中也能很容易的通过servlet容器本身的session复制机制来解决session同步的问题。
	随着应用服务器部署的越来越多，基于servlet容器本身的session复制能力已经不能满足session同步的问题，所以采用基于memcache或者redis的集中session管理方案吸引了架构师的眼球，在很长一段时间内，这种session管理方案确实是一个不错的选择。
	但是，在微服务的架构下，传统的单体应用被拆分成若干个微小的独立服务，在独立的进程中运行，这些微服务可能采用的是不同的开发语言，不同的系统架构，不同的servlet服务器，运行在不同的硬件服务器上。所以基于servlet容器的session复制机制已经完全失去作用，这个时候基于像memcache或者redis这种集中的session管理方案或许可以解决一部分问题。
	然而，基于memcache和redis这种集中的session管理也有自身的缺点，或局限性，主要有以下两点：
1)	memcache或者redis的稳定性会影响业务系统的可用性
2)	微服务的并发能力受限于memcache或者redis的并发能力


由此可见，集中的session管理也不是最优的选择。
	解决计算机难题的唯一有效手段就是“不解决”，我们不再关注session本
	，把session的问题抛开，让我们的服务都是无状态的。于是我们把目光转向WEB TOKEN，接下来就是如何设计这个token的问题，接下来就想到了JWT（Json web token ），jwt非常适合在微服务架构下管理使用。
