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
package orca.ndllib;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import orca.ndllib.ndl.*;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 * holds common state for  panes
 * @author ibaldin
 *
 */
public abstract class NDLLIBCommon {
	

	
	
	protected static Logger logger;
	protected Slice slice;
	
	public Slice getSlice() {
		return slice;
	}

	protected NDLLIBCommon(Slice slice){
		logger = Logger.getLogger(NDLLIBCommon.class.getCanonicalName());
		logger.setLevel(Level.INFO);
		this.slice = slice;
	}
	
	// where are we saving
	String saveDirectory = null;
	
	public Logger getLogger() {
		return logger;
	}

	public static Logger logger() {
		return logger;
	}
	
	

	public void setSaveDir(String s) {
		saveDirectory = s;
	}
	
	public String getSaveDir() {
		return saveDirectory;
	}
	

	
	


}
	
