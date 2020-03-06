import sa.*;
import ts.Ts;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor {

    private Ts tableGlobale;
    private Ts tableLocal;

    public Sa2ts(SaNode saNode) {
        this.tableGlobale = new Ts();
        this.tableLocal = new Ts();
        saNode.accept(this);
    }

    public Ts getTableGlobale() {
        return this.tableGlobale;
    }

    @Override
    public Object visit(SaDecTab node) {
        defaultIn(node);

        if (tableGlobale.getVar(node.getNom()) == null){
            tableGlobale.addVar(node.getNom(), node.tsItem.getTaille());
        } else {
            System.out.println("Var "+ node.getNom() + " n'existe pas");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaDecFonc node) {
        defaultIn(node);

        this.tableLocal = new Ts();

        if (tableGlobale.getFct(node.getNom()) != null) {

            for (TsItemVar var : tableGlobale.variables.values()) {
                tableLocal.addVar(var.getIdentif(),var.getTaille());
            }

            Ts paramTable = new Sa2ts(node.getParametres()).tableGlobale;

            for (TsItemVar tsItemVar : paramTable.variables.values()) {
                tableLocal.addParam(tsItemVar.getIdentif());
            }

            Ts varTable = new Sa2ts(node.getVariable()).tableGlobale;

            for (TsItemVar tsItemVar : varTable.variables.values()) {
                tableLocal.addVar(tsItemVar.getIdentif(),tsItemVar.getTaille());
            }

            tableGlobale.addFct(node.getNom(),node.getParametres().length(),this.tableLocal, node);
        }

        node.accept(this);

        return null;
    }

    @Override
    public Object visit(SaDecVar node) {
        defaultIn(node);

        if (tableGlobale.getVar(node.getNom()) == null){
            tableGlobale.addVar(node.getNom(), node.tsItem.getTaille());
        } else {
            System.out.println("Var "+ node.getNom() + " n'existe pas");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaVarSimple node) {
        defaultIn(node);

        if (tableGlobale.getVar(node.getNom()) == null) {
            System.out.println("La var n'existe pas");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaAppel node) {
        defaultIn(node);

        if (tableGlobale.getFct(node.getNom()) == null) {
            System.out.println("Le tab n'existe pas");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaVarIndicee node) {
        defaultIn(node);

        if (tableGlobale.getVar(node.getNom()) == null) {
            System.out.println("Le tab n'existe pas");
        }

        defaultOut(node);

        return null;
    }
}
