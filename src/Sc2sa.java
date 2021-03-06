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
        this.returnValue = apply(node.getPProgramme());
    }

    @Override
    public void caseAProgrammeProgramme(AProgrammeProgramme node) {
        SaLDec dec_var = (SaLDec) apply(node.getListedecvaropt());
        SaLDec dec_fonc = (SaLDec) apply(node.getListedecfonc());
        this.returnValue = new SaProg(dec_var,dec_fonc);
    }

    @Override
    public void caseAListeVarVar(AListeVarVar node) {
        String nom = node.getIdentifiant().getText();
        SaExp exp = (SaExp) apply(node.getExpression());

        this.returnValue = new SaVarIndicee(nom,exp);
    }

    @Override
    public void caseAVarVar(AVarVar node) {
        String nom = node.getIdentifiant().getText();
        this.returnValue = new SaVarSimple(nom);
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
        this.returnValue = exp2;
    }

    @Override
    public void caseAEtExp2(AEtExp2 node) {

        SaExp exp2 = (SaExp) apply(node.getExp2());
        SaExp exp3 = (SaExp) apply(node.getExp3());

        this.returnValue = new SaExpAnd(exp2,exp3);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node) {
        SaExp exp3 = (SaExp) apply(node.getExp3());
        this.returnValue = exp3;
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
        SaExp exp4 = (SaExp) apply(node.getExp4());
        this.returnValue = exp4;
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
        SaExp exp5 = (SaExp) apply(node.getExp5());
        this.returnValue = exp5;
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
        SaExp exp6 = (SaExp) apply(node.getExp6());
        this.returnValue = exp6;
    }

    @Override
    public void caseANonExp6(ANonExp6 node) {
        SaExp exp7 = (SaExp) apply(node.getExp6());

        this.returnValue = new SaExpNot(exp7);
    }

    @Override
    public void caseAExp7Exp6(AExp7Exp6 node) {
        SaExp exp7 = (SaExp) apply(node.getExp7());
        this.returnValue = exp7;
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
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = exp;
    }

    @Override
    public void caseAAppelfonctionExp7(AAppelfonctionExp7 node) {
        SaAppel appel = (SaAppel) apply(node.getAppelfonction());
        this.returnValue = new SaExpAppel(appel);
    }

    @Override
    public void caseALireExp7(ALireExp7 node) {
        /*SaLExp parenthese_fermant = (SaLExp) apply(node.getParantheseFermante());
        SaExp parenthese_ouvrant = (SaExp) apply(node.getParantheseOuvrante());
        SaExpLire lire = (SaExpLire) apply(node.getLire());

        SaLExp saLExp1 = new SaLExp(parenthese_ouvrant,parenthese_fermant);*/

        this.returnValue = new SaExpLire();
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
        SaExp exp = (SaExp) apply(node.getExpression());
        this.returnValue = new SaLExp(exp,null);
    }

    @Override
    public void caseARecursifListeExpBis(ARecursifListeExpBis node) {
        /*SaExp virgule = (SaExp) apply(node.getVirgule());
        SaExp exp = (SaExp) apply(node.getExpression());
        SaLExp liste_exp_bis = (SaLExp) apply(node.getListeExpBis());

        SaLExp saLExp1 = new SaLExp(exp,liste_exp_bis);

        this.returnValue = new SaLExp(virgule,saLExp1);*/
        SaLExp liste_exp_bis = (SaLExp) apply(node.getListeExpBis());
        SaExp exp = (SaExp) apply(node.getExpression());

        this.returnValue = new SaLExp(exp,liste_exp_bis);
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
        /*SaExp exp = (SaExp) apply(node.getInstructionRetour());
        this.returnValue = new SaInstRetour(exp);*/
        this.returnValue = apply(node.getInstructionRetour());
    }

    @Override
    public void caseAInstructionEcrireInstruction(AInstructionEcrireInstruction node) {
        /*SaExp exp = (SaExp) apply(node.getInstructionEcrire());
        this.returnValue = new SaInstEcriture(exp);*/
        this.returnValue = apply(node.getInstructionEcrire());
    }

    @Override
    public void caseAInstructionVideInstruction(AInstructionVideInstruction node) {
        this.returnValue = apply(node.getInstructionVide());
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
        SaExp exp = (SaExp) apply(node.getExpression());
        SaInst si = (SaInst) apply(node.getInstructionBloc());
        SaInst sinon = (SaInst) apply(node.getInstructionSinon());
        this.returnValue = new SaInstSi(exp, si, sinon);
    }

    @Override
    public void caseASanssinonInstructionSi(ASanssinonInstructionSi node) {
        SaExp exp = (SaExp) apply(node.getExpression());
        SaInst si = (SaInst) apply(node.getInstructionBloc());
        this.returnValue = new SaInstSi(exp, si, null);
    }

    @Override
    public void caseAInstructionSinon(AInstructionSinon node) {
        SaInstBloc instBloc = (SaInstBloc) apply(node.getInstructionBloc());
        this.returnValue = instBloc;
    }

    @Override
    public void caseAInstructionTantque(AInstructionTantque node) {
        SaExp exp = (SaExp) apply(node.getExpression());
        SaInst inst = (SaInst) apply(node.getInstructionBloc());
        this.returnValue = new SaInstTantQue(exp, inst);
    }

    @Override
    public void caseAInstructionRappel(AInstructionRappel node) {
        SaAppel appel = (SaAppel) apply(node.getAppelfonction());
        this.returnValue = new SaExpAppel(appel);
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
        this.returnValue = null;
    }

    @Override
    public void caseADecvarDecvar(ADecvarDecvar node) {
        String nom = node.getIdentifiant().getText();
        this.returnValue = new SaDecVar(nom);
    }

    @Override
    public void caseAListedecvarDecvar(AListedecvarDecvar node) {
        String nom = node.getIdentifiant().getText();
        int taille = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaDecTab(nom,taille);
    }

    @Override
    public void caseAListedecvarListedecvar(AListedecvarListedecvar node) {
        SaDec dec_var = (SaDec) apply(node.getDecvar());
        SaLDec liste_dec_var_bis = (SaLDec) apply(node.getListedecvarbis());

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
        this.returnValue = apply(node.getListedecvar());
    }

    @Override
    public void caseAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node) {
        this.returnValue = null;
    }

    @Override
    public void caseAAvecparamAppelfonction(AAvecparamAppelfonction node) {
        String nom = node.getIdentifiant().getText();

        SaLExp liste_exp = (SaLExp) apply(node.getListeExp());

        this.returnValue = new SaAppel(nom, liste_exp);
    }

    @Override
    public void caseASansparamAppelfonction(ASansparamAppelfonction node) {
        String nom = node.getIdentifiant().getText();

        this.returnValue = new SaAppel(nom, null);
    }

    @Override
    public void caseADecfoncDecfonc(ADecfoncDecfonc node) {
        String nom = node.getIdentifiant().getText();

        SaLDec liste_dec_var = (SaLDec) apply(node.getListedecvar());
        SaLDec liste_dec_var_opt = (SaLDec) apply(node.getListedecvaropt());

        SaInst liste_exp = (SaInst) apply(node.getInstructionBloc());

        this.returnValue = new SaDecFonc(nom, liste_dec_var, liste_dec_var_opt, liste_exp);
    }

    @Override
    public void caseAListedecfoncListedecfonc(AListedecfoncListedecfonc node) {

        SaDecFonc dec_fonc = (SaDecFonc) apply(node.getDecfonc());
        SaLDec liste_dec_fonc = (SaLDec) apply(node.getListedecfonc());


        this.returnValue = new SaLDec(dec_fonc, liste_dec_fonc);
    }

    @Override
    public void caseAListedecfoncFinListedecfonc(AListedecfoncFinListedecfonc node) {
        this.returnValue = null;
    }
}

