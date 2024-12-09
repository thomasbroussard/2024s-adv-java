package fr.epita;

import fr.epita.services.data.ChoiceDAO;
import fr.epita.services.data.ChoiceJPADAO;
import fr.epita.services.data.QuestionDAO;
import fr.epita.services.data.QuestionJPADAO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

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

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

   // @Bean("test")
    public QuestionDAO getQuestionDAO(@Autowired DataSource ds){
        return new QuestionDAO(ds);
    }

    @Bean
    public QuestionJPADAO getQuestionJPADAO(@Autowired EntityManager em){
        return new QuestionJPADAO(em);
    }

    @Bean
    public ChoiceJPADAO getChoiceJPADAO(@Autowired EntityManager em){
        return new ChoiceJPADAO(em);
    }


   // @Bean("test")
    public ChoiceDAO getChoiceDAO(@Autowired DataSource ds){
        return new ChoiceDAO(ds);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

}
