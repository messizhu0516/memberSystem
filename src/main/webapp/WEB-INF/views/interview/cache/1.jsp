<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body>
<h3>Redis和Memcache对比及选择</h3>
<p>
    我这段时间在用redis，感觉挺方便的，但比较疑惑在选择内存数据库的时候到底什么时候选择redis，什么时候选择memcache，然后就查到下面对应的资料，是来自redis作者的说法（stackoverflow上面）。</br>

    没有必要过多的关注性能。由于Redis只使用单核，而Memcached可以使用多核，所以在比较上，平均每一个核上Redis在存储小数据时比Memcached性能更高。而在100k以上的数据中，Memcached性能要高于Redis，</br>
    虽然Redis最近也在存储大数据的性能上进行优化，但是比起Memcached，还是稍有逊色。说了这么多，结论是，无论你使用哪一个，每秒处理请求的次数都不会成为瓶颈。</br>

    你需要关注内存使用率。对于key-value这样简单的数据储存，memcache的内存使用率更高。如果采用hash结构，redis的内存使用率会更高。当然，这些都依赖于具体的应用场景。</br>

    你需要关注关注数据持久化和主从复制时，只有redis拥有这两个特性。如果你的目标是构建一个缓存在升级或者重启后之前的数据不会丢失的话，那也只能选择redis。</br>

    你应该关心你需要的操作。redis支持很多复杂的操作，甚至只考虑内存的使用情况，在一个单一操作里你常常可以做很多，而不需要将数据读取到客户端中（这样会需要很多的IO操作）。这些复杂的操作基本上和纯GET和POST操作一样快，</br>
    所以你不只是需要GET/SET而是更多的操作时，redis会起很大的作用。</br>

    对于两者的选择还是要看具体的应用场景，如果需要缓存的数据只是key-value这样简单的结构时，我在项目里还是采用memcache，它也足够的稳定可靠。如果涉及到存储，排序等一系列复杂的操作时，毫无疑问选择redis。
</p>
   

    <h3>关于redis和memcache的不同，下面罗列了一些相关说法，供记录：</h3>
<p>
    redis和memecache的不同在于[2]：</br>

    1、存储方式：</br>
    memecache 把数据全部存在内存之中，断电后会挂掉，数据不能超过内存大小</br>
    redis有部份存在硬盘上，这样能保证数据的持久性，支持数据的持久化（笔者注：有快照和AOF日志两种持久化方式，在实际应用的时候，要特别注意配置文件快照参数，要不就很有可能服务器频繁满载做dump）。</br>
    2、数据支持类型：</br>
    redis在数据支持上要比memecache多的多。</br>
    3、使用底层模型不同：</br>
    新版本的redis直接自己构建了VM 机制 ，因为一般的系统调用系统函数的话，会浪费一定的时间去移动和请求。</br>
    4、运行环境不同：</br>
    redis目前官方只支持LINUX 上去行，从而省去了对于其它系统的支持，这样的话可以更好的把精力用于本系统 环境上的优化，虽然后来微软有一个小组为其写了补丁。但是没有放到主干上
</p>
 
<p>
个人总结一下，有持久化需求或者对数据结构和处理有高级要求的应用，选择redis，其他简单的key/value存储，选择memcache。
</p>
</body>
</html>
