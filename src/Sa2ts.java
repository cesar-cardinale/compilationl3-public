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

    public boolean varExist(String nom) {
        if (tableGlobale.getVar(nom) == null && tableLocal.getVar(nom) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void defaultIn(SaNode node) {
        System.out.println("debut Passage"+node.getClass());
    }

    @Override
    public void defaultOut(SaNode node) {
        System.out.println("fin Passage"+node.getClass());
    }

    @Override
    public Object visit(SaDecTab node) {
        defaultIn(node);
        System.out.println("DecTab");
        if (tableGlobale.getVar(node.getNom()) == null){
                tableGlobale.addVar(node.getNom(), tableGlobale.getAdrVarCourante()+node.getTaille());
        } else {
            System.out.println("Var "+ node.getNom() + " n'existe pas");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaDecFonc node) {
        defaultIn(node);
        System.out.println("DecFonc");

        this.tableLocal = new Ts();

        if (tableGlobale.getFct(node.getNom()) == null) {

            if (node.getParametres() != null) {
                Sa2ts sa2ts = new Sa2ts(node.getParametres());
                Ts paramTable = sa2ts.tableGlobale;

                for (TsItemVar tsItemVar : paramTable.variables.values()) {
                    tableLocal.addParam(tsItemVar.getIdentif());
                }
            }

            if (node.getVariable() != null) {
                Sa2ts sa2ts = new Sa2ts(node.getVariable());
                Ts varTable = sa2ts.tableGlobale;

                for (TsItemVar tsItemVar : varTable.variables.values()) {
                    if (tableLocal.getVar(tsItemVar.getIdentif()) == null)
                        tableLocal.addVar(tsItemVar.getIdentif(),tsItemVar.getTaille());
                    else System.out.println("La même variable est déclaré en paramètre");
                }

            }
            if (node.getParametres() == null)
                tableGlobale.addFct(node.getNom(),0,this.tableLocal, node);
            else
                tableGlobale.addFct(node.getNom(),node.getParametres().length(),this.tableLocal, node);
        }

        node.getCorps().accept(this);

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaDecVar node) {
        defaultIn(node);
        System.out.println("DecVar");

        if (!varExist(node.getNom())){
            if (node.tsItem == null)
                tableGlobale.addVar(node.getNom(), tableGlobale.getAdrVarCourante()+1);
            else
                tableGlobale.addVar(node.getNom(), tableGlobale.getAdrVarCourante() + node.tsItem.getTaille());
        } else {
            System.out.println("Var "+ node.getNom() + " existe déjà");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaVarSimple node) {
        defaultIn(node);
        System.out.println("VarSimple");

        if (!varExist(node.getNom())) {
            System.out.println("La var n'existe pas");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaAppel node) {
        defaultIn(node);
        System.out.println("Appel");

        if (tableGlobale.getFct(node.getNom()) == null) {
            System.out.println("Le tab n'existe pas!");
        }
        if (node.getArguments().length() != tableGlobale.getFct(node.getNom()).getNbArgs()) {
            System.out.println("Le nombre d'argument n'est pas bon!");
        }

        defaultOut(node);

        return null;
    }

    @Override
    public Object visit(SaVarIndicee node) {
        defaultIn(node);
        System.out.println("VarIndicee");

        if (tableGlobale.getVar(node.getNom()) == null) {
            System.out.println("Le tab n'existe pas");
        }

        defaultOut(node);

        return null;
    }
}
