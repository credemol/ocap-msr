package ocap.msr.util;

public enum TeamNames {
	PUBLIC("Public"),
	NOT_CATEGORIZED("Not Categorized"),
	IAAS("IaaS & Security"),
	INTEGRATION("Integration"),
	BI_BIGDATA("BI & Big Data");
	
	private String teamName;
	
	TeamNames(String teamName) {
		this.teamName = teamName;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
}
