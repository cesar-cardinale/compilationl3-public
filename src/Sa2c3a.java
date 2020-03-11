import c3a.*;
import sa.*;
import ts.Ts;
import ts.TsItemFct;
import ts.TsItemVar;

public class Sa2c3a extends SaDepthFirstVisitor<C3aOperand> {
    private C3a c3a;
    private Ts table;
    private TsItemFct foncCourante;

    public Sa2c3a(SaNode root, Ts table){
        c3a = new C3a();
        this.table = table;
        root.accept(this);
    }

    public C3a getC3a() {
        return c3a;
    }

    @Override
    public C3aOperand visit(SaProg node) {
        node.getFonctions().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaDecTab node) {
        return null;
    }

    @Override
    public C3aOperand visit(SaExp node) {
        return null;
    }

    @Override
    public C3aOperand visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    @Override
    public C3aOperand visit(SaExpVar node) {
        return  node.getVar().accept(this);
    }

    @Override
    public C3aOperand visit(SaInstEcriture node) {
        C3aInstWrite c3aInstWrite = new C3aInstWrite(node.getArg().accept(this), "");
        c3a.ajouteInst(c3aInstWrite);
        return null;
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) {
        //Prepartion des labels
        C3aLabel c3aLabel = c3a.newAutoLabel();
        c3a.addLabelToNextInst(c3aLabel);

        C3aLabel c3aLabel1 = c3a.newAutoLabel();

        //Test du tant que
        node.getTest().accept(this);

        C3aInstJumpIfEqual c3aInstJumpIfEqual = new C3aInstJumpIfEqual(new C3aTemp(c3a.getTempCounter()-1),
                new C3aConstant(0),c3aLabel1, "");
        c3a.ajouteInst(c3aInstJumpIfEqual);

        node.getFaire().accept(this);

        C3aInstJump c3aInstJump = new C3aInstJump(c3aLabel, "");
        c3a.ajouteInst(c3aInstJump);

        c3a.addLabelToNextInst(c3aLabel1);
        return null;
    }

    @Override
    public C3aOperand visit(SaLInst node) {
        node.getTete().accept(this);
        if (node.getQueue() != null) node.getQueue().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaDecFonc node) {
        C3aInstFBegin c3aInstFBegin = new C3aInstFBegin(table.getFct(node.getNom()),"entree fonction");
        this.foncCourante = table.getFct(node.getNom());
        c3a.ajouteInst(c3aInstFBegin);
        node.getCorps().accept(this);
        C3aInstFEnd c3aInstFEnd = new C3aInstFEnd("");
        c3a.ajouteInst(c3aInstFEnd);
        return null;
    }

    @Override
    public C3aOperand visit(SaDecVar node) {
        return null;
    }

    @Override
    public C3aOperand visit(SaInstAffect node) {
        C3aOperand op = node.getRhs().accept(this);
        C3aOperand result =  node.getLhs().accept(this);
        C3aInstAffect c3aInst = new C3aInstAffect(op,
                result,"");
        c3a.ajouteInst(c3aInst);
        return result;
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        node.getTete().accept(this);
        if (node.getQueue() != null) node.getQueue().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaVarSimple node) {
        C3aVar c3aVar;
        if (table.getVar(node.getNom()) == null) {
            c3aVar = new C3aVar(table.getTableLocale(foncCourante.getIdentif()).getVar(node.getNom()), null);
        } else {
            c3aVar = new C3aVar(table.getVar(node.getNom()), null);
        }
        return c3aVar;
    }

    @Override
    public C3aOperand visit(SaAppel node) {
        if (node.getArguments() != null) node.getArguments().accept(this);
        return new C3aFunction(table.getFct(node.getNom()));
    }

    @Override
    public C3aOperand visit(SaExpAppel node) {
        C3aFunction c3aFunction = (C3aFunction) node.getVal().accept(this);
        C3aTemp c3aTemp = c3a.newTemp();
        C3aInstCall c3aInstCall = new C3aInstCall(c3aFunction,
                c3aTemp,"");
        c3a.ajouteInst(c3aInstCall);
        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand c3aOperand = node.getOp1().accept(this);
        C3aOperand c3aOperand1 = node.getOp2().accept(this);
        C3aTemp c3aTemp = c3a.newTemp();
        C3aInstAdd c3aInstAdd = new C3aInstAdd(c3aOperand, c3aOperand1, c3aTemp, "");
        c3a.ajouteInst(c3aInstAdd);
        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aOperand c3aOperand = node.getOp1().accept(this);
        C3aOperand c3aOperand1 = node.getOp2().accept(this);
        C3aTemp c3aTemp = c3a.newTemp();
        C3aInstSub c3aInstSub = new C3aInstSub(c3aOperand, c3aOperand1, c3aTemp, "");
        c3a.ajouteInst(c3aInstSub);
        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aOperand c3aOperand = node.getOp1().accept(this);
        C3aOperand c3aOperand1 = node.getOp2().accept(this);
        C3aTemp c3aTemp = c3a.newTemp();
        C3aInstMult c3aInstMult = new C3aInstMult(c3aOperand, c3aOperand1, c3aTemp, "");
        c3a.ajouteInst(c3aInstMult);
        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand c3aOperand = node.getOp1().accept(this);
        C3aOperand c3aOperand1 = node.getOp2().accept(this);
        C3aTemp c3aTemp = c3a.newTemp();
        C3aInstDiv c3aInstDiv = new C3aInstDiv(c3aOperand ,c3aOperand1, c3aTemp, "");
        c3a.ajouteInst(c3aInstDiv);
        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpInf node) {
        C3aTemp c3aTemp = c3a.newTemp();
        C3aOperand c3aOperand = node.getOp1().accept(this);
        C3aOperand c3aOperand1 = node.getOp2().accept(this);

        C3aInstAffect c3aInstAffect = new C3aInstAffect(new C3aConstant(1), c3aTemp, "");
        c3a.ajouteInst(c3aInstAffect);

        C3aLabel c3aLabel = c3a.newAutoLabel();

        C3aInstJumpIfLess c3aInstJumpIfLess = new C3aInstJumpIfLess(c3aOperand ,c3aOperand1 , c3aLabel, "");
        c3a.ajouteInst(c3aInstJumpIfLess);

        C3aInstAffect c3aInstAffect2 = new C3aInstAffect(new C3aConstant(0), c3aTemp, "");
        c3a.ajouteInst(c3aInstAffect2);

        c3a.addLabelToNextInst(c3aLabel);

        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpEqual node) {
        // SaExpEqual jamais utilisé
        return null;
    }

    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel c3aLabel2 = c3a.newAutoLabel();
        C3aLabel c3aLabel = c3a.newAutoLabel();

        C3aTemp c3aTemp = c3a.newTemp();

        C3aOperand c3aOperand = node.getOp1().accept(this);
        C3aOperand c3aOperand1 = node.getOp2().accept(this);

        //Premier IF
        C3aInstJumpIfEqual c3aInstJumpIfEqual = new C3aInstJumpIfEqual(c3aOperand,new C3aConstant(0), c3aLabel, "");
        c3a.ajouteInst(c3aInstJumpIfEqual);

        // Deuxieme If
        C3aInstJumpIfEqual c3aInstJumpIfEqual2 = new C3aInstJumpIfEqual(c3aOperand1,new C3aConstant(0), c3aLabel, "");
        c3a.ajouteInst(c3aInstJumpIfEqual2);

        // Affectation temporaire


        C3aInstAffect c3aInstAffect = new C3aInstAffect(new C3aConstant(1), c3aTemp, "");
        c3a.ajouteInst(c3aInstAffect);

        //Goto

        C3aInstJump c3aInstJump = new C3aInstJump(c3aLabel2, "");
        c3a.ajouteInst(c3aInstJump);

        //Deuxieume affectation
        C3aInstAffect c3aInstAffect2 = new C3aInstAffect(new C3aConstant(0), c3aTemp, "");
        c3aInstAffect2.setLabel(c3aLabel);
        c3a.ajouteInst(c3aInstAffect2);

        c3a.addLabelToNextInst(c3aLabel2);

        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpOr node) {
        C3aLabel c3aLabel2 = c3a.newAutoLabel();
        C3aLabel c3aLabel = c3a.newAutoLabel();

        //Premier IF
        C3aInstJumpIfNotEqual c3aInstJumpIfNotEqual = new C3aInstJumpIfNotEqual( node.getOp1().accept(this),
                new C3aConstant(0), c3aLabel, "");
        c3a.ajouteInst(c3aInstJumpIfNotEqual);

        // Deuxieme If
        C3aInstJumpIfNotEqual c3aInstJumpIfNotEqual2 = new C3aInstJumpIfNotEqual( node.getOp2().accept(this),
                new C3aConstant(0), c3aLabel, "");
        c3a.ajouteInst(c3aInstJumpIfNotEqual2);

        // Affectation temporaire
        C3aTemp c3aTemp = c3a.newTemp();

        C3aInstAffect c3aInstAffect = new C3aInstAffect(new C3aConstant(0), c3aTemp, "");
        c3a.ajouteInst(c3aInstAffect);

        //Goto

        C3aInstJump c3aInstJump = new C3aInstJump(c3aLabel2, "");
        c3a.ajouteInst(c3aInstJump);

        //Deuxieume affectation
        C3aInstAffect c3aInstAffect2 = new C3aInstAffect(new C3aConstant(1), c3aTemp, "");
        c3aInstAffect2.setLabel(c3aLabel);
        c3a.ajouteInst(c3aInstAffect2);

        c3a.addLabelToNextInst(c3aLabel2);

        return c3aTemp;
    }

    @Override
    public C3aOperand visit(SaExpNot node) {
        // SaExpNot jamais utilisé
        return null;
    }

    @Override
    public C3aOperand visit(SaExpLire node) {
        C3aInstRead c3aInstRead = new C3aInstRead( node.accept(this), "");
        c3a.ajouteInst(c3aInstRead);
        return null;
    }

    @Override
    public C3aOperand visit(SaInstBloc node) {
        node.getVal().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaInstSi node) {
        C3aLabel c3aLabel = c3a.newAutoLabel();
        C3aLabel c3aLabel2 = c3a.newAutoLabel();
        if (node.getSinon() != null) {
            C3aInstJumpIfEqual c3aInstJumpIfEqual = new C3aInstJumpIfEqual( node.getTest().accept(this),
                    new C3aConstant(0), c3aLabel, "");
            c3a.ajouteInst(c3aInstJumpIfEqual);
            node.getAlors().accept(this);

            C3aInstJump c3aInstJump = new C3aInstJump(c3aLabel2,"");
            c3a.ajouteInst(c3aInstJump);

            c3a.addLabelToNextInst(c3aLabel);
            node.getSinon().accept(this);
            c3a.addLabelToNextInst(c3aLabel2);
        } else {
            C3aInstJumpIfEqual c3aInstJumpIfEqual = new C3aInstJumpIfEqual( node.getTest().accept(this),
                    new C3aConstant(0), c3aLabel2, "");
            c3a.ajouteInst(c3aInstJumpIfEqual);
            node.getAlors().accept(this);

            c3a.addLabelToNextInst(c3aLabel2);
        }
        return null;
    }

    @Override
    public C3aOperand visit(SaInstRetour node) {
        C3aInstReturn c3aInstReturn = new C3aInstReturn(node.getVal().accept(this), "");
        c3a.ajouteInst(c3aInstReturn);
        return null;
    }

    @Override
    public C3aOperand visit(SaLExp node) {
        C3aOperand c3aOperand = node.getTete().accept(this);
        C3aInstParam c3aInstParam = new C3aInstParam(c3aOperand, "");
        c3a.ajouteInst(c3aInstParam);
        if (node.getQueue() != null) node.getQueue().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return new C3aVar(table.getVar(node.getNom()), node.getIndice().accept(this));
    }
}