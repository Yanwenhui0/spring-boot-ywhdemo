# spring-fastdfs

# 安装 fastdfs

fastdfs 5.11版本对照：Version 5.11对应的fastdfs-nginx-module的Version 1.20 

fastdfs  5.10版本对照：Version 5.10对应的fastdfs-nginx-module的Version 1.19

如果版本不对应，后期安装会报错！！！

## 1.安装前的准备

首先搭建需要用到的所有工具

- fastdfs-5.11
- fastdfs-client-java-master -> java client
- fastdfs-nginx-module-1.20
- libfastcommon-master
- nginx-1.12.0.tar

下面开始安装：

**首先下载 所需全部工具运行命令**

```shell script
yum -y install zlib zlib-devel pcre pcre-devel gcc gcc-c++ openssl openssl-devel libevent libevent-devel perl unzip net-tools wget
yum install lrzsz -y
```

**通过rz命令 或者sftp上传到/home目录下自己新建一个目录（根据个人习惯）**

这边我创建在 /home/my-fastdfs

## 2.安装 libfastcommon

**解压刚才上传的文件，然后进入解压完成的文件目录，找的 make.sh 并执行安装**

```shell script
unzip libfastcommon-master.zip 
cd libfastcommon-master
./make.sh 
./make.sh install
```

**如果没有错误就可以执行软链接了**

```shell script
ln -s /usr/lib64/libfastcommon.so /usr/local/lib/libfastcommon.so
ln -s /usr/lib64/libfastcommon.so /usr/lib/libfastcommon.so
ln -s /usr/lib64/libfdfsclient.so /usr/local/lib/libfdfsclient.so
ln -s /usr/lib64/libfdfsclient.so /usr/lib/libfdfsclient.so
```

## 3.安装 FastDfs

**回到 /home/my-fastdfs (创建的目录下)解压 fastdfs-5.11.zip 并进入解压完成的目录**

```shell script
unzip fastdfs-5.11.zip
cd fastdfs-5.11
./make.sh
./make.sh install
```

**把这三个文件复制一份，并去掉 sample**

```shell script
cp client.conf.sample client.conf
cp storage.conf.sample storage.conf
cp tracker.conf.sample tracker.conf
```

## 4.安装 tracker

**创建 tarcker 工作目录(个人习惯)**

这边我创建在 /usr/local/fastdfs

**配置 tracker**

```shell script
cd /etc/fdfs
vi tracker.conf
```

**打开后找到下面4处然后修改即可**

```yaml
1.disabled=false #默认开启 
2.port=22122 #默认端口号 
3.base_path=/usr/local/fastdfs/fastdfs_tracker #刚刚创建的目录 
4.http.server_port=6666 #默认端口是8080
```

**启动 tracker**

```shell script
service fdfs_trackerd start # 不成功则使用下面这条
systemctl start fdfs_trackerd 
```

**成功之后可以看见**

```shell script
[root@localhost fdfs]# service fdfs_trackerd start
Starting fdfs_trackerd (via systemctl):                    [  OK  ]
```

**我们不能每次都这么启动tracker，我们需要给tracker加入开机启动,首先需要给执行权限，然后开始修改rc.local**

```shell script
chmod +x /etc/rc.d/rc.local
vi /etc/rc.d/rc.local
service fdfs_trackerd start # 在最后加上这句话
```

## 5.安装 storage

**为storage配置工作目录，由于storage还需要一个目录用来存储数据，所以我另外多建了一个fasdfs_storage_data** 

```shell script
mkdir fastdfs_storage  
mkdir fastdfs_storage_data  
mkdir fastdfs_tracker
```

**修改 storage.conf**

```shell script
vi /etc/fdfs/storage.conf
```

找到如下几处地方修改即可

```yaml
1.disabled=false 
2.group_name=group1 #组名，根据实际情况修改 
3.port=23000  #设置storage的端口号，默认是23000，同一个组的storage端口号必须一致 
4.base_path=/usr/local/fastdfs/fastdfs_storage  #设置storage数据文件和日志目录 
5.store_path_count=1 #存储路径个数，需要和store_path个数匹配 
6.#base_path0=/usr/local/fastdfs/fastdfs_storage_data #实际文件存储路径 
6.store_path0=/usr/local/fastdfs/fastdfs_storage_data
7.tracker_server=192.168.150.132:22122 #我CentOS7的ip地址 
8.http.server_port=8888 #设置 http 端口号
```

**保存之后 创建软引用,启动storage**

```shell script
ln -s /usr/bin/fdfs_storaged /usr/local/bin
service fdfs_storaged start # 如果不行，使用下面的命令
systemctl start fdfs_storaged
```

**设置开机自启，修改rc.local**

```shell script
chmod +x /etc/rc.d/rc.local
vi /etc/rc.d/rc.local
service fdfs_storaged start # 在最后加上这句话
```

**服务启动，到此fastdfs已经配置完成了。最后我们再确认一下，storage是否注册到了tracker中去。** 

```shell script
/usr/bin/fdfs_monitor /etc/fdfs/storage.conf
# 成功后可以看到： 
ip_addr = 192.168.150.132 (localhost.localdomain) ACTIVE 的字样
```

**ok，修改客户端配置文件**

```shell script
vi /etc/fdfs/client.conf
```

找到如下几处地方修改即可
```yaml
base_path=/usr/local/fastdfs/fastdfs_tracker #tracker服务器文件路径
tracker_server=192.168.150.132:22122 #tracker服务器IP地址和端口号
http.tracker_server_port=6666 # tracker 服务器的 http端口号，必须和tracker的设置对应起来
```

**测试**

```shell script
/usr/bin/fdfs_upload_file  /etc/fdfs/client.conf  ~/test.txt
# 成功之后会返回路径
group1/M00/00/00/wKiWhFrdeCeAC_vCAABqgowGIFg399.txt
```

## HTTP请求不能访问文件的原因
我们在使用FastDFS部署一个分布式文件系统的时候，通过FastDFS的客户端API来进行文件的上传、下载、删除等操作。同时通过FastDFS的HTTP服务器来提供HTTP服务。但是FastDFS的HTTP服务较为简单，无法提供负载均衡等高性能的服务，所以FastDFS的开发者——淘宝的架构师余庆同学，为我们提供了Nginx上使用的FastDFS模块（也可以叫FastDFS的Nginx模块）。 
FastDFS通过Tracker服务器,将文件放在Storage服务器存储,但是同组之间的服务器需要复制文件,有延迟的问题.假设Tracker服务器将文件上传到了192.168.128.131,文件ID已经返回客户端,这时,后台会将这个文件复制到192.168.128.131,如果复制没有完成,客户端就用这个ID在192.168.128.131取文件,肯定会出现错误。这个fastdfs-nginx-module可以重定向连接到源服务器取文件,避免客户端由于复制延迟的问题,出现错误。 
正是这样，FastDFS需要结合nginx，所以取消原来对HTTP的直接支持。

## 6. FastDFS 的 nginx 模块安装

**安装 nginx**

```shell script
# 安装nginx所需的依赖lib
yum -y install pcre pcre-devel  
yum -y install zlib zlib-devel  
yum -y install openssl openssl-devel

# 解压nginx 和 fastdfs-nginx-module:
tar -zxvf nginx-1.12.0.tar.gz
unzip fastdfs-nginx-module-master.zip
```

**然后进入 nginx 安装目录，添加 fastdfs-nginx-module**

```shell script
./configure --prefix=/usr/local/nginx --add-module=/usr/local/nginx/fastdfs-nginx-module-1.20/src    #解压后fastdfs-nginx-module所在的位置
```

**开始安装** 

```shell script
make
make install
```

**修改nginx.conf**

```yaml
server {
        listen       9999;
        server_name  localhost;

        location / {
            root   html;
            index  index.html index.htm;
        }

        location /group1/M00 {
            root /usr/local/fastdfs/fastdfs_storage_data/data;
            ngx_fastdfs_module;
        }
}
```

**然后进入FastDFS安装时的解压过的目录，将http.conf和mime.types拷贝到/etc/fdfs目录下**
**另外还需要把fastdfs-nginx-module安装目录中src目录下的mod_fastdfs.conf也拷贝到/etc/fdfs目录下**

```shell script
cd /home/my-fastdfs/fastdfs-5.11/conf/
cp http.conf /etc/fdfs/
cp mime.types /etc/fdfs/
cp /usr/local/nginx/fastdfs-nginx-module-1.20/src/mod_fastdfs.conf /etc/fdfs/
```

**对刚刚拷贝的mod_fastdfs.conf文件进行修改**
```shell script
vi /etc/fdfs/mod_fastdfs.conf
```

**如下**

```yaml
base_path=/usr/local/fastdfs/fastdfs_storage  #保存日志目录
tracker_server=192.168.150.132:22122 #tracker服务器的IP地址以及端口号
storage_server_port=23000 #storage服务器的端口号
url_have_group_name = true #文件 url 中是否有 group 名
store_path0=/usr/local/fastdfs/fastdfs_storage_data   #存储路径
group_count = 3 #设置组的个数，事实上这次只使用了group1

# 在文件最后设置组
[group1]
group_name=group1
storage_server_port=23000
store_path_count=1
store_path0=/usr/local/fastdfs/fastdfs_storage_data
store_path1=/usr/local/fastdfs/fastdfs_storage_data

# group settings for group #2
# since v1.14
# when support multi-group, uncomment following section as neccessary
[group2]
group_name=group2
storage_server_port=23000
store_path_count=1
store_path0=/usr/local/fastdfs/fastdfs_storage_data

[group3]
group_name=group3
storage_server_port=23000
store_path_count=1
store_path0=/usr/local/fastdfs/fastdfs_storage_data
```

**创建M00至storage存储目录的符号连接**

```shell script
ln  -s  /usr/local/fastdfs/fastdfs_storage_data/data/ /usr/local/fastdfs/fastdfs_storage_data/data/M00
```

**ok,启动 nginx**