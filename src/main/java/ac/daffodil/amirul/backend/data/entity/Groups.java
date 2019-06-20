package ac.daffodil.amirul.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="GroupsInfo")
public class Groups extends AbstractEntity{
	
	@NotNull
	@Size(min = 1, max = 255)
	private String groupName;

	@NotNull
	@Size(min = 1, max = 255)
	private String groupCode;

	@NotNull
	@ManyToOne
	private AccountType2 groupAccount;

	public Groups() {
		
	}

	public Groups(String groupName, String groupCode, AccountType2 groupAccount) {
		this.groupName = groupName;
		this.groupCode = groupCode;
		this.groupAccount = groupAccount;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public AccountType2 getGroupAccount() {
		return groupAccount;
	}

	public void setGroupAccount(AccountType2 groupAccount) {
		this.groupAccount = groupAccount;
	}

	@Override
	public String toString() {
		return groupName;/*"Groups{" +
				"groupName='" + groupName + '\'' +
				'}';*/
	}
}
