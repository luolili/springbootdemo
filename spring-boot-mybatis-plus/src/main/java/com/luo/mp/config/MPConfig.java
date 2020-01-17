package com.luo.mp.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MPConfig {

    public static ThreadLocal<String> tableNameThreadLocal = new ThreadLocal<>();

    /**
     * 多租户
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor pi = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        /*
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
tenantSqlParser.setTenantHandler(new TenantHandler() {
    @Override
    public Expression getTenantId() {
        //从session or 配置文件来,在sql里面加上where manager_id=1
        return new LongValue(1L);
    }

    @Override
    public String getTenantIdColumn() {
        return "manager_id";
    }

    @Override
    public boolean doTableFilter(String tableName) {
        //角色表不加租户id
        if ("role".equalsIgnoreCase(tableName)) {
            return true;
        }
        return false;
    }
});

        sqlParserList.add(tenantSqlParser);
        pi.setSqlParserList(sqlParserList);
        //方法级别的过滤（sql过滤）
        pi.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                //该方法不增加租户信息
                if ("com.luo.mp.mapper.selectById".equalsIgnoreCase(ms.getId())) {
return true;
                }
                return false;
            }
        });*/

        //动态表名解析:注意：3.1.0没有这个类
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        tableNameHandlerMap.put("user", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {

                return tableNameThreadLocal.get();
            }
        });
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);

        return pi;
    }

    @Bean
    public ISqlInjector sqlInjector() {

        return new LogicSqlInjector();
    }

    /**
     * 性能分析:-Dspring.profiles.active=dev
     *
     * @return
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {

        PerformanceInterceptor pi = new PerformanceInterceptor();
        pi.setFormat(true);
        //pi.setMaxTime(10);
        return pi;
    }
}
