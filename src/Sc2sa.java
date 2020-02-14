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
        SaVar var = (SaVar) apply(node.getIdentifiant());
        this.returnValue = new SaExpVar(var);
    }

    @Override
    public void caseAOuExpression(AOuExpression node) {
        SaExp exp = (SaExp) apply(node.getExpression());
        SaExp exp2 = (SaExp) apply(node.getExp2());

        this.returnValue = new SaExpOr(exp,exp2);
    }

    @Override
    public void caseAExp2Expression(AExp2Expression node) {
        SaExp exp2 = (SaExp) apply(node.getExp2());
        this.returnValue = new SaLExp(exp2,null);
    }

    @Override
    public void caseAEtExp2(AEtExp2 node) {
        SaExp exp2 = (SaExp) apply(node.getExp2());
        SaExp exp3 = (SaExp) apply(node.getExp3());

        this.returnValue = new SaExpAnd(exp2,exp3);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node) {
        SaExp exp2 = (SaExp) apply(node.getExp3());
        this.returnValue = new SaLExp(exp2,null);
    }

    @Override
    public void caseAEgalExp3(AEgalExp3 node) {
        SaExp exp3 = (SaExp) apply(node.getExp3());
        SaExp exp4 = (SaExp) apply(node.getExp4());

        this.returnValue = new SaExpEqual(exp3,exp4);
    }

    @Override
    public void caseAInferieurExp3(AInferieurExp3 node) {
        SaExp exp3 = (SaExp) apply(node.getExp3());
        SaExp exp4 = (SaExp) apply(node.getExp4());

        this.returnValue = new SaExpInf(exp3,exp4);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node) {
        SaExp exp2 = (SaExp) apply(node.getExp4());
        this.returnValue = new SaLExp(exp2,null);
    }

    @Override
    public void caseAPlusExp4(APlusExp4 node) {
        SaExp exp4 = (SaExp) apply(node.getExp4());
        SaExp exp5 = (SaExp) apply(node.getExp5());

        this.returnValue = new SaExpAdd(exp4,exp5);
    }

    @Override
    public void caseAMoinsExp4(AMoinsExp4 node) {
        SaExp exp4 = (SaExp) apply(node.getExp4());
        SaExp exp5 = (SaExp) apply(node.getExp5());

        this.returnValue = new SaExpSub(exp4,exp5);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node) {
        SaExp exp2 = (SaExp) apply(node.getExp5());
        this.returnValue = new SaLExp(exp2,null);
    }

    @Override
    public void caseAFoisExp5(AFoisExp5 node) {
        SaExp exp5 = (SaExp) apply(node.getExp5());
        SaExp exp6 = (SaExp) apply(node.getExp6());

        this.returnValue = new SaExpMult(exp5,exp6);
    }

    @Override
    public void caseADiviserExp5(ADiviserExp5 node) {
        SaExp exp5 = (SaExp) apply(node.getExp5());
        SaExp exp6 = (SaExp) apply(node.getExp6());

        this.returnValue = new SaExpDiv(exp5,exp6);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node) {
        SaExp exp2 = (SaExp) apply(node.getExp6());
        this.returnValue = new SaLExp(exp2,null);
    }

    @Override
    public void caseANonExp6(ANonExp6 node) {
        SaExp exp7 = (SaExp) apply(node.getExp7());

        this.returnValue = new SaExpNot(exp7);
    }

    @Override
    public void caseAExp7Exp6(AExp7Exp6 node) {
        SaExp exp2 = (SaExp) apply(node.getExp7());
        this.returnValue = new SaLExp(exp2,null);
    }

    @Override
    public void caseANombreExp7(ANombreExp7 node) {
        this.returnValue = new SaExpInt(Integer.parseInt(node.getNombre().getText()));
    }

    @Override
    public void caseAVarExp7(AVarExp7 node) {
        SaVar var = (SaVar) apply(node.getVar());
        this.returnValue = new SaExpVar(var);
    }

    @Override
    public void caseAParanthesesExp7(AParanthesesExp7 node) {
        SaLExp parenthese_fermant = (SaLExp) apply(node.getParantheseFermante());
        SaExp exp = (SaExp) apply(node.getExpression());
        SaExp parenthese_ouvrant = (SaExp) apply(node.getParantheseOuvrante());

        SaLExp saLExp1 = new SaLExp(exp,parenthese_fermant);

        this.returnValue = new SaLExp(parenthese_ouvrant,saLExp1);
    }

    @Override
    public void caseAAppelfonctionExp7(AAppelfonctionExp7 node) {
        SaAppel appel = (SaAppel) apply(node.getAppelfonction());
        this.returnValue = new SaExpAppel(appel);
    }

    @Override
    public void caseALireExp7(ALireExp7 node) {
        SaLExp parenthese_fermant = (SaLExp) apply(node.getParantheseFermante());
        SaExp parenthese_ouvrant = (SaExp) apply(node.getParantheseOuvrante());
        SaExpLire lire = (SaExpLire) apply(node.getLire());

        SaLExp saLExp1 = new SaLExp(parenthese_ouvrant,parenthese_fermant);

        this.returnValue = new SaLExp(lire,saLExp1);
    }

    @Override
    public void caseARecursifListeExp(ARecursifListeExp node) {
        SaLExp liste_exp_bis = (SaLExp) apply(node.getListeExpBis());
        SaExp exp = (SaExp) apply(node.getExpression());

        this.returnValue = new SaLExp(exp,liste_exp_bis);
    }

    @Override
    public void caseAFinalListeExp(AFinalListeExp node) {
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = new SaLExp(exp,null);
    }

    @Override
    public void caseAFinalListeExpBis(AFinalListeExpBis node) {
        SaExp virgule = (SaExp) apply(node.getVirgule());
        SaLExp exp = (SaLExp) apply(node.getExpression());
        this.returnValue = new SaLExp(virgule,exp);
    }

    @Override
    public void caseARecursifListeExpBis(ARecursifListeExpBis node) {
        SaExp virgule = (SaExp) apply(node.getVirgule());
        SaExp exp = (SaExp) apply(node.getExpression());
        SaLExp liste_exp_bis = (SaLExp) apply(node.getListeExpBis());

        SaLExp saLExp1 = new SaLExp(exp,liste_exp_bis);

        this.returnValue = new SaLExp(virgule,saLExp1);
    }

    @Override
    public void caseAInstructionAffectationInstruction(AInstructionAffectationInstruction node) {
        this.returnValue = apply(node.getInstructionAffectation());
    }

    @Override
    public void caseAInstructionBlocInstruction(AInstructionBlocInstruction node) {
        this.returnValue = apply(node.getInstructionBloc());
    }

    @Override
    public void caseAInstructionSiInstruction(AInstructionSiInstruction node) {
        this.returnValue = apply(node.getInstructionSi());
    }

    @Override
    public void caseAInstructionTantqueInstruction(AInstructionTantqueInstruction node) {
        this.returnValue = apply(node.getInstructionTantque());
    }

    @Override
    public void caseAInstructionRappelInstruction(AInstructionRappelInstruction node) {
        this.returnValue = apply(node.getInstructionRappel());
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
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = new SaInstRetour(exp);
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
        /*String nom = apply(node.getEntier()).toString();

        SaDecVar saDecVar = new SaDecVar(nom);

        this.returnValue = new SaLDec();*/
    }

    @Override
    public void caseAListedecvarListedecvar(AListedecvarListedecvar node) {
        SaLDec liste_dec_var_bis = (SaLDec) apply(node.getListedecvarbis());

        SaDecVar dec_var = (SaDecVar) apply(node.getDecvar());

        this.returnValue = new SaLDec(dec_var, liste_dec_var_bis);
    }

    @Override
    public void caseAListedecvarFinListedecvar(AListedecvarFinListedecvar node) {
        this.returnValue = null;
    }

    @Override
    public void caseAListedecvarbisListedecvarbis(AListedecvarbisListedecvarbis node) {
        SaLDec liste_dec_var_bis = (SaLDec) apply(node.getListedecvarbis());

        SaDecVar dec_var = (SaDecVar) apply(node.getDecvar());

        this.returnValue = new SaLDec(dec_var, liste_dec_var_bis);
    }

    @Override
    public void caseAListedecvarbisFinListedecvarbis(AListedecvarbisFinListedecvarbis node) {
        this.returnValue = null;
    }

    @Override
    public void caseAListedecvaroptListedecvaropt(AListedecvaroptListedecvaropt node) {
        /*SaLDec liste_dec_var_bis = (SaLDec) apply(node.getListedecvar());

        this.returnValue = new SaLDec(null, liste_dec_var_bis);*/
    }

    @Override
    public void caseAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node) {
        this.returnValue = null;
    }

    @Override
    public void caseAAvecparamAppelfonction(AAvecparamAppelfonction node) {
        String nom = apply(node.getIdentifiant()).toString();

        SaLExp liste_exp = (SaLExp) apply(node.getListeExp());

        this.returnValue = new SaAppel(nom, liste_exp);
    }

    @Override
    public void caseASansparamAppelfonction(ASansparamAppelfonction node) {
        String nom = apply(node.getIdentifiant()).toString();

        this.returnValue = new SaAppel(nom, null);
    }

    @Override
    public void caseADecfoncDecfonc(ADecfoncDecfonc node) {
        String nom = apply(node.getIdentifiant()).toString();

        SaLDec liste_dec_var = (SaLDec) apply(node.getListedecvar());
        SaLDec liste_dec_var_opt = (SaLDec) apply(node.getListedecvaropt());

        SaInst liste_exp = (SaInst) apply(node.getInstructionBloc());

        this.returnValue = new SaDecFonc(nom, liste_dec_var, liste_dec_var_opt, liste_exp);
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

