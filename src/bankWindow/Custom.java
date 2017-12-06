package bankWindow;

import java.util.Date;

/**
 * Created by qtfs on 2017/12/6.
 */
public class Custom implements Comparable<Custom>{
    private Long order;             // 客户次序
    private CustomType customType;  // 客户类型
    private Date arrivalDate;       // 到达时间
    private int serviceTime;        // 服务时长

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public CustomType getCustomType() {
        return customType;
    }

    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Custom o) {
        return this.order.compareTo(o.order);
    }
}

/**
 * 客户类型枚举类
 * @author zyh
 */
enum CustomType {
    VIP("the VIP custom."),
    COMMON("the COMMON custom.");

    private String description;

    private CustomType(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}