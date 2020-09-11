package com.xander.java8._07streamAPI;

import com.xander.java8._03methodRef.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/8 15:32
 */
public class StreamAPITest {
    List<Person> persons = null;

    @Before
    public void before() {
        persons = Arrays.asList(
                new Person("刘一", 25),
                new Person("陈二", 12),
                new Person("张三", 24),
                new Person("李四", 29),
                new Person("王五", 22),
                new Person("赵六", 15),
                new Person("孙七", 16),
                new Person("周八", 18)
        );
    }

    /**
     * 求所有年龄大于20的人，并且按年龄升序排序
     * 不用 Stream API 的做法
     */
    @Test
    public void test00() {
        // 过滤年龄大于20后的列表
        List<Person> list = new ArrayList<>();
        // 求所有年龄大于20的人，并且按年龄升序排序
        for (Person person : persons) {
            if (person.getAge() > 20)
                list.add(person);
        }

        // 排序
//        Collections.sort(list, Comparator.comparingInt(Person::getAge));//方法引用方式
        Collections.sort(list, (p1, p2) -> p1.getAge() - p2.getAge());// lambda表达式方式
        // 打印排序后的列表
        list.forEach(item -> System.out.println(item));
    }

    /**
     * 求所有年龄大于20的人，并且按年龄升序排序
     * Stream API 的做法
     */
    @Test
    public void test01() {
        // 求所有年龄大于20的人，并且按年龄升序排序
        List<Person> list = persons.stream()
                .filter(person -> person.getAge() > 20)//保留年龄大于20后的元素
                .sorted((p1, p2) -> p1.getAge() - p2.getAge())//按年龄升序排序
                .collect(Collectors.toList());//终止操作，将流转为List
        // 打印排序后的列表
        list.forEach(item -> System.out.println(item));
    }
////////////////////////////////////////////   创建操作    /////////////////////////////////////////////////////////////////////

    /**
     * 通过数组创建Stream
     */
    @Test
    public void testCreateByArray() {
        int[] nums = new int[]{0, 1, 5, 2, 4, 8, 9};
        Person[] personArray = new Person[]{
                new Person("张三", 24),
                new Person("李四", 29),
                new Person("王五", 22),
                new Person("赵六", 15)
        };
        // Arrays.stream(T[] array) 将指定的数组作为数据源，返回一个顺序流
        IntStream stream = Arrays.stream(nums);
        Stream<Person> personStream = Arrays.stream(personArray);

        //打印 stream 中的元素
        System.out.println("打印 stream 中的元素: ");
        stream.forEach(item -> System.out.print(item + " "));
        //打印 personStream 中的元素
        System.out.println("\n打印 personStream 中的元素: ");
        personStream.forEach(item -> System.out.println(item + " "));
    }

    /**
     * 通过Collection创建Stream
     */
    @Test
    public void testCreateByCollection() {
        //stream() 将此集合作为数据源，返回一个顺序流
        Stream<Person> seqStream = persons.stream();
        //parallelStream() 将此集合作为数据源，返回一个并行流
        Stream<Person> parallelStream = persons.parallelStream();


        //打印顺序流 seqStream 中的元素
        System.out.println("打印顺序流 seqStream 中的元素: ");
        seqStream.forEach(item -> System.out.println("线程名" + Thread.currentThread().getName() + "--" + item + " "));


        //并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流
        // 打印并行流 parallelStream 中的元素，并行输出，输出结果是无序的
        System.out.println("\n打印并行流 parallelStream 中的元素，并行输出，输出结果是无序的: ");
        parallelStream.forEach(item -> System.out.println("线程名" + Thread.currentThread().getName() + "--" + item + " "));

        System.out.println("\nsequential()把并行流切换成顺序流，输出: ");
        persons.parallelStream()
                .sequential()//并行流切换成顺序流
                .forEach(item -> System.out.println("线程名" + Thread.currentThread().getName() + "--" + item + " "));
    }

    /**
     * 通过Stream的静态方法创建Stream
     */
    @Test
    public void testCreateByStaticMethod() {
        // 将参数数组作为数据源，返回一个顺序流，底层调用的是Arrays.stream(T[] array)
        Stream<Integer> of = Stream.of(0, 1, 5, 2, 4, 8, 9);// 不断产生 0，2，4，6，8，10.，...，n，n+2
        System.out.println("of() 将参数数组作为数据源，返回一个顺序流: ");
        of.forEach(item -> System.out.print(item + " "));

        // 创建无限流，入参是初始元素和UnaryOperator函数式接口，返回一个有规律的无限顺序流
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2);// 不断产生 0，2，4，6，8，10.，...，n，n+2
        System.out.println("\n\niterate() 返回一个有规律的无限顺序流，限制输出前10个: ");
        iterate.limit(10).forEach(item -> System.out.print(item + " "));
        // 创建无限流，入参是Supplier，返回一个无规律的无限顺序流，其中每个元素由提供的Supplier生成，适用于生成恒定流、随机元素流
        Stream<Integer> generate = Stream.generate(() -> (int) (Math.random() * 10));// 不断产生 0-9 的随机数
        System.out.println("\n\ngenerate() 返回一个有规律的无限顺序流，限制输出前10个: ");
        generate.limit(10).forEach(item -> System.out.print(item + " "));
    }
////////////////////////////////////////////    终止操作    /////////////////////////////////////////////////////////////////////

    /**
     * allMatch操作，如果流中所有元素都匹配 predicate 或者是空流，返回true，否则返回false
     */
    @Test
    public void testStreamAllMatch() {
        Stream<Person> emptyStream = Stream.empty();
        Stream<Person> stream = persons.stream();
        // 流中所有Person元素都大于20岁
        boolean allMathchGt20 = stream.allMatch(item -> item.getAge() > 20);
        System.out.println("流中所有Person元素都大于20岁: " + allMathchGt20);
        // 空流中所有Person元素都大于20岁
        boolean allMathchGt20InEmptyStream = emptyStream.allMatch(item -> item.getAge() > 20);
        System.out.println("空流中所有Person元素都大于20岁: " + allMathchGt20InEmptyStream);
    }

    /**
     * noneMatch 操作，如果流中没有元素匹配 predicate 或者是空流，返回true，否则返回false
     */
    @Test
    public void testStreamNoneMatch() {
        Stream<Person> emptyStream = Stream.empty();
        Stream<Person> stream = persons.stream();
        // 流中没有Person元素大于30岁
        boolean noneMathchGt30 = stream.noneMatch(item -> item.getAge() > 30);
        System.out.println("流中没有Person元素大于30岁: " + noneMathchGt30);
        // 空流中所有Person元素都大于30岁
        boolean noneMathchGt30InEmptyStream = emptyStream.noneMatch(item -> item.getAge() > 30);
        System.out.println("空流中没有Person元素大于30岁: " + noneMathchGt30InEmptyStream);
    }

    /**
     * anyMatch 操作，如果流中任意一个元素匹配 predicate返回true，否则返回false，空流返回false
     */
    @Test
    public void testStreamAnyMatch() {
        Stream<Person> emptyStream = Stream.empty();
        Stream<Person> stream = persons.stream();
        // 流中任意一个Person元素都小于18岁
        boolean anyMathchGt18 = stream.anyMatch(item -> item.getAge() > 18);
        System.out.println("流中任意一个Person元素都小于18岁: " + anyMathchGt18);
        // 空流中任意一个Person元素都小于18岁
        boolean anyMathchGt18InEmptyStream = emptyStream.anyMatch(item -> item.getAge() > 18);
        System.out.println("空流中任意一个Person元素都小于18岁: " + anyMathchGt18InEmptyStream);
    }

    /**
     * collect操作：接收一个Collector实例，将流中元素收集成另外一个数据结构
     */
    @Test
    public void testStreamCollect() {
        Stream<Person> stream2List = persons.stream();
        // 将流中元素收集成 List
        List<Person> list = stream2List.collect(Collectors.toList());
        System.out.println("将流中元素收集成 List :\n" + list);

        Stream<Person> stream2Map = persons.stream();
        // 将流中元素收集成 map，key: name,value: person
        // Function，通过输入的Person，获取name作为map的key
        Function<Person, String> nameFunc = item -> item.getName();
        // Function，通过输入的Person，获取 person 作为map的value
        Function<Person, Person> personFunc = item -> item;
        Map<String, Person> map = stream2Map.collect(Collectors.toMap(nameFunc, personFunc));
        System.out.println("\n将流中元素收集成 map，key: name,value: person :\n" + map);
    }

    /**
     * count操作：返回到达终止操作时，流中元素总数
     */
    @Test
    public void testStreamCount() {
        Stream<Person> stream = persons.stream();
        // 流中元素总数
        long count = stream.count();
        System.out.println("流中元素总数: " + count);
    }

    /**
     * findAny 操作：返回任意一个元素，用Optional描述，如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
     */
    @Test
    public void testStreamFindAny() {
        Stream<Person> stream = persons.stream();
        // 非空流：返回任意一个元素
        Optional<Person> any = stream.findAny();
        System.out.println("非空流：返回任意一个元素: " + any);

        Stream<Person> emptyStream = Stream.empty();
        // 如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
        Optional<Person> findAnyEmpty = emptyStream.findAny();
        System.out.println("空流：取任意一个元素时返回空的Optional: " + findAnyEmpty);
    }

    /**
     * findFirst操作：返回流中第一个元素，用Optional描述，如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
     */
    @Test
    public void testStreamFindFirst() {
        Stream<Person> stream = persons.stream();
        // 非空流：返回流中第一个元素
        Optional<Person> first = stream.findFirst();
        System.out.println("非空流：返回流中第一个元素: " + first);

        Stream<Person> emptyStream = Stream.empty();
        // 如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
        Optional<Person> findFirstEmpty = emptyStream.findFirst();
        System.out.println("空流：取第一个第一个元素时返回空的Optional: " + findFirstEmpty);
    }

    /**
     * forEach操作：对此流的每个元素执行 Consumer 操作
     */
    @Test
    public void testStreamForEach() {
        Stream<Person> stream = persons.stream();
        //forEach操作：对此流的每个元素执行 Consumer 操作
        // 打印流中的每一个元素
        System.out.println("打印流中的每一个元素:");
        stream.forEach(item -> System.out.println(item));

        Stream<Person> stream2 = persons.stream();
        // 把流中的每一个元素收集进list
        List<Person> list = new ArrayList<>();
        stream2.forEach(list::add);
        System.out.println("\n把流中的每一个元素收集进list:");
        System.out.println(list);
    }

    /**
     * max 操作：根据给定的Comparator，返回流中最大的元素，用Optional描述，
     * 如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
     */
    @Test
    public void testStreamMax() {
        Stream<Person> stream = persons.stream();

        // 返回流中年龄最大的元素
        // Comparator.comparingInt(person -> person.getAge()) 等同于 (p1, p2) -> p1.getAge() - p2.getAge()
        // stream.max(Comparator.comparingInt(person -> person.getAge()));
        // person的age属性比较器
        Comparator<Person> ageComparator = (p1, p2) -> p1.getAge() - p2.getAge();
        Optional<Person> max = stream.max(ageComparator);
        System.out.println("返回流中最大的元素:" + max);

        Stream<Person> emptyStream = Stream.empty();
        // 如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
        Optional<Person> maxInEmpty = emptyStream.max(ageComparator);
        System.out.println("空流：取最大元素时返回空的Optional: " + maxInEmpty);
    }

    /**
     * min操作：根据给定的Comparator，返回流中最小的元素，用Optional描述，
     * 如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
     */
    @Test
    public void testStreamMin() {
        Stream<Person> stream = persons.stream();

        // 返回流中年龄最小的元素
        // Comparator.comparingInt(person -> person.getAge()) 等同于 (p1, p2) -> p1.getAge() - p2.getAge()
        // stream.min(Comparator.comparingInt(person -> person.getAge()));
        // person的age属性比较器
        Comparator<Person> ageComparator = (p1, p2) -> p1.getAge() - p2.getAge();
        Optional<Person> min = stream.min(ageComparator);
        System.out.println("返回流中最小的元素:" + min);

        Stream<Person> emptyStream = Stream.empty();
        // 如果是空流，返回空的Optional(Optional.empty)，Optional的value值为null
        Optional<Person> minInEmpty = emptyStream.min(ageComparator);
        System.out.println("空流：去最小元素时返回空的Optional: " + minInEmpty);
    }

    /**
     * Optional reduce(BinaryOperator accumulator)：
     * 第一次执行时，accumulator函数的第一个参数为流中的第一个元素，第二个参数为流中元素的第二个元素；
     * 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；
     * 依次类推。最终得到用Optional描述的T类型的结果。
     */
    @Test
    public void testStreamReduce1() {
        // 计算 nums 数组中所有元素的和

        int[] nums = new int[]{1, 5, 2, 4, 8, 9};
        // Arrays.stream(T[] array) 将指定的数组作为数据源，返回一个顺序流
        IntStream stream = Arrays.stream(nums);
        // (n1, n2) -> n1 + n2 二元运算 lambda 表达式，用于返回两个数值 n1 + n2 的结果
        OptionalInt reduce = stream.reduce((n1, n2) -> n1 + n2);
        System.out.println("计算 nums 数组中素有元素的和: " + reduce.getAsInt());
    }

    /**
     * T reduce(T identity, BinaryOperator accumulator)：
     * 第一次执行时，accumulator函数的第一个参数identity，第二个参数为流中元素的第一个元素；
     * 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第二个元素；
     * 依次类推。最终得到T类型的结果。
     */
    @Test
    public void testStreamReduce2() {
        // strs 数组中所有元素用空格连接，并加上 "黑桃" 字符串作为前缀

        String[] strs = new String[]{"hello", "Stream", "终止操作", "reduce"};
        // Arrays.stream(strs) 将指定的数组作为数据源，返回一个顺序流
        Stream<String> stream = Arrays.stream(strs);
        // (n1, n2) -> n1 + n2 二元运算 lambda 表达式，把 str1 和 str2 两个字符串用 " " 空格连接
        BinaryOperator<String> binaryOperator = (str1, str2) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(str1).append(" ").append(str2);
            return sb.toString();
        };
        String reduce = stream.reduce("黑桃", binaryOperator);
        System.out.println("strs 数组中所有元素用空格连接，并加上 \"黑桃\" 字符串作为前缀:\n " + reduce);
    }

    /**
     * Object[] toArray() 操作：返回包含此流元素的数组
     */
    @Test
    public void testToArray() {
        Stream<Person> stream = persons.stream();
        // 返回包含此流元素的数组
        Object[] objects = stream.toArray();
        System.out.println(Arrays.asList(objects));
    }

////////////////////////////////////////////   中间操作    /////////////////////////////////////////////////////////////////////

    /**
     * distinct 操作：返回由不同元素组成的流，根据元素的equals()方法去重
     */
    @Test
    public void testDistinct() {
        Person[] personArray = new Person[]{
                new Person("张三", 24),
                new Person("李四", 29),
                new Person("王五", 22),
                new Person("张三", 24),
                new Person("王五", 22),
                new Person("赵六", 15)
        };
        Stream<Person> stream = Arrays.stream(personArray);
        System.out.println("根据元素的equals()方法去重：");
        stream.distinct().forEach(item -> System.out.println(item));
    }

    /**
     * filter 操作：过滤，排除掉没有匹配 predicate 函数式接口的元素
     */
    @Test
    public void testFilter() {
        //过滤，返回年龄大于25岁的Person
        System.out.println("返回年龄大于25岁的Person：");
        // Predicate 函数式接口的 lambda表达式，判断传入的 person 的年龄是否大于25岁
        Predicate<Person> ageGt25Predicate = person -> person.getAge() > 25;
        persons.stream()
                .filter(ageGt25Predicate)
                .forEach(item -> System.out.println(item));
    }

    /**
     * limit 操作：限制流的元素数量，截断流，使其元素不超过给定数量
     */
    @Test
    public void testlimit() {
        System.out.println("persons 列表：\n" + persons);

        // 返回 persons 列表中前3个元素
        List<Person> collect = persons.stream()
                .limit(3)//限制流中的最大元素数量为3
                .collect(Collectors.toList());//将流中元素收集到list中
        System.out.println("\n返回 persons 列表中前3个元素：\n" + collect);
    }

    /**
     * skip 操作：跳过元素，返回一个扔掉了前 n 个元素的流。如果此流包含的元素少于n，则返回空流
     */
    @Test
    public void testSkip() {
        System.out.println("persons 列表：\n" + persons);

        // 跳过 persons 列表中前3个元素
        List<Person> collect = persons.stream()
                .skip(3)//跳过流中的前3个元素
                .collect(Collectors.toList());//将流中元素收集到list中
        System.out.println("\n跳过 persons 列表中前3个元素：\n" + collect);

        // 如果此流包含的元素少于n，则返回空流
        List<Person> empty = persons.stream()
                //跳过流中的前10个元素，流包含的元素少于10，返回空流
                .skip(10)
                .collect(Collectors.toList());//将流中元素收集到list中
        System.out.println("\n跳过 persons 列表中前10个元素：\n" + empty);
    }

    /**
     * sorted 操作：自然排序后返回一个新的流，如果元素不是Comparable类型，则抛出ClassCastException
     */
    @Test
    public void testSorted() {
        int[] nums = new int[]{1, 5, 2, 4, 8, 9};
        System.out.println("对数组进行自然排序：");
        Arrays.stream(nums).sorted().forEach(System.out::println);
    }

    /**
     * Stream sorted(Comparator<? super T> comparator) 操作：根据给定 Comparator 排序后，返回一个新流
     */
    @Test
    public void testSorted2() {
        System.out.println("persons 列表：\n" + persons);
        // person的age属性比较器
        Comparator<Person> ageComparator = (p1, p2) -> p1.getAge() - p2.getAge();

        System.out.println("按照 age 升序排序：");
        persons.stream()
                // 根据给定 Comparator 排序
                .sorted(ageComparator)
                .forEach(System.out::println);
    }

    /**
     * map 操作：通过入参Function函数式接口将流中的每个T类型元素映射成一个R类型的新元素
     */
    @Test
    public void testMap() {
        System.out.println("persons 列表：\n" + persons);

        //获取 persons 中每个元素的 name 属性存放到  List<String> 中
        List<String> list = persons.stream()
                //map 操作：通过入参Function函数式接口将流中的每个T类型元素映射成一个R类型的新元素
                // Function函数式接口，获取入参person中的 name 属性
                // 将 Person 类型元素映射成String类型的新元素
                .map(person -> person.getName())
                // 将流中元素收集成 List
                .collect(Collectors.toList());
        System.out.println("获取 persons 中每个元素的 name 属性存放到  List<String> 中：\n" + list);
    }

    /**
     * mapToInt 操作：通过入参ToIntFunction函数式接口将流中的每个T类型元素映射成一个Integer类型的新元素
     */
    @Test
    public void testMapToInt() {
        System.out.println("persons 列表：\n" + persons);

        //获取 persons 中每个元素的 age 属性存放到  List<Integer> 中
        List<Integer> list = new ArrayList<>();
        persons.stream()
                //map 操作：通过入参Function函数式接口将流中的每个T类型元素映射成一个R类型的新元素
                // Function函数式接口，获取入参person中的 age 属性
                // 将 Person 类型元素映射成 int 类型的新元素
                .mapToInt(person -> person.getAge())
                // 将流中元素收集成 List
                .forEach(list::add);
        System.out.println("获取 persons 中每个元素的 age 属性存放到  List<Integer> 中：\n" + list);
    }

    /**
     * peek 操作：通过入参Consumer函数式接口将流中的每个T类型元素进行操作，如给元素属性赋值，每个元素引用没变
     */
    @Test
    public void testPeek() {
        System.out.println("persons 列表：\n" + persons);

        System.out.println("\n给 persons 中每个元素的 name 属性添加\"_peek\"后缀：");
        //给 persons 中每个元素的 name 属性添加"_peek"后缀
        persons.stream()
                //peek 操作：通过入参Consumer函数式接口将流中的每个T类型元素进行操作
                // Consumer函数式接口，将入参person中的 name 属性添加"_peek"后缀
                // 将 Person 类型元素映射成String类型的新元素
                .peek(person -> {
                    String name = person.getName();
                    person.setName(name + "_peek");
                })
                .forEach(System.out::println);
    }

    /**
     * flatMap 操作：通过入参Function函数式接口将流中的每个元素都换成单独一个流，然后把转换后的所有流连接成一个流
     */
    @Test
    public void testFlatMap() {
        Person[] array1 = new Person[]{
                new Person("张三", 24),
                new Person("李四", 29),
                new Person("王五", 22),
        };
        Person[] array2 = new Person[]{
                new Person("刘一", 25),
                new Person("陈二", 12),
        };
        Person[] array3 = new Person[]{
                new Person("孙七", 16),
                new Person("周八", 18)
        };
        // 将 array1、array2、array3 3个person数组的所有Person元素收集存放到  List<Person> 中
        List<Person> list = Stream.of(array1, array2, array3)
                //flatMap 操作：通过入参Function函数式接口将流中的每个元素(Person[])都换成单独一个流，
                // 然后把转换后的所有流连接成一个流
                .flatMap(array -> Arrays.stream(array))
                // 将流中元素收集成 List
                .collect(Collectors.toList());
        System.out.println("将 array1、array2、array3 3个person数组的所有Person元素收集存放到  List<Person> 中：\n" + list);
    }

    /**
     * flatMapToInt 操作：通过入参Function函数式接口将流中的每个元素都换成单独一个IntStream(流中元素的类型是Integer)，
     * 然后把转换后的所有IntStream连接成一个IntStream
     */
    @Test
    public void testFlatMapToInt() {
        Person[] array1 = new Person[]{
                new Person("张三", 24),
                new Person("李四", 29),
                new Person("王五", 22),
        };
        Person[] array2 = new Person[]{
                new Person("刘一", 25),
                new Person("陈二", 12),
        };
        Person[] array3 = new Person[]{
                new Person("孙七", 16),
                new Person("周八", 18)
        };
        // 将 array1、array2、array3 3个person数组的所有Person的age收集存放到 List<Integer> 中：
        List<Integer> list = new ArrayList<>();
        Stream.of(array1, array2, array3)
                //flatMap 操作：通过入参Function函数式接口将流中的每个元素(Person[])都换成单独一个 IntStream 流，
                // 然后把转换后的所有 IntStream 流连接成一个 IntStream 流
                .flatMapToInt(array -> {
                    int[] ages = new int[array.length];
                    for (int i = 0; i < array.length; i++) {
                        ages[i] = array[i].getAge();
                    }
                    return IntStream.of(ages);
                })
                // 将流中元素收集成 List
                .forEach(item -> list.add(item));
        System.out.println("将 array1、array2、array3 3个person数组的所有Person的age收集存放到 List<Integer> 中：\n" + list);
    }


    /**
     * 测试多个中间操作：Stream操作中支持 0个或者多个 中间操作
     */
    @Test
    public void testMultiOpr() {
        System.out.println("persons 列表：\n" + persons);

        //将 persons 中 age 大于18 的前两个person的name 收集到  List<String> 中
        List<String> collect = persons.stream()
                //过滤出年龄大于18的元素
                .filter(person -> person.getAge() > 18)
                //限制返回前两个元素
                .limit(2)
                // 映射：返回person的name属性
                .map(person -> person.getName())
                // 将流中的元素收集到 list 中
                .collect(Collectors.toList());
        System.out.println("\n将 persons 中 age 大于18 的前两个person的name 收集到  List<String> 中:");
        System.out.println(collect);
    }
}
