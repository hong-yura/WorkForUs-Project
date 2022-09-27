package site.workforus.forus.employee.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// sercurity 로그인을 위한 UserDetails vo 객체
public class LoginVO implements Principal, UserDetails{
	
	private String empId;
	private String empPw;
	private List<GrantedAuthority> authorities;
	
	public LoginVO() {}
	
	public LoginVO(EmpDTO empDto) {
		this.empId = empDto.getEmpId();
		this.empPw = empDto.getEmpPw();
	}

	public void setUsername(String empId) {
		this.empId = empId;
	}
	
	public void setPassword(String empPw) {
		this.empPw = empPw;
	}
	
	public void setAuthorities(List<String> authList) {
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		
		for(int i = 0; i < authList.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(authList.get(i)));
		}
		
		this.authorities = authorities;
	}

	@Override
	// 권힌
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	// PW
	public String getPassword() {
		return empPw;
	}

	@Override
	// ID
	public String getUsername() {
		return empId;
	}

	@Override
	// 계정이 만료되지 않았는가?
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	// 계정이 잠기지 않았았는가?
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	// 패스워드가 만료되지 않았는가?
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	// 계정이 활성화 되었는가?
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getName() {
		return empId;
	}

}
