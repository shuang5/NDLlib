package orca.ndllib.resources.manifest;

import orca.ndllib.Manifest;
import orca.ndllib.Request;
import orca.ndllib.Slice;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.util.Pair;


public class CrossConnect extends ManifestResource{
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
	
	
	    protected long bandwidth;
	    protected long latency;
	    protected String label = null;
	    protected String realName = null;
	    
		
	    public CrossConnect(Slice slice, Manifest manifest, String name) {
	    	super(slice, manifest);
	        this.name = name;
	    }

	    interface ILinkCreator {
	    	public LinkConnection create(String prefix);
	    	public LinkConnection create(String nm, long bw);
	    	public void reset();
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
		
	    @Override
	    public String toString() {
	        return name;
	    }
	    
	  
	    
	    public static class OrcaLinkFactory implements Factory<LinkConnection> {
	       private ILinkCreator inc = null;
	        
	        public OrcaLinkFactory(ILinkCreator i) {
	        	inc = i;
	        }
	        
	        public LinkConnection create() {
	        	if (inc == null)
	        		return null;
	        	synchronized(inc) {
	        		return inc.create(null);
	        	}
	        }    
	    }
	    
	    // link to broadcast?
	    public boolean linkToBroadcast() {
//	    	// if it is a link to broadcastlink, no editable properties
//	    	Pair<OrcaNode> pn = Request.getInstance().getGraph().getEndpoints(this);
//	    	
//	    	if (pn == null)
//	    		return false;
//	    	
//	    	//if ((pn.getFirst() instanceof OrcaVLAN) || 
//	    	//		(pn.getSecond() instanceof OrcaVLAN))
//	    	//	return true;
	    	return false;
	    }
	    
	    // link to shared storage?
	    public boolean linkToSharedStorage() {
	    	// if it is a link to broadcastlink, no editable properties
//	    	Pair<OrcaNode> pn = Request.getInstance().getGraph().getEndpoints(this);
//	    	
//	    	if (pn == null)
//	    		return false;
//	    	
//	    	if (pn.getFirst() instanceof OrcaStorageNode) {
//	    		OrcaStorageNode snode = (OrcaStorageNode)pn.getFirst();
//	    		if (snode.getSharedNetwork())
//	    			return true;
//	    	}
//	    	
//	    	if (pn.getSecond() instanceof OrcaStorageNode) {
//	    		OrcaStorageNode snode = (OrcaStorageNode)pn.getSecond();
//	    		if (snode.getSharedNetwork())
//	    			return true;
//	    	}
	    	return false;
	    }

		@Override
		public String getPrintText() {
			// TODO Auto-generated method stub
			return null;
		}
	    


}
