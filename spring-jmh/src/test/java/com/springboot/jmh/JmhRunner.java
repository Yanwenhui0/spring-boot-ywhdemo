package com.springboot.jmh;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author : yanwenhui
 * @description :
 * @@date : 2020/8/14
 */
public class JmhRunner {

    @Test
    public void runJmhTest() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include("OneTest")
                .warmupIterations(3)
                .measurementIterations(5)
                .forks(3)
                .build();

        new Runner(opt).run();
    }

}
