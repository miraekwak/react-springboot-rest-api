package com.example.backend;

import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.ProductRepository;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_10;

@SpringBootTest
class BackendApplicationTests {


    @Test
    void contextLoads() {
    }

}
