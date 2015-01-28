
package pl.edu.amu.lasuch.solr;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ResponseHeader {

    @Expose
    private Integer status;
    @SerializedName("QTime")
    @Expose
    private Integer qTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQTime() {
        return qTime;
    }

    public void setQTime(Integer qTime) {
        this.qTime = qTime;
    }

}
