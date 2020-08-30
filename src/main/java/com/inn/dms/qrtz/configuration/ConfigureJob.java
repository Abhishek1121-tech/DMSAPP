package com.inn.dms.qrtz.configuration;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inn.dms.report.job.ReportJob;

@Configuration
public class ConfigureJob {

    @Bean
    public JobDetail jobADetails() {
        return JobBuilder.newJob(ReportJob.class).withIdentity("ReportCreation")
                .storeDurably().build();
    }

    @Bean
    public Trigger jobATrigger(JobDetail jobADetails) {

        return TriggerBuilder.newTrigger().forJob(jobADetails)

                .withIdentity("ReportCreationTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * ? * *"))
                .build();
    }
}