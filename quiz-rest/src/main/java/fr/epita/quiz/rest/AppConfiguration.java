package fr.epita.quiz.rest;

import fr.epita.services.data.ChoiceJPADAO;
import fr.epita.services.data.QuestionJPADAO;
import fr.epita.services.data.QuizDataService;
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
public class AppConfiguration {


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
    public EntityManager entityManager(LocalContainerEntityManagerFactoryBean factoryBean) {
        return factoryBean.getObject().createEntityManager();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();

        //TODO this should be handled by configuration, eg not hardcoded
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

    @Bean
    public QuestionJPADAO getQuestionJPADAO(@Autowired EntityManager em){
        return new QuestionJPADAO(em);
    }

    @Bean
    public ChoiceJPADAO getChoiceJPADAO(@Autowired EntityManager em){
        return new ChoiceJPADAO(em);
    }
    @Bean
    public QuizDataService getQuizDataService(QuestionJPADAO questionJPADAO, ChoiceJPADAO choiceJPADAO){
        return new QuizDataService(questionJPADAO, choiceJPADAO);
    }


}
