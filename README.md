# SpringTest
SpringTest is Spring based servlet sample following is also integrated
1. Log4j 2 
Log4j 2 has asynchrnous mode to improve app's performance.
Refer to log4j2.component.properties to find out how to enable asynchrnous logger

2. Redis
Refer to RedisController.java to find out how to integrate Jedis and Lettuce as sync and async access to RedisCache server respectively.

3. Spring Scheduler
Refer to ScheduledBean.java and AppConfiguration.java

4. Akka integration
Refer to CountingActor.java, AppConfiguration.java, SpringExtension.java, SpringActorProducer.java and CountingService.java as well as akka_remote.conf to understand how to inject Bean and Akka to start Actor remote server.
Under test directory there is Akka test codes also presented.
