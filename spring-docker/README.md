## JDK docker 镜像

| openjdk | java |
|:-------:|:----:|
| openjdk:8            | java:8               |
| *openjdk:8-jre       | *java:8-jre          |
| openjdk:8-jre-alpine | openjdk:8-jre-alpine |
| openjdk:8-jre-slim   | openjdk:8-jre-slim   |

### JDK 镜像的关键字

- stretch 关键字：

    以8-jre-stretch这个tag为例，其中的stretch表明这个镜像的操作系统是debian9，这是debian的一个稳定版本
- alpine 关键字：

    以13-ea-19-jdk-alpine3.9这个tag为例，其中的alpine表明镜像的操作系统是alpine linux，alpine linux本身很小，alpine镜像的大小是5M左右，如下图，因此以alpine作为基础镜像构建出的openjdk镜像也很小

- oraclelinux7关键字：

    以13-ea-oraclelinux7这个tag为例，其中的oraclelinux7表明镜像的操作系统是Oracle Linux 7，从jdk12开始，openjdk官方开始提供基于Oracle Linux 7的jdk镜像

- slim关键字：

    以8-jre-slim这个tag为例，其中的slim表明当前的jre并非标准jre版本，而是headless版本，该版本的特点是去掉了UI、键盘、鼠标相关的库，因此更加精简，适合服务端应用使用，官方的建议是除非有明确的体积限制是再考虑使用该版本

- ea关键字：
    以13-ea-19-jdk-alpine3.9这个tag为例，其中的ea的意思是"Early Access"，这里代表jdk13正是发布之前的预览版本，该版本带有新特性并且修复了若干bug，但毕竟是预览版，质量还未达到release要求，不推荐生产环境使用

