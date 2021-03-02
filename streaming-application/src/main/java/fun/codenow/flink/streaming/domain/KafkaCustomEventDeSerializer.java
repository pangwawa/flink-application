package fun.codenow.flink.streaming.domain;

import com.google.gson.Gson;
import fun.codenow.flink.streaming.domain.event.ProductConsumerEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author Jack Wu
 * @Description
 * @Version V1.0
 * @Date2021/3/2 15:44
 **/
public class KafkaCustomEventDeSerializer implements DeserializationSchema<ProductConsumerEvent>, SerializationSchema<ProductConsumerEvent> {
    private static final Gson gson = new Gson();

    @Override
    public ProductConsumerEvent deserialize(byte[] bytes) throws IOException {
        return gson.fromJson(new String(bytes), ProductConsumerEvent.class);
    }

    @Override
    public boolean isEndOfStream(ProductConsumerEvent productConsumerEvent) {
        return false;
    }

    @Override
    public byte[] serialize(ProductConsumerEvent productConsumerEvent) {
        return gson.toJson(productConsumerEvent).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public TypeInformation<ProductConsumerEvent> getProducedType() {
        return TypeInformation.of(ProductConsumerEvent.class);
    }
}
