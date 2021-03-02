package fun.codenow.flink.streaming.domain.event;

import java.io.Serializable;

/**
 * @Author Jack Wu
 * @Description
 * @Version V1.0
 * @Date2021/3/2 16:18
 **/
public class ProductConsumerEvent implements Serializable {
    private static final long serialVersionUID=1L;

    private Long orderId;
    private Long productId;
    private Long timestamp;
    private Long count;
    private Long price;
    private Long sum;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
