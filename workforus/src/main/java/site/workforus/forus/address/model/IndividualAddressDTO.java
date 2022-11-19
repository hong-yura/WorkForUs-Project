package site.workforus.forus.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("individualAddressDto")
public class IndividualAddressDTO {
    private int addrIndvId; // 개인주소록 ID
    private int addrId; // 주소록 ID
    private String addrName;    // 이름(표시명)
    private String addrPhone;   // 전화번호
    private String addrDept;    // 부서
    private String addrEmail;   // 이메일
    private String addrDetail;  // 주소(회사명, 거래처 등)
    private String addrTagId;   // 태그 ID
    private AddressTagDTO addrTagObj;
    
    public IndividualAddressDTO() {}

    public IndividualAddressDTO(int addrIndvId, int addrId, String addrName, String addrPhone
            , String addrDept, String addrEmail, String addrDetail, String addrTagId, AddressTagDTO addrTagObj) {
        super();
        this.addrIndvId = addrIndvId;
        this.addrId = addrId;
        this.addrName = addrName;
        this.addrPhone = addrPhone;
        this.addrDept = addrDept;
        this.addrEmail = addrEmail;
        this.addrDetail = addrDetail;
        this.addrTagId = addrTagId;
        this.addrTagObj = addrTagObj;
    }

}
