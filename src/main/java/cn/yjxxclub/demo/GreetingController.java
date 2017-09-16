package cn.yjxxclub.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-12
 * Time: 下午12:57
 * Describe: GreetingController
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/message")
    //@SendTo("/topic/greetings")
    //接收/app/message发来的value，然后将value转发到/topic/greetings客户端
    public Greeting message(String message) throws Exception{
        //通过convertAndSendToUser 向用户发送信息,
        // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身
        //messagingTemplate.convertAndSendToUser();
        messagingTemplate.convertAndSend("/topic/greetings",new Greeting(message));
        return null;
    }


    /*
    * cron：指定cron表达式

    *zone:官方文档解释：A time zone for which the cron expression will be resolved。指定cron表达式运行的时区

    *fixedDelay：官方文档解释：An interval-based trigger where the interval is measured from the completion time of the previous task. The time unit value is measured in milliseconds.即表示从上一个任务完成开始到下一个任务开始的间隔，单位是毫秒。

    *fixedRate：官方文档解释：An interval-based trigger where the interval is measured from the start time of the previous task. The time unit value is measured in milliseconds.即从上一个任务开始到下一个任务开始的间隔，单位是毫秒。

    *initialDelay:官方文档解释:Number of milliseconds to delay before the first execution of a fixedRate() or fixedDelay() task.任务第一次被调用前的延时，单位毫秒
    * */

    /*
                CRON表达式    含义
    "0 0 12 * * ?"    每天中午十二点触发
    "0 15 10 ? * *"    每天早上10：15触发
    "0 15 10 * * ?"    每天早上10：15触发
    "0 15 10 * * ? *"    每天早上10：15触发
    "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
    "0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
    "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
    "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
    "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
    "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
    "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
    http://cron.qqe2.com/
    */
    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void time(){
        System.out.println("dd");
        messagingTemplate.convertAndSend("/topic/greetings",new Greeting(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    }
}