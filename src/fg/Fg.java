package fg;
import nasm.*;
import util.graph.*;
import java.util.*;
import java.io.*;

public class Fg implements NasmVisitor <Void> {
    public Nasm nasm;
    public Graph graph;
    Map< NasmInst, Node> inst2Node;
    Map< Node, NasmInst> node2Inst;
    Map< String, NasmInst> label2Inst;

    public Fg(Nasm nasm){
	this.nasm = nasm;
	this.inst2Node = new HashMap< NasmInst, Node>();
	this.node2Inst = new HashMap< Node, NasmInst>();
	this.label2Inst = new HashMap< String, NasmInst>();
	this.graph = new Graph();
    }

    public void affiche(String baseFileName){
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null){
            try {
            baseFileName = baseFileName;
            fileName = baseFileName + ".fg";
            out = new PrintStream(fileName);
            }

            catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            }
        }

        for (NasmInst nasmInst : nasm.listeInst) {
            creatNode(nasmInst);
        }

        for (NasmInst nasmInst : nasm.listeInst) {
            nasmInst.accept(this);
        }

        for(NasmInst nasmInst : nasm.listeInst){
            Node n = this.inst2Node.get(nasmInst);
            out.print(n + " : ( ");
            for(NodeList q=n.succ(); q!=null; q=q.tail) {
                out.print(q.head.toString());
                out.print(" ");
            }
            out.println(")\t" + nasmInst);
        }
    }

    void creatNode(NasmInst inst) {
        Node node = graph.newNode();
        inst2Node.put(inst, node);
        node2Inst.put(node, inst);
        if (inst.label != null) {
            label2Inst.put(inst.label.toString(),inst);
        }
    }

    void creatArcSuiv(NasmInst inst) {

        Node nodeFrom = inst2Node.get(inst);
        int numNodeFrom = nodeFrom.label();

        if (numNodeFrom == graph.nodeCount()-1) return;

        Node nodeTo = null;
        for (Node node : inst2Node.values()) {
            if (node.label() == numNodeFrom+1) {
                nodeTo = node;
            }
        }
        graph.addEdge(nodeFrom, nodeTo);
    }

    void createArcWithLabel(NasmInst inst) {
        Node nodeTo = null;
        for (String str : label2Inst.keySet()) {
            if (inst.address.toString().equals(str)) {
                nodeTo = inst2Node.get(label2Inst.get(str));
            }
        }
        graph.addEdge(inst2Node.get(inst), nodeTo);
    }
    
    public Void visit(NasmAdd inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmCall inst){
        if (inst.address.toString().equals("iprintLF")) return null;
        createArcWithLabel(inst);
        return null;
    }
    public Void visit(NasmDiv inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJe inst){
        createArcWithLabel(inst);
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJle inst){
        createArcWithLabel(inst);
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJne inst){
        createArcWithLabel(inst);
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmMul inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmOr inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmCmp inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmInst inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJge inst){
        createArcWithLabel(inst);
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJl inst){
        createArcWithLabel(inst);
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmNot inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmPop inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmRet inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmXor inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmAnd inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJg inst){
        createArcWithLabel(inst);
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmJmp inst){
        createArcWithLabel(inst);
        return null;
    }
    public Void visit(NasmMov inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmPush inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmSub inst){
        creatArcSuiv(inst);
        return null;
    }
    public Void visit(NasmEmpty inst){
        creatArcSuiv(inst);
        return null;
    }

    public Void visit(NasmAddress operand){return null;}
    public Void visit(NasmConstant operand){return null;}
    public Void visit(NasmLabel operand){return null;}
    public Void visit(NasmRegister operand){return null;}


}
