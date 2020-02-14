package springpayroll.service;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springpayroll.model.CtcData;
@Component
public class RabbitMQSender
{
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;
    String kafkaTopic = "java_in_use_topic";

    public void send(CtcData company) {
        amqpTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }

}
