<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<%@ include file="/commons/basejs.jsp" %>
<style type="text/css">
	.sj20 {padding-left:20px;}
	.sj40 {padding-left:40px;}
	.gh1 {margin-bottom:20px;}
	.java {color:#de1c1c;}
	.zjsj20 {padding-left:20px;color:#34b90b;}
	.hh {word-break:break-all;word-wrap:break-word;}
</style>
</head>
<body>
	<div class="gh1">
		/**</br>
		 * 1.Lambda表达式</br>
		 */</br>
		@Test</br>
		<p class="java">public void testLambda() {</p>
			<span class="sj20"/>list.forEach(System.out::println);</br>
			<span class="sj20"/>list.forEach(e -> System.out.println("方式二：" + e));</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 2.Stream函数式操作流元素集合</br>
		 */</br>
		@Test</br>
		<p class="java">public void testStream() {</p>
			<span class="sj20"/>List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);</br>
			<span class="sj20"/>System.out.println("求和：" + nums.stream()<span class="zjsj20">// 转成Stream</span></br>
					<span class="sj40"/>.filter(team -> team != null)<span class="zjsj20">// 过滤</span></br>
					<span class="sj40"/>.distinct()<span class="zjsj20">// 去重</span></br>
					<span class="sj40"/>.mapToInt(num -> num * 2)<span class="zjsj20">// map操作</span></br>
					<span class="sj40"/>.skip(2)<span class="zjsj20">// 跳过前2个元素</span></br>
					<span class="sj40"/>.limit(4)<span class="zjsj20">// 限制取前4个元素</span></br>
					<span class="sj40"/>.peek(System.out::println)<span class="zjsj20">// 流式处理对象函数</span></br>
					<span class="sj40"/>.sum());</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 3.接口新增：默认方法与静态方法 default 接口默认实现方法是为了让集合类默认实现这些函数式处理，而不用修改现有代码</br>
		 * （List继承于Iterable<T>，接口默认方法不必须实现default forEach方法）</br>
		 */</br>
		@Test</br>
		<p class="java">public void testDefaultFunctionInterface() {</p>
			<span class="sj20"/><span class="zjsj20">// 可以直接使用接口名.静态方法来访问接口中的静态方法</span></br>
			<span class="sj20"/>JDK8Interface1.staticMethod();</br>
			<span class="sj20"/><span class="zjsj20">// 接口中的默认方法必须通过它的实现类来调用</span></br>
			<span class="sj20"/>new JDK8InterfaceImpl1().defaultMethod();</br>
			<span class="sj20"/><span class="zjsj20">// 多实现类，默认方法重名时必须复写</span></br>
			<span class="sj20"/>new JDK8InterfaceImpl2().defaultMethod();</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		<p class="java">public class JDK8InterfaceImpl1 implements JDK8Interface1 {</p>
			<span class="zjsj20">// 实现接口后，因为默认方法不是抽象方法，重写/不重写都成！</span></br>
			<span class="zjsj20">// @Override</span></br>
			<span class="zjsj20">// public void defaultMethod(){</span></br>
			<span class="zjsj20">// System.out.println("接口中的默认方法");</span></br>
			<span class="zjsj20">// }</span>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		<p class="java">public class JDK8InterfaceImpl2 implements JDK8Interface1, JDK8Interface2 {</p>
			<span class="sj20"/><span class="zjsj20">// 实现接口后，默认方法名相同，必须复写默认方法</span></br>
			<span class="sj20"/>@Override</br>
			<span class="sj20"/>public void defaultMethod() {</br>
				<span class="sj40"/><span class="zjsj20">// 接口的</span></br>
				<span class="sj40"/>JDK8Interface1.super.defaultMethod();</br>
				<span class="sj40"/>System.out.println("实现类复写重名默认方法！！！！");</br>
			<span class="sj20"/>}</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 4.方法引用,与Lambda表达式联合使用</br>
		 */</br>
		@Test</br>
		<p class="java">public void testMethodReference() {</p>
			<span class="zjsj20">// 构造器引用。语法是Class::new，或者更一般的Class< T >::new，要求构造器方法是没有参数；</span></br>
			<span class="sj20"/>final Car car = Car.create(Car::new);</br>
			<span class="sj20"/>final List<Car> cars = Arrays.asList(car);</br>
			<span class="zjsj20">// 静态方法引用。语法是Class::static_method，要求接受一个Class类型的参数；</span></br>
			<span class="sj20"/>cars.forEach(Car::collide);</br>
			<span class="zjsj20">// 任意对象的方法引用。它的语法是Class::method。无参，所有元素调用；</span></br>
			<span class="sj20"/>cars.forEach(Car::repair);</br>
			<span class="zjsj20">// 特定对象的方法引用，它的语法是instance::method。有参，在某个对象上调用方法，将列表元素作为参数传入；</span></br>
			<span class="sj20"/>final Car police = Car.create(Car::new);</br>
			<span class="sj20"/>cars.forEach(police::follow);</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		<p class="java">public static class Car {</p>
			<p>
			<span class="sj20"/>public static Car create(final Supplier<Car> supplier) {</br>
				<span class="sj40"/>return supplier.get();</br>
			<span class="sj20"/>}</br>
			</p>
			<p>
			<span class="sj20"/>public static void collide(final Car car) {</br>
				<span class="sj40"/>System.out.println("静态方法引用 " + car.toString());</br>
			<span class="sj20"/>}</br>
			</p>
			<p>
			<span class="sj20"/>public void repair() {</br>
				<span class="sj40"/>System.out.println("任意对象的方法引用 " + this.toString());</br>
			<span class="sj20"/>}</br>
			</p>
			<p>
			<span class="sj20"/>public void follow(final Car car) {</br>
				<span class="sj40"/>System.out.println("特定对象的方法引用 " + car.toString());</br>
			<span class="sj20"/>}</br>
			</p>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 5.引入重复注解 1.@Repeatable 2.可以不用以前的“注解容器”写法，直接写2次相同注解即可</br>
		 * </br>
		 * Java 8在编译器层做了优化，相同注解会以集合的方式保存，因此底层的原理并没有变化。</br>
		 */</br>
		@Test</br>
		<p class="java">public void RepeatingAnnotations() {</p>
			<span class="sj20"/>RepeatingAnnotations.main(null);</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 6.类型注解 新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）</br>
		 * </br>
		 */</br>
		@Test</br>
		<p class="java">public void ElementType() {</p>
			<span class="sj20"/>Annotations.main(null);</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 7.最新的Date/Time API (JSR 310)</br>
		 */</br>
		@Test</br>
		<p class="java">public void DateTime() {</p>
			<p>
			<span class="zjsj20">// 1.Clock</span></br>
			<span class="sj20"/>final Clock clock = Clock.systemUTC();</br>
			<span class="sj20"/>System.out.println(clock.instant());</br>
			<span class="sj20"/>System.out.println(clock.millis());</br>
			</p>
			<p>
			<span class="zjsj20">// 2. ISO-8601格式且无时区信息的日期部分</span></br>
			<span class="sj20"/>final LocalDate date = LocalDate.now();</br>
			<span class="sj20"/>final LocalDate dateFromClock = LocalDate.now(clock);</br>
			</p>
			<p>
			<span class="sj20"/>System.out.println(date);</br>
			<span class="sj20"/>System.out.println(dateFromClock);</br>
			</p>
			<p>
			<span class="zjsj20">// ISO-8601格式且无时区信息的时间部分</span></br>
			<span class="sj20"/>final LocalTime time = LocalTime.now();</br>
			<span class="sj20"/>final LocalTime timeFromClock = LocalTime.now(clock);</br>
			</p>
			<p>
			<span class="sj20"/>System.out.println(time);</br>
			<span class="sj20"/>System.out.println(timeFromClock);</br>
			</p>
			<p>
			<span class="zjsj20">// 3.ISO-8601格式无时区信息的日期与时间</span></br>
			<span class="sj20"/>final LocalDateTime datetime = LocalDateTime.now();</br>
			<span class="sj20"/>final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);</br>
			</p>
			<p>
			<span class="sj20"/>System.out.println(datetime);</br>
			<span class="sj20"/>System.out.println(datetimeFromClock);</br>
			</p>
			<p>
			<span class="zjsj20">// 4.特定时区的日期/时间，</span></br>
			<span class="sj20"/>final ZonedDateTime zonedDatetime = ZonedDateTime.now();</br>
			<span class="sj20"/>final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);</br>
			<span class="sj20"/>final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));</br>
			</p>
			<p>
			<span class="sj20"/>System.out.println(zonedDatetime);</br>
			<span class="sj20"/>System.out.println(zonedDatetimeFromClock);</br>
			<span class="sj20"/>System.out.println(zonedDatetimeFromZone);
			</p>
			<p>
			<span class="zjsj20">// 5.在秒与纳秒级别上的一段时间</span></br>
			<span class="sj20"/>final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);</br>
			<span class="sj20"/>final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);</br>
			</p>
			<p>
			<span class="sj20"/>final Duration duration = Duration.between(from, to);</br>
			<span class="sj20"/>System.out.println("Duration in days: " + duration.toDays());</br>
			<span class="sj20"/>System.out.println("Duration in hours: " + duration.toHours());</br>
			</p>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 8.新增base64加解密API</br>
		 */</br>
		@Test</br>
		<p class="java">public void testBase64() {</p>
			<p>
			<span class="sj20"/>final String text = "就是要测试加解密！！abjdkhdkuasu!!@@@@";</br>
			<span class="sj20"/>String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));</br>
			<span class="sj20"/>System.out.println("加密后=" + encoded);</br>
			</p>
			<span class="sj20"/>final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);</br>
			<span class="sj20"/>System.out.println("解密后=" + decoded);</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 9.数组并行（parallel）操作</br>
		 */</br>
		@Test</br>
		<p class="java">public void testParallel() {</p>
			<span class="sj20"/>long[] arrayOfLong = new long[20000];</br>
			<span class="zjsj20">// 1.给数组随机赋值</span></br>
			<span class="sj20"/>Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));</br>
			<span class="zjsj20">// 2.打印出前10个元素</span></br>
			<span class="sj20"/>Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));</br>
			<span class="sj20"/>System.out.println();</br>
			<span class="zjsj20">// 3.数组排序</span></br>
			<span class="sj20"/>Arrays.parallelSort(arrayOfLong);</br>
			<span class="zjsj20">// 4.打印排序后的前10个元素</span></br>
			<span class="sj20"/>Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));</br>
			<span class="sj20"/>System.out.println();</br>
		<p class="java">}</p>
	</div>
	<div class="gh1">
		/**</br>
		 * 10.JVM的PermGen空间被移除：取代它的是Metaspace（JEP 122）元空间</br>
		 */</br>
		@Test</br>
		<p class="java">public void testMetaspace() {</p>
			<span class="zjsj20">// -XX:MetaspaceSize初始空间大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整</span></br>
			<span class="zjsj20">// -XX:MaxMetaspaceSize最大空间，默认是没有限制</span></br>
			<span class="zjsj20">// -XX:MinMetaspaceFreeRatio在GC之后，最小的Metaspace剩余空间容量的百分比，减少为分配空间所导致的垃圾收集</span></br>
			<span class="zjsj20">// -XX:MaxMetaspaceFreeRatio在GC之后，最大的Metaspace剩余空间容量的百分比，减少为释放空间所导致的垃圾收集</span></br>
		<p class="java">}</p>
	</div>
</body>
</html>
