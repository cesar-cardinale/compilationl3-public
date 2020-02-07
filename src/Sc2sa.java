import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    public SaNode getRoot() {
        return this.returnValue;
    }

    private SaNode apply(Switchable sc) {
        sc.apply(this);
        return this.returnValue;
    }

    @Override
    public void caseStart(Start node) {
        super.caseStart(node);
    }

    @Override
    public void caseAProgrammeProgramme(AProgrammeProgramme node) {
        SaLDec dec_var = (SaLDec) apply(node.getListedecvaropt());
        SaLDec dec_fonc = (SaLDec) apply(node.getListedecfonc());
        this.returnValue = new SaProg(dec_var,dec_fonc);
    }

    @Override
    public void caseAListeVarVar(AListeVarVar node) {
        SaLExp crochet_fermant = (SaLExp) apply(node.getCrochetFermant());
        SaExp exp = (SaExp) apply(node.getExpression());
        SaExp crochet_ouvrant = (SaExp) apply(node.getCrochetOuvrant());
        SaExp id = (SaExp) apply(node.getIdentifiant());

        SaLExp saLExp1 = new SaLExp(exp,crochet_fermant);
        SaLExp saLExp2 = new SaLExp(crochet_ouvrant,saLExp1);

        this.returnValue = new SaLExp(id,saLExp2);
    }

    @Override
    public void caseAVarVar(AVarVar node) {
        String var = apply(node.getIdentifiant()).toString();
        this.returnValue = new SaVarSimple(var);
    }

    @Override
    public void caseAOuExpression(AOuExpression node) {
        super.caseAOuExpression(node);
    }

    @Override
    public void caseAExp2Expression(AExp2Expression node) {
        super.caseAExp2Expression(node);
    }

    @Override
    public void caseAEtExp2(AEtExp2 node) {
        super.caseAEtExp2(node);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node) {
        super.caseAExp3Exp2(node);
    }

    @Override
    public void caseAEgalExp3(AEgalExp3 node) {
        super.caseAEgalExp3(node);
    }

    @Override
    public void caseAInferieurExp3(AInferieurExp3 node) {
        super.caseAInferieurExp3(node);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node) {
        super.caseAExp4Exp3(node);
    }

    @Override
    public void caseAPlusExp4(APlusExp4 node) {
        super.caseAPlusExp4(node);
    }

    @Override
    public void caseAMoinsExp4(AMoinsExp4 node) {
        super.caseAMoinsExp4(node);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node) {
        super.caseAExp5Exp4(node);
    }

    @Override
    public void caseAFoisExp5(AFoisExp5 node) {
        super.caseAFoisExp5(node);
    }

    @Override
    public void caseADiviserExp5(ADiviserExp5 node) {
        super.caseADiviserExp5(node);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node) {
        super.caseAExp6Exp5(node);
    }

    @Override
    public void caseANonExp6(ANonExp6 node) {
        super.caseANonExp6(node);
    }

    @Override
    public void caseAExp7Exp6(AExp7Exp6 node) {
        super.caseAExp7Exp6(node);
    }

    @Override
    public void caseANombreExp7(ANombreExp7 node) {
        super.caseANombreExp7(node);
    }

    @Override
    public void caseAVarExp7(AVarExp7 node) {
        super.caseAVarExp7(node);
    }

    @Override
    public void caseAParanthesesExp7(AParanthesesExp7 node) {
        super.caseAParanthesesExp7(node);
    }

    @Override
    public void caseAAppelfonctionExp7(AAppelfonctionExp7 node) {
        super.caseAAppelfonctionExp7(node);
    }

    @Override
    public void caseALireExp7(ALireExp7 node) {
        super.caseALireExp7(node);
    }

    @Override
    public void caseARecursifListeExp(ARecursifListeExp node) {
        super.caseARecursifListeExp(node);
    }

    @Override
    public void caseAFinalListeExp(AFinalListeExp node) {
        super.caseAFinalListeExp(node);
    }

    @Override
    public void caseAFinalListeExpBis(AFinalListeExpBis node) {
        SaVar virgule = (SaVar) apply(node.getVirgule());
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = new SaInstAffect(virgule,exp);
    }

    @Override
    public void caseARecursifListeExpBis(ARecursifListeExpBis node) {
        /*SaVar virgule = (SaVar) apply(node.getVirgule());
        SaExp exp = (SaExp) apply(node.getExpression());
        SaLExp liste_exp = (SaLExp) apply(node.getListeExpBis());
        SaLExp saLExp = new SaLExp(exp,liste_exp);
        this.returnValue = new SaInstAffect(virgule,saLExp);*/
    }

    @Override
    public void caseAInstructionAffectationInstruction(AInstructionAffectationInstruction node) {
        super.caseAInstructionAffectationInstruction(node);
    }

    @Override
    public void caseAInstructionBlocInstruction(AInstructionBlocInstruction node) {
        super.caseAInstructionBlocInstruction(node);
    }

    @Override
    public void caseAInstructionSiInstruction(AInstructionSiInstruction node) {
        super.caseAInstructionSiInstruction(node);
    }

    @Override
    public void caseAInstructionTantqueInstruction(AInstructionTantqueInstruction node) {
        super.caseAInstructionTantqueInstruction(node);
    }

    @Override
    public void caseAInstructionRappelInstruction(AInstructionRappelInstruction node) {
        super.caseAInstructionRappelInstruction(node);
    }

    @Override
    public void caseAInstructionRetourInstruction(AInstructionRetourInstruction node) {
        SaExp exp = (SaExp) apply(node.getInstructionRetour());
        this.returnValue = new SaInstRetour(exp);
    }

    @Override
    public void caseAInstructionEcrireInstruction(AInstructionEcrireInstruction node) {
        SaExp exp = (SaExp) apply(node.getInstructionEcrire());
        this.returnValue = new SaInstEcriture(exp);
    }

    @Override
    public void caseAInstructionVideInstruction(AInstructionVideInstruction node) {
        super.caseAInstructionVideInstruction(node);
    }

    @Override
    public void caseAListeInstructionListeInstruction(AListeInstructionListeInstruction node) {
        SaInst tete = (SaInst) apply(node.getInstruction());
        SaLInst queue = (SaLInst) apply(node.getListeInstruction());
        this.returnValue = new SaLInst(tete, queue);
    }

    @Override
    public void caseAListeFinaleListeInstruction(AListeFinaleListeInstruction node) {
        this.returnValue = null;
    }

    @Override
    public void caseAInstructionAffectation(AInstructionAffectation node) {
        SaVar var = (SaVar) apply(node.getVar());
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = new SaInstAffect(var, exp);
    }

    @Override
    public void caseAInstructionBlocInstructionBloc(AInstructionBlocInstructionBloc node) {
        SaLInst exp = (SaLInst) apply(node.getListeInstruction());
        this.returnValue = new SaInstBloc(exp);
    }

    @Override
    public void caseAAvecsinonInstructionSi(AAvecsinonInstructionSi node) {
        SaExp exp = (SaExp) apply(node.getInstructionBloc());
        SaInst alors = (SaInst) apply(node.getAlors());
        SaInst si = (SaInst) apply(node.getSi());
        this.returnValue = new SaInstSi(exp, alors, si);
    }

    @Override
    public void caseASanssinonInstructionSi(ASanssinonInstructionSi node) {
        SaExp exp = (SaExp) apply(node.getInstructionBloc());
        SaInst alors = (SaInst) apply(node.getAlors());
        this.returnValue = new SaInstSi(exp, alors, null);
    }

    @Override
    public void caseAInstructionSinon(AInstructionSinon node) {
        SaExp exp = (SaExp) apply(node.getInstructionBloc());
        SaInst sinon = (SaInst) apply(node.getSinon());
        this.returnValue = new SaInstSi(exp, null, sinon);
    }

    @Override
    public void caseAInstructionTantque(AInstructionTantque node) {
        SaExp exp = (SaExp) apply(node.getExpression());
        SaInst inst = (SaInst) apply(node.getFaire());
        this.returnValue = new SaInstTantQue(exp, inst);
    }

    @Override
    public void caseAInstructionRappel(AInstructionRappel node) {
        super.caseAInstructionRappel(node);
    }

    @Override
    public void caseAInstructionRetour(AInstructionRetour node) {
        super.caseAInstructionRetour(node);
    }

    @Override
    public void caseAInstructionEcrire(AInstructionEcrire node) {
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = new SaInstEcriture(exp);
    }

    @Override
    public void caseAInstructionVide(AInstructionVide node) {
        super.caseAInstructionVide(node);
    }

    @Override
    public void caseADecvarDecvar(ADecvarDecvar node) {
        int val = Integer.parseInt(node.getIdentifiant().getText());
        this.returnValue = new SaExpInt(val);
    }

    @Override
    public void caseAListedecvarDecvar(AListedecvarDecvar node) {
        super.caseAListedecvarDecvar(node);
    }

    @Override
    public void caseAListedecvarListedecvar(AListedecvarListedecvar node) {
        super.caseAListedecvarListedecvar(node);
    }

    @Override
    public void caseAListedecvarFinListedecvar(AListedecvarFinListedecvar node) {
        this.returnValue = null;
    }

    @Override
    public void caseAListedecvarbisListedecvarbis(AListedecvarbisListedecvarbis node) {
        super.caseAListedecvarbisListedecvarbis(node);
    }

    @Override
    public void caseAListedecvarbisFinListedecvarbis(AListedecvarbisFinListedecvarbis node) {
        this.returnValue = null;
    }

    @Override
    public void caseAListedecvaroptListedecvaropt(AListedecvaroptListedecvaropt node) {
        super.caseAListedecvaroptListedecvaropt(node);
    }

    @Override
    public void caseAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node) {
        this.returnValue = null;
    }

    @Override
    public void caseAAvecparamAppelfonction(AAvecparamAppelfonction node) {
        super.caseAAvecparamAppelfonction(node);
    }

    @Override
    public void caseASansparamAppelfonction(ASansparamAppelfonction node) {
        super.caseASansparamAppelfonction(node);
    }

    @Override
    public void caseADecfoncDecfonc(ADecfoncDecfonc node) {
        super.caseADecfoncDecfonc(node);
    }

    @Override
    public void caseAListedecfoncListedecfonc(AListedecfoncListedecfonc node) {
        super.caseAListedecfoncListedecfonc(node);
    }

    @Override
    public void caseAListedecfoncFinListedecfonc(AListedecfoncFinListedecfonc node) {
        this.returnValue = null;
    }
}

