package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/4
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

