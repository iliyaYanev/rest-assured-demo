package dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    private MetaInformation meta;
    private User result;

    public BaseResponse() {
    }

    @JsonProperty("_meta")
    public MetaInformation getMeta() {
        return meta;
    }

    @JsonProperty("_meta")
    public void setMeta(MetaInformation meta) {
        this.meta = meta;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
