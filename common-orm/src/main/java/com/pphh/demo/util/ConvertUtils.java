package com.pphh.demo.util;


import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * ConvertUtils
 *
 * @author yinzuolong
 * @date 2017/7/17
 */
public class ConvertUtils {
    static Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    public static <S, T> T convert(S s, Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
            BeanCopier beanCopier = BeanCopier.create(s.getClass(), tClass, false);
            beanCopier.copy(s, t, null);
        } catch (Exception e) {
            logger.error("a exception happened when trying to covert object.");
        }
        return t;
    }

    public static <S, T> T convert(S s, T t) {
        try {
            BeanCopier beanCopier = BeanCopier.create(s.getClass(), t.getClass(), false);
            beanCopier.copy(s, t, null);
        } catch (Exception e) {
            logger.error("a exception happened when trying to covert object.");
        }
        return t;
    }

    public static <S, T> List<T> convert(Iterable<S> iterable, Class<T> tClass) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(s -> ConvertUtils.convert(s, tClass)).collect(Collectors.toList());
    }

    public static <S, T> List<T> convert(Iterable<S> iterable, Function<? super S, ? extends T> mapper) {
        return StreamSupport.stream(iterable.spliterator(), false).map(mapper).collect(Collectors.toList());
    }

}
