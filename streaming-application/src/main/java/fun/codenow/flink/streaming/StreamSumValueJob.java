package fun.codenow.flink.streaming;

import fun.codenow.flink.streaming.domain.KafkaCustomEventDeSerializer;
import fun.codenow.flink.streaming.domain.event.ProductConsumerEvent;
import fun.codenow.flink.streaming.utils.WordCountData;
import org.apache.flink.api.common.functions.Partitioner;
import org.apache.flink.api.common.operators.Keys;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.jackson2.org.yaml.snakeyaml.events.Event;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import javax.xml.crypto.KeySelector;
import java.util.Properties;

/**
 * @Author Jack Wu
 * @Description 接收kafka数据， 有状态流计算， 所有商户总额，各个商户的总额， sink ElasticSearch
 * @Version V1.0
 * @Date2021/3/2 10:36
 **/
public class StreamSumValueJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment evn=StreamExecutionEnvironment.getExecutionEnvironment();
        //source
        final String kafkaTopic="";
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("bootstrap.servers", "192.168.5.98");
        FlinkKafkaConsumer<ProductConsumerEvent> kafkaConsumer = new FlinkKafkaConsumer<>(kafkaTopic,new KafkaCustomEventDeSerializer(), kafkaProps);

        DataStreamSource<ProductConsumerEvent> dataStreamSource=evn.addSource(kafkaConsumer);
        //transformation
        SingleOutputStreamOperator<ProductConsumerEvent> singleOutputStreamOperator=dataStreamSource.keyBy("productId").sum("sum");

        System.out.print("累加sumn：");
        singleOutputStreamOperator.print();
        //sink

        evn.execute("StreamSumValue Job Execute");

    }
}
