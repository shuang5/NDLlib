/*
* Copyright (c) 2011 RENCI/UNC Chapel Hill 
*
* @author Ilia Baldine
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
* and/or hardware specification (the "Work") to deal in the Work without restriction, including 
* without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or 
* sell copies of the Work, and to permit persons to whom the Work is furnished to do so, subject to 
* the following conditions:  
* The above copyright notice and this permission notice shall be included in all copies or 
* substantial portions of the Work.  
*
* THE WORK IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS 
* OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
* OUT OF OR IN CONNECTION WITH THE WORK OR THE USE OR OTHER DEALINGS 
* IN THE WORK.
*/
package orca.ndllib.resources.request;

import orca.ndllib.Request;
import orca.ndllib.Slice;
import orca.ndllib.util.IP4Subnet;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.util.Pair;

public abstract class Network extends RequestResource {
	//default size for auto ip
	private static int DEFAULT_SIZE = 256;
	
    protected long bandwidth;
    protected long latency;
    protected String label = null;
    protected String realName = null;
    
    //Subnet for autoIP
    protected IP4Subnet ipSubnet;
	
    public Network(Slice slice, Request request, String name) {
    	super(slice, request);
        this.name = name;
        this.ipSubnet = null;
    }

    public void setBandwidth(long bw) {
    	bandwidth = bw;
    }

    public void setLatency(long l) {
    	latency = l;
    }

    public void setLabel(String l) {
    	if ((l != null) && l.length() > 0)
    		label = l;
    	else
    		label = null;
    }

    public String getLabel() {
    	return label;
    }
    
    public long getBandwidth() {
    	return bandwidth;
    }
    
    public long getLatency() {
    	return latency;
    }
    
    
    public void setRealName(String n) {
    	this.realName = n;
    }
	
    //set IP subnet for autoIP
    public void setIPSubnet(String ip, int mask){
    	ipSubnet = request.setSubnet(ip,mask);
    }
    
    //allocate new subnet for autoIP
    public void allocateIPSubnet(int count){
    	ipSubnet = request.allocateSubnet(count);
    }  

    public void clearAvailableIPs(){
    	if(ipSubnet != null) {
    		ipSubnet.markAllIPsUsed();
    	} 
    }
    
    public void addAvailableIP(String ip){
    	addAvailableIPs(ip, 32);
    }
    
    public void addAvailableIPs(String ip, int maskLength){
    	int count = 1<<(32-maskLength);
    	
    	ipSubnet.markIPsFree(ip, count);
    }
    
    //automatically set IPs on interfaces
    public void autoIP(){
    	request.logger().debug("AutoIP for network ");
    	//Do not set IPs for storage networks
    	for (Interface i : this.getInterfaces()){
    		if (i instanceof InterfaceNode2Net){
    			Node n = ((InterfaceNode2Net)i).getNode();
    			if(n instanceof StorageNode){
    				request.logger().info("Skipping autoip for storage network: " + this.getName());
    				return;
    			}
    		} else {
    			//unknown interface type
    			request.logger().warn("Unkown interface type. Can not autoIP for interface: " + i.toString());
    		}
    	}
    	
    	
    	if (ipSubnet == null){
    		ipSubnet = request.allocateSubnet(Network.DEFAULT_SIZE);
    	}
    	
    	for (Interface i : this.getInterfaces()){
    		request.logger().debug("AutoIP for interface: " + i);
    		if (i instanceof InterfaceNode2Net){
    			Node n = ((InterfaceNode2Net)i).getNode();
    			if(n instanceof ComputeNode){
    				int count = ((ComputeNode)n).getMaxNodeCount();
    				
    				int maskLength = ipSubnet.getMaskLength();
    				String ip = ipSubnet.getFreeIPs(count).getHostAddress();
    				String mask = IP4Subnet.netmaskIntToString(maskLength);
    				request.logger().debug("AutoIP for interface: count: " + count + ", maskLength: " + maskLength + ", mask: " + mask + ", ip: " + ip);
    				((InterfaceNode2Net)i).setNetmask(mask);
    				((InterfaceNode2Net)i).setIpAddress(ip);
    			}
    		} else {
    			//unknown interface type
    			request.logger().warn("Unkown interface type. Can not autoIP for interface: " + i.toString());
    		}
    	}
    }
    
    @Override
    public String toString() {
        return name;
    }

}
