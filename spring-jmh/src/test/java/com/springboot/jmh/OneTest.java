package com.springboot.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/14
 */

/**
 *
 * @ BenchmarkMode(Mode.AverageTime) // 测试方法平均执行时间
 * @ OutputTimeUnit(TimeUnit.MICROSECONDS) // 输出结果的时间粒度为微秒
 * @ State(Scope.Thread) // 每个测试线程一个实例
 *
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread) // 每个测试线程一个实例
//@OutputTimeUnit(TimeUnit.MICROSECONDS) // 输出结果的时间粒度为微秒
public class OneTest {

    private static List<Integer> INTEGER_LIST = new ArrayList<>(100000);

    @Setup
    public void init() {
        for(int i = 0; i < 100000; i++) {
            INTEGER_LIST.add(i);
        }
    }

    @Benchmark
//    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    public void testFor() {
        for(int i = 0; i < INTEGER_LIST.size(); i++) {
            INTEGER_LIST.get(i);
        }
    }

    @Benchmark
//    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    public void testReFor() {
        for(int i = 0, j = INTEGER_LIST.size(); i < j; i++) {
            INTEGER_LIST.get(i);
        }
    }

}
