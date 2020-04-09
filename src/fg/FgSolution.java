package fg;
import util.graph.*;
import nasm.*;
import util.intset.*;
import java.io.*;
import java.util.*;

public class FgSolution implements NasmVisitor <Void>{
    int iterNum = 0;
    public Nasm nasm;
    Fg fg;
    public Map< NasmInst, IntSet> use;
    public Map< NasmInst, IntSet> def;
    public Map< NasmInst, IntSet> in;
    public Map< NasmInst, IntSet> out;
    
    public FgSolution(Nasm nasm, Fg fg){
	this.nasm = nasm;
	this.fg = fg;
	this.use = new HashMap< NasmInst, IntSet>();
	this.def = new HashMap< NasmInst, IntSet>();
	this.in =  new HashMap< NasmInst, IntSet>();
	this.out = new HashMap< NasmInst, IntSet>();
    }
    
    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;
	
		if (baseFileName != null){
			try {
				baseFileName = baseFileName;
				fileName = baseFileName + ".fgs";
				out = new PrintStream(fileName);
			}

			catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}

		for (NasmInst nasmInst : nasm.listeInst) {
			nasmInst.accept(this);
		}

		algoInOut();

		out.println("iter num = " + iterNum);
		for(NasmInst nasmInst : this.nasm.listeInst){
			out.println("use = "+ this.use.get(nasmInst) + " def = "+ this.def.get(nasmInst) + "\tin = " + this.in.get(nasmInst) + "\t \tout = " + this.out.get(nasmInst) + "\t \t" + nasmInst);
		}
    }

    void passageInst(NasmInst inst) {
		def.put(inst,new IntSet(fg.inst2Node.size()));
		use.put(inst,new IntSet(fg.inst2Node.size()));
		if (inst.source instanceof NasmRegister  && inst.source.isGeneralRegister()) {
			NasmRegister source = (NasmRegister) inst.source;
			System.out.println("Source : "+inst + " -> " + source.val);
			if (inst.srcUse) {
				use.get(inst).add(source.val);
			}
			if (inst.srcDef) {
				def.get(inst).add(source.val);
			}
		}
		if (inst.destination instanceof NasmRegister  && inst.destination.isGeneralRegister()) {
			NasmRegister dest = (NasmRegister) inst.destination;
			System.out.println("Dest : "+ inst + " -> "+dest.val);
			if (inst.destUse) {
				use.get(inst).add(dest.val);
			}
			if (inst.destDef) {
				def.get(inst).add(dest.val);
			}
		}

		in.put(inst,new IntSet(fg.inst2Node.size()));
		out.put(inst,new IntSet(fg.inst2Node.size()));
	}

	void algoInOut() {
		System.out.println("Algo In Out");
		while (true) {
			Map< NasmInst, IntSet> inBis = new HashMap< NasmInst, IntSet>();
			Map< NasmInst, IntSet> outBis = new HashMap< NasmInst, IntSet>();

			for (NasmInst inst : nasm.listeInst) {
				//Instanciation des primes
				IntSet instInIntSet = in.get(inst).copy();
				IntSet instOutIntSet = out.get(inst).copy();
				inBis.put(inst, instInIntSet);
				outBis.put(inst, instOutIntSet);

				// Calcul de In
				IntSet useCopy = use.get(inst).copy();
				IntSet outCopy = out.get(inst).copy();
				IntSet defCopy = def.get(inst).copy();
				IntSet inIntSet = useCopy.union(outCopy.minus(defCopy));
				in.replace(inst, inIntSet);

				//Calcul de Out
				if (fg.inst2Node.get(inst).succ() != null) {
					NodeList nodeList = fg.inst2Node.get(inst).succ();
					while (nodeList.tail != null) {
						IntSet outIntSet = in.get(fg.node2Inst.get(nodeList.head));
						out.get(inst).union(outIntSet);
						nodeList = nodeList.tail;
					}
					IntSet outIntSet = in.get(fg.node2Inst.get(nodeList.head));
					out.get(inst).union(outIntSet);
				}
			}
			iterNum += 1;

			// Condition de sortie

			boolean inOk = true;
			boolean outOk = true;

			for (NasmInst inst : nasm.listeInst) {
				if (!in.get(inst).equal(inBis.get(inst))) {
					inOk = false;
				}
				if (!out.get(inst).equal(outBis.get(inst))) {
					outOk = false;
				}
			}
			if (inOk && outOk) break;
		}
	}

	@Override
	public Void visit(NasmAdd inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmCall inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmDiv inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJe inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJle inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJne inst) {
		def.put(inst,new IntSet(fg.inst2Node.size()));
		use.put(inst,new IntSet(fg.inst2Node.size()));
		in.put(inst,new IntSet(fg.inst2Node.size()));
		out.put(inst,new IntSet(fg.inst2Node.size()));
		return null;
	}

	@Override
	public Void visit(NasmMul inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmOr inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmCmp inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmInst inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJge inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJl inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmNot inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmPop inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmRet inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmXor inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmAnd inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJg inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmJmp inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmMov inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmPush inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmSub inst) {
		passageInst(inst);
		return null;
	}

	@Override
	public Void visit(NasmEmpty inst) {
		passageInst(inst);
		return null;
	}




	@Override
	public Void visit(NasmAddress operand) {
		return null;
	}

	@Override
	public Void visit(NasmConstant operand) {
		return null;
	}

	@Override
	public Void visit(NasmLabel operand) {
		return null;
	}

	@Override
	public Void visit(NasmRegister operand) {
		return null;
	}
}

    
