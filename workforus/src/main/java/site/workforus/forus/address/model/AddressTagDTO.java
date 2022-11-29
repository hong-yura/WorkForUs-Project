package site.workforus.forus.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("addressTagDto")
public class AddressTagDTO {
    private int addrTagId;  // 태그 ID
    private String addrTagName; // 태그명
    private String addrTagDate; // 태그 등록일
    private String addrTagDelFl;    // 태그 삭제 여부

    public AddressTagDTO() {}

    public AddressTagDTO(int addrTagId, String addrTagName, String addrTagDate, String addrTagDelFl) {
        super();
        this.addrTagId = addrTagId;
        this.addrTagName = addrTagName;
        this.addrTagDate = addrTagDate;
        this.addrTagDelFl = addrTagDelFl;
    }
}
