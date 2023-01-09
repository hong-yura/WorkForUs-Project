package site.workforus.forus.employee.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("authDto")
public class AuthDTO {
    private String empId;
    private String authority;

    public AuthDTO() {
    }

    public AuthDTO(String empId, String authority) {
        this.empId = empId;
        this.authority = authority;
    }
}
