package fr.epita;

import fr.epita.services.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class TestConfiguration {

    @Bean
    public DataSource getDataSource(){
        fr.epita.services.Configuration conf = fr.epita.services.Configuration.getInstance();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        String dbUrl = conf.getValue("db.url");
        String dbUser = conf.getValue("db.user");
        String dbPassword = conf.getValue("db.pwd");
        ds.setUrl(dbUrl);
        ds.setPassword(dbPassword);
        ds.setUsername(dbUser);
        return ds;
    }

    @Bean("test")
    public QuestionDAO getQuestionDAO(@Autowired DataSource ds){
        return new QuestionDAO(ds);
    }


}
