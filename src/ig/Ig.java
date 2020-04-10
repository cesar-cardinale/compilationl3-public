package ig;

import fg.*;
import nasm.*;
import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class Ig {
    public Graph graph;
    public FgSolution fgs;
    public int regNb;
    public Nasm nasm;
    public Node int2Node[];

    
    public Ig(FgSolution fgs){
		this.fgs = fgs;
		this.graph = new Graph();
		this.nasm = fgs.nasm;
		this.regNb = this.nasm.getTempCounter();
		this.int2Node = new Node[regNb];
		this.construction();
    }

    public void construction(){
    	for (int i=0; i<regNb;i++){
			Node node = graph.newNode();
			this.int2Node[i] = node;
		}

    	for (NasmInst nasmInst : nasm.listeInst) {
			IntSet inIntSet = fgs.in.get(nasmInst);
			for (int i = 0; i<inIntSet.getSize();i++) {
				if (inIntSet.isMember(i)) {
					for (int j = i+1; j<inIntSet.getSize();j++) {
						if (inIntSet.isMember(j)) {
							graph.addEdge(int2Node[i], int2Node[j]);
						}
					}
				}

			}
			IntSet outIntSet = fgs.out.get(nasmInst);
			for (int i = 0; i<outIntSet.getSize();i++) {
				if (outIntSet.isMember(i)) {
					for (int j = i+1; j<outIntSet.getSize();j++) {
						if (outIntSet.isMember(j)) {
							graph.addEdge(int2Node[i], int2Node[j]);
						}
					}
				}

			}
		}
    }

    public int[] getPrecoloredTemporaries()
    {
    	return null;
    }


    public void allocateRegisters(){
    }


    public void affiche(String baseFileName){
		String fileName;
		PrintStream out = System.out;

		if (baseFileName != null){
			try {
			baseFileName = baseFileName;
			fileName = baseFileName + ".ig";
			out = new PrintStream(fileName);
			}

			catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			}
		}

		for(int i = 0; i < regNb; i++){
			Node n = this.int2Node[i];
			out.print(n + " : ( ");
			for(NodeList q=n.succ(); q!=null; q=q.tail) {
				out.print(q.head.toString());
				out.print(" ");
			}
			out.println(")");
		}
    }
}
	    
    

    
    
