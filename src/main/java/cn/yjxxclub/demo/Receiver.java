package cn.yjxxclub.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-13
 * Time: 下午2:02
 * Describe: Recceiver
 */
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private CountDownLatch latch;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        messagingTemplate.convertAndSend("/topic/greetings",new Greeting(message));
        latch.countDown();
    }
}
