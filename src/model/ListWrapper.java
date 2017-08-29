package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "representative")
public class ListWrapper {

private List<Representative> representative;
	
	@XmlElement(name = "representatives")
	public List<Representative> getRepresentatives() {
		return representatives;
	}
	
	public void setRepresentatives(List<Representative> representativess) {
		this.representatives = representatives;
	}
	
}
