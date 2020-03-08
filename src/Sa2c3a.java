import c3a.*;
import sa.*;
import ts.Ts;
import ts.TsItemVar;

public class Sa2c3a extends SaDepthFirstVisitor {
    private C3a c3a;
    private Ts table;

    public Sa2c3a(SaNode root, Ts table){
        c3a = new C3a();
        table = new Ts();
        root.accept(this);
    }

    public C3a getC3a() {
        return c3a;
    }

    @Override
    public Object visit(SaProg node) {
        node.getFonctions().accept(this);

        return super.visit(node);
    }

    @Override
    public Object visit(SaDecTab node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExp node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    @Override
    public Object visit(SaExpVar node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaInstEcriture node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaInstTantQue node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaLInst node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaDecFonc node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaDecVar node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaInstAffect node) {
        C3aInstAffect c3aInst = new C3aInstAffect((C3aOperand) node.getRhs().accept(this),
                (C3aOperand) node.getLhs().accept(this),"Affect");
        c3a.ajouteInst(c3aInst);
        return null;
    }

    @Override
    public Object visit(SaLDec node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaVarSimple node) {
        return new C3aVar(table.getVar(node.getNom()), null);
    }

    @Override
    public Object visit(SaAppel node) {
        return new C3aFunction(table.getFct(node.getNom()));
    }

    @Override
    public Object visit(SaExpAppel node) {
        C3aTemp c3aTemp = new C3aTemp(c3a.getTempCounter());
        C3aInstCall c3aInstCall = new C3aInstCall((C3aFunction) node.getVal().accept(this),
                c3aTemp,"Call");
        c3a.ajouteInst(c3aInstCall);
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpAdd node) {
        C3aTemp c3aTemp = new C3aTemp(c3a.getTempCounter());
        C3aInstAdd c3aInstAdd = new C3aInstAdd((C3aOperand) node.getOp1().accept(this),
                (C3aOperand) node.getOp2().accept(this), c3aTemp, "Add");
        c3a.ajouteInst(c3aInstAdd);
        return null;
    }

    @Override
    public Object visit(SaExpSub node) {
        C3aTemp c3aTemp = new C3aTemp(c3a.getTempCounter());
        C3aInstSub c3aInstSub = new C3aInstSub((C3aOperand) node.getOp1().accept(this),
                (C3aOperand) node.getOp2().accept(this), c3aTemp, "Sub");
        c3a.ajouteInst(c3aInstSub);
        return null;
    }

    @Override
    public Object visit(SaExpMult node) {
        C3aTemp c3aTemp = new C3aTemp(c3a.getTempCounter());
        C3aInstMult c3aInstMult = new C3aInstMult((C3aOperand) node.getOp1().accept(this),
                (C3aOperand) node.getOp2().accept(this), c3aTemp, "Mult");
        c3a.ajouteInst(c3aInstMult);
        return null;
    }

    @Override
    public Object visit(SaExpDiv node) {
        C3aTemp c3aTemp = new C3aTemp(c3a.getTempCounter());
        C3aInstDiv c3aInstDiv = new C3aInstDiv((C3aOperand) node.getOp1().accept(this),
                (C3aOperand) node.getOp2().accept(this), c3aTemp, "Div");
        c3a.ajouteInst(c3aInstDiv);
        return null;
    }

    @Override
    public Object visit(SaExpInf node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpEqual node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpAnd node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpOr node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpNot node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaExpLire node) {
        C3aInstRead c3aInstRead = new C3aInstRead((C3aOperand) node.accept(this), "Lire");
        c3a.ajouteInst(c3aInstRead);
        return null;
    }

    @Override
    public Object visit(SaInstBloc node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaInstSi node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaInstRetour node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaLExp node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaVarIndicee node) {
        return super.visit(node);
    }
}
