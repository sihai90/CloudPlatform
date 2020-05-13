package club.byteyun.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @version 1.0
 * @ClassName ByteYunMyBatisPlusConfigure
 * @Description //TODO MyBatisPlus全局配置
 * @Date 11:06 2020/5/13
 * @Author lql
 **/
@Configuration
public class ByteYunMyBatisPlusConfigure
{
    @Bean
    public PaginationInterceptor paginationInterceptor()
    {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        ArrayList<ISqlParser> iSqlParsers = new ArrayList<>();
        iSqlParsers.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(iSqlParsers);
        return paginationInterceptor;
    }
}
