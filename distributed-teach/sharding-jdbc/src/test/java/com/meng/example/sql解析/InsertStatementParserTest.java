package com.meng.example.sql解析;

import io.shardingjdbc.core.constant.DatabaseType;
import io.shardingjdbc.core.parsing.SQLParsingEngine;
import io.shardingjdbc.core.parsing.lexer.LexerEngine;
import io.shardingjdbc.core.parsing.lexer.LexerEngineFactory;
import io.shardingjdbc.core.parsing.parser.sql.dml.insert.InsertStatement;
import io.shardingjdbc.core.parsing.parser.sql.dql.DQLStatement;
import io.shardingjdbc.core.parsing.parser.token.SQLToken;
import io.shardingjdbc.core.rule.ShardingRule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class InsertStatementParserTest extends AbstractStatementParserTest{

    @Test
    public void assertParseWithoutParameter() throws SQLException {
        ShardingRule shardingRule = createShardingRule();//创建分片规则
        SQLParsingEngine statementParser = new SQLParsingEngine(DatabaseType.MySQL, "INSERT INTO `TABLE_XXX` (`field1`, `field2`) VALUES (10, 1)", shardingRule);//解析sql引擎
        InsertStatement insertStatement = (InsertStatement) statementParser.parse();//解析sql开始

        System.out.println("sql解析tables："+insertStatement.getTables());
        List<SQLToken> list=insertStatement.getSqlTokens();
        for(SQLToken sqlToken:list){
            System.out.println(sqlToken);
        }
        System.out.println("toString:"+insertStatement.toString());
    }


    @Test
    public void assertParseWithoutParameter2() throws SQLException {
        ShardingRule shardingRule = createShardingRule();
        SQLParsingEngine statementParser = new SQLParsingEngine(DatabaseType.MySQL, "SELECT * FROM  TTT WHERE ID=1", shardingRule);
        DQLStatement insertStatement = (DQLStatement) statementParser.parse();
        System.out.println("sql解析tables："+insertStatement.getTables());
        List<SQLToken> list=insertStatement.getSqlTokens();
        for(SQLToken sqlToken:list){
            System.out.println(sqlToken);
        }
        System.out.println("toString:"+insertStatement.toString());
    }

    public static void main(String[] args) {

        LexerEngine lexerEngine = LexerEngineFactory.newInstance(DatabaseType.MySQL,  "INSERT INTO `TABLE_XXX` (`field1`, `field2`) VALUES (10, 1)");
        lexerEngine.nextToken();
        System.out.println(lexerEngine.getCurrentToken().getType()+","+lexerEngine.getCurrentToken().getEndPosition());

    }


}
