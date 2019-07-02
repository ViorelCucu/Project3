package IT;
import java.util.List;
public class ProjectTeam {

	
	
	String teamName;
	String teamProject;
	boolean fullTime;
	List<ActiveProgrammers> team;

	public ProjectTeam(String teamName, String teamProject, boolean fullTime, List<ActiveProgrammers> team) {
		this.teamName = teamName;
		this.teamProject = teamProject;
		this.fullTime = fullTime;
		this.team = team;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamProject() {
		return teamProject;
	}

	public void setTeamProject(String teamProject) {
		this.teamProject = teamProject;
	}

	public boolean isFullTime() {
		return fullTime;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	public List<ActiveProgrammers> getTeam() {
		return team;
	}

	public void setTeam(List<ActiveProgrammers> team) {
		this.team = team;
	}
	
	
}