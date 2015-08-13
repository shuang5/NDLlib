/**
 * 
 */
package orca.ndllib.resources.request;

/**
 * @author geni-orca
 *
 */
public class InterfaceNode2Net extends Interface{
	//private OrcaNode a;  -- from OrcaStitch
	//private OrcaLink b;  -- from OrcaStitch

	private String ipAddress; 
	private String netmask;
	private String macAddress;
	
	public InterfaceNode2Net(Node n, Network l){
		super(n,l);
	}
	
	public Node getNode() {
		return (Node)a;
	}
	public void setNode(Node node) {
		this.a = node;
	}
	public Network getLink() {
		return (Network)b;
	}
	public void setLink(Network link) {
		this.b = link;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getNetmask() {
		return netmask;
	}
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	
	public String toString(){
		String rtnStr = "";
		
		rtnStr += "Stitch "; 
		if(a != null)
			rtnStr += a.getName() + " to ";
		else
			rtnStr += "null to ";
		
		if(b != null)
			rtnStr += b.getName();
		else
			rtnStr += "null to ";	
	
		rtnStr += ", ipAddress: " + this.ipAddress;
		rtnStr += ", netmask: " + this.netmask;
		rtnStr += ", mac: " + this.macAddress;
		
		return rtnStr;
	}

}
